package com.example.fundamentals

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

open class SmartDevice protected constructor (val name: String, val category: String) {
    var deviceStatus = "online"
        protected set(value){
            field = value
        }

    open val deviceType = "unknowwn"

    constructor(name: String, category: String, statusCode: Int) : this(name, category){
        deviceStatus = when(statusCode){
            0-> "offline"
            1-> "online"
            else-> "unknown"
        }
    }

    open fun turnOn() {
        deviceStatus = "on"
    }
    open fun turnOff() {
        deviceStatus = "off"
    }
}

class SmartTvDevice(deviceName: String, deviceCategory: String):
    SmartDevice(name = deviceName, category  = deviceCategory){
    override val deviceType = "Smart TV"

    private var speakerVolume by RangeRegulator(initialValue = 2, minValue = 0, maxValue = 100)

    private var channelNumber by RangeRegulator(initialValue = 1, minValue = 1, maxValue = 200)

    fun increaseVolumeSpeaker(){
        speakerVolume++
        println("Speaker volume increased to $speakerVolume")
    }

    fun nextChannel(){
        channelNumber++
        println("Channel number increased to $channelNumber")
    }

    override fun turnOn() {
        super.turnOn()
        println("$name is turned on. Speaker volume is set to $speakerVolume and channel number is "
                + "set to $channelNumber.")
    }

    override fun turnOff() {
        super.turnOff()
        println("$name turned off")
    }
}

class SmartLightDevice(deviceName: String, deviceCategory: String):
    SmartDevice(name = deviceName, category = deviceCategory){
    override val deviceType = "Smart Light"

    private var brightnessLevel by RangeRegulator(initialValue = 1, minValue = 0, maxValue = 100)

    fun increaseBrightness(){
        brightnessLevel++
        println("Brightness increased to $brightnessLevel.")
    }

    override fun turnOn() {
        super.turnOn()
        brightnessLevel = 2
        println("$name turned on. Brightness level is $brightnessLevel")
    }

    override fun turnOff() {
        super.turnOff()
        brightnessLevel = 0
        println("Smart Light turned off")
    }
}

class SmartHome(
    val smartTvDevice: SmartTvDevice,
    val smartLightDevice : SmartLightDevice
) {
    var deviceTurnOnCount = 0
        private set

    fun turnOnTv() {
        smartTvDevice.turnOn()
        deviceTurnOnCount++
    }

    fun turnOffTv() {
        smartTvDevice.turnOff()
        deviceTurnOnCount--
    }

    fun increaseTvVolume() {
        smartTvDevice.increaseVolumeSpeaker()
    }

    fun changeTvChannelTonext() {
        smartTvDevice.nextChannel()
    }
    fun turnOnLight() {
        smartLightDevice.turnOn()
        deviceTurnOnCount++
    }

    fun turnOffLight() {
        smartLightDevice.turnOff()
        deviceTurnOnCount--
    }

    fun increaseLightBrightness() {
        smartLightDevice.increaseBrightness()
    }

    fun turnOffAllDevices() {
        turnOffTv()
        turnOffLight()
    }
}

class RangeRegulator(
    initialValue: Int,
    private val minValue: Int,
    private val maxValue: Int
) : ReadWriteProperty<Any?, Int> {
    var fieldData = initialValue

    override fun getValue(thisRef: Any?, property: KProperty<*>): Int {
        return fieldData
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
        if (value in minValue..maxValue) {
            fieldData = value
        }
    }

}

fun main() {
    var smartDevice : SmartDevice = SmartTvDevice("Android TV", "Entertainment")
    println("Device name is: ${smartDevice.name}")
    smartDevice.turnOn()
    smartDevice.turnOff()

    smartDevice = SmartLightDevice("Google Light", "Utility")
    smartDevice.turnOn()

    smartDevice = SmartTvDevice("Android Chromecast TV", "Entertainment")
    smartDevice.nextChannel()
}