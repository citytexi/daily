package baekjoon.gold.solution_16928

private class Solution16928 {
    private val diceNums = arrayOf(
        1,
        2,
        3,
        4,
        5,
        6
    )

    fun solution(
        ladderMap: HashMap<Int, Int>,
        snakeMap: HashMap<Int, Int>
    ): Int {
        val deque = ArrayDeque<Pair<Int, Int>>().apply { add(1 to 0) }
        val visited = IntArray(107) { Int.MAX_VALUE }
        visited[1] = 0
        var minCount = Int.MAX_VALUE

        while (deque.isNotEmpty()) {
            val (currentNum, currentCount) = deque.removeFirst()

            for (diceNum in diceNums) {
                var nextNum = currentNum + diceNum

                ladderMap.get(nextNum)?.let {
                    nextNum = it
                }

                snakeMap.get(nextNum)?.let {
                    nextNum = it
                }

                val nextCount = currentCount + 1
                val nextVisited = visited.getOrNull(nextNum) ?: continue

                if (nextVisited < nextCount) {
                    continue
                }

                if (nextNum > 100) {
                    continue
                }

                visited[nextNum] = nextCount
                deque.add(nextNum to nextCount)
            }
        }

        return visited[100]
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (n, m) = br.readLine().split(' ').map(String::toInt)
    val ladderMap = hashMapOf<Int, Int>()
    val snakeMap = hashMapOf<Int, Int>()

    repeat(n) {
        val (x, y) = br.readLine().split(' ').map(String::toInt)
        ladderMap[x] = y
    }

    repeat(m) {
        val (x, y) = br.readLine().split(' ').map(String::toInt)
        snakeMap[x] = y
    }

    bw.append("${Solution16928().solution(ladderMap, snakeMap)}\n")
    bw.flush()

    br.close()
    bw.close()
}