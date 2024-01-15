package com.jpmc.codecs

import com.jpmc.json.Json
import com.jpmc.json.Json.{JsonArray, JsonObj}

sealed trait JsonEncoder[A] {
  def encode(value: A): Json
}

object JsonEncoder {

  def apply[A](implicit encoder: JsonEncoder[A]): JsonEncoder[A] = encoder
  def from[A](f: A => Json): JsonEncoder[A] = new JsonEncoder[A] {
    override def encode(value: A): Json = f(value)
  }

  implicit val intEncoder: JsonEncoder[Int] = ???
  implicit val stringEncoder: JsonEncoder[String] = ???
  implicit val booleanEncoder: JsonEncoder[Boolean] = ???

  implicit def arrayEncoder: JsonEncoder[JsonArray] = ???
  implicit def objectEncoder: JsonEncoder[JsonObj] = ???
  implicit def optionEncoder[A]: JsonEncoder[Option[A]] = ???
  implicit def eitherEncoder[A,B]: JsonEncoder[Either[A, B]] = ???
}
