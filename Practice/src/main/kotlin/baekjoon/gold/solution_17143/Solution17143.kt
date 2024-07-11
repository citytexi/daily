package baekjoon.gold.solution_17143

private class Solution17143 {
    private var result = 0

    fun solution(
        r: Int,
        c: Int,
        list: MutableList<Shark>,
        map: Array<IntArray>
    ): Int {
        result = 0
        var currentMap = map

        for (i in 0 until c) {
            fishingShark(i, list, currentMap)
            currentMap = move(r, c, list, currentMap)
        }

        return result
    }

    private fun fishingShark(
        col: Int,
        list: MutableList<Shark>,
        map: Array<IntArray>
    ) {
        for (row in map.indices) {
            if (map[row][col] == -1) {
                continue
            }
            result += list[map[row][col]].z
            map[row][col] = -1
            return
        }
    }

    private fun move(
        r: Int,
        c: Int,
        list: MutableList<Shark>,
        map: Array<IntArray>
    ): Array<IntArray> {
        val newMap = Array(r) { IntArray(c) { -1 } }

        for (row in 0 until r) {
            for (col in 0 until c) {
                moveShark(row, col, r, c, list, map, newMap)
            }
        }

        return newMap
    }

    private fun moveShark(
        row: Int,
        col: Int,
        r: Int,
        c: Int,
        list: MutableList<Shark>,
        map: Array<IntArray>,
        newMap: Array<IntArray>,
    ) {
        val current = map[row][col]

        if (current == -1) {
            return
        }

        val currentShark = list[current]

        val x = if (currentShark.d < 3) {
            r
        } else {
            c
        }

        val moveDistance = currentShark.s % ((x - 1) * 2)
        var currentRow = row
        var currentCol = col

        for (t in 0 until moveDistance) {
            when (currentShark.d) {
                1 -> {
                    currentRow -= 1

                    if (currentRow < 0) {
                        currentShark.d = 2
                        currentRow = 1
                    }
                }

                2 -> {
                    currentRow += 1

                    if (currentRow > r - 1) {
                        currentShark.d = 1
                        currentRow = r - 2
                    }
                }

                3 -> {
                    currentCol += 1

                    if (currentCol > c - 1) {
                        currentShark.d = 4
                        currentCol = c - 2
                    }
                }

                else -> {
                    currentCol -= 1

                    if (currentCol < 0) {
                        currentShark.d = 3
                        currentCol = 1
                    }
                }
            }
        }

        if (newMap[currentRow][currentCol] != -1 && list[newMap[currentRow][currentCol]].z > currentShark.z) {
            return
        }

        newMap[currentRow][currentCol] = current
    }

    data class Shark(
        val s: Int,
        var d: Int,
        val z: Int
    )
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (R, C, M) = br.readLine().split(" ").map(String::toInt)
    val map = Array(R) { IntArray(C) { -1 } }
    val list = mutableListOf<Solution17143.Shark>()

    repeat(M) {
        val (r, c, s, d, z) = br.readLine().split(" ").map(String::toInt)
        list.add(Solution17143.Shark(s, d, z))
        map[r - 1][c - 1] = list.lastIndex
    }

    bw.append("${Solution17143().solution(R, C, list, map)}\n")
    bw.flush()

    br.close()
    bw.close()
}