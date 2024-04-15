package baekjoon.gold

private class Solution9019 {
    fun solution(a: Int, b: Int): String = bfs(a, b, BooleanArray(10000))

    private fun bfs(
        start: Int,
        end: Int,
        visited: BooleanArray
    ): String {
        val deque = ArrayDeque<Pair<Int, String>>().apply { add(start to "") }
        visited[start] = true

        while (deque.isNotEmpty()) {
            val first = deque.removeFirst()

            keywordD(
                first = first,
                end = end,
                next = (first.first * 2) % 10000,
                visited = visited,
                deque = deque
            )?.let { return it }

            keywordS(
                first = first,
                end = end,
                next = if (first.first - 1 < 0) 9999 else first.first - 1,
                visited = visited,
                deque = deque
            )?.let { return it }

            keywordL(
                first = first,
                end = end,
                next = (first.first % 1000) * 10 + first.first / 1000,
                visited = visited,
                deque = deque
            )?.let { return it }

            keywordR(
                first = first,
                end = end,
                next = (first.first % 10) * 1000 + (first.first / 10) % 1000,
                visited = visited,
                deque = deque
            )?.let { return it }
        }

        return ""
    }

    private fun keywordD(
        first: Pair<Int, String>,
        end: Int,
        next: Int,
        visited: BooleanArray,
        deque: ArrayDeque<Pair<Int, String>>
    ): String? {
        if (next == end) {
            return "${first.second}D"
        }

        if (!visited[next]) {
            deque.add(next to "${first.second}D")
            visited[next] = true
        }

        return null
    }

    private fun keywordS(
        first: Pair<Int, String>,
        end: Int,
        next: Int,
        visited: BooleanArray,
        deque: ArrayDeque<Pair<Int, String>>
    ): String? {
        if (next == end) {
            return "${first.second}S"
        }

        if (!visited[next]) {
            deque.add(next to "${first.second}S")
            visited[next] = true
        }

        return null
    }

    private fun keywordL(
        first: Pair<Int, String>,
        end: Int,
        next: Int,
        visited: BooleanArray,
        deque: ArrayDeque<Pair<Int, String>>
    ): String? {
        if (next == end) {
            return "${first.second}L"
        }

        if (!visited[next]) {
            deque.add(next to "${first.second}L")
            visited[next] = true
        }

        return null
    }

    private fun keywordR(
        first: Pair<Int, String>,
        end: Int,
        next: Int,
        visited: BooleanArray,
        deque: ArrayDeque<Pair<Int, String>>
    ): String? {
        if (next == end) {
            return "${first.second}R"
        }

        if(!visited[next]) {
            deque.add(next to "${first.second}R")
            visited[next] = true
        }

        return null
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val solution9019 = Solution9019()

    val t = br.readLine().toInt()
    repeat(t) {
        val (a, b) = br.readLine().split(" ").map(String::toInt)

        bw.append("${solution9019.solution(a, b)}\n")
    }
    bw.flush()

    br.close()
    bw.close()
}