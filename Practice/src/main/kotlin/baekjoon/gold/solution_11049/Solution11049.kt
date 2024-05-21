package baekjoon.gold.solution_11049

private class Solution11049 {

    fun solution(matrixArray: Array<Matrix>): Int {
        val dp = Array(matrixArray.size) { row ->
            IntArray(matrixArray.size) { col ->
                when (col) {
                    row -> 0
                    else -> Int.MAX_VALUE
                }
            }
        }

        for (l in 1 until matrixArray.size) {
            for (i in 0 until  matrixArray.size - l) {
                val j = i + l

                for (k in i until j) {
                    val temp = dp[i][k] + dp[k + 1][j] + (matrixArray[i].row * matrixArray[k].col * matrixArray[j].col)

                    if (temp < dp[i][j]) {
                        dp[i][j] = temp
                    }
                }
            }
        }

        return dp[0][matrixArray.size - 1]
    }

    data class Matrix(
        val row: Int,
        val col: Int
    )
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    val matrixArray = Array(n) {
        val (row, col) = br.readLine().split(" ").map(String::toInt)

        Solution11049.Matrix(row, col)
    }

    bw.append("${Solution11049().solution(matrixArray)}\n")
    bw.flush()

    br.close()
    bw.close()
}