package algorithm.sorting.insertion_sort

private class InsertionSortExample {
    fun solution(a: IntArray) {
        val sortedA = insertionSort(a)
        println(sortedA.toList())
    }

    private fun insertionSort(a: IntArray): IntArray {
        for (i in 1 until a.size) {
            println("$i pass start, a = ${a.toList()}")
            val currentElement = a[i]
            var j = i - 1
            while (j >= 0 && a[j] > currentElement) {
                a[j + 1] = a[j]
                j -= 1
            }
            a[j + 1] = currentElement
            println("$i pass end, a = ${a.toList()}")
            println("정렬된 부분, a = ${a.sliceArray(0 ..  i).toList()}")
            println("정렬 안된 부분, a = ${a.sliceArray(i + 1 until  a.size).toList()}")
            println()
        }

        return a
    }
}

private fun main() {
    val example = InsertionSortExample()
    val a = intArrayOf(40, 10, 50, 90, 20, 80, 30, 60)
    example.solution(a)
}