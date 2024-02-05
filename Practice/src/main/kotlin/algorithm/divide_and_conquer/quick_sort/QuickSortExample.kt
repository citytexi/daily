package algorithm.divide_and_conquer.quick_sort

private class QuickSortExample {
    fun solution(arr: IntArray): IntArray {
        quickSort(arr, 0, arr.size - 1)
        return arr
    }

    private fun quickSort(
        arr: IntArray,
        left: Int,
        right: Int
    ) {
        if(left < right) {
            val p = partition(arr, left, right)
            quickSort(
                arr = arr,
                left = left,
                right = p - 1
            )
            quickSort(
                arr = arr,
                left = p + 1,
                right = right
            )
        }
    }


    private fun partition(
        arr: IntArray,
        left: Int,
        right: Int
    ): Int {
        val pivot = (left + right) / 2
        swap(arr, left, pivot)

        var i = left
        var j = right + 1

        while (true) {
            do {
                i += 1
            } while (i < arr.size && arr[i] < arr[left])
            do {
                j -= 1
            } while (j >= 0 && arr[j] > arr[left])

            if (i >= j) {
                swap(arr, left, j)
                return j
            } else {
                swap(arr, i, j)
            }
        }
    }

    private fun swap(
        arr: IntArray,
        i: Int,
        j: Int
    ) {
        val temp = arr[i]
        arr[i] = arr[j]
        arr[j] = temp
    }
}

private fun main() {
    val solution = QuickSortExample()
    println(solution.solution(intArrayOf(6, 3, 11, 9, 12, 2, 8, 15, 18, 10, 7, 14)).toList())

    val size = (Math.random() * 1000).toInt()
    val intArr = IntArray(size) { 0 }
    for (i in 0 until size) {
        intArr[i] = (Math.random() * 10000).toInt()
    }
    println(solution.solution(intArr).toList())
}