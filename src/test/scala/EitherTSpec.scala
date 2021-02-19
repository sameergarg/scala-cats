import cats.Id
import cats.data.EitherT
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import scala.util.{Success, Try}

class EitherTSpec extends AnyWordSpec with Matchers {

  "EitherT" can {
    "subFlatMap" should {
      "operate on Right to return an Either with same left as before but different right" in {
        val either: Right[Throwable, Int] = Right[Throwable, Int](1)
        val et: EitherT[Try, Throwable, Int] = EitherT[Try, Throwable, Int](Try(either))
        val changedRightET: EitherT[Try, Throwable, String] = et.subflatMap(int => Right(int.toString))
        changedRightET.value shouldBe Success(Right("1"))
      }
    }
  }

}
