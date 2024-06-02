package baekjoon.gold.solution_15683


private class Solution15683 {
    private val orientations = arrayOf(
        Orientation.TOP,
        Orientation.BOTTOM,
        Orientation.LEFT,
        Orientation.RIGHT
    )

    private var result = Int.MAX_VALUE
    private var currentMaxSize = 0

    fun solution(
        n: Int,
        m: Int,
        map: Array<IntArray>,
        cctv: MutableList<CCTV>,
        cctv5: MutableList<CCTV>,
        maxSize: Int
    ): Int {
        currentMaxSize = maxSize

        for (current in cctv5) {
            currentMaxSize -= watchCCTVType5(current, n, m, map)
        }

        backTracking(0, 0, n, m, map, cctv)

        return result
    }

    private fun backTracking(
        depth: Int,
        removeSize: Int,
        n: Int,
        m: Int,
        map: Array<IntArray>,
        cctv: MutableList<CCTV>,
    ) {
        if (depth == cctv.size) {
            result = minOf(result, currentMaxSize - removeSize)
            return
        }

        val currentCCTV = cctv[depth]

        for (orientation in orientations) {
            val copyMap = Array(n) { row -> IntArray(m) { col -> map[row][col] } }
            var size = 0
            size += when (currentCCTV.type) {
                1 -> watchCCTVType1(currentCCTV, n, m, copyMap, orientation)
                2 -> watchCCTVType2(currentCCTV, n, m, copyMap, orientation)
                3 -> watchCCTVType3(currentCCTV, n, m, copyMap, orientation)
                4 -> watchCCTVType4(currentCCTV, n, m, copyMap, orientation)
                else -> 0
            }

            backTracking(depth + 1, removeSize + size, n, m, copyMap, cctv)
        }
    }

    private fun watchCCTVType1(
        cctv: CCTV,
        n: Int,
        m: Int,
        map: Array<IntArray>,
        orientation: Orientation
    ): Int {
        var size = 0

        size += when (orientation) {
            Orientation.TOP -> watchCCTV(cctv, n, m, map, Orientation.TOP)
            Orientation.BOTTOM -> watchCCTV(cctv, n, m, map, Orientation.BOTTOM)
            Orientation.LEFT -> watchCCTV(cctv, n, m, map, Orientation.LEFT)
            Orientation.RIGHT ->  watchCCTV(cctv, n, m, map, Orientation.RIGHT)
        }

        return size
    }

    private fun watchCCTVType2(
        cctv: CCTV,
        n: Int,
        m: Int,
        map: Array<IntArray>,
        orientation: Orientation
    ): Int {
        var size = 0

        when (orientation) {
            Orientation.TOP, Orientation.BOTTOM -> {
                size += watchCCTV(cctv, n, m, map, Orientation.TOP)
                size += watchCCTV(cctv, n, m, map, Orientation.BOTTOM)
            }
            Orientation.LEFT, Orientation.RIGHT -> {
                size += watchCCTV(cctv, n, m, map, Orientation.LEFT)
                size += watchCCTV(cctv, n, m, map, Orientation.RIGHT)
            }
        }

        return size
    }

    private fun watchCCTVType3(
        cctv: CCTV,
        n: Int,
        m: Int,
        map: Array<IntArray>,
        orientation: Orientation
    ): Int {
        var size = 0

        when (orientation) {
            Orientation.TOP -> {
                size += watchCCTV(cctv, n, m, map, Orientation.TOP)
                size += watchCCTV(cctv, n, m, map, Orientation.RIGHT)
            }
            Orientation.BOTTOM -> {
                size += watchCCTV(cctv, n, m, map, Orientation.LEFT)
                size += watchCCTV(cctv, n, m, map, Orientation.BOTTOM)
            }
            Orientation.LEFT -> {
                size += watchCCTV(cctv, n, m, map, Orientation.LEFT)
                size += watchCCTV(cctv, n, m, map, Orientation.TOP)
            }
            Orientation.RIGHT -> {
                size += watchCCTV(cctv, n, m, map, Orientation.RIGHT)
                size += watchCCTV(cctv, n, m, map, Orientation.BOTTOM)
            }
        }

        return size
    }

    private fun watchCCTVType4(
        cctv: CCTV,
        n: Int,
        m: Int,
        map: Array<IntArray>,
        orientation: Orientation
    ): Int {
        var size = 0

        when (orientation) {
            Orientation.TOP -> {
                size += watchCCTV(cctv, n, m, map, Orientation.TOP)
                size += watchCCTV(cctv, n, m, map, Orientation.RIGHT)
                size += watchCCTV(cctv, n, m, map, Orientation.LEFT)
            }
            Orientation.BOTTOM -> {
                size += watchCCTV(cctv, n, m, map, Orientation.LEFT)
                size += watchCCTV(cctv, n, m, map, Orientation.BOTTOM)
                size += watchCCTV(cctv, n, m, map, Orientation.RIGHT)
            }
            Orientation.LEFT -> {
                size += watchCCTV(cctv, n, m, map, Orientation.LEFT)
                size += watchCCTV(cctv, n, m, map, Orientation.TOP)
                size += watchCCTV(cctv, n, m, map, Orientation.BOTTOM)
            }
            Orientation.RIGHT -> {
                size += watchCCTV(cctv, n, m, map, Orientation.TOP)
                size += watchCCTV(cctv, n, m, map, Orientation.RIGHT)
                size += watchCCTV(cctv, n, m, map, Orientation.BOTTOM)
            }
        }

        return size
    }

    private fun watchCCTVType5(
        cctv: CCTV,
        n: Int,
        m: Int,
        map: Array<IntArray>,
    ): Int {
        var size = 0

        size += watchCCTV(cctv, n, m, map, Orientation.TOP)
        size += watchCCTV(cctv, n, m, map, Orientation.BOTTOM)
        size += watchCCTV(cctv, n, m, map, Orientation.LEFT)
        size += watchCCTV(cctv, n, m, map, Orientation.RIGHT)

        return size
    }

    private fun watchCCTV(
        cctv: CCTV,
        n: Int,
        m: Int,
        map: Array<IntArray>,
        orientation: Orientation
    ): Int {
        var size = 0
        var currentRow = cctv.row
        var currentCol = cctv.col

        while (
            when (orientation) {
                Orientation.TOP -> currentRow >= 0
                Orientation.BOTTOM -> currentRow < n
                Orientation.RIGHT -> currentCol < m
                Orientation.LEFT -> currentCol >= 0
            }
        ) {
            if (map[currentRow][currentCol] == 6) {
                break
            }

            if (map[currentRow][currentCol] !in 1 .. 5 && map[currentRow][currentCol] != 7) {
                map[currentRow][currentCol] = 7
                size += 1
            }

            when (orientation) {
                Orientation.TOP -> currentRow -= 1
                Orientation.BOTTOM -> currentRow += 1
                Orientation.RIGHT -> currentCol += 1
                Orientation.LEFT -> currentCol -= 1
            }
        }

        return size
    }

    data class CCTV(
        val row: Int,
        val col: Int,
        val type: Int
    ) : Comparable<CCTV> {
        override fun compareTo(other: CCTV): Int = other.type.compareTo(type)
    }

    enum class Orientation {
        TOP, BOTTOM, LEFT, RIGHT
    }
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (n, m) = br.readLine().split(" ").map(String::toInt)
    val cctv = mutableListOf<Solution15683.CCTV>()
    val cctv5 = mutableListOf<Solution15683.CCTV>()
    var maxSize = n * m

    val map = Array(n) { row ->
        val inputs = br.readLine().split(" ").map(String::toInt)
        IntArray(m) { col ->
            when (inputs[col]) {
                in 1 .. 4 -> {
                    cctv.add(Solution15683.CCTV(row, col, inputs[col]))
                    maxSize -= 1
                }
                5 -> {
                    cctv5.add(Solution15683.CCTV(row, col, inputs[col]))
                    maxSize -= 1
                }
                6 -> maxSize -= 1
            }
            inputs[col]
        }
    }

    bw.append("${Solution15683().solution(n, m, map, cctv, cctv5, maxSize)}\n")
    bw.flush()

    br.close()
    bw.close()
}