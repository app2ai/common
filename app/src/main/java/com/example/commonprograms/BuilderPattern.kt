package com.example.commonprograms

// BuilderPattern Example
fun main() {
    val myComputer = Computer.Builder()
        .osName("MacOS")
        .build()
    println("My computer configuration: ${myComputer}")

    val myComputer2 = Computer.Builder().apply {
        cpuName("Intel")
        monitorName("Samsung")
        osName("Ubuntu 18.04 LST")
    }.build()
    println("My second computer configuration: ${myComputer2}")
}

class Computer private constructor(
    var cpu: String?,
    var monitor: String?,
    var bootTime: Int?,
    var os: String?
) {
    data class Builder(
        var cpu: String = "Other",
        var monitor: String = "Other",
        var bootTime: Int? = null,
        var os: String? = null) {

        fun cpuName(cpu: String) = apply { this.cpu = cpu }
        fun monitorName(monitor: String) = apply { this.monitor = monitor }
        fun bootTiming(bootTime: Int) = apply { this.bootTime = bootTime }
        fun osName(os: String) = apply { this.os = os }

        fun build() = cpuName(cpu)
            .monitorName( monitor)
            .bootTiming( bootTime ?: 10)
            .osName(os ?: "Windows")
    }
}
