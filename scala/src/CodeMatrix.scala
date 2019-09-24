import java.util

import scala.util.Random

object CodeMatrix {

  def main(args: Array[String]): Unit = {

    val random = new Random()

//    val (rows, cols) = (args(0).toInt, args(1).toInt)
    val rows = 10
    val cols=10
    showCodeRain(rows, cols)

  }

  def showCodeRain(rows :Int=10, cols :Int=10, iterNum :Int=100): Unit ={
    val data = initData(rows, cols)
    showData(data)
    val speed = getSpeed(cols)
    for(count <- 0 until iterNum){
      Thread.sleep(300)
      moveData(data, speed)
      showData(data)
    }

  }
  val random = new Random()
  def createCode()={
    val code = random.nextInt(10)
    if (code % 2 == 0) {
       String.valueOf(code)
    } else {
       " "
    }
  }

  def getSpeed(cols :Int, maxSpeed :Int=5) = {
    val speed = new Array[Int](cols)
    for(i <- 0 until cols){
      speed(i) = 1 + random.nextInt(maxSpeed)
    }
    speed
  }
  def moveData(data :Array[Array[String]], fallSpeed :Array[Int]): Unit ={
    val (m, n) = (data.length, fallSpeed.length)
    for(colIndex <- 0 until fallSpeed.length){
      val speed = fallSpeed(colIndex)
      val fromRow = m - speed - 1
      // 整体下移某一列
      for(rowIndex <- Range(fromRow, -1, -1)){
        val targetRowIndex = rowIndex + speed
        data(targetRowIndex)(colIndex) = data(rowIndex)(colIndex)
      }
      for(i <- 0 until speed){
        data(i)(colIndex) = createCode()
      }
    }
  }

  def showData(dataMatrix: Array[Array[String]]): Unit = {
    cls()
    dataMatrix.foreach(record => println(record.mkString("  ")))
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
    //// 新建一个 ProcessBuilder，其要执行的命令是 cmd.exe，参数是 /c 和 cls
      pb.start()
      .waitFor(); // 清屏命令
  }

}
