package algorithm.dynamic_programming.chained_matrix_multiplications

private class ChainedMatrixMultiplicationsExample {
    private lateinit var dp: Array<IntArray>
    fun solution(chainedMatrix: Array<Matrix>): Int {
        dp = Array(chainedMatrix.size) { row ->
            IntArray(chainedMatrix.size) { col ->
                when (col) {
                    row -> 0
                    else -> Int.MAX_VALUE
                }
            }
        }

        for (l in 1 until chainedMatrix.size) {
//            println("L = $l")
            for (i in 0 until  chainedMatrix.size - l) {
                val j = i + l
//                println("i = $i,  j = $j\n")

                for (k in i until j) {
//                    println("k = $k")

                    val prevD = chainedMatrix[i].rows
                    val currentD = chainedMatrix[k].cols
                    val nextD = chainedMatrix[j].cols
//                    println("prevD = $prevD, currentD = $currentD, nextD = $nextD")

                    val temp = dp[i][k] + dp[k + 1][j] + (prevD * currentD * nextD)
//                    println("${dp[i][k]} + ${dp[k + 1][j]} + ${(prevD * currentD * nextD)}\n")

                    if (temp < dp[i][j]) {
//                        println("dp[i][j] = ${dp[i][j]}, temp = $temp \n")
                        dp[i][j] = temp
                    }
                }
            }
//            println()

//            for (i in dp.indices) {
//                println(dp[i].toList())
//            }
//
//            println()
        }

//        for (i in dp.indices) {
//            println(dp[i].toList())
//        }

        return dp[0][chainedMatrix.size - 1]
    }

    data class Matrix(
        val rows: Int,
        val cols: Int
    )
}

private fun main() {
    val example = ChainedMatrixMultiplicationsExample()
    val chainedMatrixArray = arrayOf(
        ChainedMatrixMultiplicationsExample.Matrix(10, 20),
        ChainedMatrixMultiplicationsExample.Matrix(20, 5),
        ChainedMatrixMultiplicationsExample.Matrix(5, 15),
        ChainedMatrixMultiplicationsExample.Matrix(15, 30),
    )

    println(example.solution(chainedMatrixArray))
}