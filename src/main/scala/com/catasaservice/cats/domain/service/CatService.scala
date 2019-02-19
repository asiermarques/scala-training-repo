package com.catasaservice.cats.domain.service

import com.catasaservice.cats.domain.model.Cat
import com.catasaservice.cats.domain.repository.CatRepository

class CatService(repository:CatRepository) {

  def find(identifier:String):Option[Cat] = repository.findByIdentifier(identifier)

}
