package baekjoon.gold.solution_4195

private class Solution4195(
    val parent: IntArray,
    val friends: IntArray
) {

    fun find(a: Int): Int = when (parent[a]) {
        a -> parent[a]
        else -> {
            parent[a] = find(parent[a])
            parent[a]
        }
    }

    fun union(a: Int, b: Int) : Int {
        val x = find(a)
        val y = find(b)

        if (x != y) {
            parent[y] = x
            friends[x] += friends[y]
        }

        return friends[x]
    }

}

private fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val t = br.readLine().toInt()

    repeat(t) {
        val f = br.readLine().toInt()

        val solution4195 = Solution4195(
            parent = IntArray(f * 2){ it },
            friends = IntArray(f * 2){ 1 }
        )

        val hashMap = hashMapOf<String, Int>()
        var index = 0

        repeat(f) {
            val (a, b) = br.readLine().split(" ")

            if (hashMap[a] == null) {
                hashMap[a] = index++
            }

            if (hashMap[b] == null) {
                hashMap[b] = index++
            }

            bw.append("${solution4195.union(hashMap[a]!!, hashMap[b]!!)}\n")
        }
    }

    bw.flush()

    br.close()
    bw.close()
}