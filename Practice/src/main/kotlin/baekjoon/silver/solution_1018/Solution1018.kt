package baekjoon.silver.solution_1018

import kotlin.math.min

private class Solution1018 {
    private val startPairs = arrayOf(
        1 to 0,
        0 to 1
    )
    fun solution(
        n: Int,
        m: Int,
        map: Array<IntArray>
    ): Int {
        var result = Int.MAX_VALUE

        for (i in 0 until (n - 7)) {
            for (j in 0 until (m - 7)) {
                result = min(result, checkingBoard(i, j, map, startPairs[0]))
                result = min(result, checkingBoard(i, j, map, startPairs[1]))
            }
        }

        return result
    }

    private fun checkingBoard(
        startRow: Int,
        startCol: Int,
        map: Array<IntArray>,
        pair: Pair<Int, Int>
    ): Int {
        var count = 0

        for (row in 0 until 8) {
            for (col in 0 until 8) {
                val current = map[row + startRow][col + startCol]

                if (row % 2 == 0 && col % 2 == 0 && current == pair.first) {
                    continue
                }

                if (row % 2 == 0 && col % 2 != 0 && current == pair.second) {
                    continue
                }

                if (row % 2 != 0 && col % 2 == 0 && current == pair.second) {
                    continue
                }

                if (row % 2 != 0 && col % 2 != 0 && current == pair.first) {
                    continue
                }

                count += 1
            }
        }

        return count
    }


}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (n, m) = br.readLine().split(" ").map(String::toInt)
    val map = Array(n) {
        val str = br.readLine()
        IntArray(m) { col ->
            when (str[col]) {
                'W' -> 1
                else -> 0
            }
        }
    }

    bw.append("${Solution1018().solution(n, m, map)}\n")
    bw.flush()

    br.close()
    bw.close()
}