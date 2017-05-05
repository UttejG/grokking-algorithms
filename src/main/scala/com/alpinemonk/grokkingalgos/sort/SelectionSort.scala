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

  implicit val listSelectionSort =
    new Sortable[List] {
      override def sort[A: Ordering](dataStructure: List[A]): List[A] = {
        @tailrec
        def selectionSort(unsortedDataStructure: List[A], sortedDataStructure: List[A] = List.empty[A]): List[A] = {
          unsortedDataStructure match {
            case Nil => if(sortedDataStructure.isEmpty) List.empty[A] else sortedDataStructure
            /* Justification for reversing the list at the end.
               Appending to a list is O(n) and reversing a list is O(n).
               So, instead of appending every iteration, prepend with O(1) and then reverse at the end. */
            case head :: Nil => if(sortedDataStructure.isEmpty) List(head) else (head :: sortedDataStructure).reverse
            case _ => {
              //Justification for get: When unsortedDataStructure is empty, it will terminate at the first case
              val smallestInTheList = findSmallestElement(unsortedDataStructure).get
              selectionSort(removeElementIfExists(unsortedDataStructure, smallestInTheList), smallestInTheList :: sortedDataStructure)
            }
          }
        }

        selectionSort(dataStructure)
      }
    }
}
