package baekjoon.silver.solution_14501

private class Solution14501 {

    fun solution(
        n: Int,
        tArray: IntArray,
        pArray: IntArray
    ): Int {
        val dp = IntArray(n + 1)

        for (i in 0 until n) {
            if (i + tArray[i] <= n) {
                if (i > 0) {
                    dp[i] = maxOf(dp[i], dp[i - 1])
                }
                dp[i + tArray[i]] = maxOf(dp[i + tArray[i]], dp[i] + pArray[i])
            }
        }

        return dp.max()
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    val tArray = IntArray(n)
    val pArray = IntArray(n)

    repeat(n) {
        val (t, p) = br.readLine().split(" ").map(String::toInt)
        tArray[it] = t
        pArray[it] = p
    }

    bw.append("${Solution14501().solution(n, tArray, pArray)}\n")
    bw.flush()

    br.close()
    bw.close()
}