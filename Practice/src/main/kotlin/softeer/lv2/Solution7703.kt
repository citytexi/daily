package softeer.lv2
private fun main() = with(System.`in`.bufferedReader()) {
    val bw = System.out.bufferedWriter()

    val n = readLine().toInt()
    val pairs = Array(n) { readLine().split(" ") }

    val answers = mutableListOf<Char>()
    for (i in 0 until n) {
        val (s, t) = pairs[i]
        val position = s.getXPosition()
        answers.add(t[position].toUpperCase())
    }

    bw.write(answers.joinToString("") + "\n")
    bw.flush()
    bw.close()
}

private fun String.getXPosition(): Int {
    for (i in this.indices) {
        when (this[i]) {
            'x', 'X' -> return i
            else -> Unit
        }
    }
    return 0
}