import cats.implicits._
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class SemigroupsSpec extends AnyWordSpec with Matchers {

  "Semigroups" can {
    "|+|" should {
      "gets all key value pair present in either of the map and combine matching key values based on Semigroup for the value type" in {
        Map("a" -> 1, "b" -> 10) |+| Map("b" -> 100, "c" -> 1000) shouldBe Map("a" -> 1, "b" -> 110, "c" -> 1000)
      }
    }

  }

}
