package main.java.bzt

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
object SQLDemo {

  val spark = SparkSession.builder()
    .appName("SQLDemo")
    .master("local[*]")
    .getOrCreate();
import spark.implicits._
  def main(arrgs :Array[String]): Unit ={
    val dataframe = spark.read.option("header", true)
    .csv("data/cbd-chengdu.csv")
//    dataframe.printSchema()
//    dataframe.show()
    dataframe.select("gov_id", "shop_title").write.csv("data/res")
//    dataframe.select($"gov_id").show()
//    dataframe.filter($"gov_id" === "锦江区").show()
//    dataframe.filter("gov_id = '锦江区' and type_name='中学'").show()
//    val addCol = udf{(value :String) => "成都" + value}
//
//    dataframe.withColumn("full_name", addCol($"gov_id"))
//      .show(100)
//    dataframe.drop("gov_id")

//    dataframe.groupBy("gov_id").count().show()

//    dataframe.filter($"type_name" === "甜品店")
//      .groupBy("gov_id").count().orderBy($"count" desc)
//      .show(3)
  }



}
