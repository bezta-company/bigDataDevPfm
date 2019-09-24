package bzt.scala

object Review {

  def main(args: Array[String]): Unit = {
    //    useClass2( new WhiteMan("白人"))
    //    useClass2( new WhiteMan("黑人"))

    //    testTypeConvert()
//    getZhiShu(100)
//    getZhiShu2(100)

    testMap()

  }

  //变量定义
  val name: String = ""
  val code: Byte = 1 //(Byte 的最大最小是多少)
  //方法定义
  def methodNoReturn(m: Int, n: Int) = {
    if (m > n)
      m
    else
      n
  }

  def methodWithReturn(m: Int, n: Int): Int = {
    if (m > n)
      return m
    else
      return n
  }

  val getBigger = (x: Int, y: Int) => if (x > y) x else y
  //
  //类型转换

  def testTypeConvert(): Unit = {

    println(2.1 toInt)
    println(3 toDouble)
    println("3" toDouble)
    println(1 / 3)
    println(1 / 3.0)
  }
  //带条件的循环

  def loopWithCondition: Unit ={
    for(i <- 2 to 10 if i >5){
      println(i)
    }
  }

  //遍历容器
  def loopInContainer() = {

    val d = Array(2, 3, 2, 5)
    for (elem <- d) {
      print(elem)
      print(",")
    }
    println()
    for (i <- 0 until d.length) {
      print(d(i))
      print(",")
    }
    println()
    val iterator = d.iterator
    while (iterator.hasNext) {
      print(iterator.next())
      print(",")
    }
  }

  //类定义
  class Man(name: String) {
    def sayHello(): Unit = {
      println("你好,我叫 %s ".format(name))
    }

    def haveLunch(): Unit = {
      println("我是 %s, 我在吃午饭".format(this.name))
    }
  }

  class WhiteMan(name: String) extends Man(name) {
  }

  class BlackMan(name: String) extends Man(name) {

  }

  //继承的使用场景
  def useClass1(): Unit = {
    val whiteMan = new WhiteMan("白人")
    val blackMan = new BlackMan("黑人")
    whiteMan.sayHello()
    blackMan.haveLunch()
  }

  def useClass2(man: Man): Unit = {
    man.haveLunch()
  }

  //重写与重载

  // 练习 100以内的质数   练习debug

  def getZhiShu(n: Int): Unit = {
    var num = 2
    while (num <= n) {
      var flag = true
      var i = 0
      for (i <- 2 to num if flag) {
        if (num % i == 0) {
          flag = false
        }
        if (i == num) flag = true
      }
      if (flag) {
        print(num + " ")
      }
      num = num + 1
    }
    println()
  }
//
//  def getZhiShu2(n: Int): Unit = {
//    var num = 2
//    while (num <= n) {
//      var flag = true
//      var i = 0
//      for (i <- 2 to num/2 if flag) {
//        if (num % i == 0) {
//          flag = false
//        }
//        if (i > num/2) flag = true
//      }
//      if (flag) {
//        print(num + " ")
//      }
//      num = num + 1
//    }
//  }

  // map reduce
  def testMap(): Unit ={
    val data = Array(1,3,5)
    println( data.map(x => x + 1))
  }
  def testReduce: Unit ={

    val data = Array(1,3,5)
    data.reduce(_+_)
  }
  // sort

  def testSort: Unit ={
    val data = Array(2,6,3)
    data.sorted
    data.sortBy(x =>x)
    data.sortWith((x,y) =>  if(x < y) true else false)
    data.sortWith((x,y) =>  x < y)
//    data.sortWith(_<=_)

    val data2 = Array(("a", 2), ("b",3), ("a",5), ("c", 5))
    class Student(name:String, age :Int){
      def getAge = this.age
    }
    val data3 = Array(new Student("aaa", 11), new Student("BB",33))
    data3.sortBy( s => s.getAge)(Ordering.Int.reverse)
  }

}
