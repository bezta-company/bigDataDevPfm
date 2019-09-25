package bzt.scala

object Review2 {

  def main(args: Array[String]): Unit = {

//    val getBigger = (x: Int, y: Int) => x > y

//    print(getBigger(1, 5))
//    loopContainer()

//    containerSort()
    getZhiShu(100)

  }

  def isBigger1(x :Int, y :Int):Boolean = {
    if( x >y){return true}
    return true
  }

  def containerSort(): Unit ={
    val data = Array(12,2,33)
    println(data.sortWith(isBigger1).mkString(","))
  }

  def getZhiShu( n :Int): Unit ={
    //1.循环2 到 100
    // 2.判断每个数是否是质数，是，输出
    for(num <- 2 to n){
      var flag = true
       for(i <- 2 to num/2 if flag){
         if(num %i ==0){
           flag = false
         }
       }
      if(flag){
        print(num + "  ")
      }
    }



  }

  def loopContainer(): Unit = {
    val data = Array(3, 1, 3, 6)
    for (e <- data) {
      print(e + " ")
    }
    println()
    for (i <- 0 until data.length) {
      print(data(i) + "  ")
    }
    println()
    val iter = data.iterator
    while (iter.hasNext) {
      val e = iter.next()
      print(e + " ")
    }
  }


  def getBig(m: Int = 0, n: Int = 0): Int = {
    if (m > n) {
      return m
    }
    n
  }

  def isBigger(m: Int, n: Int): Boolean = {
    if (m < n) {
      return false
    }
    return true
  }

  def isBigger2(m: Int, n: Int): Boolean = {
    return m < n
  }

}
