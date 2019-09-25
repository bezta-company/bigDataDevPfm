package bzt.convertion

import ImplictConvertion.updateIphone

object ConvertionDemo {
  //    def main(args: Array[String]): Unit = {
  //      val iphone6 = new Iphone6()
  //      iphone6.playGame()
  //      iphone6.VR();
  //    }
  //  def main(args: Array[String]): Unit = {
  //    val iphone6 = new Iphone6()
  //    testParameterConvertion(iphone6)
  //  }
  def main(args: Array[String]): Unit = {
    val iphone6 = new Iphone6()
//    iphone6.asInstanceOf[Iphone7]
    testParameterConvertion(iphone6)
  }

  //
  def testParameterConvertion(iphone7: Iphone7): Unit = {
    iphone7.VR()
  }

}
