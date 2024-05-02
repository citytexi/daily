package baekjoon.silver.solution_1764

import java.util.PriorityQueue

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (n, m) = br.readLine().split(" ").map(String::toInt)
    val memberMap = hashMapOf<String, Int>()
    repeat(n) {
        memberMap[br.readLine()] = 1
    }

    val result = PriorityQueue<String>()
    repeat(m) {
        val name = br.readLine()

        if (memberMap.getOrDefault(name, 0) == 1) {
            result.add(name)
        }
    }

    bw.append("${result.size}\n")
    while (result.isNotEmpty()) {
        bw.append("${result.poll()}\n")
    }
    bw.flush()

    br.close()
    bw.close()
}