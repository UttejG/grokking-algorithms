package com.alpinemonk.search

import org.scalatest.{Matchers, WordSpec}

class BinarySearchSpec extends WordSpec with Matchers {
  "Search function with Binary Search implementation" should {
    import com.alpinemonk.search.BinarySearch._

    val intList = List(1, 5, 10, 15, 20, 25, 30)
    val testValues = List(1 , 2, 5, 7, 10, 12 , 15, 17, 20, 22, 25, 27, 30, 35)

    "find the element if exists" in {
      import com.alpinemonk.typeclasses.Searchable._

      val result = for(element <- testValues) yield search(intList, element)
      result shouldBe (1 to 7).flatMap(_ => List(true, false))
    }

    "find the element if exists using Ops interface" in {
      import com.alpinemonk.typeclasses.Searchable.Ops._

      val result = for(element <- testValues) yield intList.has(element)
      result shouldBe (1 to 7).flatMap(_ => List(true, false))
    }
  }
}
