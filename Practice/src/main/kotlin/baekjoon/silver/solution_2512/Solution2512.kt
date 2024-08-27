package baekjoon.silver.solution_2512

private class Solution2512 {

    fun solution(
        n: Int,
        requestList: List<Int>,
        max: Int
    ): Int {
        var right = requestList[n - 1]
        var left = 1
        var pivot = 0

        while (right >= left) {
            pivot = (right + left) / 2

            var totalBudget = max
            for (budge in requestList) {
                totalBudget -= if (budge > pivot) pivot else budge
            }

            if (totalBudget >= 0) {
                left = pivot + 1
            } else {
                right = pivot - 1
            }
        }

        return right
    }

}


private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    val requestList = br.readLine().split(" ").map(String::toInt).sorted()
    val max = br.readLine().toInt()

    bw.append("${Solution2512().solution(n, requestList, max)}")
    bw.flush()

    br.close()
    bw.close()
}