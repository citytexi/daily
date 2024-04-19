package baekjoon.gold

private class Solution3190 {
    private lateinit var map: Array<IntArray>
    private val directions = arrayOf(
        0 to 1, // 오른
        1 to 0, // 아래
        0 to -1, // 왼
        -1 to 0 // 위
    )

    fun solution(
        n: Int,
        applePositions: Array<Pair<Int, Int>>,
        commends: ArrayDeque<Pair<Int, String>>
    ): Int {
        map = Array(n + 2) { row ->
            IntArray(n + 2) { col ->
                if (row == 0 || row == n + 1 || col == 0 || col == n + 1) {
                    1
                } else {
                    if (row == 1 && col == 1) {
                        3
                    } else {
                        0
                    }
                }
            }
        }
        applePositions.forEach { map[it.first][it.second] = 2 }

        val positions = ArrayDeque<Pair<Int, Int>>().apply {
            add(1 to 1)
        }
        var currentDirectionIndex = 0
        var time = 0

        while (true) {
            time += 1
            val (headRow, headCol) = positions.first()
            val nextRow = headRow + directions[currentDirectionIndex].first
            val nextCol = headCol + directions[currentDirectionIndex].second

            when (map[nextRow][nextCol]) {
                1, 3 -> break
                0, 2 -> {
                    if (map[nextRow][nextCol] == 0) {
                        val (lastRow, lastCol) = positions.removeLast()
                        map[lastRow][lastCol] = 0
                    }
                    map[nextRow][nextCol] = 3
                    positions.addFirst(nextRow to nextCol)
                }
            }

            commends.firstOrNull()?.let { (commendTime, commend) ->
                if (commendTime == time) {
                    when (commend) {
                        "D" -> currentDirectionIndex = (currentDirectionIndex + 1) % 4
                        "L" -> when (currentDirectionIndex) {
                            0 -> currentDirectionIndex = 3
                            else -> currentDirectionIndex -= 1
                        }
                    }
                    commends.removeFirst()
                }
            }
        }

        return time
    }
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    val k = br.readLine().toInt()
    val applePositions = Array(k) {
        val (row, col) = br.readLine().split(" ").map(String::toInt)

        row to col
    }
    val l = br.readLine().toInt()
    val commends = ArrayDeque<Pair<Int, String>>()
    repeat(l) {
        val (time, commend) = br.readLine().split(" ")

        commends.add(time.toInt() to commend)
    }

    bw.append("${Solution3190().solution(n, applePositions, commends)}\n")
    bw.flush()

    br.close()
    bw.close()
}