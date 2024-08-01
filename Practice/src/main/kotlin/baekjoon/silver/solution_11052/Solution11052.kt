package baekjoon.silver.solution_11052

private class Solution11052 {

    fun solution(
        n: Int,
        p: IntArray
    ): Int {
        val dp = IntArray(n + 1)

        for (i in 1.. n) {
            for (j in 1 .. i) {
                dp[i] = maxOf(dp[i], dp[i - j] + p[j - 1])
            }
        }

        return dp[n]
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    val p = br.readLine().split(" ").map(String::toInt).toIntArray()

    bw.append("${Solution11052().solution(n, p)}\n")
    bw.flush()

    br.close()
    bw.close()
}