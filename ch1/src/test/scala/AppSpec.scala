import zio._
import zio.test._
import zio.Console._

// 서버를 테스트해보세요
// https://zio.dev/reference/test/
// https://sttp.softwaremill.com/en/latest/quickstart.html
// https://sttp.softwaremill.com/en/latest/backends/zio.html

object AppSpec extends ZIOSpecDefault {
  import ch1.AppSample._

  override def spec = suite("HelloSpec")(
    test("sayhi test") {
      for {
        _ <- TestConsole.feedLines("Freyr")
        _ <- sayHi
        out <- TestConsole.output
      } yield assertTrue(out == Vector("Please enter your name: ", "Hello, Freyr!\n"))
    },

    test("fib matchcase test") {
      for {
        _ <- TestConsole.feedLines("5")
        f <- fibExampleMatchCase
      } yield assertTrue(f == ZIO.succeed(BigInt(8)))
      // 여기 안되는 중
    },

    test("fib recursive test") {
      for {
        _ <- TestConsole.feedLines("5")
        _ <- fibExampleRecursive
        out <- TestConsole.output
      } yield assertTrue(out(0) == "f is :")
    }
  )
}
