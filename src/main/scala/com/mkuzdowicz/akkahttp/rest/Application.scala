package com.mkuzdowicz.akkahttp.rest

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer


object Application extends App {

  implicit val system = ActorSystem("example-rest-system")
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

  val route = path("") {
    get {
      complete(200, "it si working akka http server !")
    }
  }

  val server = Http().bindAndHandle(route, "localhost", 8080)

  println("starting server at port 8080")


}
