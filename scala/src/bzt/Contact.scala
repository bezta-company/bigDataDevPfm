package bzt

//
//class Contact {
//  var name = ""
//  var phone = ""
//  var gender = ""
//  var age = 0
//  var job = ""
//
//  def getInfo() = {
//    "姓名：%s\n电话：%s\n性别：%s\n年龄:%d\n职业:%s".format(name, phone, gender, age, job)
//  }
//}

//class Contact(name :String, phone :String, gender :String, age :Int, job :String) {
//  def info() = {
//    "姓名：%s\n电话：%s\n性别：%s\n年龄:%d\n职业:%s".format(name, phone, gender, age, job)
//  }
//}

class Contact(name :String, phone :String, gender :String, age :Int, job :String) {

  def this(name :String, phone :String, age :Int){
    this(name, phone, gender="男", age, job="无业")
  }


  def getInfo() = {
    "姓名：%s\n电话：%s\n性别：%s\n年龄:%d\n职业:%s".format(name, phone, gender, age, job)
  }
  def getName()={
    this.name
  }
  def getAge()= {
    this.age
  }
  def getGender() = {
    this.gender
  }
  def getPhone()={
    this.phone
  }
  def getJob()={
    this.job
  }
}

object Contact{
  def main(args: Array[String]): Unit = {
    val contact = new Contact("jim", "12384824", "男", 18, "学生")
//    val contact = new Contact("jim", "12384824", 18)
//    val contact = new Contact()
//    contact.name = "jim"
//    contact.age = 20
//    contact.gender = "男"
//    contact.phone = ""
//    print(contact.getInfo())
    print(contact.getName())
  }
}