package baekjoon.platinum.solution_1786

private class Solution1786 {
    fun solution(
        t: String,
        p: String
    ): String {
        val arr = IntArray(p.length)
        var j = 0
        for (i in 1 until p.length) {
            while (j > 0 && p[i] != p[j]) {
                j = arr[j - 1]
            }
            if (p[i] == p[j]) {
                arr[i] = ++j
            }
        }

        j = 0
        val results = mutableListOf<Int>()

        for (i in t.indices) {
            while (j > 0 && t[i] != p[j]) {
                j = arr[j - 1]
            }
            if (t[i] == p[j]) {
                when (j) {
                    p.length - 1 -> {
                        results.add(i - p.length + 2)
                        j = arr[j]
                    }
                    else -> j += 1
                }
            }
        }

        val stringBuilder = StringBuilder()
        stringBuilder.append("${results.size}\n")
        results.forEach { stringBuilder.append("$it ") }
        return stringBuilder.toString()
    }
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val t = br.readLine()
    val p = br.readLine()

    bw.append("${Solution1786().solution(t, p)}\n")
    bw.flush()

    br.close()
    bw.close()
}