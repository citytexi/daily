# 트리 순회 (Tree Traversal)

- 트리 구조의 모든 노드를 방문하는 방법
- 트리의 모든 노드를 한 번씩 방문하고 각 노드에 대해 원하는 작업을 수행하는 방법

## **전위 순회 (Preorder traversal)**

- 현재 노드에 대한 작업을 수행
- 이후 각 자식 노드를 순서대로 방문하여 작업을 수행

### Example

```kotlin
fun preOrder(root: TreeNode<String>) {
    print(root.data)
    root.left?.let { preOrder(it) }
    root.right?.let { preOrder(it) }
}
```

## **중위 순회 (Inorder traversal)**

- 왼쪽 자식 노드를 순회
- 현재 노드에 대한 작업을 수행
- 오른쪽 자식 노드를 순회

### Example

```kotlin
fun inOrder(root: TreeNode<String>) {
    root.left?.let { inOrder(it) }
    print(root.data)
    root.right?.let { inOrder(it) }
}
```

## **후위 순회 (Postorder traversal)**

- 왼쪽 자식 노드를 순회
- 오른쪽 자식 노드를 순회
- 현재 노드에 대한 작업을 수행

### Example

```kotlin
fun postOrder(root: TreeNode<String>) {
    root.left?.let { postOrder(it) }
    root.right?.let { postOrder(it) }
    print(root.data)
}
```

## **레벨 순회 (Level-order traversal)**

- 위에서 아래로 순서대로
- 한 번에 같은 레벨의 모든 노드를 방문

### Example

```kotlin
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
```

## Example All Code

```kotlin
private data class TreeNode<T>(
    var data: T,
    var left: TreeNode<T>? = null,
    var right: TreeNode<T>? = null
)

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
}
```