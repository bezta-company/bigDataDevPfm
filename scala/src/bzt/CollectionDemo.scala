package bzt

import java.io.FileNotFoundException

import scala.collection.mutable
import scala.collection.mutable._

object CollectionDemo {





  def intersection2(set1 :Array[Int], set2 :Array[Int]):Array[Int] = {
    val result = new ArrayBuffer[Int]()
    val map = new mutable.HashMap[Int, Int]()
    for(v1 <- set1){ 5
        map.put(v1, 1)
    }
    for(v2 <- set2){  8
      if(map.contains(v2)){
        result.append(v2)
      }
    }

    result.toArray
  }
  def intersection(set1 :Array[Int], set2 :Array[Int]) :Array[Int]={
    val result = new ArrayBuffer[Int]()
    for(v1 <- set1){
      for(v2 <- set2){
        if(v1 == v2 && !result.contains(v1)){

          result.append(v1)
        }
      }
    }
    result.toArray
  }





  def main(args: Array[String]): Unit = {

    val set1 = Array(2,35,6)
    val set2 = Array(3, 6, 8 )
    val res = intersection2(set1, set2)
    println(res.mkString(",  "))

//    val map = new HashMap[String, Int]()
//    map.put("李二", 100)
//    map.put("Tom", 77)
//    println(map.get("Tom").get)
//    map.remove("Tom")
//    println(map)
//
//    val list = new ListBuffer[Int]()
//    list.append(3)
//    list.append(2)
//    list.appendAll(Array(4,5))
//    println(list)
//    println(list(3))
//    println(list.remove(0))
//    println(list)
//
//    val tuple = ("AA", 1, "CC")
//    println(tuple._1)
////    tuple(0) = 3
//
//    test_catch_error()
  }

  def test_catch_error() = {

    try{
      println("我开始执行了。。。")
      error_metho()
    }catch {
      case e:FileNotFoundException=>{
        println("没有找到文件错误")
      }
      case e :Exception=>{
        println("有错误发生了")
      }
    }
  }

  def error_metho() ={

    throw new FileNotFoundException()
  }
}
