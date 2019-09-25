package bzt

import bzt.homework.game.Hero

import scala.collection.mutable.ArrayBuffer

object SortDemo {

  def main(args: Array[String]): Unit = {

    //    val data = Array(5, 1,2,8,3,11,9,12)
    val data = buildTestData()
    selectSort(data)
    for (contact <- data) {
      println("****************")
      println(contact.getInfo())
      println("****************")
    }
  }

  def buildTestData() = {
    val datas = new ArrayBuffer[Contact]()
    datas.append(new Contact("Jack", "18234", 28))
    datas.append(new Contact("Tim", "15823", 18))
    datas.append(new Contact("Lulu", "183485", 38))
    datas.append(new Contact("lili", "182346", 11))
    datas.append(new Contact("qinqin", "181234", 21))
    datas.toArray
  }

  def selectSort(data: Array[Int]) = {
    for (i <- 0 until data.length) {
      var minIndex = i
      for (j <- i + 1 until data.length) {
        if (data(j) < data(minIndex)) {
          minIndex = j
        }
      }
      val temp = data(i)
      data(i) = data(minIndex)
      data(minIndex) = temp
    }
  }

  def selectSort(data: Array[Contact]) = {
    for (i <- 0 until data.length) {
      var minIndex = i
      for (j <- i + 1 until data.length) {
        if (data(j).getAge() < data(minIndex).getAge()) {
          minIndex = j
        }
      }
      val temp = data(i)
      data(i) = data(minIndex)
      data(minIndex) = temp
    }
  }

  def selectSort(data: Array[Hero]) = {
    for (i <- 0 until data.length) {
      var minIndex = i
      for (j <- i + 1 until data.length) {
        if (data(j).getAttack() < data(minIndex).getAttack()) {
          minIndex = j
        }
      }
      val temp = data(i)
      data(i) = data(minIndex)
      data(minIndex) = temp
    }
  }

  //  def bubbleSort(data :Array[Int]): Unit ={
  //    for(i <- Range(data.length - 1, 0, -1)){
  ////      var changed = false
  //      for(j <- 0 until i){
  //        if(data(j) > data(j + 1)){
  //          val temp = data(j)
  //          data(j) = data(j + 1)
  //          data(j + 1) = temp
  ////          changed = true
  //        }
  //      }
  //    }
  //  }

  def bubbleSort(data: Array[Int]): Unit = {
    for (i <- Range(data.length - 1, 0, -1)) {
      for (j <- 0 until i) {
        if (data(j) > data(j + 1)) {
          val temp = data(j)
          data(j) = data(j + 1)
          data(j + 1) = temp
        }
      }
    }
  }

}
