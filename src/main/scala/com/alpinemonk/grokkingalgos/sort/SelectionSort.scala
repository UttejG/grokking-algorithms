package com.alpinemonk.grokkingalgos.sort

import com.alpinemonk.grokkingalgos.typeclasses.Sortable

import scala.annotation.tailrec

object SelectionSort {

  def findSmallestElement[A: Ordering](xs: List[A]): Option[A] =
    if(xs.isEmpty) None
    else Some(xs.reduceLeft((acc: A, element: A) => if(implicitly[Ordering[A]].lteq(acc, element)) acc else element))

  def removeElementIfExists[A: Ordering](dataStructure: List[A], element: A): List[A] = {
    if(dataStructure.isEmpty || !dataStructure.contains(element))
      dataStructure
    else {
      val (x, xs) = dataStructure.splitAt(dataStructure.indexOf(element))
      x ++ xs.tail
    }
  }

  /* Justification for some decisions
    1) Reversing the list on line 36 -
        Appending to a list is O(n) and reversing a list is O(n).
        So, instead of appending at every iteration, prepend to the list with O(1) and then reverse at the end.
    2) Using get on an Option on line 41 -
        When unsortedDataStructure is empty, it will terminate in the first two cases.
  */

  implicit val listSelectionSort =
    new Sortable[List] {
      override def sort[A: Ordering](dataStructure: List[A]): List[A] = {
        @tailrec
        def selectionSort(unsortedDataStructure: List[A], sortedDataStructure: List[A] = List.empty[A]): List[A] = {
          unsortedDataStructure match {
            case Nil if(sortedDataStructure.isEmpty) => List.empty[A]
            case Nil => sortedDataStructure
            case head :: Nil if(sortedDataStructure.isEmpty) => List(head)
            case head :: Nil => (head :: sortedDataStructure).reverse
            case _ => {
              val smallestInTheList = findSmallestElement(unsortedDataStructure).get
              selectionSort(
                removeElementIfExists(unsortedDataStructure, smallestInTheList),
                smallestInTheList :: sortedDataStructure
              )
            }
          }
        }

        selectionSort(dataStructure)
      }
    }
}
