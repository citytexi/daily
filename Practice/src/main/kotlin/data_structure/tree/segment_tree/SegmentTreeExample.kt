package data_structure.tree.segment_tree

private class SegmentTreeExample {
    private lateinit var originalArray: IntArray
    private lateinit var segmentTree: IntArray

    fun example(numbers: IntArray) {
        originalArray = numbers
        initSegmentTree()

        println("${sumWithRange(0, 4)}\n")

        updateSegmentTreeByIndex(0, 5)
    }

    private fun initSegmentTree() {
        segmentTree = IntArray(originalArray.size * 2)

        for (i in originalArray.size until segmentTree.size) {
            // 맨 아래 자식 노드 채우기
            segmentTree[i] = originalArray[i - originalArray.size]
        }

        // [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 3, 6, 7, 2, 5, 8, 10, 1, 9]
        println("${segmentTree.toList()}\n")

        for (i in (segmentTree.size / 2) - 1 downTo 1) {
            println("$i, ${segmentTree[i * 2]} + ${segmentTree[(i * 2) + 1]}")
            segmentTree[i] = segmentTree[i * 2] + segmentTree[(i * 2) + 1]
        }

        // [0, 55, 35, 20, 28, 7, 13, 7, 18, 10, 4, 3, 6, 7, 2, 5, 8, 10, 1, 9]
        println("${segmentTree.toList()}\n")
    }

    private fun updateSegmentTreeByIndex(
        index: Int,
        value: Int
    ) {
        var treeIndex = index + (segmentTree.size / 2)

        originalArray[index] = value
        segmentTree[treeIndex] = value
        while (treeIndex > 1) {
            treeIndex /= 2
            segmentTree[treeIndex] = segmentTree[treeIndex * 2] + segmentTree[(treeIndex * 2) + 1]
        }

        println("segmentTree: ${segmentTree.toList()}")
    }

    private fun sumWithRange(
        start: Int,
        end: Int
    ): Int {
        var left = start + (segmentTree.size / 2)
        var right = end + (segmentTree.size / 2)

        var result = 0

        while (left <= right) {
            if (left % 2 == 1) {
                println("left = $left, value = ${segmentTree[left]}")
                result += segmentTree[left]
                left += 1
            }
            if (right % 2 == 0) {
                println("right = $right, value = ${segmentTree[right]}")
                result += segmentTree[right]
                right -= 1
            }
            left /= 2
            right /= 2
        }

        return result
    }
}


private fun main() {
    val intArray = intArrayOf(4, 3, 6, 7, 2, 5, 8, 10, 1, 9)
    SegmentTreeExample().example(intArray)
}