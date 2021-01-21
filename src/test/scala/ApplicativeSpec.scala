import cats.Monoid
import org.scalatest.wordspec.AnyWordSpec
import cats.implicits._
import org.scalatest.matchers.should.Matchers

class ApplicativeSpec extends AnyWordSpec with Matchers {

  "Applicative" can {
    "mapN" should {
      "combine on matching keys for Map with the provided function" in {
        def combineMatchingKeysFn[T](x: T, y: T)(implicit monoid: Monoid[T]): T = monoid.combine(x,y)

        (Map("a" -> 1, "b" -> 10), Map("b" -> 100, "c" -> 1000)).mapN(combineMatchingKeysFn[Int]) shouldBe Map("b" -> 110)
      }
    }

  }

}
