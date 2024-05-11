package baekjoon.platinum.solution_14003

private class Solution14003 {

    fun solution(
        n: Int,
        a: IntArray
    ): Pair<String, String> {
        val indexDp = IntArray(n)
        val list = mutableListOf<Int>()

        list.add(Int.MIN_VALUE)

        for (i in 0 until n) {
            when {
                list[list.size - 1] < a[i] -> {
                    list.add(a[i])
                    indexDp[i] = list.size - 1
                }
                else -> {
                    var left = 0
                    var right = list.size - 1

                    while (left < right) {
                        val center = (left + right) / 2

                        when {
                            list[center] >= a[i] -> right = center
                            else -> left = center + 1
                        }
                    }

                    list[right] = a[i]
                    indexDp[i] = right
                }
            }
        }

        val resultDeque = ArrayDeque<Int>()
        var index = list.size - 1

        for (i in n - 1 downTo 0) {
            if (index == indexDp[i]) {
                resultDeque.add(a[i])
                index -= 1
            }
        }

        var result = ""

        while (resultDeque.isNotEmpty()) {
            result += "${resultDeque.removeLast()} "
        }

        return (list.size - 1).toString() to result
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    val a = when (n) {
        1 -> IntArray(n) { br.readLine().toInt() }
        else -> br.readLine().split(" ").map(String::toInt).toIntArray()
    }

    val solution14003 = Solution14003()

    val result = solution14003.solution(n, a)

    bw.append("${result.first}\n")
    bw.append("${result.second}\n")
    bw.flush()

    br.close()
    bw.close()
}