package baekjoon.gold.solution_15685

private class Solution15685(
    private val curve: Array<MutableList<Triple<Int, Int, Int>>>
) {
    private val directions = arrayOf(
        0 to 1,
        -1 to 0,
        0 to -1,
        1 to 0
    )

    private val map = Array(101) { BooleanArray(101) }


    fun solution(
        index: Int,
        x: Int,
        y: Int,
        d: Int,
        g: Int
    ) {
        map[y][x] = true
        curve[index].add(Triple(y, x, d))

        val nextRow = y + directions[d].first
        val nextCol = x + directions[d].second


        if (nextRow !in 0 ..100 || nextCol !in 0..100) {
            return
        }

        map[nextRow][nextCol] = true
        curve[index].add(Triple(nextRow, nextCol, d))


        move(index, g)
    }

    private fun move(
        index: Int,
        g: Int
    ) {
        repeat(g){
            for (i in curve[index].size - 1 downTo 1) {
                val d = curve[index][i].third
                val nextDirection = (((d + 3) % 4) + 2) % 4
                val nextRow = curve[index][curve[index].size - 1].first + directions[nextDirection].first
                val nextCol = curve[index][curve[index].size - 1].second + directions[nextDirection].second

                if (nextRow !in 0 .. 100 || nextCol !in 0 .. 100) {
                    break
                }

                map[nextRow][nextCol] = true
                curve[index].add(Triple(nextRow, nextCol, nextDirection))
            }
        }
    }

    fun getSize(): Int {
        var result = 0
        for (i in map.indices) {
            for(j in map[i].indices) {
                val leftTop = map.getOrNull(i)?.getOrNull(j) ?: continue
                val rightTop = map.getOrNull(i)?.getOrNull(j + 1) ?: continue
                val leftBottom = map.getOrNull(i + 1)?.getOrNull(j) ?: continue
                val rightBottom = map.getOrNull(i + 1)?.getOrNull(j + 1) ?: continue

                if (leftTop && rightTop && leftBottom && rightBottom) {
                    result += 1
                }
            }
        }

        return result
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()

    val solution15685 = Solution15685(
        curve = Array(n) { mutableListOf() }
    )

    repeat(n) {
        val (x, y, d, g) = br.readLine().split(" ").map(String::toInt)

        solution15685.solution(it, x, y, d, g)
    }

    bw.append("${solution15685.getSize()}\n")
    bw.flush()

    br.close()
    bw.close()
}