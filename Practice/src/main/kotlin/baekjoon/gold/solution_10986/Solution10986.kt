package baekjoon.gold.solution_10986

private class Solution10986 {

    fun solution(
        n: Int,
        m: Int,
        a: LongArray
    ): Long {
        val count = LongArray(m)

        for (i in a.indices) {
            a[i] = (a[i] + (a.getOrNull(i - 1) ?: 0)) % m
            count[a[i].toInt()] = count[a[i].toInt()] + 1
        }

        var answer = count[0]

        for(i in 0 until m){
            answer += count[i] * (count[i] - 1) / 2
        }

        return answer
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (n, m) = br.readLine().split(" ").map(String::toInt)
    val a = br.readLine().split(" ").map(String::toLong).toLongArray()

    bw.write("${Solution10986().solution(n, m, a)}\n")
    bw.flush()

    br.close()
    bw.close()
}