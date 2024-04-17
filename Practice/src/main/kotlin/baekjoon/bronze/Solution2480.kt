package baekjoon.bronze

import kotlin.math.max

private class Solution2480 {
    fun solution(a: Int, b: Int, c: Int): Int = when {
        a == b && b == c -> 10_000 + a * 1_000
        a == b -> 1_000 + a * 100
        b == c -> 1_000 + b * 100
        a == c -> 1_000 + c * 100
        else -> max(a, max(b, c)) * 100
    }
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (a, b, c) = br.readLine().split(" ").map(String::toInt)

    bw.append("${Solution2480().solution(a, b, c)}\n")
    bw.flush()

    br.close()
    bw.close()
}