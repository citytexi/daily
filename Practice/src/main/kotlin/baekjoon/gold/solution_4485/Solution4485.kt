package baekjoon.gold.solution_4485

import java.util.PriorityQueue

private class Solution4485(
    private var count: Int = 1
) {
    private val directions = arrayOf(
        -1 to 0,
        1 to 0,
        0 to -1,
        0 to 1
    )

    fun solution(
        n: Int,
        map: Array<IntArray>
    ): String {
        val distance = Array(n) { IntArray(n) { Int.MAX_VALUE } }.apply { this[0][0] = map[0][0] }
        val pq = PriorityQueue<Triple<Int, Int, Int>>(compareBy { it.third }).apply { add(Triple(0, 0, 0)) }

        while (!pq.isEmpty()) {
            val current = pq.poll()
            val currentDistance = distance[current.first][current.second]

            for (direction in directions) {
                val nextRow = current.first + direction.first
                val nextCol = current.second + direction.second
                val nextDistance = distance.getOrNull(nextRow)?.getOrNull(nextCol) ?: continue

                if (nextDistance <= currentDistance + map[nextRow][nextCol]) {
                    continue
                }
                distance[nextRow][nextCol] = currentDistance + map[nextRow][nextCol]
                pq.add(Triple(nextRow, nextCol, distance[nextRow][nextCol]))
            }
        }


        return "Problem ${count++}: ${distance[n - 1][n - 1]}\n"
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val solution4485 = Solution4485()
    var n = br.readLine().toInt()

    while (n != 0) {
        val map = Array(n) { br.readLine().split(" ").map(String::toInt).toIntArray() }
        bw.append(solution4485.solution(n, map))
        n = br.readLine().toInt()
    }
    bw.flush()

    br.close()
    bw.close()
}