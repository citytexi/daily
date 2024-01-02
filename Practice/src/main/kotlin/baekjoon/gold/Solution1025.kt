package baekjoon.gold

import kotlin.math.abs
import kotlin.math.sqrt

/*

문제
N행 M열의 표 A가 있고, 표의 각 칸에는 숫자가 하나씩 적혀있다.

연두는 서로 다른 1개 이상의 칸을 선택하려고 하는데, 행의 번호가 선택한 순서대로 등차수열을 이루고 있어야 하고, 열의 번호도 선택한 순서대로 등차수열을 이루고 있어야 한다. 이렇게 선택한 칸에 적힌 수를 순서대로 이어붙이면 정수를 하나 만들 수 있다.

연두가 만들 수 있는 정수 중에서 가장 큰 완전 제곱수를 구해보자. 완전 제곱수란 어떤 정수를 제곱한 수이다.

입력

첫째 줄에 N, M이 주어진다. 둘째 줄부터 N개의 줄에는 표에 적힌 숫자가 1번 행부터 N번 행까지 순서대로 한 줄에 한 행씩 주어진다. 한 행에 적힌 숫자는 1번 열부터 M번 열까지 순서대로 주어지고, 공백없이 모두 붙여져 있다.

출력

첫째 줄에 연두가 만들 수 있는 가장 큰 완전 제곱수를 출력한다. 만약, 완전 제곱수를 만들 수 없는 경우에는 -1을 출력한다.

* */

private class Solution1025(
    private val n: Int,
    private val m: Int,
    private val input: List<List<Int>>
) {

    fun solution() {
        var result = -1

        for (row in 0 until n) {
            // 열 반복
            for (col in 0 until m) {
                // 행 반복
                for (minusRow in -n until n) {
                    for (minusCol in -m until m) {
                        if (minusRow == 0 && minusCol == 0) {
                            continue
                        }

                        var t = 0
                        var i = row
                        var j = col

                        while (i in 0 until n && j in 0 until m) {
                            t = 10 * t + input[i][j]

                            if (abs(sqrt(t.toDouble()) - sqrt(t.toDouble()).toInt()) < EPSILON) {
                                result = result.coerceAtLeast(t)
                            }

                            i += minusRow
                            j += minusCol
                        }

                    }
                }
            }
        }

        println(result)
    }

    companion object {
        private const val EPSILON = 0.00000001
    }
}

private fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val list = MutableList(n) { MutableList(m) { 0 } }

    repeat(n) {
        readLine()?.let { line ->
            for (i in 0 until m) {
                list[it][i] = line[i].code - 48
            }
        }
    }

    Solution1025(
        n = n,
        m = m,
        input = list.toList()
    ).solution()
}