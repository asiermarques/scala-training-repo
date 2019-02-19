package com.catasaservice.cats.app.formatter

import com.catasaservice.cats.domain.model.Cat

trait JsonFormatter {

  def format(cat:Cat):String

}
