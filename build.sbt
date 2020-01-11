import Dependencies.TestDeps._
import Dependencies.CompileDeps._
import sbt.Keys.{libraryDependencies, version, _}
import sbt._

lazy val commonSettings = Seq(
  name := "WebSocket_POC",
  version := "0.1",
  scalaVersion := "2.12.7"
)

scalaVersion in ThisBuild := "2.12.5"

lazy val  WebSocket_POC  = {
  Project("desk-service",file("."))
    .settings(commonSettings:_*)
    .settings(libraryDependencies ++= Seq(
      sprayJson,
      slf4j,
      scalaTest,
      scalaMock,
      sprayJson,
      actor,
      akkaHttp,
      akkaTest,
      akkaHttpTestKit,
      akkaStream,
      akkaHttpSpray,
      logback,
    ))
    .settings(parallelExecution in Test := false)

}