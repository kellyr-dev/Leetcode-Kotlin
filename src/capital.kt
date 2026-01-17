import java.util.Comparator
import java.util.PriorityQueue
import kotlin.math.max
import kotlin.math.min

class Capital {

    // 896. Monotonic Array
    fun isMonotonic(nums: IntArray): Boolean{
        //[1,2,2,3]
        //     ^ ^
        if (nums.size <= 1){
            return true
        }
        return isIncreasing(nums) || isDecreasing(nums)
    }

    fun isIncreasing(nums: IntArray) : Boolean{
        var i = 0
        var j = 1

        while (j < nums.size){
            if (nums[i] <= nums[j]){
                j+= 1
                i+= 1

            } else {
                return false
            }
        }
        return true
    }

    fun isDecreasing(nums: IntArray): Boolean{
        var i = 0
        var j = 1

        while (j < nums.size){
            if (nums[i] >= nums[j]){
                j+= 1
                i+= 1

            } else {
                return false
            }
        }
        return true
    }

    // 2544. Alternating Digit Sum
    fun alternateDigitSum(n: Int): Int {

        var auxnum = n
        var stack = ArrayDeque<Int>()


        var num = auxnum.toString()
        println("Num: ${num}")

        var i = 0
        var suma = 0

        while (i < num.length){
            suma += num[i].digitToInt()
            i += 2
        }
        var j = 1
        var resta = 0
        while (j < num.length){
            resta += num[j].digitToInt()
            j += 2
        }

        return suma - resta
    }

    // 2672. Number of Adjacent Elements With the Same Color
    fun colorTheArray(n: Int, queries: Array<IntArray>): IntArray {

        val colors = IntArray(n) {0}
        val result = IntArray(queries.size) {0}
        var countPair = 0

        for (i in 0 .. queries.size-1){

            var color = queries[i][1]
            var index = queries[i][0]
            var oldColor = colors[index]

            if (oldColor != 0){

                // check leftSide
                if (index > 0 && colors[index - 1] == oldColor){
                    countPair--
                }
                // check rightSide
                if (index < colors.size-1 && colors[index+1] == oldColor){
                    countPair--
                }
            }

            colors[index] = color

            if (color != 0){
                if (index > 0 && color == colors[index-1]){
                    countPair++
                }
                if (index < colors.size-1 && color == colors[index+1]){
                    countPair++
                }
            }


            result[i] = countPair

        }

        return result
    }

    // 1052. Grumpy Bookstore Owner
    fun maxSatisfied(customers: IntArray, grumpy: IntArray, minutes: Int): Int {

        if (minutes >= customers.size){
            return customers.sum()
        }

        var i = 0
        var j = minutes-1
        var maxCount = 0
        var windowCount = 0

        for (h in 0 .. j){
            windowCount += customers[h]
        }

        maxCount = windowCount

        for (index in j+1 .. grumpy.size-1){
            if (grumpy[index] == 0){
                maxCount += customers[index]
            }
        }

        while (j < customers.size){
            var localCount = 0
            if (j+1 < customers.size){
                localCount = windowCount - customers[i] + customers[j+1]
                windowCount = localCount

                for (ith in 0 .. i){
                    if (grumpy[ith] == 0){
                        localCount += customers[ith]
                    }
                }

                for (jth in j+2 .. grumpy.size-1){
                    if (grumpy[jth] == 0){
                        localCount += customers[jth]
                    }
                }
            }
            maxCount = Math.max(maxCount, localCount)

            j++
            i++
        }

        return maxCount

    }

    // 11. Container With Most Water
    fun maxArea(height: IntArray): Int {

        var j = height.size-1
        var i = 0
        var mArea = 0
        var localArea = 0

        while (i < j){
            localArea = (j - i) * (min(height[j], height[i]))
            mArea = max(localArea, mArea)

            if (height[j] <= height[i]){
                j--
            } else {
                i++
            }

        }

        return mArea
    }

    // 1438. Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit
    fun longestSubarray(nums: IntArray, limit: Int): Int {

        // [1, 5, 6, 7, 8, 10, 6, 5, 6] p=4

        var i = 0
        var j = 0
        /*
            Pair<A, B>
            // A: is the Max or Min value
            // B: is the index

         */
        var comparedByValue : Comparator<Pair<Int,Int>> = compareBy { it.first }
        var pqMax : PriorityQueue<Pair<Int,Int>> = PriorityQueue(comparedByValue.reversed())
        var pqMin : PriorityQueue<Pair<Int,Int>> = PriorityQueue(comparedByValue)
        var longest = 0

        while (j < nums.size){

            pqMax.add(Pair(nums[j],j))
            pqMin.add(Pair(nums[j],j))

            if (Math.abs(pqMax.peek().first - pqMin.peek().first) <= limit ){
                longest = max(longest, j-i+1)
            } else {
                var valueInPq = i

                // update my Priorities Queues
                if (valueInPq == pqMax.peek().second) pqMax.poll()
                if (valueInPq == pqMin.peek().second) pqMin.poll()

                i++
            }
            j++

        }

        return longest
    }


}

fun main(args : Array<String>){


    val capitalTest = Capital()
    val arr1 = intArrayOf(1,2,2,3)
    val arr2 = intArrayOf(1000)
    val balance = longArrayOf(0)
    val testClass = Bank(balance)
    val intervals = arrayOf(intArrayOf(1,2), intArrayOf(2,3), intArrayOf(3,4), intArrayOf(1,3))
    val queries = arrayOf(intArrayOf(0,2), intArrayOf(1,2), intArrayOf(3,1), intArrayOf(1,1), intArrayOf(2,1))
    val customers = intArrayOf(1,0,1,2,1,1,7,5)
    val grumpy = intArrayOf(0,1,0,1,0,1,0,1)
    val minutes = 3
    val height = intArrayOf(1,1)
    val n = 4
    val nums = intArrayOf(24,12,71,33,5,87,10,11,3,58,2,97,97,36,32,35,15,80,24,45,38,9,22,21,33,68,22,85,35,83,92,38,59,90,42,64,61,15,4,40,50,44,54,25,34,14,33,94,66,27,78,56,3,29,3,51,19,5,93,21,58,91,65,87,55,70,29,81,89,67,58,29,68,84,4,51,87,74,42,85,81,55,8,95,39)
    val limit = 87
    println(capitalTest.longestSubarray(nums, limit))

}

// 2043. Simple Bank System
class Bank(balance: LongArray) {

    var mapBalances = HashMap<Int, Long>()
    val default : Long = -1
    init {
        for (i in 0 .. balance.size-1){

            if (!(mapBalances.containsKey(i+1))){
                mapBalances[i+1] = balance[i]
            }
        }
    }

    fun printingBalanceByAccount(){

        for ((key, value) in mapBalances){
            println("Key: ${key} -> ${value}")
        }
    }

    fun transfer(account1: Int, account2: Int, money: Long): Boolean {

        if ((mapBalances.containsKey(account1) && mapBalances.containsKey(account2))){

            if (account1 != account2){
                var accountTotal1 = mapBalances.getValue(account1)
                var accountTotal2 = mapBalances.getValue(account2)

                if (accountTotal1 >= money){

                    accountTotal2 += money
                    accountTotal1 -= money
                    mapBalances.replace(account1, accountTotal1)
                    mapBalances.replace(account2, accountTotal2)
                    return true

                }else{
                    return false
                }

            } else {

                var accountTotal1 = mapBalances.getValue(account1)
                if (accountTotal1 >= money){
                    return true
                } else {
                    return false
                }

            }


        } else {
            return false
        }

    }

    fun deposit(account: Int, money: Long): Boolean {

        val existAccount = mapBalances.getOrDefault(account, -1)
        if (existAccount != default){
            var newValue = mapBalances.getValue(account)
            newValue += money
            mapBalances.replace(account, newValue)
            return true
        }
        return false

    }

    fun withdraw(account: Int, money: Long): Boolean {

        val totalMoney = mapBalances.getOrDefault(account, -1)
        if (totalMoney != default){

            if (totalMoney >= money){

                var newValue = totalMoney - money
                mapBalances.replace(account, newValue)
                return true

            }else{
                return false
            }

        }else {
            return false
        }

    }

}
