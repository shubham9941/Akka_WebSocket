package ws

import akka.actor.{Actor, ActorRef}

class GameAreaActor extends Actor{

  var players = collection.mutable.LinkedHashMap[String , ActorRef]()

  override def receive: Receive = {
    case PlayerJoined(playerName , ref) =>
      players.foreach(x => x._2 ! s"Hey new player joined with the name $playerName")
      players += (playerName -> ref)
    case PlayerMessage(playerName , message) =>
      players.filter(v => v._1!=playerName).foreach(x => x._2 ! s"Message from $playerName = $message")
//      players.foreach(x => x._2 ! s"Message from $playerName = $message")
    case PlayerLeft(playerName) =>
      players -= playerName
      players.foreach(x => x._2 ! s"Hey player left with the name $playerName")

    case message =>
      players.foreach(x => x._2 ! message)
  }

}
trait GameEvent
case class PlayerJoined(playerName : String , conn : ActorRef) extends GameEvent
case class PlayerMessage(playerName : String, message : String) extends GameEvent
case class PlayerLeft(playerName : String) extends GameEvent
