package org.flowutils

/**
 *
 */

public data class Units(val lengthExponent: Int = 0,
                val timeExponent: Int = 0,
                val massExponent: Int = 0) // TODO: Other SI units
                 {
    fun plus(other: Units): Units {
        if (this != other) throw IllegalStateException("Can not add measurements with types $this and $other")
        return this
    }

    fun times(other: Units): Units {
        return Units(lengthExponent + other.lengthExponent,
                     timeExponent + other.timeExponent,
                massExponent + other.massExponent)
    }

    override fun toString(): String {
        var s = ""
        if (lengthExponent != 0) {
            s += "m"
            if (lengthExponent != 1) {
                s += "^$lengthExponent"
            }
        }
        return s
    }
}

public val m: Units = Units(lengthExponent = 1)

public fun Double.m() : Measure = Measure(this, m)
public fun Double.times(units: Units) : Measure = Measure(this, units)

public data class Measure(var value: Double, val units: Units) {

    fun plus(other: Measure ): Measure = Measure(value + other.value, units + other.units)
    fun times(other: Measure ): Measure = Measure(value * other.value, units * other.units)

    override fun toString(): String {
        return "$value$units"
    }
}


fun main(args: Array<String>) {

    val dist = 5.0*m
    val dist2 = 3.0.m()

    val foo = dist + dist2
    val bar = dist * dist2

    println("foo = ${foo}")
    println("bar = ${bar}")

}
