package baekjoon.silver

/*
문제
어느 날, 미르코는 우연히 길거리에서 양수 N을 보았다. 미르코는 30이란 수를 존경하기 때문에, 그는 길거리에서 찾은 수에 포함된 숫자들을 섞어 30의 배수가 되는 가장 큰 수를 만들고 싶어한다.

미르코를 도와 그가 만들고 싶어하는 수를 계산하는 프로그램을 작성하라.

입력
N을 입력받는다. N는 최대 105개의 숫자로 구성되어 있으며, 0으로 시작하지 않는다.

출력
미르코가 만들고 싶어하는 수가 존재한다면 그 수를 출력하라. 그 수가 존재하지 않는다면, -1을 출력하라.
* */

private class Solution10610 {
    fun solution(n: String): String {
        val sortedList = n.toList().sortedDescending()

        if (!sortedList.contains('0')) {
            return "-1"
        }

        if (sortedList.sumOf { it.digitToInt() } % 3 != 0) {
            return "-1"
        }

        return sortedList.joinToString("")
    }
}

private fun main() {
    val solution10610 = Solution10610()

    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine()

    bw.append("${solution10610.solution(n)}\n")
    bw.flush()

    br.close()
    bw.close()
}