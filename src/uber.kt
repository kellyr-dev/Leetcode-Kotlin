import java.sql.SQLOutput
import java.util.Collections
import java.util.PriorityQueue
import kotlin.math.min

class Uber {

    // 238. Product of Array Except Self
    fun productExceptSelf(nums: IntArray): IntArray {
        // I have 3 cases
        // * No Zeros in the array (prefix)
        // * One zero in the array (zero for all value except the zero index)
        // * More than One Zero (result is zero for the array)

        var multi = 1
        var zero = -1
        var zeroCount = 0

        for (i in 0 until nums.size){
            if (nums[i] == 0){
                zero = i
                zeroCount += 1
                multi = multi * 1
            } else {
                multi = multi * nums[i]
            }
        }

        val result = IntArray(nums.size)

        if (zeroCount > 1){ // case when we more than two index with 0 value
            return IntArray(nums.size)
        } else if (zeroCount == 1){
            result[zero] = multi
        } else {
            for (j in 0 until nums.size){
                result[j] = multi / nums[j]
            }
        }

        return result
    }

    // 121. Best Time to Buy and Sell Stock
    fun maxProfit(prices: IntArray): Int {

        if (prices.size <= 1) {
            return 0
        }

        var right = 1
        var left = 0
        var maxProfit = 0

        while (right < prices.size){

            if (prices[right] <= prices[left]){
                left = right
                right += 1
            } else {
                maxProfit = Math.max(maxProfit, prices[right] - prices[left])
                right += 1
            }

        }
        return maxProfit

    }

    // 322. Coin Change
    fun coinChange(coins: IntArray, amount: Int): Int {

        // [1,2,5] amount=11
        //  amount-1, count +=1
        //  amount, count += 0

        val dp = HashMap<Pair<Int, Int>, Int>()

        fun helper(coins: IntArray, amount: Int, index: Int): Int{

            if (amount == 0){
                return 0
            }

            val key = Pair(index, amount)
            if (dp.containsKey(key)) return dp[key]!!

            if (amount < 0 ){
                return Int.MAX_VALUE - 1
            }

            if (index == coins.size){
                return Int.MAX_VALUE - 1
            }

            var r = minOf( 1 + helper(coins, amount - coins[index], index), helper(coins, amount, index+1) )
            dp[key] = r
            return r

        }

        val result = helper(coins, amount, 0)
        if (result == Int.MAX_VALUE - 1){
            return -1
        } else {
            return result
        }


    }

    // 1005. Maximize Sum Of Array After K Negations
    fun largestSumAfterKNegations(nums: IntArray, k: Int): Int {

        val pq = PriorityQueue<Int>()

        for (i in 0 until nums.size){
            pq.add(nums[i])
        }
        var aux = k
        while (aux > 0){
            var value = pq.poll()
            value *= -1
            pq.add(value)
            aux -= 1
        }

        return pq.sum()
    }

    // 2008. Maximum Earnings From Taxi
    fun maxTaxiEarnings(n: Int, rides: Array<IntArray>): Long {
        var suma : Long = 0
        rides.sortBy { it[0] }

        val ridesMerge = ArrayList<IntArray>()
        ridesMerge.add(rides[0])
        var prev = rides[0]

        for (i in 1 until rides.size){
            if (rides[i][0] < prev[1]){ // if there is a collision
                // if yes, I have to check what will be the most profitable
                var preValue = prev[1] - prev[0] + prev[2]
                var currentValue = rides[i][1] - rides[i][0] + rides[i][2]

                if (currentValue > preValue){
                    ridesMerge.removeLast()
                    ridesMerge.add(rides[i])
                    prev = rides[i]
                }

            } else {
                // update prev
                ridesMerge.add(rides[i])
                prev = rides[i]
            }
        }

        for (interval in ridesMerge){
            println("(${interval[0]},${interval[1]},${interval[2]})")
            suma += interval[1] - interval[0] + interval[2]
        }

        return suma
    }

}

fun main(){


    var coins = intArrayOf(1,2,5)
    var amount = 11
    val nums = intArrayOf(2,-3,-1,5,-4)
    val rides = arrayOf(intArrayOf(2,5,4), intArrayOf(1,5,1))
    val k = 2
    val testClass = Uber()
    println(testClass.maxTaxiEarnings(k, rides))

    // [[2,3,6],[8,9,8],[5,9,7],[8,9,1],[2,9,2],[9,10,6],[7,10,10],[6,7,9],[4,9,7],[2,3,1]]
    // n = 10
    // out = 33

}


    // 729. My Calendar I

class MyCalendar() {

    val bookings = ArrayList<Pair<Int,Int>>() // start & end
    fun book(startTime: Int, endTime: Int): Boolean {

        for (book in bookings){

            if (startTime < book.second){
                if ( (endTime > book.first) ){
                    return false
                }
            }
        }
        bookings.add( Pair(startTime, endTime) )
        println("${bookings}")
        return true

    }
}

data class IndexAndValue(var index : Int, var value: Int)

    // 1429. First Unique Number
class FirstUnique(nums: IntArray) {

    val exist = HashMap<Int, Pair<Int, Int>>()
        val comparedByFreq : Comparator<IndexAndValue> = compareBy{ it.index }
    val queue : PriorityQueue<IndexAndValue> = PriorityQueue(comparedByFreq)
    var globalIndex = 0
        init {
            for (i in 0 until nums.size){
                if (!(exist.containsKey(nums[i]))){
                    exist[nums[i]] = Pair(1,globalIndex)
                    globalIndex += 1
                }else {
                    val auxPair = exist[nums[i]]!!
                    val newPair = Pair(auxPair.first+1, auxPair.second)
                    exist[nums[i]] = newPair
                }
            }

            for ((key, value) in exist){
                if (value.first == 1){
                    queue.add( IndexAndValue(value.second, key))
                }
            }

        }

    fun showFirstUnique(): Int {

        if (queue.isNotEmpty()){
            return queue.peek().value
        } else {
            return -1
        }

    }

    fun add(value: Int) {

        if (exist.containsKey(value)){
            val pair = IndexAndValue(exist[value]!!.second, value)
            if (queue.contains(pair)){
                println("contains!!")
                queue.remove(pair)
            }

        }else {

            exist[value] = Pair(1,globalIndex)
            val auxPair = IndexAndValue(globalIndex, value)
            queue.add(auxPair)
            globalIndex += 1

        }

    }

}