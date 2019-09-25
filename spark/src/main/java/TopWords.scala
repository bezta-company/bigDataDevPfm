package main.java

import org.apache.spark.sql.SparkSession

object TopWords {

  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .appName("countDistinctDemo")
      .master("local[*]")
      .getOrCreate();

    spark.sparkContext.parallelize(Seq("张飞", "关羽", "刘备"))
    val data = spark.sparkContext.textFile("data\\test.data");
    val result = data.flatMap(line => line.split("\\s+")).map(word => (word, 1))
      .reduceByKey( (x:Int, y:Int) => x + y )

    result.foreach(println)
//
    val sortedRes = result.sortBy(t => t._2, ascending = false)
      .take(2)
    for ( (w,c)<- sortedRes){
      println(w  +  " "  + c)
    }

//    result.saveAsTextFile("data/res")


//    val distinctData = data.distinct();
//    val distinctSize = distinctData.count();
//    println(distinctSize)
  }
}


//import spark.sqlContext.implicits._
//    spark.createDataFrame(data.map(str => Row.fromSeq(str.split(","))),null).show()
