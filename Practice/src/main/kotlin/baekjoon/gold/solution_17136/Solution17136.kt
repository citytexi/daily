package baekjoon.gold.solution_17136

private class Solution17136 {
    private var result = Int.MAX_VALUE

    fun solution(map: Array<IntArray>): Int {
        val paperCount = intArrayOf(0, 5, 5, 5, 5, 5)
        result = Int.MAX_VALUE

        dfs(map, paperCount, 0, 0, 0)

        return when (result) {
            Int.MAX_VALUE -> -1
            else -> result
        }
    }

    private fun dfs(
        map: Array<IntArray>,
        paperCount: IntArray,
        row: Int,
        col: Int,
        depth: Int,
    ) {
        if (row >= 9 && col >= 9) {
            val finalDepth = when (map[row][col]) {
                1 -> if (paperCount[1] > 0) {
                    depth + 1
                } else {
                    Int.MAX_VALUE
                }
                else -> depth
            }

            result = minOf(result, finalDepth)
            return
        }

        if (result <= depth) {
            return
        }

        if (col > 9) {
            dfs(
                map = map,
                paperCount = paperCount,
                row = row + 1,
                col = 0,
                depth = depth
            )
            return
        }

        if (map[row][col] == 1) {
            for (i in 5 downTo 1) {
                if (paperCount[i] <= 0) {
                    continue
                }

                if (!isAcceptRange(map, row, col, i)) {
                    continue
                }

                attachPaper(map, row, col, i, 0)
                paperCount[i] -= 1
                dfs(
                    map = map,
                    paperCount = paperCount,
                    row = row,
                    col = col + 1,
                    depth = depth + 1,
                )
                attachPaper(map, row, col, i, 1)
                paperCount[i] += 1
            }
        } else {
            dfs(map, paperCount, row, col + 1, depth)
        }
    }

    private fun attachPaper(
        map: Array<IntArray>,
        row: Int,
        col: Int,
        n: Int,
        num: Int,
    ) {
        for (i in row until   row + n) {
            for (j in col until   col + n) {
                map[i][j] = num
            }
        }
    }

    private fun isAcceptRange(
        map: Array<IntArray>,
        row: Int,
        col: Int,
        n: Int,
    ): Boolean {
        for (i in row until row + n) {
            for (j in col until col + n) {
                val nextValue = map.getOrNull(i)?.getOrNull(j) ?: return false

                if (nextValue != 1) {
                    return false
                }
            }
        }

        return true
    }
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val map = Array(10) { br.readLine().split(" ").map(String::toInt).toIntArray() }

    bw.append(Solution17136().solution(map).toString())
    bw.flush()

    br.close()
    bw.close()
}

