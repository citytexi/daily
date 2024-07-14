package baekjoon.silver.solution_1620

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (n, m) = br.readLine().split(" ").map(String::toInt)
    val nameMap = hashMapOf<String, Int>()
    val numMap = hashMapOf<Int, String>()

    repeat(n) {
        val name = br.readLine()
        nameMap[name] = it + 1
        numMap[it + 1] = name
    }

    repeat(m) {
        val input = br.readLine()

        when (val num = input.toIntOrNull()) {
            null -> bw.write("${nameMap[input]}\n")
            else -> bw.write("${numMap[num]}\n")
        }
    }
    bw.flush()

    br.close()
    bw.close()
}