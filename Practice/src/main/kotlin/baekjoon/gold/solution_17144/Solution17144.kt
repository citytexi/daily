package baekjoon.gold.solution_17144

private class Solution17144 {
    private val firstDirections = arrayOf(
        0 to 1,
        -1 to 0,
        0 to -1,
        1 to 0
    )

    private val secondDirections = arrayOf(
        0 to 1,
        1 to 0,
        0 to -1,
        -1 to 0
    )

    fun solution(
        r: Int,
        c: Int,
        t: Int,
        map: Array<IntArray>,
        airCleaner: AirCleaner
    ): Int {
        var currentMap = map
        var currentTime = 0

        while (currentTime < t) {
            currentMap = spread(r, c, currentMap)
            cleaningAir(r, c, currentMap, airCleaner)

            currentTime += 1
        }

        var result = 0
        for (row in 0 until r) {
            for (col in 0 until c) {
                result += when (val value = currentMap[row][col]) {
                    -1, 0 -> 0
                    else -> value
                }
            }
        }

        return result
    }

    private fun spread(
        r: Int,
        c: Int,
        map: Array<IntArray>,
    ): Array<IntArray> {
        val copyMap = Array(r) { row -> IntArray(c) { col -> map[row][col] } }

        for (row in 0 until r) {
            for (col in 0 until c) {
                val spreadValue = map[row][col] / 5

                var count = 0
                for (direction in firstDirections) {
                    val nextRow = row + direction.first
                    val nextCol = col + direction.second
                    val value = map.getOrNull(nextRow)?.getOrNull(nextCol) ?: continue

                    if (value == -1) {
                        continue
                    }

                    copyMap[nextRow][nextCol] += spreadValue
                    count += 1
                }

                copyMap[row][col] -= spreadValue * count
            }
        }

        return copyMap
    }

    private fun cleaningAir(
        r: Int,
        c: Int,
        map: Array<IntArray>,
        airCleaner: AirCleaner,
    ) {
        var prev = 0
        var temp: Int

        var (row, col) = airCleaner.firstPosition
        var currentDirection = 0

        while (true) {
            row += firstDirections[currentDirection].first
            col += firstDirections[currentDirection].second

            when {
                row !in 0 until r -> {
                    row += if (row < 0) { 1 } else { -1 }
                    currentDirection += 1
                    continue
                }
                col !in 0 until c  -> {
                    col += if (col < 0) { 1 } else { -1 }
                    currentDirection += 1
                    continue
                }
            }

            if (map[row][col] == -1) {
                break
            }

            temp = map[row][col]
            map[row][col] = prev
            prev = temp
        }

        prev = 0
        temp = 0

        row = airCleaner.secondPosition.first
        col = airCleaner.secondPosition.second
        currentDirection = 0

        while (true) {
            row += secondDirections[currentDirection].first
            col += secondDirections[currentDirection].second

            when {
                row !in 0 until r -> {
                    row += if (row < 0) { 1 } else { -1 }
                    currentDirection += 1
                    continue
                }
                col !in 0 until c  -> {
                    col += if (col < 0) { 1 } else { -1 }
                    currentDirection += 1
                    continue
                }
            }

            if (map[row][col] == -1) {
                break
            }

            temp = map[row][col]
            map[row][col] = prev
            prev = temp
        }

    }

    data class AirCleaner(
        val firstPosition: Pair<Int, Int>,
        val secondPosition: Pair<Int, Int>,
    )
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (r, c, t) = br.readLine().split(" ").map(String::toInt)
    var firstPosition: Pair<Int, Int>? = null
    var secondPosition: Pair<Int, Int>? = null

    val map = Array(r) { row ->
        val inputs = br.readLine().split(" ").map(String::toInt)
        IntArray(c) { col ->
            if (inputs[col] == -1) {
                if (firstPosition == null) {
                    firstPosition = row to col
                } else {
                    secondPosition = row to col
                }
            }
            inputs[col]
        }
    }

    bw.append("${Solution17144().solution(r, c, t, map, Solution17144.AirCleaner(firstPosition!!, secondPosition!!))}\n")
    bw.flush()

    br.close()
    bw.close()
}