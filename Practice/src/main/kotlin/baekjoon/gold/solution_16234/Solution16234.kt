package baekjoon.gold.solution_16234

import kotlin.math.abs

private class Solution16234 {
    private val directions = arrayOf(
        1 to 0,
        -1 to 0,
        0 to 1,
        0 to -1
    )
    fun solution(
        n: Int,
        l: Int,
        r: Int,
        map: Array<IntArray>
    ): Int {

        val range = l .. r
        var result = 0
        var currentMap = map

        while (true) {
            val newMap = Array(currentMap.size) { row -> IntArray(currentMap.size) { col -> currentMap[row][col] } }
            val visited = Array(map.size) { BooleanArray(map.size) }

            var isActive = false
            for (row in map.indices) {
                for (col in map[row].indices) {
                    val union = mutableListOf<Pair<Int, Int>>()
                    dfs(row, col, range, union, currentMap, visited)
                    if (union.size > 1) {
                        var sum = 0
                        for ((newRow, newCol) in union) {
                            sum += currentMap[newRow][newCol]
                        }
                        sum /= union.size

                        for ((newRow, newCol) in union) {
                            newMap[newRow][newCol] = sum
                        }
                        isActive = true
                    }
                }
            }

            currentMap = newMap
            if (isActive) {
                result += 1
            } else {
                break
            }
        }

        return result
    }

    private fun dfs(
        row: Int,
        col: Int,
        range: IntRange,
        union: MutableList<Pair<Int, Int>>,
        map: Array<IntArray>,
        visited: Array<BooleanArray>
    ) {
        if (!visited[row][col]) {
            visited[row][col] = true
            union.add(row to col)

            for (direction in directions) {
                val nextRow = row + direction.first
                val nextCol = col + direction.second
                val nextValue = map.getOrNull(nextRow)?.getOrNull(nextCol) ?: continue

                if (!visited[nextRow][nextCol] && abs(map[row][col] - nextValue) in range) {
                    dfs(nextRow, nextCol, range, union, map, visited)
                }
            }
        }
    }
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (n, l, r) = br.readLine().split(" ").map(String::toInt)
    val map = Array(n) {
        val str = br.readLine().split(" ")
        IntArray(n) { col -> str[col].toInt() }
    }

    bw.append("${Solution16234().solution(n, l, r, map)}\n")
    bw.flush()

    br.close()
    bw.close()
}