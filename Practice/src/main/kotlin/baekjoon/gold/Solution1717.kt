package baekjoon.gold

private class Solution1717 {
    private lateinit var parent: IntArray

    fun solution(
        n: Int,
        commends: List<Triple<Int, Int, Int>>
    ) {
        parent = IntArray(n + 1) { it }

        val bw = System.out.bufferedWriter()

        for ((commend, a, b) in commends) {
            when (commend) {
                0 -> union(a, b)
                1 -> when (find(a)) {
                    find(b) -> bw.append("YES\n")
                    else-> bw.append("NO\n")
                }
            }
        }

        bw.close()
    }

    private fun find(x: Int): Int = when (x) {
        parent[x] -> x
        else -> {
            parent[x] = find(parent[x])
            parent[x]
        }
    }

    private fun union(a: Int, b: Int) {
        val nextA = find(a)
        val nextB = find(b)

        if (nextA != nextB) {
            parent[nextB] = nextA
        }
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()

    val (n, m) = br.readLine().split(" ").map(String::toInt)
    val commends = List(m) {
        val str = br.readLine().split(" ").map(String::toInt)
        Triple(str[0], str[1], str[2])
    }

    Solution1717().solution(n, commends)

    br.close()
}