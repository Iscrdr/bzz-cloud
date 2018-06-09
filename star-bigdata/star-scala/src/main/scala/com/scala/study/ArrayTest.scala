package com.scala.study

import scala.collection.mutable.ArrayBuffer

object ArrayTest {
  def main(args: Array[String]): Unit = {
    var arr1 = new Array[Int](10);
    arr1(0) = 1;
    arr1(1) = 2;
    arr1(2) = 3;
    arr1(3) = 4;
    println(arr1.length,arr1(1));

   val b = ArrayBuffer[Int]();
    b += 1;
    b += (2,34,6);
   println(b)
    b.insert(3,7);
    println(b.trimEnd(4))

  }

}
