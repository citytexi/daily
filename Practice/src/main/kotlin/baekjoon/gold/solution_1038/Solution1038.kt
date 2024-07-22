package baekjoon.gold.solution_1038

private class Solution1038 {

    fun solution(n: Int): Long {
        return when (n) {
            0 -> 0L
            else -> {
                val list = mutableListOf<Long>()
                backTracking(0, 1, 0, list)

                if (list.size - 1 < n) {
                    return -1L
                }

                list.sort()
                return list[n]
            }
        }
    }

    private fun backTracking(
        index: Int,
        t: Long,
        result: Long,
        list: MutableList<Long>
    ) {
        var currentResult = result
        for(i in index..9)
        {
            currentResult += i * t
            list.add(currentResult)
            backTracking(i + 1, t * 10, currentResult, list)
            currentResult -= i * t
        }
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()

    bw.append("${Solution1038().solution(n)}\n")
    bw.flush()

    br.close()
    bw.close()
}