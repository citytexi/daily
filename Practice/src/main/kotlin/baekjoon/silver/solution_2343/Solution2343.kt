package baekjoon.silver.solution_2343

private class Solution2343 {

    fun solution(
        n: Int,
        m: Int,
        arr: IntArray
    ): Int {
        var left = arr.max()
        var right = arr.sum()

        while (left < right) {
            var count = 1
            val mid = (left + right) / 2
            var sum = 0

            for (num in arr) {
                when {
                    sum + num <= mid -> sum += num
                    else -> {
                        sum = num
                        count += 1
                    }
                }
            }
            when {
                count <= m -> right = mid
                else -> left = mid + 1
            }
        }

        return left
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (n, m) = br.readLine().split(" ").map(String::toInt)
    val arr = br.readLine().split(" ").map(String::toInt).toIntArray()

    bw.append("${Solution2343().solution(n, m, arr)}\n")
    bw.flush()

    br.close()
    bw.close()
}