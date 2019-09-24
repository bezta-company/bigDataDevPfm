package bzt.convertion

class Iphone6{
  val screenSize = 5.1f
  val name = "苹果6"
  def playGame(): Unit ={
    println("我可以打游戏")
  }
}
class Iphone7(screenSize :Float = 5.5f, name :String){
  def VR(): Unit ={
    println("我是:" + this.name + " 我有牛逼的VR")
  }
}

object ImplictConvertion {

  implicit def updateIphone(phone :Iphone6) = {
    new Iphone7( phone.screenSize, phone.name)
  }
}

