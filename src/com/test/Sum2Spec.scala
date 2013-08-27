package com.test

import org.scalatest._
import com.limeblast.algorithms.Sum2HashTable

class Sum2Spec extends FlatSpec {

  "countTwoSums" should "do the easy test case correctly" in {
    val file = "twosummedium.txt"
    val hash = com.limeblast.algorithms.FileToHash.getHash(file)

    val sumTwoHas = new Sum2HashTable(hash)

    val count = sumTwoHas.countTwoSums(30, 60)
    println(count)
    assert(count == 9)
  }

  "countTwoSums Big Test Case" should "find how many tuples in the interval are satisfied" in {
    val file = "HashInt.txt"
    val hash = com.limeblast.algorithms.FileToHash.getHash(file)

    val sum2 = new Sum2HashTable(hash)

    val count = sum2.countTwoSums(2500, 4000)
    println(count)
    assert(count == 1477)

  }

}