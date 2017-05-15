package com.mkuzdowicz.akkahttp.rest

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer

object Application extends App with JsonSupport {

  implicit val system = ActorSystem("example-rest-system")
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

  val host = "localhost"
  val port = 8080

  val allRoutes = OrdersService.routes()

  val server = Http().bindAndHandle(allRoutes, host, port)

  println(s"starting server at host: ${host}  port: ${port}")


}
