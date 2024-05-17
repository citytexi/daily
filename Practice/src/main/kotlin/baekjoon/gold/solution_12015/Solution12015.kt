package baekjoon.gold.solution_12015

private class Solution12015 {

    fun solution(
        n: Int,
        a: IntArray
    ): Int {
        val result = mutableListOf(Int.MAX_VALUE)

        for (i in a.indices) {
            val current = a[i]
            when {
                result.last() < current -> result.add(current)
                else -> result[binarySearch(current, result)] = current
            }
        }

        return result.size
    }

    private fun binarySearch(
        n: Int,
        a: MutableList<Int>
    ): Int {
        var left = 0
        var right = a.lastIndex

        while (right >= left) {
            val mid = (left + right) / 2

            when {
                a[mid] == n -> return mid
                a[mid] > n -> right = mid - 1
                else -> left = mid + 1
            }
        }


        return left
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()

    val a = when (n) {
        1 -> intArrayOf(br.readLine().toInt())
        else -> br.readLine().split(" ").map(String::toInt).toIntArray()
    }

    bw.append("${Solution12015().solution(n, a)}\n")
    bw.flush()

    br.close()
    bw.close()
}