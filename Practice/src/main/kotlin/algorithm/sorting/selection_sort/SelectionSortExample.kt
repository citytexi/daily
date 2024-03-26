package algorithm.sorting.selection_sort

private class SelectionSortExample {
    fun solution(a: IntArray) {
        val sorted = selectionSort(a)
        println(sorted.toList())
    }

    private fun selectionSort(a: IntArray): IntArray {
        for (i in 0 until a.size - 1) {
            var min = i
            for (j in i + 1 until a.size) {
                if (a[j] < a[min]) {
                    min = j
                }
            }

            val temp = a[i]
            a[i] = a[min]
            a[min] = temp

            println("${i + 1} pass end, a = ${a.toList()}")
        }

        return a
    }
}

private fun main() {
    val example = SelectionSortExample()
    val a = intArrayOf(40, 10, 50, 90, 20, 80, 30, 60)
    example.solution(a)
}