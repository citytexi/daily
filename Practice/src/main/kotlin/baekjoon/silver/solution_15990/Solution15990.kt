package baekjoon.silver.solution_15990

private class Solution15990 {
    private val dp = Array<IntArray>(4) { IntArray(100001) }
        .apply {
            this[1][1]=1
            this[2][2]=1
            this[3][3]=1
        }

    init {
        for (i in 1..100000) {
            for (j in 1..3) {
                if (i - j < 0) {
                    continue
                }

                for (k in 1..3) {
                    if(j!=k){
                        dp[j][i] = (dp[j][i] + dp[k][i - j]) % DIVIDE_NUM
                    }
                }
            }
        }
    }

    fun solution(n: Int): Int = ((dp[1][n] + dp[2][n]) % DIVIDE_NUM + dp[3][n]) % DIVIDE_NUM

    companion object {
        private const val DIVIDE_NUM = 1_000_000_009
    }
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val t = br.readLine().toInt()
    val solution15990 = Solution15990()

    repeat(t) {
        val n = br.readLine().toInt()
        bw.append("${solution15990.solution(n)}\n")
    }
    bw.flush()

    br.close()
    bw.close()
}
