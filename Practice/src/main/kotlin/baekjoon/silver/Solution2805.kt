package baekjoon.silver

private class Solution2805 {
    fun solution(
        n: Long,
        m: Long,
        trees: LongArray
    ): Long {
        val sortedTrees = trees.sortedDescending().toLongArray()
        var start = 0L
        var end = sortedTrees[0]
        var result = -1L

        while (result == -1L && start + 1 < end) {
            var wood = 0L
            val height = (start + end) / 2

            for (tree in sortedTrees) {
                if (tree >= height) {
                    wood += tree - height
                }
            }

            if (wood > m) {
                start = height
            } else if (wood < m) {
                end = height
            } else {
                result = height
            }
        }

        return when (result) {
            -1L -> start
            else -> result
        }
    }
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (n, m) = br.readLine().split(" ").map(String::toLong)
    val trees = br.readLine().split(" ").map(String::toLong).toLongArray()

    bw.append("${Solution2805().solution(n, m, trees)}\n")
    bw.flush()

    br.close()
    bw.close()
}
