import zio._

// web server를 만들어 보세요
// https://github.com/dream11/zio-http/blob/main/example/src/main/scala/example/HelloWorld.scala

object App extends ZIOAppDefault {
  def run =
    for {
      _ <- Console.print("Please enter your name: ")
      name <- Console.readLine
      _ <- Console.printLine(s"Hello, $name!")
    } yield ()
}

object WebApp extends ZIOAppDefault {
  // import
  val server = ???
  def run = ???
}

object fibApp extends ZIOAppDefault {
  import scala.math.BigInt
  implicit def Int2Big(n:Int) : BigInt = BigInt(n)
  implicit def Big2Int(n:BigInt) : Int = n.toInt
  
  val one = BigInt(1)
  val two = BigInt(2)

  def fib(n: BigInt) : UIO[BigInt] = n match {
    case `one` => ZIO.succeed(1)
    case `two` => ZIO.succeed(2)
    case n:BigInt => fib(n-2).zipWith(fib(n-1))(_+_)
  }

  def run = for {
    _ <- Console.print(s"f is :")
    n <- Console.readLine
    k = BigInt(n.toInt)
    f = fib(k)
    _ <-f.debug
    
  } yield ()
}


object fibApp2 extends ZIOAppDefault {
  import scala.math.BigInt
  
  def fib(n: BigInt, a0: BigInt, a1:BigInt) : UIO[BigInt] = {
    if (n == BigInt(0)) ZIO.succeed(a0 + a1)
    else fib(n-1, a1, a0+a1)
  }

  def run = for {
    _ <- Console.print(s"f is :")
    n <- Console.readLine
    k = BigInt(n.toInt)
    f = fib(k, 0, 1)
    _ <-f.debug
    
  } yield ()
}