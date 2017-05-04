package com.alpinemonk.grokkingalgos.sort

import org.scalatest.{Matchers, WordSpec}

import scala.language.implicitConversions


class SelectionSortSpec extends WordSpec with Matchers {
  "Sort function with Selection Sort implementation" should {
    import com.alpinemonk.grokkingalgos.sort.SelectionSort._

    val moderateSizedIntList = List(15, 25, 10, 20, 5, 30)
    val moderateSizedIntListWithDuplicates = List(15, 20, 10, 20, 5, 15)
    val emptyList = List()
    val singleElementIntList = List(5)
    val twoElementIntList = List(10, 5)

    "sort a moderate sized int list" in {
      import com.alpinemonk.grokkingalgos.typeclasses.Sortable._

      sort(moderateSizedIntList) shouldBe ((1 to 6) map (_ * 5)).toList
    }
    "sort a moderate sized int list with duplicates" in {
      import com.alpinemonk.grokkingalgos.typeclasses.Sortable._

      sort(moderateSizedIntListWithDuplicates) shouldBe moderateSizedIntListWithDuplicates.sorted
    }
    "sort an empty list" in {
      import com.alpinemonk.grokkingalgos.typeclasses.Sortable._

      /*
       In absence of explicit type setting, compiler does this
       Error:(24, 11) ambiguous implicit values:
        both object BigIntIsIntegral in object Numeric of type scala.math.Numeric.BigIntIsIntegral.type
        and object ShortIsIntegral in object Numeric of type scala.math.Numeric.ShortIsIntegral.type
        match expected type Numeric[A]
      */
      sort[List, Int](emptyList) shouldBe emptyList
    }
    "sort an single element int list" in {
      import com.alpinemonk.grokkingalgos.typeclasses.Sortable._

      sort(singleElementIntList) shouldBe singleElementIntList
    }
    "sort an two element int list" in {
      import com.alpinemonk.grokkingalgos.typeclasses.Sortable._

      sort(twoElementIntList) shouldBe twoElementIntList.reverse
    }
  }
}
