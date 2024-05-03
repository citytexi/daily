package baekjoon.silver.solution_4949

private class Solution4949 {

    fun solution(str: String): String {
        val deque = ArrayDeque<Char>()

        for (char in str) {
            when (char) {
                '(', '[' -> deque.add(char)
                ')' -> when (deque.lastOrNull()) {
                    null -> return "no"
                    '(' -> deque.removeLast()
                    else -> deque.add(char)
                }
                ']' -> when (deque.lastOrNull()) {
                    null -> return "no"
                    '[' -> deque.removeLast()
                    else -> deque.add(char)
                }
            }
        }

        return when (deque.isEmpty()) {
            true -> "yes"
            false -> "no"
        }
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val solution4949 = Solution4949()

    var str = br.readLine()
    while (str != ".") {
        bw.append("${solution4949.solution(str)}\n")
        str = br.readLine()
    }

    bw.flush()

    br.close()
    bw.close()
}