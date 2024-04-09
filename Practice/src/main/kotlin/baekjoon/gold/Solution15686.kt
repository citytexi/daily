package baekjoon.gold

import kotlin.math.abs
import kotlin.math.min

private class Solution15686 {
    private var result = Int.MAX_VALUE
    fun solution(
        m: Int,
        hList: List<Pair<Int, Int>>,
        cList: List<Pair<Int, Int>>
    ): Int {
        result = Int.MAX_VALUE

        dfs(0, 0, hList, cList, BooleanArray(cList.size), m)

        return result
    }

    private fun dfs(
        index: Int,
        depth: Int,
        hList: List<Pair<Int, Int>>,
        cList: List<Pair<Int, Int>>,
        visited: BooleanArray,
        m: Int
    ) {
        if (depth == m) {
            var sum = 0

            for (home in hList) {
                var temp = Int.MAX_VALUE

                for (c in cList.indices) {
                    if (visited[c]) {
                        if (temp == 1) {
                            break
                        }

                        temp = min(temp, abs(home.first - cList[c].first) + abs(home.second - cList[c].second))
                    }
                }

                sum += temp
            }

            result = min(result, sum)
            return
        }

        for (i in index until cList.size) {
            visited[i] = true
            dfs(i + 1, depth + 1, hList, cList, visited, m)
            visited[i] = false
        }
    }
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    val hList = mutableListOf<Pair<Int, Int>>()
    val cList = mutableListOf<Pair<Int, Int>>()
    repeat(n) { row ->
        br.readLine().split(" ").forEachIndexed { col, str ->
            when (str.toInt()) {
                1 -> hList.add(row to col)
                2 -> cList.add(row to col)
            }
        }
    }

    bw.append("${Solution15686().solution(m, hList, cList)}\n")
    bw.flush()

    br.close()
    bw.close()
}