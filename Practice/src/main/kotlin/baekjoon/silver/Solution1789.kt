package baekjoon.silver

/*
문제
서로 다른 N개의 자연수의 합이 S라고 한다. S를 알 때, 자연수 N의 최댓값은 얼마일까?

입력
첫째 줄에 자연수 S(1 ≤ S ≤ 4,294,967,295)가 주어진다.

출력
첫째 줄에 자연수 N의 최댓값을 출력한다.
* */

private class Solution1789 {
    fun solution(s: Long): Long {
        var count = 0L
        var number = 0L

        while (number < s) {
            count += 1
            number += count
            if (number + (count + 1) > s) {
                break
            }
        }

        return count
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val s = br.readLine().toLong()
    val solution = Solution1789()

//    solution.solution(200)
//    solution.solution(4_294_967_295)
    bw.append(solution.solution(s).toString())
    bw.flush()

    br.close()
    bw.close()
}