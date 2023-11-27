package com.example.fundamentals

fun main() {
    //27.0 degrees Celsius is 80.60 degrees Fahrenheit. ->  ° F = 9/5 (° C) + 32
    printFinalTemperatureV2(27.0, "Celsius", "Fahrenheit") {(9.0 * it / 5.0) +32}

    //350.0 degrees Kelvin is 76.85 degrees Celsius. -> ° C = K - 273.15
    printFinalTemperatureV2(350.0, "Kelvin", "Celsius") {it - 273.15}

    //10.0 degrees Fahrenheit is 260.93 degrees Kelvin. -> K = 5/9 (° F - 32) + 273.15
    printFinalTemperatureV2(10.0, "Fahrenheit", "kelvin") {(5.0/9.0)*(it - 32.0) + 273.15}

}


fun printFinalTemperatureV2(
    initialMeasurement: Double,
    initialUnit: String,
    finalUnit: String,
    conversionFormula: (Double) -> Double
) {
    val finalMeasurement = String.format("%.2f", conversionFormula(initialMeasurement)) // two decimal places
    println("$initialMeasurement degrees $initialUnit is $finalMeasurement degrees $finalUnit.")
}