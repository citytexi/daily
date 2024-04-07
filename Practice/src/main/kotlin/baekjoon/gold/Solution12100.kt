package baekjoon.gold

import kotlin.math.max

private class Solution12100 {
    private val directions = arrayOf(
        Direction.DOWN,
        Direction.LEFT,
        Direction.UP,
        Direction.RIGHT
    )

    private var max = 0

    fun solution(
        n: Int,
        map: Array<IntArray>
    ): Int {
        max = 0

        dfs(0, map)

        return max
    }

    private fun dfs(depth: Int, map: Array<IntArray>) {
        if (depth == 5) {
            return
        }

        for (direction in directions) {
            val copyMap = Array(map.size) { row -> IntArray(map.size) { col -> map[row][col] } }
            copyMap.move(direction)
            dfs(depth + 1, copyMap)
        }
    }

    private fun Array<IntArray>.move(direction: Direction) {
        val deque = ArrayDeque<Int>()

        when (direction) {
            Direction.RIGHT -> for (row in this.indices) {
                for (col in this.indices) {
                    when (val item = this[row][col]) {
                        0 -> Unit
                        else -> {
                            deque.add(item)
                            this[row][col] = 0
                        }
                    }
                }

                var newCol = this.size - 1

                while (deque.isNotEmpty()) {
                    val last = deque.removeLast()

                    this[row][newCol] = if (deque.isNotEmpty() && deque.last() == last) {
                        last + deque.removeLast()
                    } else {
                        last
                    }

                    max = max(this[row][newCol], max)
                    newCol -= 1
                }
            }
            Direction.LEFT -> for (row in this.indices) {
                for (col in this.indices) {
                    when (val item = this[row][col]) {
                        0 -> Unit
                        else -> {
                            deque.add(item)
                            this[row][col] = 0
                        }
                    }
                }

                var newCol = 0

                while (deque.isNotEmpty()) {
                    val first = deque.removeFirst()

                    this[row][newCol] = if (deque.isNotEmpty() && deque.first() == first) {
                        first + deque.removeFirst()
                    } else {
                        first
                    }

                    max = max(this[row][newCol], max)
                    newCol += 1
                }
            }
            Direction.DOWN -> for (col in this.indices) {
                for (row in this.indices) {
                    when (val item = this[row][col]) {
                        0 -> Unit
                        else -> {
                            deque.add(item)
                            this[row][col] = 0
                        }
                    }
                }

                var newRow = this.size - 1

                while (deque.isNotEmpty()) {
                    val last = deque.removeLast()

                    this[newRow][col] = if (deque.isNotEmpty() && deque.last() == last) {
                        last + deque.removeLast()
                    } else {
                        last
                    }

                    max = max(this[newRow][col], max)
                    newRow -= 1
                }
            }
            Direction.UP -> for (col in this.indices) {
                for (row in this.indices) {
                    when (val item = this[row][col]) {
                        0 -> Unit
                        else -> {
                            deque.add(item)
                            this[row][col] = 0
                        }
                    }
                }

                var newRow = 0

                while (deque.isNotEmpty()) {
                    val first = deque.removeFirst()

                    this[newRow][col] = if (deque.isNotEmpty() && deque.first() == first) {
                        first + deque.removeFirst()
                    } else {
                        first
                    }

                    max = max(this[newRow][col], max)
                    newRow += 1
                }
            }
        }
    }

     enum class Direction {
        RIGHT,
        LEFT,
        UP,
        DOWN,
    }
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    val map = Array(n) { _ ->
        val str = br.readLine().split(" ").map { it.toInt() }
        IntArray(n) { str[it] }
    }

    bw.append("${Solution12100().solution(n, map)}\n")
    bw.flush()

    br.close()
    bw.close()
}

/*
4
2 0 2 8
0 0 2 2
0 0 0 0
0 0 0 0

4
2 4 16 8
8 4 0 0
16 8 2 0
2 8 2 0


*/