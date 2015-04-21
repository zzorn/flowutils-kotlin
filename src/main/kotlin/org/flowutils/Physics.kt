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

    fun minus(other: Units): Units {
        if (this != other) throw IllegalStateException("Can not add measurements with types $this and $other")
        return this
    }

    fun times(other: Units): Units {
        return Units(lengthExponent + other.lengthExponent,
                     timeExponent + other.timeExponent,
                     massExponent + other.massExponent)
    }

    fun div(other: Units): Units {
        return Units(lengthExponent - other.lengthExponent,
                     timeExponent - other.timeExponent,
                     massExponent - other.massExponent)
    }

    override fun toString(): String {
        var s = ""
        if (lengthExponent != 0) {
            s += " m"
            if (lengthExponent != 1) {
                s += "^$lengthExponent"
            }
        }
        if (timeExponent != 0) {
            s += " s"
            if (timeExponent != 1) {
                s += "^$timeExponent"
            }
        }
        if (massExponent != 0) {
            s += " kg"
            if (massExponent != 1) {
                s += "^$massExponent"
            }
        }
        return s
    }
}

public val m: Units = Units(lengthExponent = 1)
public val s: Units = Units(timeExponent = 1)

public fun Double.times(units: Units) : Measure = Measure(this, units)
public fun Double.times(measure: Measure) : Measure = Measure(this * measure.value, measure.units)
public fun Int.times(units: Units) : Measure = Measure(this.toDouble(), units)
public fun Int.times(measure: Measure) : Measure = Measure(this.toDouble() * measure.value, measure.units)

public data class Measure(var value: Double, val units: Units) {

    fun plus(other: Measure ): Measure = Measure(value + other.value, units + other.units)
    fun minus(other: Measure ): Measure = Measure(value - other.value, units - other.units)
    fun times(other: Measure ): Measure = Measure(value * other.value, units * other.units)
    fun div(other: Measure ): Measure = Measure(value / other.value, units / other.units)

    override fun toString(): String {
        return "$value$units"
    }
}


fun main(args: Array<String>) {

    val dist = 5*m
    val dist2 = 3*m
    val time = 1*s

//    val foo = dist + dist2 - time
    val foo = dist *time+ dist2 * time
    val bar = dist * dist2 / time

    println("foo = ${foo}")
    println("bar = ${bar}")

}

