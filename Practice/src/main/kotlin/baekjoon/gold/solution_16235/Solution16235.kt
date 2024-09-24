package baekjoon.gold.solution_16235

private class Solution16235 {
    private val directions = arrayOf(
        0 to 1,
        1 to 0,
        0 to -1,
        -1 to 0,
        1 to 1,
        1 to -1,
        -1 to 1,
        -1 to -1,
    )

    fun solution(
        k: Int,
        energyGraph: Array<IntArray>,
        a: Array<IntArray>,
        trees: ArrayDeque<TreeNode>
    ): Int {
        var year = 0

        while (year < k) {
            val temp = ArrayDeque<TreeNode>()

            runSpring(energyGraph, trees, temp)
            runSummer(energyGraph, temp)
            runAutumn(energyGraph, trees, temp)
            runWinter(energyGraph, a)

            year += 1
        }

        return trees.size
    }

    private fun runSpring(
        energyGraph: Array<IntArray>,
        trees: ArrayDeque<TreeNode>,
        temp: ArrayDeque<TreeNode>,
    ) {
        for (i in trees.indices) {
            val tree = trees.removeFirst()

            when {
                energyGraph[tree.row][tree.col] < tree.age -> temp.add(tree)
                else -> {
                    energyGraph[tree.row][tree.col] -= tree.age
                    trees.addLast(TreeNode(tree.row, tree.col, tree.age + 1))
                }
            }
        }
    }

    private fun runSummer(
        energyGraph: Array<IntArray>,
        temp: ArrayDeque<TreeNode>,
    ) {
        for (tree in temp) {
            energyGraph[tree.row][tree.col] += tree.age / 2
        }
        temp.clear()
    }

    private fun runAutumn(
        energyGraph: Array<IntArray>,
        trees: ArrayDeque<TreeNode>,
        temp: ArrayDeque<TreeNode>,
    ) {
        for (tree in trees) {
            if (tree.age % 5 != 0) {
                continue
            }

            for (direction in directions) {
                val nextRow = tree.row + direction.first
                val nextCol = tree.col + direction.second

                if (nextRow !in energyGraph.indices || nextCol !in energyGraph.indices) {
                    continue
                }

                temp.add(TreeNode(nextRow, nextCol, 1))
            }
        }

        for (tree in temp) {
            trees.addFirst(tree)
        }
    }

    private fun runWinter(
        energyGraph: Array<IntArray>,
        a: Array<IntArray>,
    ) {
        for (row in energyGraph.indices) {
            for (col in energyGraph[row].indices) {
                energyGraph[row][col] += a[row][col]
            }
        }
    }

    data class TreeNode(
        val row: Int,
        val col: Int,
        val age: Int
    ) : Comparable<TreeNode> {
        override fun compareTo(other: TreeNode): Int = this.age - other.age
    }
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (n, m, k) = br.readLine().split(" ").map(String::toInt)

    val energyGraph = Array(n) { IntArray(n) { 5 } }
    val a = Array(n) { br.readLine().split(" ").map(String::toInt).toIntArray() }
    val trees = ArrayDeque<Solution16235.TreeNode>()

    repeat(m) {
        val (x, y, z) = br.readLine().split(" ").map(String::toInt)
        trees.add(
            Solution16235.TreeNode(
                row = x - 1,
                col = y - 1,
                age = z
            )
        )
    }

    bw.append("${Solution16235().solution(k, energyGraph, a, trees)}")
    bw.flush()

    br.close()
    bw.close()
}