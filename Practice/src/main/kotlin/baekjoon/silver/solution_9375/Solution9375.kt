package baekjoon.silver.solution_9375

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    repeat(br.readLine().toInt()) {
        val map = hashMapOf<String, Int>()

        repeat(br.readLine().toInt()) {
            val clothes = br.readLine().split(" ")[1]

            map[clothes] = map.getOrDefault(clothes, 0) + 1
        }

        var result = 1
        map.values.forEach { result *= (it + 1) }
        bw.append("${result - 1}\n")
    }

    bw.flush()

    br.close()
    bw.close()
}