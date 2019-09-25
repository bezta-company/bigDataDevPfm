package main.java.bzt

import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}

import scala.collection.mutable.ArrayBuffer
object SparkSQLDemo {

  val spark = SparkSession.builder()
    .appName("RddDemo")
    .master("local[*]")
    .getOrCreate();
  import spark.implicits._
  def main(args: Array[String]): Unit = {
    //    val dataframe1 = spark.read.json("data/cbd-chengdu.json")
    //    dataframe1.show()

    val dataframe = spark.read.option("header", "true")
      .csv("data/cbd-chengdu.csv")
    //      .option("inferSchema", "false")
    //      .option("delimiter"," ")
//    dataframe.printSchema()
//    dataframe.show()
//    dataframe.select("gov_id").show()
//    dataframe.select("gov_id", "shop_title").show()
//    dataframe.select("gov_id", "shop_title")
//      .write.csv("data/res")
//
//    spark.read.csv("res").show()
    println(spark.read.csv("data/res").count())
//
    dataframe.filter($"gov_id" ===  "锦江区").show()
//    dataframe.filter($"gov_id" ===  "锦江区" && $"type_name" === "中学").show()
//    dataframe.filter("gov_id = '锦江区' and type_name='中学'").show()
//    dataframe.where($"gov_id" ===  2270 && $"type_name" === "中学").show()

//    dataframe.select( "gov_id", "shop_title").show()

//    dataframe.select( "gov_id", "shop_title")

//    dataframe.withColumnRenamed("gov_id", "gov_name").show()
//    val addNewCol = udf{(value :String) => "成都市" + value}
//    dataframe.withColumn("full_name", addNewCol($"gov_id")).show()

//    dataframe.drop("gov_id").show()

//    import org.apache.spark.sql.functions.monotonically_increasing_id
//    dataframe.withColumn("rownum", monotonically_increasing_id)
//      .filter("rownum <3 ").show()

//    dataframe.groupBy("gov_id").count().show()
//    dataframe.groupBy("gov_id").count().orderBy($"count" desc).show()
//
//    dataframe.groupBy("gov_id", "type_name").count()
//      .orderBy($"gov_id", $"count").show()

//    val res = dataframe.filter($"type_name" === "甜品店")
//      .groupBy("gov_id", "type_name").count()
//      .orderBy($"count" desc).take(3)
//    res.foreach(println)

//    dataframe.registerTempTable()
//      dataframe.createOrReplaceTempView("CBD")
//      spark.sql("select * from CBD").show()
//      spark.sql("select gov_id, shop_title, type_name from CBD").show()
//      spark.sql("select gov_id, shop_title, type_name from CBD where gov_id ='成华区'").show()
//      spark.sql("select gov_id,type_name,  count(shop_title) count from CBD " +
//        "group by gov_id, type_name").show()

//      val fields = new ArrayBuffer[StructField]()
//    fields.append(StructField("name", StringType, false))
//    fields.append(StructField("gender", StringType, false))
//    fields.append(StructField("age", IntegerType, false))
//    val schema = StructType(fields)
////      val rdd = spark.sparkContext.parallelize(Array(("Tom", "男", 28), ("刘强", "男",31)))
////      rdd.toDF().show()
//
//    val rdd = spark.sparkContext.parallelize(Array(("Tom", "男", 28), ("刘强", "男",31)))
//    val rowRdd = rdd.map( values => Row.apply(values._1, values._2, values._3))
////    spark.createDataFrame(rowRdd, schema).show()
//
//    spark.createDataFrame(rowRdd, schema).write.csv("data/res")
  }
}
