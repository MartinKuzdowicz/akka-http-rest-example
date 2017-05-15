package com.mkuzdowicz.akkahttp.rest

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.StatusCodes.OK
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.DefaultJsonProtocol


final case class Order(prize: Double, description: String)

trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val orderFormat = jsonFormat2(Order)
}

object Application extends App with JsonSupport {

  implicit val system = ActorSystem("example-rest-system")
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

  val host = "localhost"
  val port = 8080

  val route =
    path("cars") {
      get {
        complete(OK, List(Order(30.55, "car")))
      }
    } ~ path("bikes") {
      get {
        complete(OK,  List(Order(100.55, "bike")))
      }
    }


  val server = Http().bindAndHandle(route, host, port)

  println(s"starting server at host: ${host}  port: ${port}")


}
