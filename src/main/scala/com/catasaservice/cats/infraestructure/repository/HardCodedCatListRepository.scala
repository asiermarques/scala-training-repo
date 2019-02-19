package com.catasaservice.cats.infraestructure.repository

import com.catasaservice.cats.domain.model.Cat
import com.catasaservice.cats.domain.repository.CatRepository

class HardCodedCatListRepository extends CatRepository {

  def findByIdentifier(identifier: String): Option[Cat] = {
    val allCats = findAll()
    allCats.find(cat => cat.identifier.equals(identifier))
  }

  def findAll(): List[Cat] = List(Cat("maine", "", "Maine Coon"), Cat("bombay", "", "Maine Coon"))

}
