package algorithm.greedy.set_cover

private class SetCoverExample {
    fun solution(
        u: List<Int>,
        f: MutableList<MutableList<Int>>
    ): List<List<Int>> = setCover(u, f)

    private fun setCover(
        u: List<Int>,
        f: MutableList<MutableList<Int>>,
    ): List<List<Int>> {
        val c = mutableListOf<MutableList<Int>>()
        var currentU = u

        while (currentU.isNotEmpty()) {
            f.maxByOrNull { fractionalF -> currentU.filter { it in fractionalF }.size }
                ?.let { s ->
                    currentU = currentU.filter { it !in s }
                    f.remove(s)
                    c.add(s)
                }
        }

        return c
    }
}

private fun main() {
    val example = SetCoverExample()
    val u = MutableList(10) { it + 1 }

    val s1 = mutableListOf(1, 2, 3, 8)
    val s2 = mutableListOf(1, 2, 3, 4, 8)
    val s3 = mutableListOf(1, 2, 3, 4)
    val s4 = mutableListOf(2, 3, 4, 5, 7, 8)
    val s5 = mutableListOf(4, 5, 6, 7)
    val s6 = mutableListOf(5, 6, 7, 9, 10)
    val s7 = mutableListOf(4, 5, 6, 7)
    val s8 = mutableListOf(1, 2, 4, 8)
    val s9 = mutableListOf(6, 9)
    val s10 = mutableListOf(6, 10)

    val f = mutableListOf(
        s1,
        s2,
        s3,
        s4,
        s5,
        s6,
        s7,
        s8,
        s9,
        s10
    )

    println(example.solution(u, f))
}