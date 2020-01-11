package ws

import akka.http.scaladsl.server.Directives

class WsRestService(chatService: ChatService) extends Directives {

  val routes = path("join") {
    get {
      parameters("name".as[String]) { name =>
        handleWebSocketMessages(chatService.gamingFlow(name))
      }
    }
  } ~
    path("send") {
      (post & entity(as[String])) { data =>
        chatService.sendMessagesToAllClients(data)
        complete("sucess")
      }
    }
}