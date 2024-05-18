package baekjoon.gold.solution_1918

private class Solution1918(
    private val stringBuilder: StringBuilder = StringBuilder(),
    private val deque: ArrayDeque<Char> = ArrayDeque()
) {

    fun solution(char: Char) {
        when (char) {
            in 'A' .. 'Z' -> stringBuilder.append(char)
            '(' -> deque.add(char)
            ')' -> {
                while (deque.isNotEmpty() && deque.last() != '(') {
                    stringBuilder.append(deque.removeLast())
                }
                if (deque.isNotEmpty() && deque.last() == '(') {
                    deque.removeLast()
                }
            }
            '+', '-' -> {
                while (deque.isNotEmpty() && deque.last() != '(') {
                    stringBuilder.append(deque.removeLast())
                }
                deque.add(char)
            }
            '*', '/' -> {
                while (deque.isNotEmpty() && deque.last() != '(' && deque.last() != '-' && deque.last() != '+') {
                    stringBuilder.append(deque.removeLast())
                }
                deque.add(char)
            }
            else -> Unit
        }
    }

    fun getOperation(): String {
        while (deque.isNotEmpty()) {
            stringBuilder.append(deque.removeLast())
        }

        return stringBuilder.toString()
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val solution1918 = Solution1918()
    br.readLine().forEach(solution1918::solution)
    bw.append("${solution1918.getOperation()}\n")
    bw.flush()

    br.close()
    bw.close()
}