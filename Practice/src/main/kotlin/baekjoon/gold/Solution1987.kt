package baekjoon.gold

/*
문제
세로
$R$칸, 가로
$C$칸으로 된 표 모양의 보드가 있다. 보드의 각 칸에는 대문자 알파벳이 하나씩 적혀 있고, 좌측 상단 칸 (
$1$행
$1$열) 에는 말이 놓여 있다.

말은 상하좌우로 인접한 네 칸 중의 한 칸으로 이동할 수 있는데, 새로 이동한 칸에 적혀 있는 알파벳은 지금까지 지나온 모든 칸에 적혀 있는 알파벳과는 달라야 한다. 즉, 같은 알파벳이 적힌 칸을 두 번 지날 수 없다.

좌측 상단에서 시작해서, 말이 최대한 몇 칸을 지날 수 있는지를 구하는 프로그램을 작성하시오. 말이 지나는 칸은 좌측 상단의 칸도 포함된다.

입력
첫째 줄에
$R$과
$C$가 빈칸을 사이에 두고 주어진다. (
$1 ≤ R,C ≤ 20$) 둘째 줄부터
$R$개의 줄에 걸쳐서 보드에 적혀 있는
$C$개의 대문자 알파벳들이 빈칸 없이 주어진다.

출력
첫째 줄에 말이 지날 수 있는 최대의 칸 수를 출력한다.
* */

private class Solution1987 {
    private val direction = arrayOf(
        1 to 0,
        -1 to 0,
        0 to 1,
        0 to -1
    )

    private var max: Int = 0

    fun solution(map: Array<CharArray>): Int {
        max = 0

        val visited = BooleanArray('Z' - 'A' + 1)
        visited[map[0][0] - 'A'] = true

        dfs(0, 0, 1, map, visited)

        return max
    }

    private fun dfs(
        x: Int,
        y: Int,
        depth: Int,
        map: Array<CharArray>,
        visited: BooleanArray
    ) {
        if (max < depth) {
            max = depth
        }

        for (i in direction.indices) {
            val nextX = x + direction[i].first
            val nextY = y + direction[i].second

            if (nextX !in map.indices || nextY !in map[nextX].indices || visited[map[nextX][nextY] - 'A']) {
                continue
            }

            visited[map[nextX][nextY] - 'A'] = true
            dfs(nextX, nextY, depth + 1, map, visited)
            visited[map[nextX][nextY] - 'A'] = false
        }

    }
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (r, c) = br.readLine().split(" ").map { it.toInt() }
    val map = Array(r) { _ ->
        val str = br.readLine()
        CharArray(c) { str[it] }
    }

    val solution1987 = Solution1987()
    bw.append("${solution1987.solution(map)}\n")
    bw.flush()

    br.close()
    bw.close()
}