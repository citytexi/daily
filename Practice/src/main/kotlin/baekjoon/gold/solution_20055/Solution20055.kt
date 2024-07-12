package baekjoon.gold.solution_20055

private class Solution20055 {

    fun solution(
        n: Int,
        k: Int,
        a: IntArray
    ): Int {
        val visited = BooleanArray(n * 2)

        var currentK = k
        var result = 0

        while (currentK > 0) {
            result += 1

            val temp = a[n * 2 - 1]
            for (i in n * 2 - 1 downTo 1) {
                a[i] = a[i - 1]
            }
            a[0] = temp

            for (i in n - 1 downTo 1) {
                visited[i] = visited[i - 1]
            }
            visited[n - 1] = false
            visited[0] = false

            for (i in n - 1 downTo 1) {
                if (visited[i]) {
                    continue
                }

                if (!visited[i - 1]) {
                    continue
                }

                if (a[i] <= 0) {
                    continue
                }

                a[i] = a[i] - 1
                if (a[i] == 0) {
                    currentK -= 1
                }

                visited[i] = true
                visited[i - 1] = false
            }

            if (a[0] > 0) {
                a[0] = a[0] - 1
                if (a[0] == 0) {
                    currentK -= 1
                }
                visited[0] = true
            }
        }

        return result
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (n, k) = br.readLine().split(" ").map(String::toInt)
    val a = br.readLine().split(" ").map(String::toInt).toIntArray()

    bw.append("${Solution20055().solution(n, k, a)}\n")
    bw.flush()

    br.close()
    bw.close()
}