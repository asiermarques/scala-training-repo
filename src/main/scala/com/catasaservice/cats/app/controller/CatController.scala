package com.catasaservice.cats.app.controller

import cats.effect.Effect
import com.catasaservice.cats.app.formatter.JsonFormatter
import com.catasaservice.cats.domain.model.Cat
import com.catasaservice.cats.domain.service.CatService
import org.http4s.HttpService
import org.http4s.dsl.Http4sDsl

class CatController[F[_]: Effect](service:CatService, jsonFormatter:JsonFormatter) extends Http4sDsl[F] {

  val controller: HttpService[F] = {
    HttpService[F] {
      case GET -> Root / name  => service.find(name).getOrElse(None) match {
          case cat:Cat  => Ok(jsonFormatter.format(cat))
          case _        => NotFound()
      }
    }
  }
}
