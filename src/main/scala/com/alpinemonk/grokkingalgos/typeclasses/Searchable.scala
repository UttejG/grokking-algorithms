package com.alpinemonk.grokkingalgos.typeclasses

trait Searchable[M[_], T] {
  def search(dataStructure: M[T], element: T): Boolean
}

object Searchable {
  
  def search[M[_], T](dataStructure: M[T], element: T)(implicit searchable: Searchable[M, T]): Boolean =
    searchable.search(dataStructure, element)

  object Ops {
    implicit class SearchableOps[M[_], T](dataStructure: M[T]) {
      def has(element: T)(implicit searchable: Searchable[M, T]): Boolean =
        searchable.search(dataStructure, element)
    }
  }
}

