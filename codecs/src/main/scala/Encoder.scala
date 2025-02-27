package com.jpmc.codecs

import com.jpmc.json.Json

sealed trait JsonEncoder[A] {
  def encode(value: A): Json
}

object JsonEncoder {

  def apply[A] = ???
  def from[A] = ???

  implicit val intEncoder = ???
  implicit val stringEncoder = ???
  implicit val booleanEncoder = ???

  implicit def arrayEncoder = ???
  implicit def objectEncoder = ???
  implicit def optionEncoder = ???
  implicit def eitherEncoder = ???
}
