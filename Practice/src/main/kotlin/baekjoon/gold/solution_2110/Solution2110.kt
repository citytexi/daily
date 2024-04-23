package baekjoon.gold.solution_2110

import kotlin.math.max

private class Solution2110 {

    fun solution(
        n: Int,
        c: Int,
        arr: LongArray
    ): Long {
        var left = 1L
        var right = arr[n - 1] - arr[0]

        var result = 0L

        while (left <= right) {
            val center = (left + right) / 2
            var prev = arr[0]

            var count = 1

            for (i in 1 until n) {
                if (arr[i] - prev >= center) {
                    count += 1
                    prev = arr[i]
                }
            }

            when {
                count >= c -> {
                    left = center + 1
                    result = max(result, center)
                }
                else -> {
                    right = center - 1
                }
            }
        }

        return result
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (n, c) = br.readLine().split(" ").map(String::toInt)
    val arr = LongArray(n) { br.readLine().toLong() }.sortedArray()

    bw.append("${Solution2110().solution(n, c, arr)}\n")
    bw.flush()

    br.close()
}