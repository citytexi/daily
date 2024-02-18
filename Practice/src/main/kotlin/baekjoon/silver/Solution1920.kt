package baekjoon.silver

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()

    val hashMap = hashMapOf<String, Int>()

    br.readLine().split(" ").forEach { hashMap[it] = 1 }

    val m = br.readLine().toInt()
    br.readLine().split(" ").forEach {
        bw.append("${hashMap.getOrDefault(it, 0)}\n")
    }
    bw.flush()
    br.close()
    bw.close()
}