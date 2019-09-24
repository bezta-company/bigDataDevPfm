package bzt.oop.animals

class Animal{
  def run(): Unit ={
    println( " 我在快速地奔跑！！！！")
  }
  def showSkill(): Unit ={
    println( " 该我展现技能了")
  }
}

class Cat extends Animal{
  val name = "猫"
  override def showSkill(): Unit = {
    println("我是:" + this.name + " 我能捉老鼠")
  }
}
class Dog extends Animal{
  val name = "狗"
  override def showSkill(): Unit = {
    println("我是:" + this.name + "我能咬人")
  }
}







object OOPdemo {


  def main(args: Array[String]): Unit = {
//    new Cat().run()
//    new Cat().showSkill()
//    new Dog().showSkill()
    listAnimals(Array(new Cat(), new Dog()))
//    var animal :Animal = new Animal()
//    animal.showSkill()
//    animal = new Dog()
//    animal.showSkill()
//    animal = new Cat()
//    animal.showSkill()
  }

  def listAnimals(animals :Array[Animal]): Unit ={
    for(animal <- animals){
      animal.showSkill()
    }
  }
}
