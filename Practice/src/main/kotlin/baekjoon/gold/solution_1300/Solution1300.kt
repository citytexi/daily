package baekjoon.gold.solution_1300

import java.util.StringTokenizer

private class Solution1300 {

    fun solution(
        n: Long,
        k: Long
    ): Long {
        var left = 1L
        var right = k

        while (left <= right) {
            val mid = (left + right) / 2
            var count = 0L

            for (i in 1L .. n) {
                count += minOf(mid / i, n)
            }

            when {
                count < k -> left = mid + 1
                else -> right = mid - 1
            }
        }

        return left
    }
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = StringTokenizer(br.readLine()).nextToken().toLong()
    val k = StringTokenizer(br.readLine()).nextToken().toLong()

    val sb = StringBuilder()
    sb.append(Solution1300().solution(n, k))
    sb.append("\n")

    bw.append(sb.toString())
    bw.flush()

    br.close()
    bw.close()
}