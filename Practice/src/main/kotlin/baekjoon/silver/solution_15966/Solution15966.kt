package baekjoon.silver.solution_15966

private class Solution15966 {

    fun solution(
        n: Int,
        arr: IntArray,
    ): Int {
        val dp = IntArray(1_000_001)

        var result = 0
        for (i in arr.indices) {
            dp[arr[i]] = maxOf(dp[arr[i]], dp[arr[i] - 1] + 1)
            result = maxOf(result, dp[arr[i]])
        }

        return result
    }
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    val arr = br.readLine().split(" ").map(String::toInt).toIntArray()

    bw.append(Solution15966().solution(n, arr).toString())
    bw.flush()

    br.close()
    bw.close()
}