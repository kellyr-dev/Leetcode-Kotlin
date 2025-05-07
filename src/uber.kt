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

    // 1438. Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit

    // 1861. Rotating the Box
    fun rotateTheBox(boxGrid: Array<CharArray>): Array<CharArray> {

        return emptyArray()
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

    
}

fun main(){

    val testClass = MyCalendar()
    testClass.book(10,20)
    testClass.book(15,25)
    testClass.book(20,30)
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