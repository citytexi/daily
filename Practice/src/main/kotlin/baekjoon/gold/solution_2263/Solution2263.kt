package baekjoon.gold.solution_2263

private class Solution2263 {

    fun solution(
        n: Int,
        inOrder: IntArray,
        postOrder: IntArray,
        map: MutableMap<Int, Int>
    ): String {
        val result = mutableListOf<Int>()

        preOrder(
            inOrderLeftIndex = 0,
            inOrderRightIndex = n - 1,
            postOrderLeftIndex = 0,
            postOrderRightIndex = n - 1,
            inOrder = inOrder,
            postOrder = postOrder,
            map = map,
            current = result
        )

        return result.joinToString(" ")
    }

    private fun preOrder(
        inOrderLeftIndex: Int,
        inOrderRightIndex: Int,
        postOrderLeftIndex: Int,
        postOrderRightIndex: Int,
        inOrder: IntArray,
        postOrder: IntArray,
        map: MutableMap<Int, Int>,
        current: MutableList<Int>
    ){
        if (inOrderLeftIndex > inOrderRightIndex || postOrderLeftIndex > postOrderRightIndex) {
            return
        }

        val rootIndex = map[postOrder[postOrderRightIndex]] ?: return

        val left = rootIndex - inOrderLeftIndex

        current.add(postOrder[postOrderRightIndex])
        preOrder(
            inOrderLeftIndex = inOrderLeftIndex,
            inOrderRightIndex = rootIndex - 1,
            postOrderLeftIndex = postOrderLeftIndex,
            postOrderRightIndex = postOrderLeftIndex + left - 1,
            inOrder = inOrder,
            postOrder = postOrder,
            map = map,
            current
        )
        preOrder(
            inOrderLeftIndex = rootIndex + 1,
            inOrderRightIndex = inOrderRightIndex,
            postOrderLeftIndex = postOrderLeftIndex + left,
            postOrderRightIndex = postOrderRightIndex - 1,
            inOrder = inOrder,
            postOrder = postOrder,
            map = map,
            current
        )
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    val inOrder = br.readLine().split(" ").map(String::toInt).toIntArray()
    val postOrder = br.readLine().split(" ").map(String::toInt).toIntArray()
    val map = mutableMapOf<Int, Int>()

    for (i in inOrder.indices) {
        map[inOrder[i]] = i
    }

    bw.append(Solution2263().solution(n, inOrder, postOrder, map))
    bw.flush()

    br.close()
    bw.close()
}