package baekjoon.silver.solution_5397

private class Solution5397  {

    fun solution(l: CharArray): String {
        var cursor = 0
        val deque = ArrayDeque<Char>()

        for (i in l.indices) {
            val currnet = l[i]

            when (currnet) {
                '<' -> if (cursor != 0) cursor -= 1
                '>' -> if (cursor != deque.size) cursor += 1
                '-' -> if (cursor != 0 && !deque.isEmpty()) deque.removeAt(--cursor)
                else -> deque.add(cursor++, currnet)
            }
        }

        return deque.joinToString("")
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val t = br.readLine().toInt()
    val solution5397 = Solution5397()

    repeat(t) {
        val l = br.readLine().toCharArray()
        bw.append("${solution5397.solution(l)}\n")
    }

    bw.flush()

    br.close()
    bw.close()
}