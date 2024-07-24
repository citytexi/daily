package baekjoon.gold.solution_2981

import kotlin.math.abs

private class Solution2981 {

    fun solution(
        n: Int,
        inputs: IntArray
    ): String {
        var gcdValue = 0

        for (i in 1 until inputs.size) {
            gcdValue = gcd(
                a = abs(inputs[i] - inputs[i - 1]),
                b = gcdValue
            )
        }

        val gcdValues = hashSetOf(gcdValue)

        for (i in 2 .. kotlin.math.sqrt(gcdValue.toDouble()).toInt()) {
            if (gcdValue % i != 0) {
                continue
            }
            gcdValues.add(i)
            gcdValues.add(gcdValue/i)
        }

        return gcdValues.toList().sorted().joinToString(" ")
    }

    private fun gcd(
        a: Int,
        b: Int
    ): Int = when (b) {
        0 -> a
        else -> gcd(b, a % b)
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    val inputs = IntArray(n) { br.readLine().toInt() }

    bw.append(Solution2981().solution(n, inputs))
    bw.flush()

    br.close()
    bw.close()
}