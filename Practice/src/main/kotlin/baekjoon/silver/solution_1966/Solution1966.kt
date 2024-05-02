package baekjoon.silver.solution_1966

import kotlin.math.max

private class Solution1966 {

    fun solution(
        m: Int,
        max: Int,
        priorityMap: HashMap<Int, Int>,
        nodes: ArrayDeque<Node>
    ): Int {
        var currentMax = max
        var result = 0

        while (nodes.isNotEmpty()) {
            val node = nodes.removeFirst()
            when {
                node.priority < currentMax -> nodes.addLast(node)
                else -> {
                    result += 1
                    when (node.num) {
                        m -> break
                        else -> {
                            priorityMap[currentMax] = priorityMap.getOrDefault(currentMax, 0) - 1
                            while (priorityMap.getOrDefault(currentMax, 0) == 0) {
                                currentMax -= 1
                            }
                        }
                    }
                }
            }
        }

        return result
    }

    data class Node(
        val num: Int,
        val priority: Int
    )
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val t = br.readLine().toInt()
    repeat(t) {
        val (n, m) = br.readLine().split(" ").map(String::toInt)
        val nodes = ArrayDeque<Solution1966.Node>()
        val hashMap = HashMap<Int, Int>()
        var max = 0

        when (n) {
            1 -> {
                br.readLine()
                bw.append("1\n")
            }
            else -> {
                br.readLine().split(" ").forEachIndexed { index, s ->
                    val value = s.toInt()
                    max = max(value, max)
                    hashMap[value] = hashMap.getOrDefault(value, 0) + 1
                    nodes.add(Solution1966.Node(index, value))
                }
                bw.append("${Solution1966().solution(m, max, hashMap, nodes)}\n")
            }
        }
    }

    bw.flush()

    br.close()
    bw.close()
}