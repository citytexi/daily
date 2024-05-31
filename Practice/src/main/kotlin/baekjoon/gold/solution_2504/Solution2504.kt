package baekjoon.gold.solution_2504

private class Solution2504 {

    fun solution(str: String): Int {
        var result = 0
        val deque = ArrayDeque<Char>()

        var temp = 1
        for (i in str.indices) {
            when (str[i]) {
                '('-> {
                    deque.addLast('(')
                    temp *= 2
                }
                '[' -> {
                    deque.addLast('[')
                    temp *= 3
                }
                ')' -> when (deque.isEmpty()) {
                    true -> return 0
                    false -> {
                        val last = deque.lastOrNull()

                        if (last == null || last != '(') {
                            return 0
                        }

                        if (str[i - 1] == '(') {
                            result += temp
                        }

                        deque.removeLast()
                        temp /= 2
                    }
                }
                ']' -> when (deque.isEmpty()) {
                    true -> return 0
                    false -> {
                        val last = deque.lastOrNull()

                        if (last == null || last != '[') {
                            return 0
                        }

                        if (str[i - 1] == '[') {
                            result += temp
                        }

                        deque.removeLast()
                        temp /= 3
                    }
                }
            }
        }

        return when (deque.isNotEmpty()) {
            true -> 0
            false -> result
        }
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val str = br.readLine()

    bw.append("${Solution2504().solution(str)}\n")
    bw.flush()

    br.close()
    bw.close()
}