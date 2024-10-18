package baekjoon.silver.solution_17626

import kotlin.math.sqrt

private class Solution17626 {

    fun solution(n: Int): Int {
        val dp = IntArray(50001)
        for (i in 1 until 224) {
            dp[i*i]=1
        }

        for (i in 2 .. n) {
            var key = Int.MAX_VALUE
            val a = sqrt(i.toDouble()).toInt()
            for(k in 1 .. a){
                if (key > dp[k * k] + dp[i - k * k]){
                    key = dp[k * k] + dp[i - k * k]
                }
            }

            if (dp[i] != 1) {
                dp[i] = key
            }
        }

        return dp[n]
    }
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    bw.append(Solution17626().solution(n).toString())
    bw.flush()

    br.close()
    bw.close()
}
