package com.alpinemonk.grokkingalgos.search

import com.alpinemonk.grokkingalgos.typeclasses.Searchable

import scala.annotation.tailrec

object BinarySearch {

  implicit val IntListSearch = new Searchable[List, Int] {
    def search(dataStructure: List[Int], element: Int): Boolean = {
      @tailrec
      def recSearch(dataStructure: List[Int]): Boolean = {
        val middleIndex = (dataStructure.length - 1) / 2
        if(dataStructure.length <= 2)
          return dataStructure match {
            case head :: tail => head == element || tail.contains(element)
            case _ => false
          }
        else if(dataStructure(middleIndex) == element)
          return true

        def conditionalStatement(condition: Boolean) =
          (index: Int, alternateIndex: Int) => if(condition) index else alternateIndex

        def evaluateIndex = conditionalStatement(dataStructure(middleIndex) > element)

        val ds = dataStructure.slice(
            evaluateIndex(0, middleIndex),
            evaluateIndex(middleIndex, dataStructure.length)
          )

        recSearch(ds)
      }

      recSearch(dataStructure)
    }
  }
}
