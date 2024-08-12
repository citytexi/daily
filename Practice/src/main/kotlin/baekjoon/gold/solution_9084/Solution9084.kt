package baekjoon.gold.solution_9084

private class Solution9084 {

    fun solution(
        n: Int,
        arr: IntArray,
        m: Int
    ): Int {
        val dp = Array(arr.size) { IntArray(m + 1) { if (it == 0) 1 else 0 } }

        for (i in arr.indices) {
            val coin = arr[i]

            for (j in 1 .. m) {
                if (i - 1 >= 0) {
                    dp[i][j] += dp[i-1][j]
                }
                if (j - coin >= 0) {
                    dp[i][j] += dp[i][j - coin]
                }
            }
        }

        return dp[arr.lastIndex][m]
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val t = br.readLine().toInt()
    repeat(t) {
        val n = br.readLine().toInt()
        val arr = br.readLine().split(" ").map(String::toInt).toIntArray()
        val m = br.readLine().toInt()
        bw.append("${Solution9084().solution(n, arr, m)}\n")
    }
    bw.flush()

    br.close()
    bw.close()
}