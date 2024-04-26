package baekjoon.silver.solution_10816

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val map = hashMapOf<Int, Int>()

    val n = br.readLine().toInt()
    br.readLine().split(" ").forEach {
        val num = it.toInt()
        map[num] = map.getOrDefault(num, 0) + 1
    }
    val m = br.readLine().toInt()
    br.readLine().split(" ").forEachIndexed { index, s ->
        when (index) {
            m - 1 -> bw.append("${map.getOrDefault(s.toInt(), 0)}")
            else -> bw.append("${map.getOrDefault(s.toInt(), 0)} ")
        }
    }
    bw.flush()

    br.close()
    bw.close()
}