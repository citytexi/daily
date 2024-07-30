package baekjoon.silver.solution_1269

private class Solution1269 {

    fun solution(
        a: Int,
        b: Int,
        map: HashMap<Int, Int>
    ): Int {
        var result = 0

        for (entry in map) {
            if (entry.value == 1) {
                result += 1
            }
        }

        return result
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (a, b) = br.readLine().split(" ").map(String::toInt)
    val map = hashMapOf<Int, Int>()

    repeat(2) {
        br.readLine().split(" ").forEach {
            val key = it.toInt()
            map[key] = map.getOrDefault(key, 0) + 1
        }
    }

    bw.append("${Solution1269().solution(a, b, map)}\n")
    bw.flush()

    br.close()
    bw.close()
}