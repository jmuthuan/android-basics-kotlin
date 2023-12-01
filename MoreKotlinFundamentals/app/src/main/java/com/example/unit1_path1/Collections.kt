package com.example.unit1_path1

fun main() {
    //arrayExample()
    //listExample()
    //setExample()
    mapExample()
}

fun mapExample() {
    var solarSystem = mutableMapOf<String, Int>(
        "Mercury" to 0,
        "Venus" to 0,
        "Earth" to 1,
        "Mars" to 2,
        "Jupiter" to 79,
        "Saturn" to 82,
        "Uranus" to 27,
        "Neptune" to 14
    )

    println(solarSystem.size)

    solarSystem["Pluto"] = 5
    println(solarSystem.size)

    println(solarSystem["Pluto"])
    println(solarSystem.get("Theia"))

    solarSystem.remove("Pluto")
    println(solarSystem.size)

    solarSystem.put("Jupiter", 78)
    println(solarSystem["Jupiter"])
}


fun setExample() {
    var solarSystem = mutableSetOf("Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune")

    println(solarSystem.size)
    solarSystem.add("Pluto")
    println(solarSystem.size)
    solarSystem.add("Pluto")
    println(solarSystem.size)
    println(solarSystem.contains("Pluto"))

    println("****************************")
    solarSystem.remove("Pluto")
    println(solarSystem.size)
    println(solarSystem.contains("Pluto"))


}

fun listExample() {
    //var solarSystem = listOf("Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune")
    var solarSystem = mutableListOf("Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune")

    println(solarSystem.size)

    println(solarSystem[2])
    println(solarSystem.get(3))

    println(solarSystem.indexOf("Earth"))
    println(solarSystem.indexOf("Pluto"))

    solarSystem.add("Pluto")
    solarSystem.add(3, "Theia")

    solarSystem[3] = "Future Moon"

    solarSystem.removeAt(9)
    solarSystem.remove("Future Moon")

    println(solarSystem.contains("Pluto"))
    println("Future Moon" in solarSystem)

    println("*********************************")
    for(planet in solarSystem) {
        println(planet)
    }
}

fun arrayExample() {
    val rockPlanets = arrayOf<String>("Mercury", "Venus", "Earth", "Mars")
    val gasPlanets = arrayOf("Jupiter", "Saturn", "Uranus", "Neptune")

    val solarSystem = rockPlanets + gasPlanets

    println(solarSystem[0])
    println(solarSystem[1])
    println(solarSystem[2])
    println(solarSystem[3])
    println(solarSystem[4])
    println(solarSystem[5])
    println(solarSystem[6])
    println(solarSystem[7])

    solarSystem[3] = "Little Earth"
    println(solarSystem[3])

    val newSolarSystem = arrayOf("Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune", "Pluto")

    newSolarSystem[8] = "Pluto"
}