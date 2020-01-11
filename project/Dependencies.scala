import sbt._

object Dependencies {

  private object Versions {

    val sprayJson = "1.3.5"
    val ScalaTest = "3.0.1"
    val slf4j = "1.7.25"
    val logback = "1.2.3"
    val akka = "2.5.22"
    val akkaHttp =  "10.1.8"
    val mockito = "1.9.5"
    val akkaTest = "2.5.6"
  }


  object CompileDeps {

    val logback         =     "ch.qos.logback"      %    "logback-classic"      %  Versions.logback
    val slf4j           =     "org.slf4j"           %    "slf4j-api"            %  Versions.slf4j
    val sprayJson       =     "io.spray"            %%   "spray-json"           %  Versions.sprayJson
    val actor           =     "com.typesafe.akka"   %%   "akka-actor"           %  Versions.akka
    val akkaStream      =     "com.typesafe.akka"   %%   "akka-stream"          %  Versions.akka
    val akkaHttp        =     "com.typesafe.akka"   %%   "akka-http"            %  Versions.akkaHttp
    val akkaHttpSpray   =     "com.typesafe.akka"   %%   "akka-http-spray-json" %  Versions.akkaHttp
  }


  object TestDeps {

    val scalaMock       =     "org.mockito"          %   "mockito-all"       %  Versions.mockito     %  Test
    val akkaTest        =     "com.typesafe.akka"    %   "akka-testkit_2.12" %  Versions.akkaTest    %  Test
    val scalaTest       =     "org.scalatest"        %%  "scalatest"         %  Versions.ScalaTest   %  Test
    val akkaHttpTestKit =     "com.typesafe.akka"    %% "akka-http-testkit"  %  Versions.akkaHttp    %  Test

  }

}
