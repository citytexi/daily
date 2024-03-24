package baekjoon.silver

import kotlin.math.min

/*
문제
수빈이는 동생과 숨바꼭질을 하고 있다.
수빈이는 현재 점 N(0 ≤ N ≤ 100,000)에 있고, 동생은 점 K(0 ≤ K ≤ 100,000)에 있다.
수빈이는 걷거나 순간이동을 할 수 있다.
만약, 수빈이의 위치가 X일 때 걷는다면 1초 후에 X-1 또는 X+1로 이동하게 된다.
순간이동을 하는 경우에는 1초 후에 2*X의 위치로 이동하게 된다.

수빈이와 동생의 위치가 주어졌을 때, 수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇 초 후인지 구하는 프로그램을 작성하시오.

입력
첫 번째 줄에 수빈이가 있는 위치 N과 동생이 있는 위치 K가 주어진다. N과 K는 정수이다.

출력
수빈이가 동생을 찾는 가장 빠른 시간을 출력한다.
* */

private class Solution1697 {
    fun solution(n: Int, k: Int): Int {
        val dp = IntArray(100_001) {
            when (it) {
                in 0 until n -> n - it
                else -> 0
            }
        }

        for (i in n + 1 .. k) {
            val minNum = when (i % 2) {
                0 -> dp[i/2] + 1
                else -> min(dp[(i + 1)/2], dp[(i-1)/2]) + 2
            }
            dp[i] = min(minNum, dp[i - 1] + 1)
        }

        return dp[k]
    }
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val solution1697 = Solution1697()

    val (n, k) = br.readLine().split(" ").map { it.toInt() }
    bw.append("${solution1697.solution(n, k)}\n")
    bw.flush()

    br.close()
    bw.close()
}