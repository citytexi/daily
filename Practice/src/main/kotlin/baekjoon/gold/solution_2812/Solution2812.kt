package baekjoon.gold.solution_2812

private class Solution2812 {

    fun solution(
        n: Int,
        k: Int,
        num: String
    ): String {
        val deque = ArrayDeque<Char>()

        var currentCount = 0

        for (i in num.indices) {
            if (currentCount == k) {
                for (c in num.substring(i)) {
                    deque.add(c)
                }
                break
            }
            while (deque.isNotEmpty() && deque.last() < num[i] && currentCount < k) {
                deque.removeLast()
                currentCount += 1
            }
            deque.add(num[i])
        }

        while (currentCount < k) {
            deque.removeLast()
            currentCount += 1
        }

        return deque.joinToString("")
    }
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (n, k) = br.readLine().split(" ").map(String::toInt)

    val num = br.readLine()

    bw.append(Solution2812().solution(n, k, num))
    bw.flush()

    br.close()
    bw.close()
}
