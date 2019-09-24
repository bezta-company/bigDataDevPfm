package bzt

import java.io.File

object ExceptionDemo {

  def main(args: Array[String]): Unit = {

//    val s = null
//    s.toString
    testException4()

  }

  def testException(): Unit ={
    print(5 / 0)
    println("异常发生了，我不能执行了")
  }

  def testException2(): Unit = {
    try{
    }catch {
      case e :Exception =>{
        println("异常被捕捉到了 " )
        throw e
      }
    }
    println("testException2  成功")
  }

  def testException3() = {
    println("我将要主动抛出异常")
    throw new Exception()
    println("我是不会执行的")
  }

  def testException4(): Unit = {
    try{
      testException()
    }catch {
      case e :Exception =>{
        println("异常被捕捉到了 " )
      }
    }finally {
      println("我完成一些善后工作，比如释放资源")
    }
    println("testException2  成功")
  }

}
