package com.example.app.transformers

import org.json4s._
import org.json4s.jackson.JsonMethods._

import scala.util.{Failure, Success, Try}

object DataTransformer {

  implicit val formats = DefaultFormats // Brings in default date formats etc.


    def getItems[T](rawJSON: String)(implicit m: Manifest[T]) : List[T] = {
      parse(rawJSON).children.map(child => child.extract[T])
    }

    def getItem[T](rawJSON: String)(implicit m: Manifest[T]) = {
      Try(parse(rawJSON).extract[T]) match {
        case Success(item) => item
        case Failure(e) => e
      }
    }

}
