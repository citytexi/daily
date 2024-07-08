package baekjoon.silver.solution_1074

import kotlin.math.pow

private class Solution1074 {
    private var count: Int = 0

    fun solution(
        n: Int,
        row: Int,
        col: Int,
    ): Int {
        count = 0

        val size = 2.0.pow(n).toInt()

        solve(size, row, col)

        return count
    }

    private fun solve(
        size: Int,
        row: Int,
        col: Int,
    ) {
        if (size == 1) {
            return
        }

        when {
            row < size/2 && col < size/2 -> solve(size/2, row, col)

            row < size/2 && col >= size/2 -> {
                count += size * size / 4;
                solve(size/2, row, col - size/2)
            }

            row >= size/2 && col < size/2 -> {
                count += (size * size / 4) * 2;
                solve(size/2, row - size/2, col)
            }

            else -> {
                count += (size * size / 4) * 3;
                solve(size/2, row - size/2, col - size/2)
            }

        }
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (n, row, col) = br.readLine().split(" ").map(String::toInt)

    bw.append("${Solution1074().solution(n, row, col)}\n")
    bw.flush()

    br.close()
    bw.close()
}