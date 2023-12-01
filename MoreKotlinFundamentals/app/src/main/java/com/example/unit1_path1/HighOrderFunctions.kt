package com.example.unit1_path1

class Cookie(
    val name: String,
    val softBaked: Boolean,
    val hasFilling:Boolean,
    val price: Double
)

val cookies = listOf(
    Cookie(
        name = "Chocolate Chip",
        softBaked = false,
        hasFilling = false,
        price = 1.69
    ),
    Cookie(
        name = "Banana Walnut",
        softBaked = true,
        hasFilling = false,
        price = 1.49
    ),
    Cookie(
        name = "Vanilla Creme",
        softBaked = false,
        hasFilling = true,
        price = 1.59
    ),
    Cookie(
        name = "Chocolate Peanut Butter",
        softBaked = false,
        hasFilling = true,
        price = 1.49
    ),
    Cookie(
        name = "Snickerdoodle",
        softBaked = true,
        hasFilling = false,
        price = 1.39
    ),
    Cookie(
        name = "Blueberry Tart",
        softBaked = true,
        hasFilling = true,
        price = 1.79
    ),
    Cookie(
        name = "Sugar and Sprinkles",
        softBaked = false,
        hasFilling = false,
        price = 1.39
    )

)


fun main() {
    //forEachExample(cookies)
    //mapp()
    //filterExample()
    //groupByExample()
    //foldExample()
    sortedByExample()
}

fun sortedByExample() {
    val alphabeticalMenu = cookies.sortedBy {
        it.name
    }

    println("Alphabetical Menu:")
    alphabeticalMenu.forEach {
        println("${it.name} - $${it.price}")
    }

}

fun foldExample() {
    val totalPrice = cookies.fold(0.0) { total, cookie ->
        total + cookie.price
    }

    println("Total price: $${totalPrice}")
}

fun groupByExample() {
    val groupedMenu = cookies.groupBy { it.softBaked }

    val softBakedMenu = groupedMenu[true] ?: listOf()
    val crunchyMenu = groupedMenu[false] ?: listOf()

    println("Soft cookies: ")
    softBakedMenu.forEach {
        println("${it.name} - $${it.price}")
    }

    println("Crunchy cookies: ")
    crunchyMenu.forEach {
        println("${it.name} - $${it.price}")
    }


}

fun filterExample() {
    val softBakedMenu = cookies.filter {
        it.softBaked
    }
    println("Soft cookies: ")
    softBakedMenu.forEach {
        println("${it.name} - $${it.price}")
    }
}

fun mapp() {
    val fullMenu = cookies.map {
        "${it.name} - $${it.price}"
    }
    fullMenu.forEach {
        println(it)
    }

}

fun forEachExample(cookies: List<Cookie>) {
    cookies.forEach {
        println("Menu item: ${it.name}")
    }
}