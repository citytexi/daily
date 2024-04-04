package algorithm.sorting.heap_sort

private class HeapSortExample {
    fun solution(arr: IntArray): IntArray {
        heapSort(arr)
        return arr
    }

    private fun heapSort(a: IntArray) {
        var heapSize = a.lastIndex

        for (i in 1 until a.lastIndex) {
            println("entry i = $i ${a.toList()}")
            swap(1, heapSize, a)
            println("swap i = $i ${a.toList()}")
            heapSize -= 1
            downHeap(heapSize, a)
            println("downHeap i = $i ${a.toList()}\n")
        }
    }

    private fun swap(a: Int, b: Int, arr: IntArray) {
        val temp = arr[b]
        arr[b] = arr[a]
        arr[a] = temp
    }

    private fun downHeap(
        heapSize: Int,
        arr: IntArray,
        root: Int = 1
    ) {
        val left = root * 2
        val right = root * 2 + 1

        val leftValue = arr.getOrNull(left)
        val rightValue = arr.getOrNull(right)

        if (left !in 1 .. heapSize || right !in 1 .. heapSize) {
            return
        }

        when (rightValue) {
            null -> {
                // 왼쪽은 null or nonNull
                if (leftValue != null) {
                    if (arr[root] < leftValue) {
                        swap(root, left, arr)
                        downHeap(heapSize, arr, left)
                    }
                }
            }
            else -> {
                if (leftValue!! > rightValue)  {
                    if (arr[root] < leftValue) {
                        swap(root, left, arr)
                        downHeap(heapSize, arr, left)
                    }
                } else {
                    if (arr[root] < rightValue) {
                        swap(root, right, arr)
                        downHeap(heapSize, arr, right)
                    }
                }
            }
        }
    }
}

private fun main() {
    val array = intArrayOf(0, 90, 60, 80, 50, 30, 70, 10, 20, 40)

    val bw = System.out.bufferedWriter()
    bw.append("${HeapSortExample().solution(array).toList()}\n")
    bw.flush()
    bw.close()
}