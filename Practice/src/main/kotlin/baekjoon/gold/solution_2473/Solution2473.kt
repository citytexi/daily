package baekjoon.gold.solution_2473

import kotlin.math.abs

private class Solution2473 {

    fun solution(
        n: Int,
        waters: LongArray
    ): String {
        val sb = StringBuilder()
        val sortedArray = waters.sortedArray()
        var min = Long.MAX_VALUE

        var minFirst = sortedArray[0]
        var minSecond = sortedArray[1]
        var minThird = sortedArray[2]

        for (i in 0 until n) {
            var left = i + 1
            var right = n - 1

            while (left < right) {
                val current = sortedArray[i] + sortedArray[left] + sortedArray[right]

                if (abs(min) > abs(current)) {
                    min = current
                    minFirst = sortedArray[i]
                    minSecond = sortedArray[left]
                    minThird = sortedArray[right]
                }

                when {
                    current < 0 -> left += 1
                    else -> right -= 1
                }
            }
        }

        sb.append("$minFirst ")
        sb.append("$minSecond ")
        sb.append("$minThird\n")

        return sb.toString()
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    val waters = br.readLine().split(" ").map(String::toLong).toLongArray()

    bw.append(Solution2473().solution(n, waters))
    bw.flush()

    br.close()
    bw.close()
}