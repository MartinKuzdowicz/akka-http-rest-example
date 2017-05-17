import sbt._

name := "akka-http-rest-example"

version := "1.0"

scalaVersion := "2.11.5"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http-experimental" % "1.0",
  "com.typesafe.akka" %% "akka-http-spray-json-experimental" % "1.0",
  "org.reactivemongo" %% "reactivemongo" % "0.12.3",
  "com.typesafe.play" % "play-json_2.11" % "2.6.0-M7",
  "org.reactivemongo" %% "play2-reactivemongo" % "0.12.3",
  "com.typesafe.akka" %%"akka-http-testkit-experimental" % "1.0",
  "org.scalatest" %% "scalatest" % "2.2.5" % "test",
  "ch.qos.logback" % "logback-classic" % "1.1.2"
)
        