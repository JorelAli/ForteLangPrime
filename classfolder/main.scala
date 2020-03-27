object HelloWorld {
   def main(args: Array[String]) {
      println("Hello, world!")
   }
   
   case class Point(x: Float, y: Float)
   val origin: Point = Point(0, 0)
}