package baekjoon.silver.solution_2108

import kotlin.math.roundToInt

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    val numbers = MutableList(n) {
        val value = br.readLine().toInt()
        value
    }

    numbers.sort()

    val map = numbers.groupingBy { it }.eachCount()
    val maxValue = map.maxOf { it.value }
    val final = map.filter { it.value == maxValue }.keys.sorted()

    bw.append("${numbers.average().roundToInt()}\n")
    bw.append("${numbers[numbers.size / 2]}\n")
    bw.append("${
        when (final.size) {
            1 -> final.first()
            else -> final[1]
        }
    }\n")
    bw.append("${numbers.maxOf { it } - numbers.minOf { it }}\n")
    bw.flush()

    br.close()
    bw.close()
}