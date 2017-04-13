package com.alpinemonk.search

import com.alpinemonk.typeclasses.Searchable

import scala.annotation.tailrec

object Search {

  def search[M[_], T](dataStructure: M[T], element: T)(implicit searchable: Searchable[M, T]) =
    searchable.search(dataStructure, element)

  object BinarySearchImpl {

    implicit object IntListSearch extends Searchable[List, Int] {

      def search(dataStructure: List[Int], element: Int): Boolean = {
        @tailrec
        def recSearch(dataStructure: List[Int]): Boolean = {
          val middleIndex = 0 + (dataStructure.length - 1) / 2
          if(dataStructure.length <= 2) {
            return dataStructure match {
              case head :: tail => head == element || tail.contains(element)
              case _ => false
            }
          } else if(dataStructure(middleIndex) == element) return true

          val ds =
            if(dataStructure(middleIndex) < element)
              dataStructure.slice(middleIndex, dataStructure.length)
            else
              dataStructure.slice(0, middleIndex)

          recSearch(ds)
        }

        recSearch(dataStructure)
      }
    }
  }
}
