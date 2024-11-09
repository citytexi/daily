package baekjoon.gold.solution_16933

private class Solution16933 {
    private val directions = arrayOf(
        -1 to 0,
        1 to 0,
        0 to -1,
        0 to 1,
    )

    fun solution(
        n: Int,
        m: Int,
        k: Int,
        map: Array<IntArray>
    ): Int {
        val deque = ArrayDeque<Node>().apply {
            add(
                Node(
                    row = 0,
                    col = 0,
                    isNight = false,
                    brokenCount = 0,
                    distance = 1
                )
            )
        }

        val brokenMap = Array(n) { IntArray(m) { Int.MAX_VALUE } }.apply {
            this[0][0] = 0
        }

        while (deque.isNotEmpty()) {
            val current = deque.removeFirst()

            if (current.row == n - 1 && current.col == m - 1) {
                return current.distance
            }

            for (direction in directions) {
                val nextRow = current.row + direction.first
                val nextCol = current.col + direction.second
                val nextValue = map.getOrNull(nextRow)?.getOrNull(nextCol) ?: continue

                if (brokenMap[nextRow][nextCol] <= current.brokenCount) {
                    continue
                }

                if (nextValue == 1) {
                    if (current.brokenCount >= k) {
                        continue
                    }

                    if (!current.isNight) {
                        brokenMap[nextRow][nextCol] = current.brokenCount + 1
                        deque.add(
                            Node(
                                row = nextRow,
                                col = nextCol,
                                isNight = true,
                                brokenCount = current.brokenCount + 1,
                                distance = current.distance + 1,
                            )
                        )
                    } else {
                        deque.add(
                            Node(
                                row = current.row,
                                col = current.col,
                                isNight = false,
                                brokenCount = current.brokenCount,
                                distance = current.distance + 1,
                            )
                        )
                    }
                } else {
                    brokenMap[nextRow][nextCol] = current.brokenCount
                    deque.add(
                        Node(
                            row = nextRow,
                            col = nextCol,
                            isNight = !current.isNight,
                            brokenCount = current.brokenCount,
                            distance = current.distance + 1,
                        )
                    )
                }
            }
        }

        return -1
    }

    private class Node(
        val row: Int,
        val col: Int,
        val isNight: Boolean,
        val brokenCount: Int,
        val distance: Int
    )
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (n, m, k) = br.readLine().split(" ").map(String::toInt)
    val map = Array(n) {
        val inputs = br.readLine()
        IntArray(m) { col -> inputs[col].digitToInt() }
    }

    bw.append(Solution16933().solution(n, m, k, map).toString())
    bw.flush()

    br.close()
    bw.close()
}