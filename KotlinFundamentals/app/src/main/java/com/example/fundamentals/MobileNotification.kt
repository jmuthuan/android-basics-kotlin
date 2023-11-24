package com.example.fundamentals

fun main() {
    val morningNotification = 51
    val eveningNotification = 135

    printNotificationSummary(morningNotification)
    printNotificationSummary(eveningNotification)
}


fun printNotificationSummary(numberOfMessages: Int) {
    println(messageLimitCheck(numberOfMessages))
}

fun messageLimitCheck(quantity: Int): String {
    var message = "You have $quantity notifications"
    if(quantity > 99) {
        message = "Your phone is blowing up! You have 99+ notifications"
    }

    return message
}