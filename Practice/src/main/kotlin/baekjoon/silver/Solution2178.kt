package baekjoon.silver

/*
문제
N×M크기의 배열로 표현되는 미로가 있다.

1	0	1	1	1	1
1	0	1	0	1	0
1	0	1	0	1	1
1	1	1	0	1	1
미로에서 1은 이동할 수 있는 칸을 나타내고, 0은 이동할 수 없는 칸을 나타낸다. 이러한 미로가 주어졌을 때, (1, 1)에서 출발하여 (N, M)의 위치로 이동할 때 지나야 하는 최소의 칸 수를 구하는 프로그램을 작성하시오. 한 칸에서 다른 칸으로 이동할 때, 서로 인접한 칸으로만 이동할 수 있다.

위의 예에서는 15칸을 지나야 (N, M)의 위치로 이동할 수 있다. 칸을 셀 때에는 시작 위치와 도착 위치도 포함한다.

입력
첫째 줄에 두 정수 N, M(2 ≤ N, M ≤ 100)이 주어진다. 다음 N개의 줄에는 M개의 정수로 미로가 주어진다. 각각의 수들은 붙어서 입력으로 주어진다.

출력
첫째 줄에 지나야 하는 최소의 칸 수를 출력한다. 항상 도착위치로 이동할 수 있는 경우만 입력으로 주어진다.
* */

private class Solution2178 {
    private val direction = arrayOf(
        0 to 1,
        0 to -1,
        1 to 0,
        -1 to 0
    )
    fun solution(
        n: Int,
        m: Int,
        map: Array<IntArray>
    ) {
        bfs(0, 0, n, m, map, Array(n) { BooleanArray(m) })
    }

    private fun bfs(
        x: Int,
        y: Int,
        n: Int,
        m: Int,
        map: Array<IntArray>,
        visited: Array<BooleanArray>
    ) {
        val deque = ArrayDeque<Pair<Int, Int>>()
        deque.add(x to y)
        visited[x][y] = true

        while (deque.isNotEmpty()){
            val first = deque.removeFirst()

            for (i in 0 until 4) {
                val (dx, dy) = direction[i]
                val tempX = first.first + dx
                val tempY = first.second + dy

                if (tempX in 0 until n && tempY in 0 until m) {
                    if (!visited[tempX][tempY] && map[tempX][tempY] != 0 ) {
                        visited[tempX][tempY] = true
                        map[tempX][tempY] = map[first.first][first.second] + 1
                        deque.add(tempX to tempY)
                    }
                }
            }
        }
        println(map[n - 1][m - 1])
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    val map = Array(n) { br.readLine().map { it.digitToInt() }.toIntArray() }

    val solution2178 = Solution2178()
    solution2178.solution(n, m, map)

    br.close()
    bw.close()
}