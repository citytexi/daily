package baekjoon.gold.solution_3055

private class Solution3055 {
    private val directions = arrayOf(
        1 to 0,
        -1 to 0,
        0 to 1,
        0 to -1
    )

    fun solution(
        r: Int,
        c: Int,
        sPosition: Pair<Int, Int>,
        waterPositions: ArrayDeque<Pair<Int, Int>>,
        map: Array<CharArray>
    ): String {
        val sb = StringBuilder()

        when (val value = bfs(r, c, sPosition, waterPositions, map)) {
            0 -> sb.append("KAKTUS\n")
            else -> sb.append("${value}\n")
        }

        return sb.toString()
    }

    fun bfs(
        r: Int,
        c: Int,
        start: Pair<Int,Int>,
        waterPositions: ArrayDeque<Pair<Int,Int>>,
        map: Array<CharArray>
    ): Int {
        val visited = Array(r){ BooleanArray(c) }
        val currentPositions = ArrayDeque<Pair<Int,Int>>()

        visited[start.first][start.second] = true

        currentPositions.add(start)

        var result = 0

        while (currentPositions.isNotEmpty()) {
            result += 1

            if (waterPositions.isNotEmpty()) {
                for (i in waterPositions.indices) {
                    val current = waterPositions.removeFirst()

                    for (direction in directions) {
                        val nextRow = current.first + direction.first
                        val nextCol = current.second + direction.second
                        val nextValue = map.getOrNull(nextRow)?.getOrNull(nextCol) ?: continue

                        if (nextValue == '.') {
                            waterPositions.addLast(nextRow to nextCol)
                            map[nextRow][nextCol] = '*'
                        }
                    }
                }
            }

            for (i in currentPositions.indices) {
                val current = currentPositions.removeFirst()

                for (direction in directions) {
                    val nextRow = current.first + direction.first
                    val nextCol = current.second + direction.second
                    val nextValue = map.getOrNull(nextRow)?.getOrNull(nextCol) ?: continue

                    if (visited[nextRow][nextCol]) {
                        continue
                    }

                    when (nextValue) {
                        '.' -> {
                            visited[nextRow][nextCol] = true
                            currentPositions.add(nextRow to nextCol)
                        }
                        'D' -> return result
                    }
                }
            }
        }

        return 0
    }
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (r, c) = br.readLine().split(" ").map(String::toInt)

    var sRow = 0
    var sCol = 0
    val waterPositions = ArrayDeque<Pair<Int, Int>>()
    val map = Array(r) { CharArray(c) }
    for (row in 0 until r) {
        val inputs = br.readLine()
        for (col in 0 until c) {
            when (inputs[col]) {
                'S' -> {
                    sRow = row
                    sCol = col
                }
                '*' -> waterPositions.add(row to col)
            }
            map[row][col] = inputs[col]
        }
    }

    bw.append(Solution3055().solution(r, c, sRow to sCol, waterPositions, map))
    bw.flush()

    br.close()
    bw.close()
}