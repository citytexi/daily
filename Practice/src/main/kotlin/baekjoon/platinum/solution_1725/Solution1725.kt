package baekjoon.platinum.solution_1725

private class Solution1725 {

    fun solution(
        n: Int,
        heights: LongArray
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

    val n = br.readLine().toInt()
    val heights = LongArray(n + 2) {
        when (it) {
            0, n + 1 -> 0
            else -> br.readLine().toLong()
        }
    }

    bw.append("${Solution1725().solution(n, heights)}\n")
    bw.flush()

    br.close()
    bw.close()
}