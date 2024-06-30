package baekjoon.silver.solution_10799

private class Solution10799 {

    fun solution(input: String): Int {
        var result = 0
        var base = 0
        var index = 0
        val deque = ArrayDeque<Char>()

        while (index < input.length) {
            when (val char = input[index]) {
                '(' -> {
                    if (index + 1 < input.length && input[index + 1] == ')') {
                        result += deque.size
                        index++
                    } else {
                        deque.add(char)
                        base++
                    }
                }
                else -> if (deque.isNotEmpty()) {
                    deque.removeLast()
                }
            }

            index += 1
        }

        return result + base
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val input = br.readLine()
    bw.append("${Solution10799().solution(input)}\n")
    bw.flush()

    br.close()
    bw.close()
}