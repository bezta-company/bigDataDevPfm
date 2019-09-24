package bzt

import scala.collection.mutable.HashMap

object JoinDemo {

  def main(args: Array[String]): Unit = {



    val data1 = new HashMap[String, Int]()
    data1.put("jim", 88)
    data1.put("luis", 98)
    data1.put("jack", 70)

    val data2 = new HashMap[String, Int]()
    data2.put("jack", 22)
    data2.put("luis", 23)
    data2.put("tick", 41)

    val res = innerJoin(data1, data2)
    printData(res)

  }
  def innerJoin(data1 :HashMap[String, Int], data2 :HashMap[String, Int])={
    val res = new HashMap[String,Array[Int]]()
    for((key1, value1) <- data1){
      if(data2.contains(key1)){
        res.put(key1, Array(value1, data2.get(key1).get))
      }
    }
    res
  }

  def printData(data :HashMap[String, Array[Int]]): Unit ={
    for((key, values) <- data){
      println(key + "\t" + values.mkString(","))
    }
  }
}
