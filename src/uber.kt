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

    //
}

fun main(){

    val testClass = MyCalendar()
    testClass.book(10,20)
    testClass.book(15,25)
    testClass.book(20,30)
}

class MyCalendar() {

    val bookings = ArrayList<Pair<Int,Int>>() // start & end

    // [10, 20], [15, 25], [20, 30],
    //  [12,18]
    // put [45, 45] // if (start < iEnd)
    //  if (start < istar) && (end < istar){
    // return true
    //else
    // return false
    //

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