import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class EqualSpec extends AnyWordSpec with Matchers {

  "Equals" should {
    "consider maps equal" when {
      "keys and value are same" in {
        Map("A" -> 1, "B"->2) === Map("A" -> 1, "B"->2) shouldBe true
        Map("A" -> 1, "B"->2) === Map("A" -> 1) shouldBe false

        Map("A" -> 1, "B"->Map("C" ->3)) === Map("A" -> 1, "B"->Map("C" ->3)) shouldBe true

      }
    }
  }

}
