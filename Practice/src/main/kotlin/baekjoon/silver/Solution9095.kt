package baekjoon.silver

/*
문제
정수 4를 1, 2, 3의 합으로 나타내는 방법은 총 7가지가 있다. 합을 나타낼 때는 수를 1개 이상 사용해야 한다.

1+1+1+1
1+1+2
1+2+1
2+1+1
2+2
1+3
3+1
정수 n이 주어졌을 때, n을 1, 2, 3의 합으로 나타내는 방법의 수를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 테스트 케이스의 개수 T가 주어진다. 각 테스트 케이스는 한 줄로 이루어져 있고, 정수 n이 주어진다. n은 양수이며 11보다 작다.

출력
각 테스트 케이스마다, n을 1, 2, 3의 합으로 나타내는 방법의 수를 출력한다.
* */

private class Solution9095 {
    fun solution(n: Int): Int {
        /*
        * n = 1 1    1
        * n = 2 2    1 + 1, 2
        * n = 3 4    1 + 1 + 1 2 + 1 1 + 2 3
        * n = 4 7
        * */

        val dp = IntArray(n + 1) {
            when (it) {
                1 -> 1
                2 -> 2
                3 -> 4
                else -> 0
            }
        }

        for (i in 4 .. n) {
            dp[i] = dp[i - 3] + dp[i - 2] + dp[i - 1]
        }

        return dp[n]
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val solution9095 = Solution9095()
    val t = br.readLine().toInt()
    repeat(t) {
        bw.append("${solution9095.solution(br.readLine().toInt())}\n")
        bw.flush()
    }

    br.close()
    bw.close()
}