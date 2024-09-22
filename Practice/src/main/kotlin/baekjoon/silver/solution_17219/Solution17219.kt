package baekjoon.silver.solution_17219

import java.util.*

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (n, m) = br.readLine().split(" ").map(String::toInt)

    val map = TreeMap<String,String>()

    repeat(n) {
        val (key, value) =  br.readLine().split(" ")
        map[key] = value
    }

    repeat(m) {
        bw.append("${map[br.readLine()]}\n")
    }
    bw.flush()

    br.close()
    bw.close()
}