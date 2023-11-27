package com.example.fundamentals

open class Phone(var isScreenLightOn: Boolean = false){
    open fun switchOn() {
        isScreenLightOn = true
    }

    fun switchOff() {
        isScreenLightOn = false
    }

    fun checkPhoneScreenLight() {
        val phoneScreenLight = if (isScreenLightOn) "on" else "off"
        println("The phone screen's light is $phoneScreenLight.")
    }
}

//A property that indicates whether the phone is folded.
//A different switchOn() function behavior than the Phone class so that it only turns the screen on when the phone isn't folded.
//Methods to change the folding state.

class FoldablePhones(var isFoldedScreenLightOn: Boolean = false, var isFolded: Boolean = true) :
    Phone (isScreenLightOn = isFoldedScreenLightOn) {

    override fun switchOn() {
        if(!isFolded) super.switchOn()
    }

    fun fold() {
        isFolded = true
        switchOff()
    }

    fun unfold() {
        isFolded = false
    }

    }

fun main() {
    val newFoldPhone = FoldablePhones()

    newFoldPhone.switchOn()
    newFoldPhone.checkPhoneScreenLight()

    newFoldPhone.unfold()
    newFoldPhone.switchOn()
    newFoldPhone.checkPhoneScreenLight()

    newFoldPhone.fold()
    newFoldPhone.checkPhoneScreenLight()
}