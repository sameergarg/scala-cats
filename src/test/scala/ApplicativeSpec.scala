import cats.{Applicative, Monoid}
import org.scalatest.wordspec.AnyWordSpec
import cats._
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

    "product" should {
      "create a tuple inside the effect from two separate effect" in {
        Option(1).product(Option(2)) shouldBe Option((1,2))
        Option(1).product(None) shouldBe None
      }

      "work with 3 effects" in {
        Applicative[Option].product(Option(1), Option(2)).product(Option(3)) shouldBe Option((1,2),3)
      }
    }

    "product right or *>" should {
      "run all effects in a chain and return the result of right side after ignoring results of the left as long as its successful" in {
        Option(1) *> Option(2) shouldBe Option(2)
        Option(1) *> None shouldBe None
        None *> Option(2) shouldBe None
      }
    }

  }

}
