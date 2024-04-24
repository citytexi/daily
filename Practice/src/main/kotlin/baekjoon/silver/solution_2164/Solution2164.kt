package baekjoon.silver.solution_2164

private class Solution2164 {
    fun solution(n: Int): Int {
        val deque = ArrayDeque<Int>().apply {
            for (i in 1 .. n) {
                add(i)
            }
        }

        while (deque.size > 1) {
            // 제일 위에 있는 카드를 바닥에 버린다
            deque.removeFirst()

            // 제일 위에 있는 카드를 제일 아래에 있는 카드 밑으로 옮긴다
            deque.addLast(deque.removeFirst())
        }

        return deque.first()
    }
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    bw.append("${Solution2164().solution(br.readLine().toInt())}\n")
    bw.flush()

    br.close()
    bw.close()
}