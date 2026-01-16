
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

}

fun main(args : Array<String>){


    val arr1 = intArrayOf(1,2,2,3)
    val arr2 = intArrayOf(1000)
    val balance = longArrayOf(0)
    val testClass = Bank(balance)
    val intervals = arrayOf(intArrayOf(1,2), intArrayOf(2,3), intArrayOf(3,4), intArrayOf(1,3))

    val capitalTest = Capital()
    println(capitalTest.isMonotonic(arr1))

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
