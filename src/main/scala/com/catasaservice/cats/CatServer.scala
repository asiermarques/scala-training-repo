package com.catasaservice.cats

import cats.effect.{Effect, IO}
import com.catasaservice.cats.app.controller.CatController
import com.catasaservice.cats.app.formatter.CatJsonFormatter
import com.catasaservice.cats.domain.service.CatService
import com.catasaservice.cats.infraestructure.repository.HardCodedCatListRepository
import fs2.StreamApp
import org.http4s.server.blaze.BlazeBuilder

import scala.concurrent.ExecutionContext

object CatServer extends StreamApp[IO] {
  import scala.concurrent.ExecutionContext.Implicits.global

  def stream(args: List[String], requestShutdown: IO[Unit]) = ServerStream.stream[IO]
}

object ServerStream {

  val service = new CatService(new HardCodedCatListRepository())
  val jsonFormatter = new CatJsonFormatter

  def CatController[F[_]: Effect] = new CatController[F](service, jsonFormatter).controller

  def stream[F[_]: Effect](implicit ec: ExecutionContext) =
    BlazeBuilder[F]
      .bindHttp(8080, "0.0.0.0")
      .mountService(CatController, "/")
      .serve
}
