
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

    // 

}

fun main(args : Array<String>){


    val arr1 = intArrayOf(1,2,2,3)
    val arr2 = intArrayOf(1000)
    val balance = longArrayOf(0)
    val testClass = Bank(balance)
    val intervals = arrayOf(intArrayOf(1,2), intArrayOf(2,3), intArrayOf(3,4), intArrayOf(1,3))
    val queries = arrayOf(intArrayOf(0,2), intArrayOf(1,2), intArrayOf(3,1), intArrayOf(1,1), intArrayOf(2,1))

    val capitalTest = Capital()
    val n = 4
    println(capitalTest.colorTheArray(n, queries))

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
