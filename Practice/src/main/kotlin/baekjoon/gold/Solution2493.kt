package baekjoon.gold

private class Solution2493 {
    fun solution(towers: IntArray): String {
        val result = IntArray(towers.size)
        val deque = ArrayDeque<Pair<Int, Int>>().apply { add(1 to towers[0]) }

        for (i in 1 until  towers.size) {
            val last = deque.last()

            if (last.second > towers[i]) {
                result[i] = last.first
                deque.add(i + 1 to towers[i])
            } else {
                while (deque.isNotEmpty()) {
                    val (index, h) = deque.removeLast()

                    if (h > towers[i]) {
                        deque.add(index to h)
                        deque.add(i + 1 to towers[i])
                        result[i] = index
                        break
                    }
                }

                if (deque.isEmpty()) {
                    deque.add(i + 1 to towers[i])
                    result[i] = 0
                }
            }
        }

        return result.joinToString(" ")
    }
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    val towers = br.readLine().split(" ").map(String::toInt).toIntArray()

    bw.append(Solution2493().solution(towers))

    br.close()
    bw.close()
}