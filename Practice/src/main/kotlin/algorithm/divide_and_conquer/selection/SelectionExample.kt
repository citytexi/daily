package algorithm.divide_and_conquer.selection

private class SelectionExample {
    fun solution(nums: IntArray, k: Int): Int = selection(a = nums, k = k)

    private fun selection(
        a: IntArray,
        k: Int,
        left: Int = 0,
        right: Int = a.lastIndex
    ): Int {
        val pivotIndex = partition(a, left, right)
        val smallGroupSize = pivotIndex - left

        if (k <= smallGroupSize) {
            return selection(
                a = a,
                k = k,
                left = left,
                right = pivotIndex - 1
            )
        } else if (k == smallGroupSize + 1) {
            return a[pivotIndex]
        } else {
            return selection(
                a = a,
                k = k - smallGroupSize - 1,
                left = pivotIndex + 1,
                right = right
            )
        }
    }

    private fun partition(
        a: IntArray,
        left: Int,
        right: Int
    ): Int {
        val pivotIndex = (left .. right).random()
        if (pivotIndex != left) {
            swap(a, pivotIndex, left)
        }

        val pivot = a[left]
        var tempLeft = left + 1
        var tempRight = right

        while (true) {
            while (tempLeft < right && a[tempLeft] <= pivot) {
                tempLeft++
            }
            while (tempRight > left && a[tempRight] >= pivot) {
                tempRight--
            }
            if (tempLeft >= tempRight) {
                break
            }

            swap(a, tempLeft, tempRight)
            tempLeft++
            tempRight--
        }

        swap(a, left, tempRight)
        return tempRight
    }

    private fun swap(
        arr: IntArray,
        a: Int,
        b: Int
    ) {
        val temp = arr[a]
        arr[a] = arr[b]
        arr[b] = temp
    }

}

private fun main() {
    val solution = SelectionExample()

    var k = 1
    while (true) {
        val nums = intArrayOf(6, 3, 11, 9, 12, 2, 8, 15, 18, 10, 7, 14)

        val a = solution.solution(
            nums = nums,
            k = k
        )

        k += 1

        println(a)

        if (k > nums.size) {
            break
        }
    }
}