package main.java

import org.apache.spark.sql.SparkSession

object CountDistinct2 {

  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .appName("countDistinctDemo")
      .master("local[*]")
      .getOrCreate();
    val data = spark.sparkContext.textFile("data\\test.data");

    val distinctData = data.distinct();
    val distinctSize = distinctData.count();
    println(distinctSize)
  }
}


//import spark.sqlContext.implicits._
//    spark.createDataFrame(data.map(str => Row.fromSeq(str.split(","))),null).show()
