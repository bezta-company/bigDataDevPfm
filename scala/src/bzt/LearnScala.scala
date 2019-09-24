package bzt

import scala.collection.mutable.ArrayBuffer

object LearnScala {

  def testSwitch(): Unit = {
    var name = "张三"
    println("hello world!" + name)
    val score = scala.io.StdIn.readLine()
    if (score.toInt > 90) {
      println("优秀")
    } else if (score.toInt < 90 && score.toInt > 60) {
      println("合格")
    } else {
      println("你挂了")
    }
  }

  def testCaseMatch(): Unit ={
    val name = scala.io.StdIn.readLine()
    name match{
      case "CN" => println("中国")
      case "USA" => println("美国")
      case _ => println("未知国家")
    }
  }
  def testArray(): Unit ={
    val names = new Array[String](10)
    names(0) = "tom"
    names(1) = "jim"
    names(2) = "lucy"
    val data = new Array[Array[String]](5)
    data(0) = Array("AA", "BB")
    data(1) = Array("CC", "DD")
    for(arry <- data ){
      if(arry != null){
        println(arry.mkString("  "))
      }
    }
  }

  def main(args: Array[String]): Unit = {
//    testLoop3()
//    testArray()

    val res = testIntersection(Array(21,15,66), Array(21,15,66))
    res.foreach(println)
  }

  def testLoop1(): Unit = {
    var c = 1
    while (c < 10) {
      print(c + ", ")
      c = c + 1
    }
    do {
      println()
      print(c + ",  ")
    } while (c < 10)
  }

  def testLoop2(): Unit = {
    for (i <- 1.to(5)) {
      print(i + ", ")
    }

    for (i <- 5 until(1, -1)) {
      print(i + ", ")
    }
    //    for( i <- 1 to 5){
    //      for(j <-1 to i){
    //        print("*")
    //      }
    //      println()
    //    }
  }

  def testLoop3(): Unit = {
    for (i <- 1 to 5) {
      for(j <- 1 to i ){  // 控制星星的个数
        print("*")
      }
      println()
    }
  }

  def testOption(): Unit ={

    val value = Option("a")
    println(value.get)
  }

  def testIntersection(set1 :Array[Int], set2 :Array[Int])={
    val res = ArrayBuffer[Int]()
    for(v2 <- set2){
      var hit = false
      for(v1 <- set1 if(!hit)){
        if(v2 == v1){
          hit = true
        }
      }
      if(hit){
        res.append(v2)
      }
    }
    res.toArray
  }
}
