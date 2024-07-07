package baekjoon.gold.solution_6987

import java.util.StringTokenizer

private class Solution6987 {
    val teamSize = 6
    private val maxMatchCount = 15
    private val matches = Array(maxMatchCount) { IntArray(2) }

    init {
        var index = 0

        for (i in 0 until teamSize - 1) {
            for (j in i + 1 until teamSize) {
                matches[index][0] = i
                matches[index][1] = j
                index++
            }
        }
    }

    fun solution(matchResult: Array<IntArray>): String {
        val result = booleanArrayOf(false)

        backTracking(0, matchResult, result)

        return when (result[0]) {
            true -> "1 "
            false -> "0 "
        }
    }

    private fun backTracking(
        depth: Int,
        matchResult: Array<IntArray>,
        result: BooleanArray
    ) {
        if (result[0]) {
            // 이미 앞에서 가능한 경우인지 확인하였을 때
            return
        }

        if (depth == maxMatchCount) {
            result[0] = true
            return
        }

        val firstTeam = matches[depth][0]
        val secondTeam = matches[depth][1]

        // firstTeam = 승, secondTeam = 패
        if (matchResult[firstTeam][0] > 0 && matchResult[secondTeam][2] > 0) {
            matchResult[firstTeam][0] -= 1
            matchResult[secondTeam][2] -= 1
            backTracking(depth + 1, matchResult, result)
            matchResult[firstTeam][0] += 1
            matchResult[secondTeam][2] += 1
        }

        // firstTeam = 무, secondTeam = 무
        if (matchResult[firstTeam][1] > 0 && matchResult[secondTeam][1] > 0) {
            matchResult[firstTeam][1] -= 1
            matchResult[secondTeam][1] -= 1
            backTracking(depth + 1, matchResult, result)
            matchResult[firstTeam][1] += 1
            matchResult[secondTeam][1] += 1
        }

        // firstTeam = 패, secondTeam = 승
        if (matchResult[firstTeam][2] > 0 && matchResult[secondTeam][0] > 0) {
            matchResult[firstTeam][2] -= 1
            matchResult[secondTeam][0] -= 1
            backTracking(depth + 1, matchResult, result)
            matchResult[firstTeam][2] += 1
            matchResult[secondTeam][0] += 1
        }
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val solution6987 = Solution6987()

    val testCaseCount = 4

    var st: StringTokenizer

    for (tc in 0 until testCaseCount) {
        st = StringTokenizer(br.readLine())
        val matchResult = Array(solution6987.teamSize) { IntArray(3) }
        var isAccept = true

        for (row in matchResult.indices) {
            val win = st.nextToken().toInt()
            val draw = st.nextToken().toInt()
            val lose = st.nextToken().toInt()

            matchResult[row][0] = win
            matchResult[row][1] = draw
            matchResult[row][2] = lose

            if (win + draw + lose != 5) {
                isAccept = false
                break
            }
        }

        if (isAccept) {
            bw.append(solution6987.solution(matchResult))
        } else {
            bw.append("0 ")
        }
    }

    bw.flush()

    br.close()
    bw.close()
}