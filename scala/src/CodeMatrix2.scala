import sun.security.util.Length

import scala.util.Random

object CodeMatrix2 {
  def main(args: Array[String]): Unit = {
    val data =initData(20, 20)
    showCodeRain(data)
  }

  def showCodeRain(dataMatrix :Array[Array[String]]): Unit ={
    val length = dataMatrix.length /2
    for(i <- Range(length, -1, -1)){
      showData(i, length, dataMatrix)
      Thread.sleep(1000)
    }
  }
  val random = new Random()
  def showData(fromIndex :Int, length :Int, dataMatrix :Array[Array[String]]): Unit = {
    cls()
    for(i <- fromIndex to fromIndex + length -1){
      println(dataMatrix(i).mkString("  "))
    }
  }

  def initData(rows: Int, cols: Int) = {
    val data = new Array[Array[String]](rows)
    val random = new Random()
    for (i <- 0 until rows) {
      val row = new Array[String](cols)
      for (j <- 0 until cols) {
        val code = random.nextInt(10)
        if (code % 2 == 0) {
          row(j) = String.valueOf(code)
        } else {
          row(j) = " "
        }
      }
      data(i) = row
    }
    data
  }

  val pb = new ProcessBuilder("cmd", "/c", "cls").inheritIO()
  def cls() {
      pb.start()
      .waitFor(); // 清屏命令
  }

}
