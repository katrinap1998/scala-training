package com.jpmc.codecs

import com.jpmc.json.Json

sealed trait JsonEncoder[A] {
  def encode(value: A): Json
}

object JsonEncoder {

  def apply[A](implicit encoder: JsonEncoder[A]): JsonEncoder[A] = encoder
  def from[A](f: A => Json): JsonEncoder[A] = new JsonEncoder[A] {
    override def encode(value: A): Json = f(value)
  }

  implicit val intEncoder = ???
  implicit val stringEncoder = ???
  implicit val booleanEncoder = ???

  implicit def arrayEncoder = ???
  implicit def objectEncoder = ???
  implicit def optionEncoder = ???
  implicit def eitherEncoder = ???
}
