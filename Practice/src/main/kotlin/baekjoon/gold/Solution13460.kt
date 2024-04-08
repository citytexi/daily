package baekjoon.gold

import kotlin.math.min

private class Solution13460 {
    private val mapSize = intArrayOf(0, 0)
    private val holePosition = intArrayOf(0, 0)
    private val redPosition = intArrayOf(0, 0)
    private val bluePosition = intArrayOf(0, 0)
    private val directions = arrayOf(
        Direction.LEFT,
        Direction.RIGHT,
        Direction.UP,
        Direction.DOWN
    )

    private var min = Int.MAX_VALUE

    fun solution(
        mapSize: Pair<Int, Int>,
        holePosition: Pair<Int, Int>,
        redPosition: Pair<Int, Int>,
        bluePosition: Pair<Int, Int>,
        map: Array<CharArray>
    ): Int {
        this.mapSize[0] = mapSize.first
        this.mapSize[1] = mapSize.second
        this.holePosition[0] = holePosition.first
        this.holePosition[1] = holePosition.second
        this.redPosition[0] = redPosition.first
        this.redPosition[1] = redPosition.second
        this.bluePosition[0] = bluePosition.first
        this.bluePosition[1] = bluePosition.second

        dfs(1, map)

        return when (min) {
            Int.MAX_VALUE -> -1
            else -> min
        }
    }

    private fun dfs(depth: Int, map: Array<CharArray>) {
        if (depth > 10) {
            return
        }

        if (min != Int.MAX_VALUE && depth >= min) {
            return
        }

        for (direction in directions) {
            val copyMap = Array(mapSize[0]) { row ->
                CharArray(mapSize[1]) { col ->
                    val value = map[row][col]

                    when (value) {
                        '0' -> {
                            holePosition[0] = row
                            holePosition[1] = col
                        }
                        'R' -> {
                            redPosition[0] = row
                            redPosition[1] = col
                        }
                        'B' -> {
                            bluePosition[0] = row
                            bluePosition[1] = col
                        }
                    }

                    value
                }
            }

            val result = copyMap.move(direction)

            when (result) {
                1 -> {
                    min = min(depth, min)
                }
            }

            if (result == 0) {
                dfs(depth + 1, copyMap)
            }
        }
    }

    private fun Array<CharArray>.move(direction: Direction): Int {
        // B가 0 위치로 가는 경우 -1
        // R이 0 위치로 가는 경우 1
        // 아무런 일이 없는 경우 0

        var result = 0
        var currentActive = true
        val firstRed = when (direction) {
            Direction.UP -> redPosition[0] < bluePosition[0]
            Direction.DOWN -> redPosition[0] > bluePosition[0]
            Direction.LEFT -> redPosition[1] < bluePosition[1]
            Direction.RIGHT -> redPosition[1] > bluePosition[1]
        }

        var currentPositionN = if (firstRed) redPosition[0] else bluePosition[0]
        var currentPositionM = if (firstRed) redPosition[1] else bluePosition[1]

        this[currentPositionN][currentPositionM] = '.'

        while (
            when (direction) {
                Direction.UP, Direction.DOWN -> currentPositionN in 1 until mapSize[0] - 1
                Direction.LEFT, Direction.RIGHT -> currentPositionM in 1 until mapSize[1] - 1
            }
            && currentActive
        ) {
            when (direction) {
                Direction.UP -> currentPositionN -= 1
                Direction.DOWN -> currentPositionN += 1
                Direction.LEFT -> currentPositionM -= 1
                Direction.RIGHT -> currentPositionM += 1
            }

            val next = this[currentPositionN][currentPositionM]

            when (next) {
                '#', 'R', 'B' -> {
                    when (direction) {
                        Direction.UP -> currentPositionN += 1
                        Direction.DOWN -> currentPositionN -= 1
                        Direction.LEFT -> currentPositionM += 1
                        Direction.RIGHT -> currentPositionM -= 1
                    }

                    this[currentPositionN][currentPositionM] = if (firstRed) 'R' else 'B'

                    if (firstRed) {
                        redPosition[0] = currentPositionN
                        redPosition[1] = currentPositionM
                    } else {
                        bluePosition[0] = currentPositionN
                        bluePosition[1] = currentPositionM
                    }
                    currentActive = false
                }
                'O' -> {
                    result = if (firstRed) 1 else -1
                    if (result == -1) {
                        return result
                    }
                    currentActive = false
                }

                else -> Unit
            }
        }

        currentActive = true

        currentPositionN = if (firstRed) bluePosition[0] else redPosition[0]
        currentPositionM = if (firstRed) bluePosition[1] else redPosition[1]

        this[currentPositionN][currentPositionM] = '.'

        while (
            when (direction) {
                Direction.UP, Direction.DOWN -> currentPositionN in 1 until mapSize[0] - 1
                Direction.LEFT, Direction.RIGHT -> currentPositionM in 1 until mapSize[1] - 1
            }
            && currentActive
        ) {
            when (direction) {
                Direction.UP -> currentPositionN -= 1
                Direction.DOWN -> currentPositionN += 1
                Direction.LEFT -> currentPositionM -= 1
                Direction.RIGHT -> currentPositionM += 1
            }

            val next = this[currentPositionN][currentPositionM]

            when (next) {
                '#', 'R', 'B' -> {
                    when (direction) {
                        Direction.UP -> currentPositionN += 1
                        Direction.DOWN -> currentPositionN -= 1
                        Direction.LEFT -> currentPositionM += 1
                        Direction.RIGHT -> currentPositionM -= 1
                    }

                    this[currentPositionN][currentPositionM] = if (firstRed) 'B' else 'R'

                    if (firstRed) {
                        bluePosition[0] = currentPositionN
                        bluePosition[1] = currentPositionM
                    } else {
                        redPosition[0] = currentPositionN
                        redPosition[1] = currentPositionM
                    }
                    currentActive = false
                }
                'O' -> {
                    result = if (firstRed) -1 else 1
                    if (result == -1) {
                        return result
                    }
                    currentActive = false
                }

                else -> Unit
            }
        }


        return result
    }

    enum class Direction {
        UP,
        DOWN,
        RIGHT,
        LEFT
    }
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    var holeN = -1
    var holeM = -1
    var redN = -1
    var redM = -1
    var blueN = -1
    var blueM = -1

    val map = Array(n) { row ->
        val str = br.readLine()
        CharArray(m) { col ->
            val value = str[col]

            when (value) {
                '0' -> {
                    holeN = row
                    holeM = col
                }
                'R' -> {
                    redN = row
                    redM = col
                }
                'B' -> {
                    blueN = row
                    blueM = col
                }
            }

            value
        }
    }

    val result = Solution13460().solution(
        mapSize = n to m,
        holePosition = holeN to holeM,
        redPosition = redN to redM,
        bluePosition = blueN to blueM,
        map
    )

    bw.append("${result}\n")

    bw.flush()

    br.close()
    bw.close()
}


/*

3 6
######
#.ORB#
######

-1

8 8
########
#.####.#
#...#B##
#.##.R.#
######.#
##.##O.#
###.##.#
########

7

4 6
######
#R####
#B..O#
######

-1

4 6
######
#R#O##
#B...#
######

4

*/