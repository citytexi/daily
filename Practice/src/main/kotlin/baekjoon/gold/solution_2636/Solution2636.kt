package baekjoon.gold.solution_2636

private class Solution2636 {
    private val directions = arrayOf(
        -1 to 0,
        1 to 0,
        0 to 1,
        0 to -1
    )

    private var result = 0

    fun solution(
        r: Int,
        c: Int,
        map: Array<IntArray>
    ): Pair<Int, Int> {
        result = 0
        val visited = Array(r) { IntArray(c) { -1 } }
        val removeCheeseDeque = ArrayDeque<Pair<Int, Int>>()

        bfs(0, 0, 0, map, visited, removeCheeseDeque)
        removeCheese(map, visited, removeCheeseDeque)

        var count = 0

        for (row in 0 until r) {
            for (col in 0 until c) {
                if (visited[row][col] == result) {
                    count += 1
                }
            }
        }

        return result to count
    }

    private fun bfs(
        row: Int,
        col: Int,
        hour : Int,
        map: Array<IntArray>,
        visited: Array<IntArray>,
        removeCheeseDeque: ArrayDeque<Pair<Int, Int>>
    ) {
        val deque = ArrayDeque<Pair<Int,Int>>().apply { add(row to col) }
        visited[row][col] = 0

        while (deque.isNotEmpty()){
            val first = deque.removeFirst()

            for ((directionRow, directionCol) in directions) {
                val nextRow = first.first + directionRow
                val nextCol = first.second + directionCol

                val nextVisited = visited.getOrNull(nextRow)?.getOrNull(nextCol) ?: continue

                if (nextVisited == hour || nextVisited == hour + 1) {
                    continue
                }

                when (map[nextRow][nextCol]) {
                    1 -> {
                        visited[nextRow][nextCol] = hour + 1
                        removeCheeseDeque.add(nextRow to nextCol)
                    }
                    else -> {
                        visited[nextRow][nextCol] = hour
                        result = maxOf(result, hour)
                        deque.add(nextRow to nextCol)
                    }
                }
            }
        }
    }
    private fun removeCheese(
        map: Array<IntArray>,
        visited: Array<IntArray>,
        removeCheeseDeque: ArrayDeque<Pair<Int, Int>>
    ){
        while (removeCheeseDeque.isNotEmpty()){
            val first = removeCheeseDeque.removeFirst()
            result = maxOf(result, visited[first.first][first.second])

            for ((directionRow, directionCol) in directions) {
                val nextRow = first.first + directionRow
                val nextCol = first.second + directionCol
                val nextVisited = visited.getOrNull(nextRow)?.getOrNull(nextCol) ?: continue

                if (visited[first.first][first.second] == nextVisited) {
                    continue
                }

                if (nextVisited == -1) {
                    when (map[nextRow][nextCol]) {
                        1 -> {
                            visited[nextRow][nextCol] = visited[first.first][first.second] + 1
                            removeCheeseDeque.add(nextRow to nextCol)
                        }
                        0 -> {
                            visited[nextRow][nextCol] = visited[first.first][first.second]
                            bfs(nextRow, nextCol, visited[nextRow][nextCol], map, visited, removeCheeseDeque)
                        }
                    }
                }
            }
        }
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (r, c) = br.readLine().split(" ").map(String::toInt)
    val map = Array(r) { br.readLine().split(" ").map(String::toInt).toIntArray() }

    val result = Solution2636().solution(r, c, map)

    bw.append("${result.first}\n")
    bw.append("${result.second}\n")
    bw.flush()

    br.close()
    bw.close()
}