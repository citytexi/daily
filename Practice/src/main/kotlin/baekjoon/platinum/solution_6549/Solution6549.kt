package baekjoon.platinum.solution_6549

private class Solution6549 {

    fun solution(
        n: Int,
        heights: IntArray
    ): Long {
        var result = 0L

        val deque = ArrayDeque<Int>()
        deque.add(0)

        for (i in 1 .. n + 2) {
            while (deque.isNotEmpty() && heights[deque.last()] > heights.getOrElse(i) { 0 }) {
                val index = deque.removeLast()
                result = maxOf(result, heights[index].toLong() * (i - deque.last() - 1))
            }

            deque.add(i)
        }

        return result
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    var str = br.readLine()

    while (str.toIntOrNull() == null) {
        val inputs = str.split(" ").map(String::toInt)
        val n = inputs[0]
        val heights = IntArray(n + 2) {
            when (it) {
                0, n + 1 -> 0
                else -> inputs[it]
            }
        }

        bw.append("${Solution6549().solution(n, heights)}\n")

        str = br.readLine()
    }

    bw.flush()

    br.close()
    bw.close()
}