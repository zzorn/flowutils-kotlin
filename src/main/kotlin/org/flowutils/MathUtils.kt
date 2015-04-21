package org.flowutils

/**
 *
 */


/**
 * Tau is 2 Pi, see http://www.tauday.com
 */
public val Tau: Double = Math.PI * 2

/**
 * Floating point version of Tau.
 */
public val TauF: Float = Tau.toFloat()


/**
 * Interpolate between the start and end values (and beyond).
 * @param t 0 corresponds to start, 1 to end.
 * *
 * @return interpolated value
 */
public fun mix(t: Float, start: Float, end: Float): Float = start + t * (end - start)

/**
 * Interpolate between the start and end values (and beyond).
 * @param t 0 corresponds to start, 1 to end.
 * *
 * @return interpolated value
 */
public fun mix(t: Double, start: Double, end: Double): Double = start + t * (end - start)


