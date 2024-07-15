package baekjoon.gold.solution_15486

private class Solution15486 {

    fun solution(
        n: Int,
        arr: Array<Pair<Int, Int>>
    ): Int {
        val dp = IntArray(n + 1)

        for (i in 0 until n) {
            if (1 <= i) {
                dp[i] = maxOf(dp[i], dp[i - 1])
            }

            val (currentT, currentP) = arr[i]
            val day = i + currentT

            if (day in 1..n) {
                dp[day] = maxOf(dp[day], dp[i] + currentP)
            }
        }

        return maxOf(dp[n - 1], dp[n])
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    val arr = Array(n) {
        val (t, p) = br.readLine().split(" ").map(String::toInt)
        t to p
    }

    bw.append("${Solution15486().solution(n, arr)}\n")
    bw.flush()

    br.close()
    bw.close()
}