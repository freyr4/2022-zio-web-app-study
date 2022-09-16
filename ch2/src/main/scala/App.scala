package ch2

import zio._
import zhttp.http._
import zhttp.service.Server
import zio.json._

object WebApp extends ZIOAppDefault {

  final case class TODO(n: Int, messeage: String)
  object TODO {
    implicit val JsonEncoder: JsonEncoder[TODO] = DeriveJsonEncoder.gen[TODO]
    implicit val JsonDecoder: JsonDecoder[TODO] = DeriveJsonDecoder.gen[TODO]
  }

  val server: HttpApp[Any, Nothing] = Http.collect[Request] {
    case Method.GET -> !! / "hello" => Response.text("world!")
    case Method.GET -> !! / "todo" / n => {

      val noption = n.toIntOption

      val todoJsonResponse = noption match {
        case Some(num) => {
          val todo = TODO(num, s"$num is Good")
          val todoJson = todo.toJson
          Response.json(todoJson)
        }

        case None => Response.text("put number plz")
      }
      todoJsonResponse
    }
  }
  override def run = Server.start(8090, server)
}
