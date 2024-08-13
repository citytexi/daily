package baekjoon.gold.solution_14226

private class Solution14226 {
    fun solution(s: Int): Int {
        val deque = ArrayDeque<Pair<Int, Int>>().apply { add(1 to 0) }
        val visited = Array(2001) { BooleanArray(2001) }
        var time = 0
        while (deque.isNotEmpty()) {
            val size = deque.size
            for (i in 0 until size) {
                val (current, clip) = deque.removeFirst()
                var next = -1
                deque.add(current to current)
                visited[current][clip] = true

                if (clip != 0) {
                    next = current + clip
                    if (next == s) {
                        return time + 1
                    }

                    val condition = !(next !in 1 until 2001 || visited[next][clip])
                    if (condition) {
                        visited[next][clip] = true
                        deque.add(next to clip)
                    }
                }

                if(current > 1){
                    next = current - 1
                    if (next == s) {
                        return time + 1
                    }

                    val condition = !(next !in 1 until 2001 || visited[next][clip])
                    if (condition) {
                        visited[next][clip] = true
                        deque.add(next to clip)
                    }
                }
            }
            time += 1
        }
        return 0
    }
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val s = br.readLine().toInt()

    bw.append("${Solution14226().solution(s)}\n")
    bw.flush()

    br.close()
    bw.close()
}