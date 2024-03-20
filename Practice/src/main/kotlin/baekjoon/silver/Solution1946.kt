package baekjoon.silver

/*
문제
언제나 최고만을 지향하는 굴지의 대기업 진영 주식회사가 신규 사원 채용을 실시한다. 인재 선발 시험은 1차 서류심사와 2차 면접시험으로 이루어진다. 최고만을 지향한다는 기업의 이념에 따라 그들은 최고의 인재들만을 사원으로 선발하고 싶어 한다.

그래서 진영 주식회사는, 다른 모든 지원자와 비교했을 때 서류심사 성적과 면접시험 성적 중 적어도 하나가 다른 지원자보다 떨어지지 않는 자만 선발한다는 원칙을 세웠다. 즉, 어떤 지원자 A의 성적이 다른 어떤 지원자 B의 성적에 비해 서류 심사 결과와 면접 성적이 모두 떨어진다면 A는 결코 선발되지 않는다.

이러한 조건을 만족시키면서, 진영 주식회사가 이번 신규 사원 채용에서 선발할 수 있는 신입사원의 최대 인원수를 구하는 프로그램을 작성하시오.

입력
첫째 줄에는 테스트 케이스의 개수 T(1 ≤ T ≤ 20)가 주어진다. 각 테스트 케이스의 첫째 줄에 지원자의 숫자 N(1 ≤ N ≤ 100,000)이 주어진다. 둘째 줄부터 N개 줄에는 각각의 지원자의 서류심사 성적, 면접 성적의 순위가 공백을 사이에 두고 한 줄에 주어진다. 두 성적 순위는 모두 1위부터 N위까지 동석차 없이 결정된다고 가정한다.

출력
각 테스트 케이스에 대해서 진영 주식회사가 선발할 수 있는 신입사원의 최대 인원수를 한 줄에 하나씩 출력한다.
* */

private class Solution1946 {

    fun solution(n: Int, submitData: Array<SubmitData>): Int {
        val sortedList = submitData.sortedBy { it.documentGrade }
        val deque = ArrayDeque<SubmitData>()
        deque.addAll(sortedList)

        var maxRank = 0

        for (i in 0 until  n) {
            val first = deque.removeFirst()
            when (maxRank) {
                0 -> {
                    maxRank = first.interviewGrade
                    deque.add(first)
                }
                else -> {
                    if (maxRank > first.interviewGrade) {
                        maxRank = first.interviewGrade
                        deque.add(first)
                    }
                }
            }
        }

        return deque.size
    }

    data class SubmitData(
        val documentGrade: Int,
        val interviewGrade: Int
    )
}

private fun main() {
    val solution1946 = Solution1946()

    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val t = br.readLine().toInt()

    repeat(t) {
        val n = br.readLine().toInt()
        val array = Array(n) {
            val (documentGrade, interviewGrade) = br.readLine().split(" ").map { it.toInt() }
            Solution1946.SubmitData(documentGrade, interviewGrade)
        }

        bw.append("${solution1946.solution(n, array)}\n")
        bw.flush()
    }

//    solution1946.solution(
//        n = 5,
//        submitData = arrayOf(
//            Solution1946.SubmitData(3, 2),
//            Solution1946.SubmitData(1, 4),
//            Solution1946.SubmitData(4, 1),
//            Solution1946.SubmitData(2, 3),
//            Solution1946.SubmitData(5, 5),
//        )
//    )
//    solution1946.solution(
//        n = 7,
//        submitData = arrayOf(
//            Solution1946.SubmitData(3, 6),
//            Solution1946.SubmitData(7, 3),
//            Solution1946.SubmitData(4, 2),
//            Solution1946.SubmitData(1, 4),
//            Solution1946.SubmitData(5, 7),
//            Solution1946.SubmitData(2, 5),
//            Solution1946.SubmitData(6, 1),
//        )
//    )

    br.close()
    bw.close()
}