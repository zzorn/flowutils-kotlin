package org.flowutils


public data class Units(val lengthExponent: Int = 0,
                val timeExponent: Int = 0,
                val massExponent: Int = 0) // TODO: Other SI units
                 {

    public fun ensureSame(other: Units): Units {
        if (this != other) throw IllegalStateException("Units should have been the same, but they are $this and $other")

        // Return self for chaining
        return this;
    }

    public fun times(other: Units): Units {
        return Units(lengthExponent + other.lengthExponent,
                     timeExponent + other.timeExponent,
                     massExponent + other.massExponent)
    }

    public fun div(other: Units): Units {
        return Units(lengthExponent - other.lengthExponent,
                     timeExponent - other.timeExponent,
                     massExponent - other.massExponent)
    }

    public override fun toString(): String {
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

public val unitless: Units = Units()

public val s: Units = Units(timeExponent = 1)

public val kg: Units = Units(massExponent = 1)

public val m: Units = Units(lengthExponent = 1)

/* LATER: This needs an immutable Measure class, so that we can't change the value of a kilometer on the fly
public val km: Measure = Measure(1000.0, m)
public val mm: Measure = Measure(0.001, m)
public val cm: Measure = Measure(0.01, m)
*/

public val velocity: Units = m/s


public fun Double.times(units: Units) : Measure = Measure(this, units)
public fun Double.times(measure: Measure) : Measure = Measure(this * measure.value, measure.units)
public fun Float.times(units: Units) : Measure = Measure(this.toDouble(), units)
public fun Float.times(measure: Measure) : Measure = Measure(this * measure.value, measure.units)
public fun Int.times(units: Units) : Measure = Measure(this.toDouble(), units)
public fun Int.times(measure: Measure) : Measure = Measure(this.toDouble() * measure.value, measure.units)
public fun Number.times(units: Units) : Measure = Measure(this.toDouble(), units)
public fun Number.times(measure: Measure) : Measure = Measure(this.toDouble() * measure.value, measure.units)

public data class Measure(var value: Double = 0.0, val units: Units = unitless) {

    public fun plus(other: Measure ):  Measure = Measure(value + other.value, units ensureSame other.units)
    public fun minus(other: Measure ): Measure = Measure(value - other.value, units ensureSame other.units)
    public fun times(other: Measure ): Measure = Measure(value * other.value, units * other.units)
    public fun div(other: Measure ):   Measure = Measure(value / other.value, units / other.units)

    public fun times(scalar: Double): Measure = Measure(value * scalar, units)
    public fun div(scalar: Double):   Measure = Measure(value / scalar, units)
    public fun times(scalar: Float): Measure = Measure(value * scalar, units)
    public fun div(scalar: Float):   Measure = Measure(value / scalar, units)
    public fun times(scalar: Int): Measure = Measure(value * scalar, units)
    public fun div(scalar: Int):   Measure = Measure(value / scalar, units)
    public fun times(scalar: Number): Measure = Measure(value * scalar.toDouble(), units)
    public fun div(scalar: Number):   Measure = Measure(value / scalar.toDouble(), units)

    public fun plusAssign(other: Measure)  { units.ensureSame(other.units); value += other.value }
    public fun minusAssign(other: Measure) { units.ensureSame(other.units); value -= other.value }
    public fun timesAssign(scalar: Double) { value *= scalar }
    public fun divAssign(scalar: Double)   { value /= scalar }
    public fun timesAssign(scalar: Float) { value *= scalar }
    public fun divAssign(scalar: Float)   { value /= scalar }
    public fun timesAssign(scalar: Int) { value *= scalar }
    public fun divAssign(scalar: Int)   { value /= scalar }
    public fun timesAssign(scalar: Number) { value *= scalar.toDouble() }
    public fun divAssign(scalar: Number)   { value /= scalar.toDouble() }

    public fun unitIs(units: Units): Boolean = this.units == units
    public fun unitIsNot(units: Units): Boolean = this.units != units
    public fun ensureUnitIs(expectedUnit: Units): Measure {
        if (units != expectedUnit) {
            throw AssertionError("The unit was expected to be $expectedUnit, but it was $units")
        }
        // Return self for chaining
        return this
    }

    public override fun toString(): String {
        return "$value$units"
    }
}


fun main(args: Array<String>) {

    val dist = 5*m
    val dist2 = 3*m
    val time = 0.5*s

//    val foo = dist + dist2 - time
    val foo = 4 * dist *time+ dist2 * time * 4
    val bar = dist * dist2 / time
    bar *= 3.0

    val speed = dist / time ensureUnitIs m/s
    speed ensureUnitIs velocity

    println("foo = ${foo}")
    println("bar = ${bar}")
    println("speed = ${speed}")

}

