package programmers.lv1

/*
문제 설명
자연수 n을 뒤집어 각 자리 숫자를 원소로 가지는 배열 형태로 리턴해주세요. 예를들어 n이 12345이면 [5,4,3,2,1]을 리턴합니다.

제한 조건
n은 10,000,000,000이하인 자연수입니다.

입출력 예
n	return
12345	[5,4,3,2,1]

* */

private class Solution12932 {
    fun solution(n: Long): IntArray = n.toString().toList().reversed().map { it.digitToInt() }.toIntArray()
}

private fun main() {
    val solution12932 = Solution12932()
    println(solution12932.solution(12345L).toList())
}