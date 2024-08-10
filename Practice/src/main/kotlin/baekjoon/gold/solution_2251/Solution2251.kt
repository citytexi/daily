package baekjoon.gold.solution_2251

private class Solution2251 {

    fun solution(
        a: Int,
        b: Int,
        c: Int
    ): String {
        val visited = Array(201) { Array(201) { BooleanArray(201) } }

        val deque = ArrayDeque<Triple<Int, Int, Int>>().apply { add(Triple(0, 0, c)) }
        val result = mutableListOf<Int>()

        while (deque.isNotEmpty()) {
            val (currentA, currentB, currentC) = deque.removeFirst()

            if (visited[currentA][currentB][currentC]) {
                continue
            }

            visited[currentA][currentB][currentC] = true

            if (currentA == 0) {
                result.add(currentC)
            }

            deque.add(
                when {
                    currentA + currentB > b -> Triple(currentA + currentB - b, b, currentC)
                    else -> Triple(0, currentA + currentB, currentC)
                }
            )

            deque.add(
                when {
                    currentA + currentC > c -> Triple(currentA + currentC - c, currentB, c)
                    else -> Triple(0, currentB, currentA + currentC)
                }
            )

            deque.add(
                when {
                    currentA + currentB > a -> Triple(a, currentA + currentB - a, currentC)
                    else -> Triple(currentA + currentB, 0, currentC)
                }
            )

            deque.add(
                when {
                    currentB + currentC > c -> Triple(currentA, currentB + currentC - c, c)
                    else -> Triple(currentA, 0, currentB + currentC)
                }
            )

            deque.add(
                when {
                    currentA + currentC > a -> Triple(a, currentB, currentA + currentC - a)
                    else -> Triple(currentA + currentC, currentB, 0)
                }
            )

            deque.add(
                when {
                    currentB + currentC > b -> Triple(currentA, b, currentB + currentC - b)
                    else -> Triple(currentA, currentB + currentC, 0)
                }
            )
        }

        return result.sorted().joinToString(" ")
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (a, b, c) = br.readLine().split(" ").map(String::toInt)

    bw.append(Solution2251().solution(a, b, c))
    bw.flush()

    br.close()
    bw.close()
}