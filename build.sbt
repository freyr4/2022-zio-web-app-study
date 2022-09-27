ThisBuild / scalaVersion := "2.13.8"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "com.ychan"
ThisBuild / organizationName := "warsong"

val V = new {
  val zioVersion = "2.0.2"
  val sttpVersion = "3.7.4"
  val zttpVersion = "2.0.0-RC11"
  val zioJsonVersion = "0.3.0-RC10"
}

lazy val sharedSettings = Seq(
  libraryDependencies ++= Seq(
    "dev.zio" %% "zio" % V.zioVersion,
    "dev.zio" %% "zio-test" % V.zioVersion % Test,
    "dev.zio" %% "zio-test-sbt" % V.zioVersion % Test,
    "io.d11" %% "zhttp" % V.zttpVersion,
    "com.softwaremill.sttp.client3" %% "core" % V.sttpVersion,
    "com.softwaremill.sttp.client3" %% "zio" % V.sttpVersion,
    "com.softwaremill.sttp.client3" %% "zio" % V.sttpVersion,
    "com.softwaremill.sttp.client3" %% "slf4j-backend" % V.sttpVersion,
    "dev.zio" %% "zio-json" % V.zioJsonVersion
  ),
  testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")
)

lazy val root = (project in file("."))
  .settings(
    name := "study-yc-branch"
  )

lazy val ch1 = project
  .settings(sharedSettings)

lazy val ch2 = project
  .settings(sharedSettings)
