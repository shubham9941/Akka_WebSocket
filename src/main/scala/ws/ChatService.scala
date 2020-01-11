package ws

import akka.actor.{ActorRef, ActorSystem, Props}
import akka.http.scaladsl.model.ws.{Message, TextMessage}
import akka.stream.{FlowShape, OverflowStrategy}
import akka.stream.scaladsl.{Flow, GraphDSL, Merge, Sink, Source}

class ChatService(gameActor: ActorRef){

  private val playerActorSource = Source.actorRef[String](5, OverflowStrategy.fail)

  def basicFlow = Flow.fromGraph(GraphDSL.create() { implicit builder =>
    import GraphDSL.Implicits._
    val met = builder.materializedValue.map(x => TextMessage("Welcome!"))
    val merge = builder.add(Merge[Message](2))


    val messagePassingFlow = builder.add(Flow[Message].map(identity))

    met ~> merge.in(0)

    merge ~> messagePassingFlow
    FlowShape(merge.in(1), messagePassingFlow.out)
  })

  def gamingFlow(playerName: String) = Flow.fromGraph(GraphDSL.create(playerActorSource) {
    implicit builder => playerActor =>
      import GraphDSL.Implicits._

      val met = builder.materializedValue.map(actorRef => PlayerJoined(playerName, actorRef))
      val merge = builder.add(Merge[GameEvent](2))

      val messagesToGameEventFlow = builder.add(Flow[Message].map {
        case TextMessage.Strict(txt) => PlayerMessage(playerName, txt)
      })

      val gameEventToMessagesFlow = builder.add(Flow[String].map {
        case txt => TextMessage(txt)
      })

      val gameActorSink = Sink.actorRef[GameEvent](gameActor, PlayerLeft(playerName))

      met ~> merge
      messagesToGameEventFlow ~> merge
      merge ~> gameActorSink

      playerActor ~> gameEventToMessagesFlow

      FlowShape(messagesToGameEventFlow.in , gameEventToMessagesFlow.out)
  })

  def sendMessagesToAllClients(message : String) = gameActor ! message

}
