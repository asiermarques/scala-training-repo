package com.catasaservice.cats.domain.repository

import com.catasaservice.cats.domain.model.Cat

trait CatRepository {

  def findByIdentifier(identifier: String): Option[Cat]

  def findAll(): List[Cat]

}
