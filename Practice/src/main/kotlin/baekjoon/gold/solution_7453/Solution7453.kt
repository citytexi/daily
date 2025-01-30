package baekjoon.gold.solution_7453

private class Solution7453 {

    fun solution(
        start: LongArray,
        end: LongArray,
    ): Long {
        var answer = 0L

        var begin = 0
        var last = end.size - 1

        while (begin < start.size && last >= 0) {

            when {
                start[begin] + end[last] == 0L -> {
                    var i = begin
                    var firstCount = 0L
                    while (i < start.size && start[i] == start[begin]){
                        firstCount += 1
                        i += 1
                    }
                    begin = i

                    i = last
                    var secondCount = 0L
                    while (i >= 0 && end[i] == end[last]) {
                        secondCount += 1
                        i += -1
                    }
                    answer += firstCount * secondCount
                    last = i
                }

                start[begin] + end[last] < 0 -> {
                    begin += 1
                }

                else -> {
                    last += -1
                }
            }
        }

        return answer
    }
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    val arr = Array(4) { LongArray(n) }
    val start = LongArray(n*n)
    val end = LongArray(n*n)

    for (i in 0 until n) {
        val (a, b, c, d) = br.readLine().split(" ").map(String::toLong)
        arr[0][i] = a
        arr[1][i] = b
        arr[2][i] = c
        arr[3][i] = d
    }

    for (i in 0 until n) {
        for (j in 0 until n) {
            start[i * n + j] = arr[0][i] + arr[1][j]
            end[i * n + j] = arr[2][i] + arr[3][j]
        }
    }

    bw.append(Solution7453().solution(start.sortedArray(), end.sortedArray()).toString())
    bw.flush()

    br.close()
    bw.close()
}
