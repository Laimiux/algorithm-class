package com.limeblast.algorithms

class WeightedEdge[T](vertexOne: T, vertexTwo: T, val weight: Int) extends Edge(vertexOne, vertexTwo) {
  override def toString(): String = {
    vertexOne + "," + vertexTwo + " -> " + weight
  }
}