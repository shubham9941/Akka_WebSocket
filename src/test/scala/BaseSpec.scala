
import akka.actor.ActorSystem
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.mockito.MockitoSugar
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

trait BaseSpec extends WordSpecLike with Matchers with BeforeAndAfterAll with MockitoSugar with ScalatestRouteTest with SprayJsonSupport {

  implicit val actorSystem = ActorSystem("WS_Service")
  implicit val ec = actorSystem.dispatcher

}
