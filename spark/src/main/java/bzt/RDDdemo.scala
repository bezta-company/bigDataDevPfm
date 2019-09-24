package main.java.bzt

import org.apache.spark.sql.SparkSession

object RDDdemo {

  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .appName("RddDemo")
      .master("local[*]")
      .getOrCreate();

//    val rdd5 = spark.sparkContext.textFile("data\\test.data")
//    rdd5.foreach(println)
//    val rdd1 = spark.sparkContext.parallelize(Seq("张飞", "关羽", "刘备"))
//    rdd1.foreach(println)

    val rdd2 = spark.sparkContext.parallelize(1 to 100)
//    rdd2.foreach(println)
//    val rdd3 = rdd2.map(x => if(x%2==0) 0 else 1)
//    val res = rdd3.collect()
//    res.foreach(println)
    val res = rdd2.reduce((x: Int, y:Int) => x + y)
//    val rdd3 = rdd2.map(convert)
//    rdd3.foreach(println)

//    val rdd4 = spark.sparkContext.textFile("data\\test.data")
//    val element = rdd4.map(line => line.split("\\s+")).take(1)
//    element.foreach(println)
//
//    val rdd5 = spark.sparkContext.textFile("data\\test.data")
//    val element5 = rdd4.flatMap(line => line.split("\\s+")).take(2)
//    element5.foreach(println)

//    val rdd5 = spark.sparkContext.parallelize(Seq(1,3,5,7,9))
//    rdd5.distinct()
//    val rdd6 = spark.sparkContext.parallelize(Seq(1,3,2,4,6,8))
//    rdd5.union(rdd6).foreach(println)
//    rdd5.intersection(rdd6).foreach(println)
//    rdd5.subtract(rdd6).foreach(println)



//    val data = spark.sparkContext.textFile("data\\test.data");
//    val localData = data.collect()
//    localData.foreach(println)
//    val result1 = data.flatMap(line => line.split("\\s+")).map(word => (word, 1))
//      .reduceByKey( (x:Int, y:Int) => x + y )
//
//    val result2 = data.flatMap(line => line.split("\\s+")).map(word => (word, 1))
//      .groupByKey()
//    result2.foreach(println)


//    val rdd8 = spark.sparkContext.parallelize(Seq(1,3,5,7,9))
//    val result3 = rdd8.reduce((x :Int, y :Int) => x + y)
//    println(result3)

//    val result4 = rdd8.reduce((x,y) => getBigger(x, y))
//    println(result4)



    // join

//        val data1 = spark.sparkContext.parallelize(Seq((11, "AA"), (12, "BB")))
//        val data2 = spark.sparkContext.parallelize(Seq((11, "AA"), (12, "BB"), (13, "CC")))
////        data1.join(data2).collect().foreach(println)
//        data2.leftOuterJoin(data1).collect().foreach(println)
  }

  def getBigger(x :Int, y:Int):Int={
    if(x >=y)return x
    else return y
  }

  def convert(v :Int) :Int  = {
    if (v % 2==0)  0
    else  1
  }

  val value = (x:Int) => x + 1
  print(value)
}