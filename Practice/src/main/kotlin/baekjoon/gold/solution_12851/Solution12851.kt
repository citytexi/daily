package baekjoon.gold.solution_12851

private class Solution12851 {

    fun solution(
        n: Int,
        k: Int
    ): String {
        val sb = StringBuilder()

        val deque = ArrayDeque<Pair<Int, Int>>().apply { add(n to 0) }
        val visited = BooleanArray(200001).apply { this[n] = true }
        var minTime = 0
        var count = 1

        while (deque.isNotEmpty()) {
            val (current, time) = deque.removeFirst()
            visited[current] = true

            when (current) {
                k -> when (minTime) {
                    0 -> minTime = time
                    else -> if (minTime == time) {
                        count += 1
                    }
                }
            }

            val range = arrayOf(
                current + 1,
                current - 1,
                current * 2
            )

            for (next in range) {
                if (next in 0 until 200000 && !visited[next]) {
                    deque.add(next to (time + 1))
                }
            }
        }

        sb.append("${minTime}\n")
        sb.append("${count}\n")

        return sb.toString()
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (n, k) = br.readLine().split(" ").map(String::toInt)

    bw.append(Solution12851().solution(n, k))
    bw.flush()

    br.close()
    bw.close()
}