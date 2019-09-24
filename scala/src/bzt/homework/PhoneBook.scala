package bzt.homework

import bzt.Contact

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

object PhoneBook {

  def main(args: Array[String]): Unit = {
    startPhoneBook()
  }

  def getInputAction() = {
    scala.io.StdIn.readLine()
  }

  def showFullList(): Unit = {
    // 放入联系人到数组
    val contacts = new ArrayBuffer[Contact]()
    for (key <- phoneBook.keySet) {
      val con = phoneBook.get(key).get
      contacts.append(con)
    }
    // 对联系人排序
    val contactValues = contacts.toArray
    sortContacts(contactValues)
    // 打印
    println("姓名\t电话\t性别\t年龄\t职业")
    for (contact <- contactValues) {
      val info = "%s\t%s\t%s\t%d\t%s".format(contact.getName(), contact.getPhone(), contact.getGender()
        , contact.getAge(), contact.getJob())
      println(info)
    }
  }

  def sortContacts(data: Array[Contact]): Unit = {
    for (i <- 0 until data.length) {
      var minIndex = i
      for (j <- i + 1 until data.length) {
        if (data(j).getAge() < data(minIndex).getAge()) {
          minIndex = j
        }
      }
      val temp = data(i)
      data(i) = data(minIndex)
      data(minIndex) = temp
    }
  }

  def showMenu(): Unit = {
    println("**********BZT 通讯录 V1**********")
    println("a：添加联系人\nb：查看通讯录列表\nc：查看联系人详情\nd：删除联系人\ne：修改联系人详情\nq：退出")
  }

  val phoneBook = new mutable.HashMap[String, Contact]

  def addUser(): Unit = {
    println("**********添加联系人**********")
    showUserPromt()
    val userInfo = getInputAction()
    val userValues = userInfo.split("\\s+")
    val contact = new Contact(userValues(0), userValues(1), userValues(2),
      userValues(3).toInt, userValues(4))
    phoneBook.put(contact.getName(), contact)

    println("**********添加联系人:  成功**********")
    println("a : 继续添加")
    println("任意其它键 : 返回主菜单")
    val action = getInputAction()
    if ("a".equals(action.toLowerCase)) {
      addUser()
    }


  }

  def delUser() = {
    //    println("******删除联系人*******")
    //    showUserNamePromt()
    //    val userName = getInputAction()
    //    if (userName == null || !phoneBook.contains(userName.trim())) {
    //      println("用户名" + userName + "不存在, " + "按任意键返回主菜单")
    //      getInputAction()
    //
    //    } else {
    //      phoneBook.remove(userName.trim())
    //    }
  }

  def showUserPromt(): Unit = {
    println("请按以下格式输入：姓名  电话  性别 年龄 职业 （以一个或多个空格隔开）")
  }

  def showPhonePromt(): Unit = {
    println("请输入电话号码:")
  }

  def startPhoneBook(): Unit = {

    var action: String = null
    do {
      showMenu()
      action = getInputAction().toLowerCase()
      action.toLowerCase match {
        case "a" => addUser()
        case "b" => showFullList()
        case "d" => delUser()
        case "q" =>
          println("已退出程序")
        case _ =>
          println()
      }
    } while (!action.equals("q"))

  }


  def alterUse(): Unit = {
    //    println("**********修改联系人**********")
    //    showUserNamePromt()
    //    val userName = getInputAction()
    //    if (userName == null || !phoneBook.contains(userName.trim())) {
    //      println("用户名" + userName + "不存在, " + "按任意键返回主菜单")
    //      getInputAction()
    //
    //    } else {
    //      println("**********修改联系人**********")
    //      println(userName + "\t" + phoneBook.get(userName).get)
    //      phoneBook.remove(userName)
    //      println("请输入新的姓名：")
    //      val newUserName = getInputAction()
    //      println("请输入新的电话：")
    //      val newPhone = getInputAction()
    //      phoneBook.put(newUserName, newPhone)
    //    }
  }
}
