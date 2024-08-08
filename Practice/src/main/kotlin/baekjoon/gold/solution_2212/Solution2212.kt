package baekjoon.gold.solution_2212

private class Solution2212 {

    fun solution(
        n: Int,
        k: Int,
        arr: IntArray
    ): Int {
        val sortedArray = arr.sorted()
        val distance = IntArray(n - 1)

        for (i in 1 until n) {
            distance[i - 1] = sortedArray[i] - sortedArray[i - 1]
        }
        distance.sortDescending()

        var answer = sortedArray[n - 1] - sortedArray[0]

        when {
            k >= n -> answer = 0
            else -> for (i in 0 until k - 1) {
                answer -= distance[i]
            }
        }

        return answer
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    val k = br.readLine().toInt()
    val arr = br.readLine().split(" ").map(String::toInt).toIntArray()

    bw.append("${Solution2212().solution(n, k, arr)}\n")
    bw.flush()

    br.close()
    bw.close()
}