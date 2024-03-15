package data_structure.tree.tree_traversal

private data class TreeNode<T>(
    var data: T,
    var left: TreeNode<T>? = null,
    var right: TreeNode<T>? = null
)


/*
7
A B C
B D .
C E F
E . .
F . G
D . .
G . .
*/

private class TreeTraversalExample {
    var root : TreeNode<String>? = null

    fun addNode(
        data: String,
        left: String,
        right: String
    ) {
        when (root) {
            null -> {
                if (data != ".") {
                    root = TreeNode(data)
                }
                if (left != ".") {
                    root?.left = TreeNode(left)
                }
                if (right != ".") {
                    root?.right = TreeNode(right)
                }
            }
            else -> root?.let {
                search(
                    it,
                    data,
                    left,
                    right
                )
            }
        }
    }

    fun search(
        root: TreeNode<String>,
        data: String,
        left: String,
        right: String
    ) {
        when (root.data) {
            data -> {
                if (left != ".") {
                    root.left = TreeNode(left)
                }
                if (right != ".") {
                    root.right = TreeNode(right)
                }
            }
            else -> {
                root.left?.let {
                    search(
                        root = it,
                        data = data,
                        left = left,
                        right = right
                    )
                }
                root.right?.let {
                    search(
                        root = it,
                        data = data,
                        left = left,
                        right = right
                    )
                }
            }
        }
    }

    fun preOrder(root: TreeNode<String>?) {
        root?.let { nonNullRoot ->
            print(nonNullRoot.data)
            nonNullRoot.left?.let { preOrder(it) }
            nonNullRoot.right?.let { preOrder(it) }
        }
    }

    fun inOrder(root: TreeNode<String>?) {
        root?.let { nonNullRoot ->
            nonNullRoot.left?.let { inOrder(it) }
            print(nonNullRoot.data)
            nonNullRoot.right?.let { inOrder(it) }
        }
    }

    fun postOrder(root: TreeNode<String>?) {
        root?.let { nonNullRoot ->
            nonNullRoot.left?.let { postOrder(it) }
            nonNullRoot.right?.let { postOrder(it) }
            print(nonNullRoot.data)
        }
    }

    fun levelOrder(root: TreeNode<String>?) {
        root?.let { nonNullRoot ->
            val deque = ArrayDeque<TreeNode<String>>()
            deque.add(nonNullRoot)
            while (deque.isNotEmpty()) {
                val node = deque.removeFirst()
                print(node.data)
                node.left?.let { deque.add(it) }
                node.right?.let { deque.add(it) }
            }
        }
    }
}

private fun main() {
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val example = TreeTraversalExample()

    for(i in 0 until n){
        val (data, left, right) = br.readLine().split(" ")
        example.addNode(data, left, right)
    }

    example.preOrder(example.root)
    println()
    example.inOrder(example.root)
    println()
    example.postOrder(example.root)
    println()
    example.levelOrder(example.root)

    br.close()
}