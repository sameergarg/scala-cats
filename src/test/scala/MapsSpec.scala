import MapSpec.JobCount
import org.scalatest.wordspec.AnyWordSpec
import cats.implicits._

class MapsSpec extends AnyWordSpec {

  "A map" should {
    "merge map" in {
      val m1 = Map("A" -> 1, "B" ->2)
      val m2 = Map("A" -> 3, "C" ->4)
      val merged = m1.padZip(m2).view.mapValues {
        case (optional1, optional2) => (optional1.getOrElse(0), optional2.getOrElse(0))
      }
      println(merged)
    }

    "group tuples" in {
      val tuples3 = List((1, "Created", 3),(1, "InProgress", 2),(1, "Failed", 2),(2, "Failed", 1),(3, "InProgress", 5))
      val reduced = tuples3.groupMapReduce(_._1){
        case (_, "Created", count ) => JobCount(count, 0)
        case (_, "InProgress", count ) => JobCount(count, 0)
        case (_, "Failed", count ) => JobCount(0, count)
      }{(jobCountA, jobCountB) => JobCount(jobCountA.pending + jobCountB.pending, jobCountA.failed + jobCountB.failed)}
      println(reduced)
    }

    "mapN" in {
      val m1 = Map("Foo" -> 1, "Bar" -> 2, "Foobar" -> 3)
      val m2 = Map("Fizz" -> 4, "Buzz" -> 5, "Fizzbuzz" -> 6)
      (m1, m2).mapN { case (x, y) => println(s"$x / $y") }
    }

  }

}

object MapSpec {
  case class JobCount(pending: Int, failed: Int)
}
