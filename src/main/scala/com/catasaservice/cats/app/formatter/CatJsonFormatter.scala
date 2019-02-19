package com.catasaservice.cats.app.formatter

import com.catasaservice.cats.domain.model.Cat
import io.circe.syntax._

class CatJsonFormatter extends JsonFormatter {

  def format(cat:Cat):String = cat.asJsonObject.toString()
}
