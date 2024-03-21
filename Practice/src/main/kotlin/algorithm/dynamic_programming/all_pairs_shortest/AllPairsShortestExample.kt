package algorithm.dynamic_programming.all_pairs_shortest

import kotlin.math.min

private class AllPairsShortestExample {
    fun solution(d: Array<IntArray>) {

        for (k in d.indices) {
            for (i in d.indices) {
                if (i == k) {
                    continue
                }
                for (j in d.indices) {
                    if (j == k || j == i) {
                        continue
                    }
//                    println("entry d[$i][$j] = ${d[i][j]}")
                    d[i][j] = min(d[i][j], if (d[i][k] == Int.MAX_VALUE || d[k][j] == Int.MAX_VALUE ) Int.MAX_VALUE else d[i][k] + d[k][j])
//                    println("finish d[$i][$j] = ${d[i][j]}")
                }
            }

            println("k = $k")
            for (i in d.indices) {
                println(d[i].toList())
            }
            println()
        }


    }
}

private fun main() {
    val example = AllPairsShortestExample()

    val d = arrayOf(
        intArrayOf(0, 4, 2, 5, Int.MAX_VALUE),
        intArrayOf(Int.MAX_VALUE, 0, 1, Int.MAX_VALUE, 4),
        intArrayOf(1, 3, 0, 1, 2),
        intArrayOf(-2, Int.MAX_VALUE, Int.MAX_VALUE, 0, 2),
        intArrayOf(Int.MAX_VALUE, -3, 3, 1, 0)
    )

    println(example.solution(d))
}