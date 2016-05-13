package com.com.kotlin

import java.util.*
import kotlin.properties.Delegates

/**
 * Created by wei.li on 2016/3/31.
 */


fun main(args:Array<String>){


  var a:String ="erge"
    val len =  a.length?: -1
    val l = a!!.length
    println( l)

    var aInt:Int? = a as? Int
    throw NullPointerException("iouhgiue")
    println( aInt)
}



fun demo(x:Any){

    when(x){
        is Int -> println(x+2)
        is String ->println(x.length)
        is IntArray -> println(x.sum())
    }

    if(x is String){
        println(x.length)
    }else{
        println("==="+x)
    }
}





fun mm(){
    var sum = 0
    var list = arrayOf(1,2,3,4,5,6)
    list.filter { it > 2 }.forEach {
        println(it)
    }
}


val sum:(Int,Int) ->Int = {x,y ->x+y}

val a = arrayOf(1,2,4)
//fun <T> loo(user:User,asList:(T) -> List<T>):T{
//
//
//    return
//}


fun <T> asList(vararg ts:T ):List<T>{
    val result = ArrayList<T>()
    for(t in ts) result.add(t)
    return result
}

fun double3(x:Int) = x*2
fun double2(x:Int):Int = x*2

fun printHello(str:String?):Unit{
    if(str != null) println("Hello33 $str")
    else println("Hi there!")
}

fun read(b:String,off:Int = 0,len:Int = b.length){
    println(b)
}

infix fun Int.shl(x:Int):Int{

    println(x)
    return  x
}

fun double(x:Int):Double{
    println(x.toDouble())
    return x.toDouble()
}

class Person(val map:Map<String,Any?>){
     val name:String by map
    val age :Int by map
}


class User{
    var name:String by Delegates.observable("<no name>"){
        prop,old,new ->
        println("$old -> $new")
    }
}



val lazyMethod:String by lazy{
    println("computes1111111")
    LazyThreadSafetyMode.PUBLICATION
    "Hello"
}