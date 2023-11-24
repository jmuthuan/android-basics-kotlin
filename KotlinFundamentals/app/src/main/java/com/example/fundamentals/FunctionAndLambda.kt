package com.example.fundamentals

fun main() {
    //val coins: (Int) -> String = {"$it quarters"}

    val cupcake: (Int) -> String = {
        "Have a cupcake"
    }

    val treatFunction = trickOrTreat(false) { "$it quarters" }
    //val treatFunction2 = trickOrTreat(false, cupcake)
    val trickFunction = trickOrTreat(true, null)


    repeat(4){
        treatFunction()
    }
    //treatFunction2()
    trickFunction()
}

fun trickOrTreat(isTrick : Boolean, extraTreat: ((Int) -> String)?): () -> Unit {
    if (isTrick){
        return trick
    } else {
        if(extraTreat != null) {
            println(extraTreat(5))
        }
        return treat
    }
}

val trick = {
    println("No treats!")
}

val treat: () -> Unit = {
    println("Have a treat!!")
}