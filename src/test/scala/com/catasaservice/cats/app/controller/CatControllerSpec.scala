package com.catasaservice.cats.app.controller

import cats.effect.IO
import com.catasaservice.cats.app.formatter.CatJsonFormatter
import com.catasaservice.cats.domain.service.CatService
import com.catasaservice.cats.infraestructure.repository.HardCodedCatListRepository
import org.http4s._

class CatControllerSpec extends org.specs2.mutable.Specification {

  val CatController: Response[IO] = {
    val getHW = Request[IO](Method.GET, Uri.uri("/maine"))
    val service = new CatService(new HardCodedCatListRepository())
    val jsonFormatter = new CatJsonFormatter
    val controller:HttpService[IO] = new CatController[IO](service, jsonFormatter).controller
    controller.orNotFound(getHW).unsafeRunSync
  }

  "Cat" >> {
    "return 200" >> {
      CatController.status must beEqualTo(Status.Ok)
    }
  }
}


