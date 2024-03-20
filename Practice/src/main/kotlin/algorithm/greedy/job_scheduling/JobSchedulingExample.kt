package algorithm.greedy.job_scheduling

private class JobSchedulingExample {
    fun solution(t: List<List<Int>>): List<List<List<Int>>> = jobScheduling(t)

    private fun jobScheduling(t: List<List<Int>>): List<List<List<Int>>> {
        val l = t.sortedBy { it[0] }.toMutableList()
        val result = mutableListOf<MutableList<List<Int>>>()

        while (l.isNotEmpty()) {
            val firstT = l.removeFirst()

            var addSuccess = false
            for (i in result.indices) {
                val last = result[i].last()
                if (last[1] <= firstT[0]) {
                    result[i].add(firstT)
                    addSuccess = true
                    break
                }
            }

            if (!addSuccess) {
                result.add(mutableListOf(firstT))
            }
        }

        return result
    }
}

private fun main() {
    val example = JobSchedulingExample()

    val t1 = listOf(7, 8)
    val t2 = listOf(3, 7)
    val t3 = listOf(1, 5)
    val t4 = listOf(5, 9)
    val t5 = listOf(0, 2)
    val t6 = listOf(6, 8)
    val t7 = listOf(1, 6)

    val t = listOf(t1, t2, t3, t4, t5, t6, t7)

    println(example.solution(t))
}