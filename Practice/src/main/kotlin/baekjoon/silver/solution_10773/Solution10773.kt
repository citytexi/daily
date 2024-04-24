package baekjoon.silver.solution_10773

private class Solution10773 {
    fun solution(numbers: LongArray): Long {
        val deque = ArrayDeque<Long>()

        for (num in numbers) {
            when (num) {
                0L -> deque.removeLast()
                else -> deque.add(num)
            }
        }

        return deque.sum()
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val k = br.readLine().toInt()
    val numbers = LongArray(k) { br.readLine().toLong() }

    bw.append("${Solution10773().solution(numbers)}\n")
    bw.flush()

    br.close()
    bw.close()
}