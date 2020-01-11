//import akka.http.scaladsl.testkit.WSProbe
//import ws.WsRestService
//class WsSpec extends BaseSpec {
//
//  "WsRestService " must {
//    "Establish the connection successfully" in {
//      val wsClient: WSProbe = WSProbe()
//      val wsRoute = new WsRestService().routes
//      WS("/test" , wsClient.flow) ~> wsRoute ~>
//      check{
//        isWebSocketUpgrade shouldEqual true
//      }
//      assert(true)
//    }
//  }
//
//}
