package baekjoon.silver

/*
문제
정수 A를 B로 바꾸려고 한다. 가능한 연산은 다음과 같은 두 가지이다.

2를 곱한다.
1을 수의 가장 오른쪽에 추가한다.
A를 B로 바꾸는데 필요한 연산의 최솟값을 구해보자.

입력
첫째 줄에 A, B (1 ≤ A < B ≤ 10^9)가 주어진다.

출력
A를 B로 바꾸는데 필요한 연산의 최솟값에 1을 더한 값을 출력한다. 만들 수 없는 경우에는 -1을 출력한다.
* */

private class Solution16953 {

    fun solution(a: Int, b: Int): Int {
        var result = 0
        var currentValue = b

        while (a != currentValue) {
            if (a > currentValue) {
                return -1
            }

            currentValue = if (currentValue % 10 == 1) {
                currentValue / 10
            } else if (currentValue % 2 == 0) {
                currentValue / 2
            } else {
                return -1
            }

            result += 1
        }

        return result + 1
    }
}

private fun main() {
    val solution16953 = Solution16953()

    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (a, b) = br.readLine().split(" ").map { it.toInt() }

    bw.append("${solution16953.solution(a, b)}\n")
    bw.flush()

    br.close()
    bw.close()
}