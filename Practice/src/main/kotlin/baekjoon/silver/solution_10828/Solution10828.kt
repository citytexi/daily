package baekjoon.silver.solution_10828

private class Solution10828 {
    private val deque = ArrayDeque<Int>()

    fun push(x: Int) {
        deque.add(x)
    }

    fun pop(): Int = when (deque.size) {
        0 -> -1
        else -> deque.removeLast()
    }

    fun size(): Int = deque.size

    fun empty(): Int = when (deque.isEmpty()) {
        true -> 1
        false -> 0
    }

    fun top(): Int = when (deque.size) {
        0 -> -1
        else -> deque.last()
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val solution10828 = Solution10828()

    repeat(br.readLine().toInt()) {
        val str = br.readLine()
        when (str.length) {
            3 -> when (str) {
                "top" -> bw.append("${solution10828.top()}\n")
                "pop" -> bw.append("${solution10828.pop()}\n")
            }
            4 -> bw.append("${solution10828.size()}\n")
            5 -> bw.append("${solution10828.empty()}\n")
            else -> solution10828.push(str.split(" ")[1].toInt())
        }
    }
    bw.flush()

    br.close()
    bw.close()
}