package com.example.unit1_path1


data class Event(
    val title: String,
    val description: String? = null,
    val daypart: Daypart,
    val durationInMinutes: Int
)

enum class Daypart{
    MORNING, AFTERNOON, EVENING
}

val Event.durationOfEvent: String
    get() = if(this.durationInMinutes < 60){
        "short"
    }else {
        "long"
    }

fun main() {
    val newEvent = Event(
        title = "Study Kotlin",
        description = "Commit to studying Kotlin at least 15 minutes per day.",
        daypart = Daypart.EVENING,
        durationInMinutes = 15
    )

    println(newEvent.toString())
/*
    val event1 = Event(title = "Wake up", description = "Time to get up", daypart = Daypart.MORNING, durationInMinutes = 0)
    val event2 = Event(title = "Eat breakfast", daypart = Daypart.MORNING, durationInMinutes = 15)
    val event3 = Event(title = "Learn about Kotlin", daypart = Daypart.AFTERNOON, durationInMinutes = 30)
    val event4 = Event(title = "Practice Compose", daypart = Daypart.AFTERNOON, durationInMinutes = 60)
    val event5 = Event(title = "Watch latest DevBytes video", daypart = Daypart.AFTERNOON, durationInMinutes = 10)
    val event6 = Event(title = "Check out latest Android Jetpack library", daypart = Daypart.EVENING, durationInMinutes = 45)

 */

    val eventList = mutableListOf(
        Event(title = "Wake up",
            description = "Time to get up",
            daypart = Daypart.MORNING,
            durationInMinutes = 0),
        Event(title = "Eat breakfast",
            daypart = Daypart.MORNING,
            durationInMinutes = 15),
        Event(title = "Learn about Kotlin",
            daypart = Daypart.AFTERNOON,
            durationInMinutes = 30),
        Event(title = "Practice Compose",
            daypart = Daypart.AFTERNOON,
            durationInMinutes = 60),
        Event(title = "Watch latest DevBytes video",
            daypart = Daypart.AFTERNOON,
            durationInMinutes = 10),
        Event(title = "Check out latest Android Jetpack library",
            daypart = Daypart.EVENING,
            durationInMinutes = 45)
    )

    println("\n******************************")
    shortEvents(eventList)

    println("\n******************************")
    daypartSummary(eventList)

    println("\n******************************")
    lastEvent(eventList)

    println("\n******************************")
    println("Duration of first event of the day: ${eventList[0].durationOfEvent}")
    println("Duration of first event of the day: ${eventList[3].durationOfEvent}")
}

fun shortEvents(eventList: List<Event>) {
    //A "short" event is an event that is less than 60 minutes.
    val shortEventList = eventList.filter {
        it.durationInMinutes < 60
    }

    println("you have ${shortEventList.size} short events")
}

fun daypartSummary(eventList: List<Event>) {
    val daypartGroup = eventList.groupBy { it.daypart }

    daypartGroup.forEach {(daypart, events) ->
            println("${daypart}: ${events.size}")
    }
}

fun lastEvent(eventList: List<Event>) {
    println("Last event of the day: ${eventList.last().title}")
}