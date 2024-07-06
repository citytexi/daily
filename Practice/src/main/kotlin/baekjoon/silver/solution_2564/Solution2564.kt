package baekjoon.silver.solution_2564

import kotlin.math.abs

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (width, height) = br.readLine().split(" ").map(String::toInt)

    val count = br.readLine().toInt()
    val graph = IntArray(count)

    var man = 0

    for (i in 0 .. count) {
        val (direction, distance) = br.readLine().split(" ").map(String::toInt)

        val result = when (direction) {
            1 -> distance
            2 -> (2 * width) + height - distance
            3 -> (2 * width) + (2 * height) - distance
            else -> width + distance
        }

        if (i < count) {
            graph[i] = result
        } else {
            man = result
        }
    }

    var result = 0

    for (i in 0 until count) {
        val path1 = abs(man - graph[i])
        val path2 = (2 * width) + (2 * height) - path1
        result += minOf(path1, path2)
    }

    bw.append("$result\n")
    bw.flush()

    br.close()
    bw.close()
}