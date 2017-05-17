package com.mkuzdowicz.akkahttp.rest

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model.StatusCodes.OK
import akka.http.scaladsl.server.Directives._
import reactivemongo.bson.BSONDocument
import spray.json.DefaultJsonProtocol
import play.api.libs.json._
import play.modules.reactivemongo.json.BSONFormats
import scala.concurrent.ExecutionContext.Implicits.global

final case class Order(prize: Double, description: String)

trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val orderFormat = jsonFormat2(Order)
}

object OrdersService extends JsonSupport {

  def convertToString(input: List[BSONDocument]) : String = {
    input
      .map(f => convertToString(f))
      .mkString("[", ",", "]")
  }

  def convertToString(input: BSONDocument) : String = {
    Json.stringify(BSONFormats.toJSON(input))
  }

  def routes() =
    path("cars") {
      get {
        complete(OK, List(Order(30.55, "car")))
      }
    } ~ path("bikes") {
      get {
        complete(OK, List(Order(100.55, "bike")))
      }
    } ~ path("stocks") {
      get {

       val res = for {
          elem <-  MongoDatabase.findAllTickers()
        } yield {
         convertToString(elem)
        }

        complete(OK, res)
      }
    }


}
