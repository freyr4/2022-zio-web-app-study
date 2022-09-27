import zio.test._
import zio._
import sttp.client3._
// import sttp.client3.ziojson._
// import zttp.service.server.ServerChannelFactory
// import zttp.service.{EventLoopGroup, Server, ServerChannelFactory}

// 서버를 테스트해보세요
// https://zio.dev/reference/test/
// https://sttp.softwaremill.com/en/latest/quickstart.html
// https://sttp.softwaremill.com/en/latest/backends/zio.html

object AppSpec extends ZIOSpecDefault {
  import ch2.WebApp._
  override def spec = suite("WebAppSpec")(

    // https://zio.github.io/zio-http/docs/v1.x/testing/index
    // 여기 보고 따라해야할텐데 아직 홈피 공사 중
    test("hello test") {
      assertTrue(true)
    },
    test("todo test") {
      ???
    }
  )
}
