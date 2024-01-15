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

  implicit val intEncoder: JsonEncoder[Int] = new JsonEncoder[Int] {
    override def encode(value: Int): Json = Json.JsonInt(value)
  }
  implicit val stringEncoder: JsonEncoder[String] = new JsonEncoder[String] {
    override def encode(value: String): Json = Json.JsonString(value)
  }
  implicit val booleanEncoder: JsonEncoder[Boolean] = new JsonEncoder[Boolean] {
    override def encode(value: Boolean): Json = Json.JsonBoolean(value)
  }

  implicit def arrayEncoder: JsonEncoder[JsonArray] = new JsonEncoder[JsonArray] {
    override def encode(value: JsonArray): Json = value
  }
  implicit def objectEncoder: JsonEncoder[JsonObj] = ???

  implicit def optionEncoder[A](implicit encoder: JsonEncoder[A]): JsonEncoder[Option[A]] = from {
    case Some(value) => encoder.encode(value)
    case None => Json.JsonNull
  }

  implicit def eitherEncoder[A,B](implicit encoderA: JsonEncoder[A], encoderB: JsonEncoder[B]): JsonEncoder[Either[A, B]] = new JsonEncoder[Either[A, B]] {
    override def encode(value: Either[A, B]): Json = value match {
      case Left(value) => encoderA.encode(value)
      case Right(value) => encoderB.encode(value)
    }
  }
}
