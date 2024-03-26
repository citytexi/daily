package algorithm.sorting.shell_sort

private class ShellSortExample {
    fun solution(a: IntArray) {
        val sorted = shellSort(a)
        println(sorted.toList())
    }

    private fun shellSort(a: IntArray): IntArray {
        val h = (a.size / 3 downTo 1)
        for (gap in h) {
            println("gap = $gap start, a = ${a.toList()}\n")
            for (i in gap until a.size) {
                println("group$i start, a = ${a.toList()}")
                val currentElement = a[i]
                var j = i
                while (j >= gap && a[j - gap] > currentElement) {
                    a[j] = a[j - gap]
                    j -= gap
                }
                a[j] = currentElement
                println("group$i end, a = ${a.toList()}\n")
            }
            println("gap = $gap end, a = ${a.toList()}\n")
        }

        return a
    }
}

private fun main() {
    val example = ShellSortExample()
    val a = intArrayOf(30, 60, 90, 10, 40, 80, 40, 20, 10, 60, 50, 30, 40, 90, 80)
    example.solution(a)
}