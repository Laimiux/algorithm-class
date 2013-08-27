package com.test

import org.scalatest.FlatSpec
import com.limeblast.algorithms.MedianMaintainer

class MedianMaintainerSpec extends FlatSpec {
  
  "test median property" should "detect a proper median" in {
    val maintainer = new MedianMaintainer()
    
    maintainer.add(10)
    maintainer.add(15)
    maintainer.add(40)
    maintainer.add(5)
    maintainer.add(7)
    
    assert(maintainer.getMedian == 10)
  }
  
  "highHeap" should "return smallest element at the head" in {
    val maintainer = new MedianMaintainer()
    maintainer.add(100)
    maintainer.add(40)
    maintainer.add(50)
    maintainer.add(60)
    assert(maintainer.highHeap.head == 60)
  }
  
  "test even number of elems" should "get right median" in {
    val maintainer = new MedianMaintainer()
    
    maintainer.add(10)
    assert(maintainer.getMedian == 10)
    
    maintainer.add(15)
    maintainer.add(5)
    maintainer.add(7)
    
    assert(maintainer.getMedian == 7)
  }
  
  "get sum right" should "the sum of medians should be right" in {
    var sum = 0
    val maintainer = new MedianMaintainer()
    maintainer.add(9)
    sum += maintainer.getMedian
    maintainer.add(6)
    sum += maintainer.getMedian
    maintainer.add(14)
    sum += maintainer.getMedian
    maintainer.add(19)
    sum += maintainer.getMedian
    maintainer.add(8)
    sum += maintainer.getMedian
    maintainer.add(4)
    sum += maintainer.getMedian
    println(sum)
    assert(sum == 50)
    //9,6,14,19,8,4
  }

}