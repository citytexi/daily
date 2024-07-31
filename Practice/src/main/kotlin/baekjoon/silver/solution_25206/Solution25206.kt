package baekjoon.silver.solution_25206

private class Solution25206(
    private var total: Double = 0.0,
    private var totalScore: Double = 0.0,
) {
    private val scoreMap = hashMapOf(
        "A+" to 4.5,
        "A0" to 4.0,
        "B+" to 3.5,
        "B0" to 3.0,
        "C+" to 2.5,
        "C0" to 2.0,
        "D+" to 1.5,
        "D0" to 1.0,
        "F" to 0.0,
    )

    fun solution(
        subject: String,
        score: Double,
        grade: String
    ) {
        val realScore = scoreMap.getOrElse(grade) {
            return
        }


        total += realScore * score
        totalScore += score
    }

    fun getResult(): Double = total / totalScore

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val solution25206 = Solution25206()

    repeat(20) {
        val (subject, score, grade) = br.readLine().split(" ")
        solution25206.solution(subject, score.toDouble(), grade)
    }

    bw.append("${solution25206.getResult()}\n")
    bw.flush()

    br.close()
    bw.close()
}