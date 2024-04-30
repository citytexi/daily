package baekjoon.gold.solution_14891


private class Solution14891 {
    fun solution(gears: Array<IntArray>, commends: Array<Pair<Int, Int>>): Int {

        for ((gearIndex, rotation) in commends) {
            rotate(gearIndex - 1, rotation, gears, BooleanArray(4))
        }

        var result = 0

        if (gears[0][0] == 1) {
            result += 1
        }

        if (gears[1][0] == 1) {
            result += 2
        }

        if (gears[2][0] == 1) {
            result += 4
        }

        if (gears[3][0] == 1) {
            result += 8
        }

        return result
    }

    private fun rotate(
        gearIndex: Int,
        rotation: Int,
        gears: Array<IntArray>,
        visited: BooleanArray
    ) {
        if (visited[gearIndex]) {
            return
        }

        visited[gearIndex] = true

        if (gearIndex >= 1 && gears[gearIndex - 1][2] != gears[gearIndex][6]) {
            rotate(gearIndex - 1, rotation * -1, gears, visited)
        }

        if (gearIndex < 3 && gears[gearIndex][2] != gears[gearIndex + 1][6]) {
            rotate(gearIndex + 1, rotation * -1, gears, visited)
        }

        val gear = gears[gearIndex]

        when(rotation) {
            1 -> {
                val last = gear.last()

                for (i in 7 downTo 1) {
                    gear[i] = gear[i - 1]
                }

                gear[0] = last
            }
            -1 -> {
                val first = gear.first()

                for (i in 0..6) {
                    gear[i] = gear[i + 1]
                }

                gear[7] = first
            }
        }
    }
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val gears = Array(4) {
        val arr = br.readLine().toCharArray()
        IntArray(8) { col -> arr[col].digitToInt() }
    }
    val k = br.readLine().toInt()
    val commends = Array(k) {
        val str = br.readLine().split(" ")
        str[0].toInt() to str[1].toInt()
    }

    bw.append("${Solution14891().solution(gears, commends)}\n")
    bw.flush()

    br.close()
    bw.close()
}