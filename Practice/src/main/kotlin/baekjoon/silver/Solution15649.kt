package baekjoon.silver

/*
문제
자연수 N과 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.

1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열
입력
첫째 줄에 자연수 N과 M이 주어진다. (1 ≤ M ≤ N ≤ 8)

출력
한 줄에 하나씩 문제의 조건을 만족하는 수열을 출력한다. 중복되는 수열을 여러 번 출력하면 안되며, 각 수열은 공백으로 구분해서 출력해야 한다.

수열은 사전 순으로 증가하는 순서로 출력해야 한다.

예제
3 1

4 2

4 4

* */

private lateinit var numbers: IntArray
private lateinit var visited: BooleanArray

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    numbers = IntArray(n)
    visited = BooleanArray(n) { false }
    dfs(N = n, M = m)

    br.close()
    bw.close()
}

private fun dfs(N: Int, M: Int, depth: Int = 0) {
    if (depth == M) {
        numbers.forEach { num ->
            if (num != 0) {
                print("$num ")
            }
        }
        println()
        return
    }

    for (i in 0 until N) {
        if (!visited[i]) {
            visited[i] = true
            numbers[depth] = i + 1
            dfs(N, M, depth + 1)
            visited[i] = false
        }
    }
}