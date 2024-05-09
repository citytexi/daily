package baekjoon.gold.solution_16236

private class Solution16236 {
    private val directions = arrayOf(
        0 to 1,
        1 to 0,
        0 to -1,
        -1 to 0
    )

    fun solution(
        n: Int,
        map: Array<IntArray>,
        shark: Shark
    ): Int {
        var currentShark = shark
        var count = 0

        while(true){
            val visited = Array(n){ BooleanArray(n) }
            val next = bfs(n, currentShark, map, visited)

            if (next.row == currentShark.row && next.col == currentShark.col) {
                break
            } else {
                val time = currentShark.time
                currentShark = next
                currentShark.time += time
                map[currentShark.row][currentShark.col] = 0
                count += 1
            }

            if (count == currentShark.size) {
                currentShark.size += 1
                count = 0
            }

        }

        return currentShark.time
    }

    private fun bfs(
        n: Int,
        shark: Shark,
        map: Array<IntArray>,
        visited : Array<BooleanArray>
    ): Shark {
        val deque = ArrayDeque<Pair<Int,Int>>()
        visited[shark.row][shark.col] = true
        deque.add(shark.row to shark.col)

        var fishRow = Int.MAX_VALUE
        var fishCol = Int.MAX_VALUE
        var distance = 0

        while (deque.isNotEmpty()) {
            val size = deque.size
            distance += 1

            for (i in 0 until size) {
                val first = deque.removeFirst()

                for (direction in directions) {
                    val nextRow = first.first + direction.first
                    val nextCol = first.second + direction.second

                    if (nextRow !in 0 until n) {
                        continue
                    }

                    if (nextCol !in 0 until n) {
                        continue
                    }

                    if (visited[nextRow][nextCol]) {
                        continue
                    }

                    if (map[nextRow][nextCol] <= shark.size) {

                        if (map[nextRow][nextCol] in 1 until shark.size) {
                            when {
                                fishRow > nextRow -> {
                                    fishRow = nextRow
                                    fishCol = nextCol
                                }
                                fishRow == nextRow -> if (fishCol > nextCol) {
                                    fishCol = nextCol
                                }
                            }
                        }

                        visited[nextRow][nextCol] = true
                        deque.add(nextRow to nextCol)
                    }
                }
            }

            if (fishRow != Int.MAX_VALUE){
                return Shark(fishRow, fishCol, size = shark.size, distance)
            }
        }

        return shark
    }

    data class Shark(
        val row: Int,
        val col: Int,
        var size: Int = 2,
        var time: Int = 0
    )
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    var shark: Solution16236.Shark = Solution16236.Shark(0, 0)
    val map = Array(n) { row ->
        val nums = br.readLine().split(" ").map(String::toInt)
        IntArray(n) { col ->
            if (nums[col] == 9) {
                shark = Solution16236.Shark(row, col)
                0
            } else {
                nums[col]
            }
        }
    }

    bw.append("${Solution16236().solution(n, map, shark)}\n")
    bw.flush()

    br.close()
    bw.close()
}