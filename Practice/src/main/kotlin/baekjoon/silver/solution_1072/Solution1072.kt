package baekjoon.silver.solution_1072

import kotlin.math.floor

private class Solution1072 {

    fun solution(
        x: Double,
        y: Double,
    ): Int {
        val defaultZ = calZ(x, y)

        var answer = -1
        var left = 0
        var right = x.toInt()

        while (left <= right) {
            val mid = (left + right) / 2

            when (calZ(x + mid, y + mid)) {
                defaultZ -> left = mid + 1

                else -> {
                    answer = mid
                    right = mid - 1
                }
            }
        }

        return answer
    }

    private fun calZ(
        x: Double,
        y: Double,
    ) = floor(100 * y / x)

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (x, y) = br.readLine().split(" ").map(String::toDouble)

    bw.append(Solution1072().solution(x, y).toString())
    bw.flush()

    br.close()
    bw.close()
}
