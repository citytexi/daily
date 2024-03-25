package algorithm.dynamic_programming.knapsack

import kotlin.math.max

private class KnapsackExample {
    private lateinit var dp: Array<IntArray>
    fun solution(c: Int, list: List<KnapsackObject>) {
        dp = Array(list.size + 1) { IntArray(c + 1) }

        knapsack(c, list)

        for (i in dp.indices) {
            println(dp[i].toList())
        }

        println()
    }

    private fun knapsack(c: Int, list: List<KnapsackObject>) {
        for (i in 1 .. list.size) {
            val item = list[i - 1]
            for (w in 1 .. c) {
                if (item.w > w) {
                    // 물건 i의 무게가 임시 배낭 용량을 초과하면
                    dp[i][w] = dp[i - 1][w]
                } else {
//                    println("dp[i][w] = ${dp[i][w]}")
//                    println("dp[i][w - item.w] + item.v = ${dp[i - 1][w - item.w]} + ${item.v} = ${dp[i - 1][w - item.w] + item.v}")
//                    println()
                    dp[i][w] = max(dp[i - 1][w], dp[i - 1][w - item.w] + item.v)
                }
            }

            for (t in dp.indices) {
                println(dp[t].toList())
            }

            println()
        }
    }

    data class KnapsackObject(
        val i: Int,
        val w: Int,
        val v: Int
    )
}

private fun main() {
    val example = KnapsackExample()

    val c = 10
    val list = listOf(
        KnapsackExample.KnapsackObject(
            i = 1,
            w = 5,
            v = 10,
        ),
        KnapsackExample.KnapsackObject(
            i = 2,
            w = 4,
            v = 40,
        ),
        KnapsackExample.KnapsackObject(
            i = 3,
            w = 6,
            v = 30,
        ),
        KnapsackExample.KnapsackObject(
            i = 4,
            w = 3,
            v = 50,
        )
    )

    example.solution(c, list)
}