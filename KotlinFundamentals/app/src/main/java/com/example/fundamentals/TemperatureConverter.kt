package com.example.fundamentals

fun main() {
    // Fill in the code.
    val converisonFormulaLambda: (String) -> (Double) -> Double = {initialUnit  ->
        when(initialUnit){
            "Celsius" -> //to Fahrenheit ->  ° F = 9/5 (° C) + 32
                { temperature ->(9*temperature/5 +32)}
            "Fahrenheit" -> //to Kelvin ->  K = 5/9 (° F - 32) + 273.15
                {temperature -> (temperature - 32) *5 /9 + 273.15}
            "Kelvin" -> // to Celsius -> ° C = K - 273.15
                {temperature -> temperature - 273.15}
             else -> //error value -> return 0.0 temperature
                 { _ -> 0.0 }
        }

    }

    printFinalTemperature(27.0,"Celsius","Fahrenheit", converisonFormulaLambda("Celsius"))
    printFinalTemperature(350.0, "Kelvin", "Celsius", converisonFormulaLambda("Kelvin"))
    printFinalTemperature(10.0, "Fahrenheit", "Kelvin", converisonFormulaLambda("Fahrenheit"))
}


fun printFinalTemperature(
    initialMeasurement: Double,
    initialUnit: String,
    finalUnit: String,
    conversionFormula: (Double) -> Double
) {
    val finalMeasurement = String.format("%.2f", conversionFormula(initialMeasurement)) // two decimal places
    println("$initialMeasurement degrees $initialUnit is $finalMeasurement degrees $finalUnit.")
}

