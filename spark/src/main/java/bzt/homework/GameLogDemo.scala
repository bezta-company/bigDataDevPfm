package main.java.bzt.homework

import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.functions._

import scala.collection.mutable.ArrayBuffer
object GameLogDemo {

  val spark = SparkSession.builder()
    .appName("SQLDemo")
    .master("local[*]")
    .getOrCreate();

  def main(args: Array[String]): Unit = {
    //    val dataframe1 = spark.read.json("data/cbd-chengdu.json")
    //    dataframe1.show()
    import spark.implicits._
    val df = spark.read.option("header", "true")
      .option("inferSchema", "true")
      .csv("spark/data/save.csv")


    //      .option("delimiter"," ")
    df.printSchema()

    //question1
//    df.orderBy($"攻击力" desc).show()
//    val question1 = df.groupBy("mid").max("攻击力" ).collect()
//    df.filter($"mid"=== question1(0)(0) && $"攻击力" === question1(0)(1)
//    || $"mid"=== question1(1)(0) && $"攻击力" === question1(1)(1))
//     .select("mid", "名字", "攻击力") .distinct().show()

    //question2 解法1

//    df.groupBy("mid", "名字").count()
//      .orderBy($"mid", $"count" desc)
//      .show()
//
//    val w = Window.partitionBy($"mid").orderBy($"count".desc)
//
//
//    val res3= question2.withColumn("rn", row_number().over(w)).where($"rn" <= 3).drop("rn")


    val question2 = df.groupBy("mid", "名字").count()
      .orderBy( $"mid",$"count" desc)

    //按group 编号, group 内编号
    val w = Window.partitionBy($"mid", $"名字").orderBy($"count".desc)
//    addNewCol(sdf, asdf)

    val res3= question2.withColumn("mvp", row_number().over(w))//.where($"rn" <= 2).drop("rn")
    res3.show()

    //question2 解法2
//    val rdd = spark.sparkContext.textFile("data/save.csv")
//    val rdd2 = rdd.map(line => line.split(","))
//      .map(words => (words(0), (words(1), 1)))
//      .groupByKey().flatMap( t =>{
//        val key = t._1
//        val values = t._2
//        val d  = values.groupBy( kv => kv._1)
//      val d2 =d.map(kv => (kv._1, kv._2.map( t => t._2).sum))
//      val d3 = d2.toArray.sortWith((v1, v2) => v1._2 > v2._2).take(3)
//      val datas = new ArrayBuffer[(String,String)]()
//      for( (k, v) <- d3){
//        datas.append((key, k + "\t" + v))
//      }
//      datas
//    })
//    rdd2.foreach(println)
    // question2 解法3
//    val rdd = spark.sparkContext.textFile("data/save.csv")
//    val rdd2 = rdd.map(line => line.split(","))
//    val rdd3 = rdd2.map(words => (words(0) + "\t" + words(1), 1))
//      .reduceByKey((x, y) => x+ y)
//    rdd3.foreach(println)
//    implicit  val encoder = org.apache.spark.sql.Encoders.kryo[Row]
//    val d= question2.groupByKey(row => row.getAs[Int](0))    //    for (elem <- df.head(1)) {
//    val d2 =d.flatMapGroups((mid, rows) =>
//      rows.take(3)
//    )
//    d2.show()


    //question3
//    df.groupBy("名字").agg(count($"名字"), ("胜场", "max")).show()
    val question3 = df.groupBy("名字").agg(count($"名字") as "count", max("胜场") as "winCount")
//
    // join

//    val getMVP = udf{ (count :Int, win :Int) => win.toDouble /count}
//    question3.withColumn("mvp", getMVP($"count", $"winCount"))
//      .orderBy($"mvp" desc)
//      .show()
    //    dataframe.select("gov_id").show()
//    dataframe.select("gov_id", "shop_title").show()
//    dataframe.select("gov_id", "shop_title")
//      .write.csv("data/res")
//
//    spark.read.csv("res").show()
//    println(spark.read.csv("data/save.csv").count())
//
//    dataframe.filter($"gov_id" ===  "锦江区").show()
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
