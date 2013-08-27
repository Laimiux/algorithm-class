package com.limeblast.algorithms

object FileToGraph {

  def dijkstraFile[T](fileName: String): Array[Vector[WeightedEdge[Int]]] = {
    val source = scala.io.Source.fromFile(fileName)
    val lines = source.getLines

    val graph = (lines :\ Array[Vector[WeightedEdge[Int]]]())((line, arr) => {
      //val splitted = line.split(" +")
      val splitted = line.split("\\s+")
      var tempArr = arr

      val vertex = splitted(0).toInt
      for (i <- 1 to splitted.length - 1) {
        val split = splitted(i).split(",")
        val secondVertex = split(0).toInt
        val weight = split(1).toInt

        val edge = new WeightedEdge(vertex, secondVertex, weight)

        if (tempArr.size > vertex) {
          tempArr(vertex) match {
            case v: Vector[WeightedEdge[Int]] => tempArr.update(vertex, edge +: v)
            case null => tempArr.update(vertex, Vector(edge))
          }
        } else {
          val array = new Array[Vector[WeightedEdge[Int]]](vertex+1)
          array.update(vertex, Vector(edge))
          tempArr = tempArr ++ array
        }
      }

      tempArr
    })

    source.close()

    graph
  }

}