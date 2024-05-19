package baekjoon.platinum.solution_11003

import java.util.*
import kotlin.collections.ArrayDeque

private data class Node(
    val index: Int,
    val num: Int
)

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    var stringTokenizer = StringTokenizer(br.readLine())
    val n = stringTokenizer.nextToken().toInt()
    val l = stringTokenizer.nextToken().toInt()

    val deque = ArrayDeque<Node>()

    stringTokenizer = StringTokenizer(br.readLine())

    for (i in 0 until n) {
        val input = stringTokenizer.nextToken().toInt()
        val node = Node(i, input)

        while (deque.isNotEmpty() && deque[deque.lastIndex].num >= node.num) {
            // 구간동안 최솟값은 현재 들어온 노드
            deque.removeLast()
        }
        deque.add(node)
        if (deque[0].index < i - l + 1) {
            deque.removeFirst()
        }

        bw.write("${deque[0].num} ")
    }


    bw.flush()

    br.close()
    bw.close()
}