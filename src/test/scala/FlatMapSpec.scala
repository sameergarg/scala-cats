import cats.implicits.toFlatMapOps
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class FlatMapSpec extends AnyWordSpec with Matchers {

  "FlatMap" can {
    "flatTap:  Apply a monadic function and discard the result while keeping the effect" should {
      "flatTap list" in {
        def nCats(n: Int) = List.fill(n)("cat")
        List(0).flatTap(nCats) shouldBe List.empty
        List(3).flatTap(nCats) shouldBe List(3,3,3)
      }
      "flatTap option" in {
        Option(1).flatTap(_ => None) shouldBe None
        Option(1).flatTap(_ => Some("cats")) shouldBe Some(1)
      }

    }
  }

}
