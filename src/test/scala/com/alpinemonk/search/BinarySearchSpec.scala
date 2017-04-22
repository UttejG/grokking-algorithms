package com.alpinemonk.search

import org.scalatest.{Matchers, WordSpec}

class BinarySearchSpec extends WordSpec with Matchers {
  "Search function with Binary Search implementation" should {
    "find the element if exists" in {
      import com.alpinemonk.typeclasses.Searchable._
      import com.alpinemonk.typeclasses.Searchable.Ops._
      import com.alpinemonk.search.BinarySearch._

      val myList = List(1, 5, 10, 15, 20, 25, 30)
      val valuesToCheck = List(1 , 2, 5, 7, 10, 12 , 15, 17, 20, 22, 25, 27, 30, 35)

      myList.has(15) shouldBe true // Ops interface

      val result = for(element <- valuesToCheck) yield search(myList, element)
      result shouldBe (1 to 7).flatMap(_ => List(true, false))
    }
  }
}
