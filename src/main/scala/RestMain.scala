import akka.actor.{ActorSystem, Props}
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import ws.{ChatService, GameAreaActor, WsRestService}

import scala.util.{Failure, Success}

object RestMain extends App {

  val serverHost = "localhost"
  val port = 8080

  implicit val actorSystem = ActorSystem("WS_POC")
  implicit val mat = ActorMaterializer()
  implicit val ec = actorSystem.dispatcher

  val gameAreaActor = actorSystem.actorOf(Props[GameAreaActor])

  val restService = new WsRestService(new ChatService(gameAreaActor))
  val binding = Http().bindAndHandle(
    Route.seal(restService.routes),
    serverHost,
    port
  )
  binding.onComplete {
    case Success(_) ⇒ println("Ws service started at " + serverHost + ":" + port)
    case Failure(e) ⇒ println(s"Exception Binding failed with ${e.getMessage}")
  }

}
