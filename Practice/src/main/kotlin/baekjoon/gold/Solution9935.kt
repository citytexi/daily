package baekjoon.gold


private class Solution9935 {
    fun solution(str: String, bomb: String): String {
        val deque = ArrayDeque<Char>()

        out@ for (char in str) {
            deque.add(char)

            if (deque.size >= bomb.length) {
                for (i in bomb.indices) {
                    // 문자열 비교 후 폭탄과 문자열이 다른 경우 다음 문자를 검사
                    when (deque[deque.size - bomb.length + i]) {
                        bomb[i] -> Unit
                        else -> continue@out
                    }
                }

                repeat(bomb.length) { deque.removeLast() }
            }
        }

        return when (deque.isEmpty()) {
            true -> "FRULA"
            false -> deque.joinToString("")
        }
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val str = br.readLine()
    val bomb = br.readLine()

    bw.append("${Solution9935().solution(str, bomb)}\n")
    bw.flush()

    br.close()
    bw.close()
}