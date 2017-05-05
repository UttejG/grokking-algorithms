package com.alpinemonk.grokkingalgos.typeclasses

trait Sortable[M[_]] {
  def sort[A: Ordering](dataStructure: M[A]): M[A]
}

object Sortable {
  def apply[M[_], A: Ordering](dataStructure: M[A])(implicit sortable: Sortable[M]): M[A] = sortable.sort(dataStructure)

  def sort[M[_], A: Ordering](dataStructure: M[A])(implicit sortable: Sortable[M]): M[A] = Sortable(dataStructure)
}
