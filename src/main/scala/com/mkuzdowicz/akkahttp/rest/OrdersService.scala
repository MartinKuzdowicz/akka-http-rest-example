package com.mkuzdowicz.akkahttp.rest

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model.StatusCodes.OK
import akka.http.scaladsl.server.Directives._
import spray.json.DefaultJsonProtocol

final case class Order(prize: Double, description: String)

trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val orderFormat = jsonFormat2(Order)
}

object OrdersService extends JsonSupport {

  def routes() =
    path("cars") {
      get {
        complete(OK, List(Order(30.55, "car")))
      }
    } ~ path("bikes") {
      get {
        complete(OK, List(Order(100.55, "bike")))
      }
    }


}
