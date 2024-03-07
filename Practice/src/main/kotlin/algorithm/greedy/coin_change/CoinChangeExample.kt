package algorithm.greedy.coin_change

private class CoinChangeExample {

    /**
     * @param w 거스름돈 액수
     * @return 거스름돈 액수에 대한 최소 동전 수
     * **/
    fun coinChange(w: Int): Int {
        var change = w
        var (n500, n100, n50, n10, n1) = intArrayOf(0, 0, 0, 0, 0)

        while (change >= 500) {
            change -= 500
            n500++
        }

        while (change >= 100) {
            change -= 100
            n100++
        }

        while (change >= 50) {
            change -= 50
            n50++
        }

        while (change >= 10) {
            change -= 10
            n10++
        }

        while (change >= 1) {
            change -= 1
            n1++
        }

        return n500 + n100 + n50 + n10 + n1
    }
}

private fun main() {
    val coinChangeExample = CoinChangeExample()

    println(coinChangeExample.coinChange(760))
}