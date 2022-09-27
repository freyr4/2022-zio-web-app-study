import zio._
import zio.test._
import zio.Console._

// 서버를 테스트해보세요
// https://zio.dev/reference/test/
// https://sttp.softwaremill.com/en/latest/quickstart.html
// https://sttp.softwaremill.com/en/latest/backends/zio.html

object AppSpec extends ZIOSpecDefault {
  import ch1.AppSample._

  override def spec = suite("ModuleSpec")(
    test("sayhi test") {
      for {
        _ <- TestConsole.feedLines("Freyr")
        _ <- sayHi
        out <- TestConsole.output
      } yield assertTrue(
        out == Vector("Please enter your name: ", "Hello, Freyr!\n")
      )
    },
    test("fib matchcase test") {
      for {
        f <- fibExampleMatchCase(BigInt(5))
      } yield assertTrue(f == BigInt(8))
      // 1 2 3 5 8  인거 같은데 왜 f = 13 이 나오는거지
      // console 로 봐도  8 
    },
    test("fib recursive test") {
      for {
        f <- fibExampleRecursive(BigInt(5))
      } yield assertTrue(f == BigInt(8))

    }
  )
}

// object AppSpec2 extends ZIOSpecDefault {
//   // 여기는 run 앱이라 레이어 같은걸 넣어줘야 하나 
//   import ch1.fibApp._
//   override def spec = suite("AppSpec")(
//     test("fib mathcase app test") {
//       for {
//         _ <- run
//         _ <- TestConsole.feedLines("5")
//         out <- TestConsole.output
//       } yield assertTrue(out(0) == "f is :")
//     }
//   )
// }
