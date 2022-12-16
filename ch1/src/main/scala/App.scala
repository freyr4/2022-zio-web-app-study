package ch1

import zio._

import java.io.IOException
import scala.annotation.tailrec
import scala.language.implicitConversions
import scala.math.BigInt

// web server 만들어 보세요
// https://github.com/dream11/zio-http/blob/main/example/src/main/scala/example/HelloWorld.scala

object AppSample {
  val sayHi: ZIO[Any, IOException, Unit] =
    for {
      _ <- Console.print("Please enter your name: ")
      name <- Console.readLine
      _ <- Console.printLine(s"Hello, $name!")
    } yield ()

  def fibExampleMatchCase(n: BigInt): UIO[BigInt] = {
    implicit def Int2Big(n: Int): BigInt = BigInt(n)
    implicit def Big2Int(n: BigInt): Int = n.toInt

    val zero = BigInt(1)
    val one = BigInt(1)
    val two = BigInt(2)

    def fib(n: BigInt): UIO[BigInt] = n match {
      case `zero`     => ZIO.succeed(0)
      case `one`     => ZIO.succeed(1)
      case `two`     => ZIO.succeed(2)
      case n: BigInt => fib(n - 2).zipWith(fib(n - 1))(_ + _)
    }

    val f = fib(n)
    f
  }

  def fibExampleRecursive(n: BigInt): UIO[BigInt] = {

    @tailrec
    def fib(n: BigInt, a0: BigInt, a1: BigInt): UIO[BigInt] = {
      if (n == BigInt(0)) ZIO.succeed(a0 + a1)
      else fib(n - 1, a1, a0 + a1)
    }

    val f = fib(n, 0, 1)
    f
  }
}

object App extends ZIOAppDefault {
  import AppSample.sayHi
  def run: ZIO[Any, IOException, Unit] = sayHi
}

object fibApp extends ZIOAppDefault {
  import AppSample.fibExampleMatchCase
  override def run: ZIO[Any with ZIOAppArgs with Scope, IOException, Unit] = for {
    _ <- Console.print(s"f is : ")
    n <- Console.readLine
    k = BigInt(n.toIntOption.getOrElse(0))
    f <- fibExampleMatchCase(k)
    _ <- Console.printLine(s"$f")
   } yield ()
}


object fibApp2 extends ZIOAppDefault {
  import AppSample.fibExampleRecursive
  
  override def run: ZIO[Any with ZIOAppArgs with Scope, IOException, Unit] = for {
    _ <- Console.print(s"f is : ")
    n <- Console.readLine
    k = BigInt(n.toIntOption.getOrElse(0))
    f <- fibExampleRecursive(k)
    _ <- Console.printLine(s"$f")
   } yield ()
}
