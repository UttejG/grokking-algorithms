package com.alpinemonk.typeclasses

trait Searchable[M[_], T] {
  def search(dataStructure: M[T], element: T): Boolean
}

