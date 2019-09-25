package bzt.homework.game

class Hero(name :String, skill :String, attack :Int, level :Int, var winCount :Int=0) {
  def getWinCount() = this.winCount
  def getName() =this.name
  def getSkill() = this.skill


  def incWinCount(): Unit = {
    this.winCount = this.winCount + 1
  }

  //  val name :String =""
//  val skill :String =""
//  val attack :Int =0
//  val level :Int =1

  def showInfo(): Unit ={
    println("%s\t%s\t%d\t\t%d\t\t%d".format(name, skill, attack, level, winCount))
  }
  def this(name :String, skill :String, attack :Int) ={
    this(name, skill, attack, 1)
  }

  def getAttack() = this.attack


  def fight(target :Hero)={
    //学校
    if (attack < target.getAttack())
       false
    else
       true

    //工业界1
//      var result = true
//      if (attack < target.getAttack()){
//        result = false
//      }
//      result
    // 工业界2
//    if (attack < target.getAttack()){
//      return false
//    }
//    return true
  }
}


