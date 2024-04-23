package baekjoon.silver.solution_2606


private class Solution2606 {

    fun solution(computerMap: Array<Computer>): Int {
        val visited = BooleanArray(computerMap.size)
        virus(computerMap[0], computerMap, visited)
        return visited.count { it } - 1
    }

    private fun virus(
        computer: Computer,
        computerMap: Array<Computer>,
        visited: BooleanArray
    ) {
        visited[computer.num - 1] = true

        for (sub in computer.connect) {
            if (!visited[sub - 1]) {
                virus(computerMap[sub - 1], computerMap, visited)
            }
        }
    }

    data class Computer(
        val num: Int,
        val connect: MutableList<Int> = mutableListOf()
    )
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val computerArray = Array(br.readLine().toInt()) { Solution2606.Computer(it + 1) }
    repeat(br.readLine().toInt()) {
        val (a, b) = br.readLine().split(" ").map(String::toInt)
        computerArray[a - 1].connect.add(b)
        computerArray[b - 1].connect.add(a)
    }

    bw.append("${Solution2606().solution(computerArray)}")
    bw.flush()

    br.close()
    bw.close()
}