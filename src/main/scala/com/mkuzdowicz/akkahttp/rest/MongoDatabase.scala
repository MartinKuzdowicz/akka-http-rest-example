package com.mkuzdowicz.akkahttp.rest

import reactivemongo.api._
import reactivemongo.api.collections.bson.BSONCollection
import reactivemongo.bson.BSONDocument
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.Future

object MongoDatabase {

  val collection = connect()

  def connect(): BSONCollection = {
    val driver = new MongoDriver
    val conn = driver.connection(List("localhost"))

    val db = conn("akka")
    db.collection("stocks")

  }

  def findAllTickers(): Future[List[BSONDocument]] = {
    val query = BSONDocument()
    val filter = BSONDocument("Company" -> 1, "Country" -> 1, "Ticker" -> 1);

    MongoDatabase.collection.find(query, filter).cursor[BSONDocument].collect[List]()
  }

  def findTicker(ticker: String): Future[Option[BSONDocument]] = {
    val query = BSONDocument("Ticker" -> ticker)

    MongoDatabase.collection.find(query).one
  }

}
