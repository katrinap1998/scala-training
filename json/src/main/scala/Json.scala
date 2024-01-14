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


  def prettyPrint(json: Json): String = {
    json match {
      case JsonNull => "null"
      case JsonInt(value) => s"$value"
      case JsonString(value) => "\"" + value + "\""
      case JsonBoolean(value) => s"$value"
      case JsonArray(value) => value.map(prettyPrint).mkString("[", ", ", "]")
      case JsonObj(value) => value.toList.map {case (key, value) => "\"" + key + "\": " + prettyPrint(value)}.mkString("{", ", ", "}")
    }
  }
}