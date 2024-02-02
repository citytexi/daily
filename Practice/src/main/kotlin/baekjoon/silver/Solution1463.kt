package baekjoon.silver

import kotlin.math.*

/*
문제
정수 X에 사용할 수 있는 연산은 다음과 같이 세 가지 이다.

X가 3으로 나누어 떨어지면, 3으로 나눈다.
X가 2로 나누어 떨어지면, 2로 나눈다.
1을 뺀다.
정수 N이 주어졌을 때, 위와 같은 연산 세 개를 적절히 사용해서 1을 만들려고 한다. 연산을 사용하는 횟수의 최솟값을 출력하시오.

입력
첫째 줄에 1보다 크거나 같고, 106보다 작거나 같은 정수 N이 주어진다.

출력
첫째 줄에 연산을 하는 횟수의 최솟값을 출력한다.

2

1

10

3

* */

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    when (val x = br.readLine().toInt()) {
        1 -> bw.append("0\n")
        in 2 .. 3 -> bw.append("1\n")
        else -> {
            val dp = IntArray(x)

            dp[0] = 1
            dp[1] = 1
            dp[2] = 1

            for (i in 3 until  x) {
                dp[i] = dp[i - 1] + 1
                // dp[3] = dp[2] + 1
                if ((i + 1) % 2 == 0) {
                    dp[i] = min(dp[i], dp[i / 2] + 1)
                }
                if ((i + 1) % 3 == 0) {
                    dp[i] = min(dp[i], dp[i / 3] + 1)
                }
            }

            bw.append("${dp[x - 1]}\n")
        }
    }
    bw.flush()

    br.close()
    bw.close()
}