package com.com.kotlin

import kotlin.reflect.KProperty

/**
 * Created by wei.li on 2016/2/26.
 */

val lazyValue :String by lazy{
    println("computed!!")
    "Heohji"
}

fun testLazy(){

    println(lazyValue)
}




class Example{
    var p:String by Delegate()


}

fun f(){
    var  e = Example()
    e.p = "NEW"

}



class Delegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, thank you for delegating '${property.name}' to me!"
    }
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$value has been assigned to '${property.name} in $thisRef.'")
    }
}
interface Base{
    fun print()
}


class BaseImp(var x:Int) :Base{
    override fun print(){
        println(x)
    }
}


class Derived(b:Base) :Base by b

fun main(){
    val c = BaseImp(10)
    Derived(c).print()
}



open class  Outer{

    constructor(a:Int,b :Int ){

    }
    private val a =1
    protected val b =2
    internal  val  c=3

    protected class Nested{
        public  val e:Int =5
        public  val b = 23
        public val  a = e
        public  val c = 3


    }

}
open class T:Outer(1,32){


}




// 方法 fun 参数 （变量名 ：参数类型）　无返回值
// 方法 fun 参数 （变量名 ：参数类型）：返回值类型｛｝
fun sum(a:Int,b:Int): Int{
    return a + b;
}

fun sum1(a:Int,b:Int) = a + b

fun sum2(a:Int,b:Int) :Unit{
    print(a+b)
}

fun sum3(a:Int,b:Int){
    print(a+b)
}


fun array(args : Array<String>){
    if(args.size == 0) return
    println("First argument:${args[0]}")
}
fun max(a:Int,b:Int):Int{
    if(a > b) return a

    else return b
}


fun method(){
    val a:Int = 1
    var b = 1
    b+=2

}

fun parseInt(str:String):Int?{
    return  str.toInt()

}

fun nullable(args :Array<String>){
    if(args.size <2){
        println("Two integers expected")
        return
    }
    println(args[0]+"===>>"+args[1])
    val x = parseInt(args[0])
    val y = parseInt(args[1])

    if(x != null && y!= null){
        println(x * y)
    }

}
//不确定的对戏类型Any
// 用于判断对象的类型 相当于instanceof
fun getStringLength(obj:Any):Int?{
    if(obj is String){
        println("==>>"+obj.length)
        return obj.length
    }

    return null
}

///循环 in
fun loop(args : Array<String>){
   // for(arg in args)
    for( i in args.indices)
        println(args[i])
}

fun whileLoop(args: Array<String>){
    var i = 0;
    while(i<args.size)
        println(args[i++])
}




