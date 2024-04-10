package baekjoon.gold

private class Solution11729 {
    private val stringBuilder = StringBuilder()
    private var count = 0

    fun solution(n: Int): Pair<Int, String> {
        count = 0
        stringBuilder.clear()

        hanoiTower(n)

        return count to stringBuilder.toString()
    }

    private fun hanoiTower(
        number: Int,
        start: Int = 1,
        sub: Int = 2,
        end: Int = 3
    ) {
        if (number == 0) {
            return
        }

        count += 1

        hanoiTower(number - 1, start, end, sub)
        stringBuilder.append("$start $end\n")
        hanoiTower(number - 1, sub, start, end)
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()

    val (resultCount, resultMove) = Solution11729().solution(n)
    bw.append("${resultCount}\n")
    bw.append(resultMove)
    bw.flush()

    br.close()
    bw.close()
}