package bzt.homework.game

import bzt.SortDemo

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

class Hmanager(mid :Int) {
  def getMid() = this.mid

  val heroPool :ArrayBuffer[Hero] = new ArrayBuffer[Hero]()

  def addHero(hero :Hero): Unit ={
    heroPool.append(hero)
  }

  def showMyHeros(): Unit ={
    println("我是 hero Manager%s,我有如下heros:".format(this))
    println("名字\t技能\t攻击力\t等级\t获胜")
    val heros = heroPool.toArray
    SortDemo.selectSort(heros)
    for (hero <- heros) {
      hero.showInfo()
    }
  }
  def pickHero()={
    val random = new Random()
    heroPool(random.nextInt(heroPool.size))
  }
}
