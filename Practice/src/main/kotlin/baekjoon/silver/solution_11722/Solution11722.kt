package baekjoon.silver.solution_11722

private class Solution11722 {

    fun solution(
        n: Int,
        a: IntArray
    ): Int {

        val dp = IntArray(n){1}
        for (i in 1 until a.size) {
            for (j in 0 until i) {
                if (a[j] > a[i]) {
                    dp[i] = maxOf(dp[i], dp[j] + 1)
                }
            }
        }

        return dp.max()
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    val a = br.readLine().split(" ").map(String::toInt).toIntArray()

    bw.append("${Solution11722().solution(n, a)}")
    bw.flush()

    br.close()
    bw.close()
}