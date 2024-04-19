package baekjoon.gold

private class Solution1759 {
    private val containWords = listOf(
        'a',
        'e',
        'i',
        'o',
        'u'
    )

    private val result = mutableListOf<String>()
    fun solution(
        l: Int,
        c: Int,
        chars: List<Char>
    ): List<String> {
        dfs(
            depth = 0,
            l = l,
            current = mutableListOf(),
            chars = chars.sorted(),
            visited = BooleanArray(chars.size)
        )

        return result
    }

    private fun dfs(
        depth: Int,
        l: Int,
        current: MutableList<Char>,
        chars: List<Char>,
        visited: BooleanArray
    ) {
        if (depth == l) {
            var containACount = 0
            var containBCount = 0

            for (char in current) {
                when (char) {
                    in containWords -> containACount += 1
                    else -> containBCount += 1
                }

                if (containACount > 0 && containBCount > 1) {
                    break
                }
            }

            if (containACount > 0 && containBCount > 1) {
                result.add(current.joinToString(""))
            }
            return
        }

        for (i in chars.indices) {
            if (visited[i]) {
                continue
            }

            if ((current.lastOrNull() ?: 'a') > chars[i]) {
                continue
            }

            visited[i] = true
            current.add(chars[i])
            dfs(depth + 1, l, current, chars, visited)
            current.removeLast()
            visited[i] = false
        }
    }
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val solution1759 = Solution1759()

    val (l, c) = br.readLine().split(" ").map(String::toInt)
    val chars = br.readLine().split(" ").map { it[0] }

    val results = solution1759.solution(l, c, chars)
    for (result in results) {
        bw.append("$result\n")
    }
    bw.flush()

    br.close()
    bw.close()
}