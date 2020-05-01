import cats.data._
import cats.implicits._
import mouse.all._

import scala.concurrent.{Future}
import scala.concurrent.ExecutionContext.Implicits.global

object Ch02 {

  def getA: Future[Option[Int]] = Future.successful(Some(1))

  def getB: Future[Option[Int]] = Future.successful(Some(2))

  def getC: Future[Int] = Future.successful(3)

  val getD: Option[Int] = Some(4)

  val getE = 5

  val multiplyAB: OptionT[Future, Int] = for {
    a <- OptionT(getA)
    b <- OptionT(getB)
  } yield a * b

  val multipleABC: OptionT[Future, Int] = for {
    ab <- multiplyAB
    c <- OptionT.liftF(getC)
  } yield ab * c

  val multiplyABCD = for {
    abc <- multipleABC
    d <- OptionT(getD.pure[Future])
  } yield abc * d

  //syntax sugar for lift methods
  def liftFutureOption[A](f: Future[Option[A]]) = OptionT(f)
  def liftFuture[A](f: Future[A]) = OptionT.liftF(f)
  def liftOption[A](o: Option[A]) = OptionT(o.pure[Future])
  def lift[A](a: A) = liftOption(Option(a))

  val multiplyWithCustomLift = for {
    a <- liftFutureOption(getA)
    b <- liftFutureOption(getB)
    c <- liftFuture(getC)
    d <- liftOption(getD)
    e <- lift(getE)
  } yield a * b * c * d * e

  val multiply = for {
    a <- getA |> liftFutureOption
    b <- getB |> liftFutureOption
    c <- getC |> liftFuture
    d <- getD |> liftOption
    e <- getE |> lift

  } yield a * b * c * d * e

}
