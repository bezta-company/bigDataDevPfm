package bzt.homework.game

import java.io.{File, FileWriter}

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

object FightGame {

  def testSortHero(): Unit ={

    val heros = ArrayBuffer[Hero]()
    val random = new Random()
    var hero = new Hero("葫芦娃", "喷火", random.nextInt(100))
    heros.append(hero)
    hero = new Hero("红孩儿", "喷火", random.nextInt(100))
    heros.append(hero)
    hero = new Hero("金刚狼", "排云掌", random.nextInt(100))
    heros.append(hero)
    hero = new Hero("蜘蛛侠", "烈火腿", random.nextInt(100))
    heros.append(hero)

    var datas :Array[GameRole] = heros.toArray
    sortHeros(datas)
//    datas = datas.sortBy(r => r.asInstanceOf[Hero].getAttack())
    for (hero <- datas) {
      hero.asInstanceOf[Hero].showInfo()
    }
  }

  def main(args: Array[String]): Unit = {
    testSortHero()
//    val hmanager1 = new Hmanager(1)
//    val random = new Random()
//    var hero = new Hero("葫芦娃", "喷火", random.nextInt(100))
//    hmanager1.addHero(hero)
//    hero = new Hero("红孩儿", "喷火", random.nextInt(100))
//    hmanager1.addHero(hero)
//    hero = new Hero("金刚狼", "排云掌", random.nextInt(100))
//    hmanager1.addHero(hero)
//    hero = new Hero("蜘蛛侠", "烈火腿", random.nextInt(100))
//    hmanager1.addHero(hero)
//    hmanager1.showMyHeros()
//
//    val hmanager2 = new Hmanager(2)
//    hero = new Hero("哪吒", "喷火", random.nextInt(100))
//    hmanager2.addHero(hero)
//    hero = new Hero("孙吾帆", "光速拳", random.nextInt(100))
//    hmanager2.addHero(hero)
//    hero = new Hero("乐迪", "火箭炮", random.nextInt(100))
//    hmanager2.addHero(hero)
//    hmanager2.showMyHeros()
//
//
//    var i = 0
//    while (i < 100) {
//      val hero1 = hmanager1.pickHero()
//      val hero2 = hmanager2.pickHero()
//      val hero1Win = hero1.fight(hero2)
//
//      var record1=""
//      var record2=""
//      if (hero1Win) {
//        hero1.incWinCount()
//        record1 = ("%d,%s,%s,%d,%d,是").format(hmanager1.getMid(),hero1.getName(), hero1.getSkill(),
//          hero1.getAttack(),hero1.getWinCount())
//
//        record2 =  "%d,%s,%s,%d,%d,否".format(hmanager2.getMid(),hero2.getName(), hero2.getSkill(),
//          hero2.getAttack(),hero2.getWinCount())
//      }else{
//        hero2.incWinCount()
//        record2 = "%d,%s,%s,%d,%d,是".format(hmanager2.getMid(),hero2.getName(), hero2.getSkill(),
//          hero2.getAttack(),hero2.getWinCount())
//        record1 = ("%d,%s,%s,%d,%d,否").format(hmanager1.getMid(),hero1.getName(), hero1.getSkill(),
//          hero1.getAttack(),hero1.getWinCount())
//      }
//
//      if (i==0){
//        val title = "mid,名字,技能,攻击力,胜场,是否获胜"
//        saveToFile("save.csv", Array(title))
//      }
//      //公共方法， 抽象出来，做成工具方法
//      saveToFile("save.csv", Array(record1, record2))
//      i = i + 1
//    }
  }

  def saveToFile(filePath: String, content: Array[String], append: Boolean = true): Unit = {
    val fileWriter = new FileWriter(new File(filePath), append)
    for (line <- content) {
      fileWriter.write(line)
      fileWriter.write("\r\n")
    }
    //释放资源
    fileWriter.close()
  }



  def sortHeros(data: Array[GameRole]): Unit = {
    for (i <- 0 until data.length) {
      var minIndex = i
      for (j <- i + 1 until data.length) {
        if(data(j).compare(data(minIndex))){
          minIndex = j
        }
      }
      val temp = data(i)
      data(i) = data(minIndex)
      data(minIndex) = temp
    }
  }
}
