package com.jpmc.json


sealed trait Json extends Product with Serializable

object Json {

  case object JsonNull extends Json
  sealed trait JsonNumber extends Json
  final case class JsonString(value: String) extends Json
  final case class JsonInt(value: Int) extends JsonNumber
  final case class JsonBoolean(value: Boolean) extends Json
  final case class JsonArray(value: List[Json]) extends Json
  final case class JsonObj(value: Map[String, Json]) extends Json

}