import java.util.PriorityQueue
import kotlin.math.min

class Meta {

    // 34. Find First and Last Position of Element in Sorted Array
    fun searchRange(nums: IntArray, target: Int): IntArray {

        var right = 0
        var left = -1

        while (right < nums.size){

            if (nums[right] == target){
                left = right
                while (right < nums.size && nums[right] == target){
                    right += 1
                }
                return intArrayOf(left, right-1)

            } else {
                right += 1
            }
        }
        return intArrayOf(-1, -1)
    }

    // 34. Find First and Last Position of Element in Sorted Array
    fun searchRangeBS(nums: IntArray, target: Int) : IntArray {

        var startIndex = binarySearch(nums, target, true)
        var endIndex = binarySearch(nums, target, false)

        if ((startIndex == -1) || (endIndex == -1)){
            return intArrayOf(-1,-1)
        }
        return intArrayOf(startIndex, endIndex)
    }

    private fun binarySearch(nums: IntArray, target: Int, isFirst: Boolean): Int {

        var start = 0
        var end = nums.size-1

        while (start <= end){
            var mid = (start + end) / 2

            if (nums[mid] == target){
                if (isFirst) {
                    if (mid == start || nums[mid- 1] != target){
                        return mid
                    }
                    end = mid - 1
                } else {

                    if (mid == end || nums[mid + 1] != target){
                        return mid
                    }
                    start = mid + 1

                }
            } else if (nums[mid] > target){
                end = mid - 1
            } else {
                start = mid + 1
            }
        }
        return -1
    }

    // 670. Maximum Swap
    fun maximumSwap(num: Int): Int {

        val compareByNum : Comparator<Pair<Int, Int>> = compareBy<Pair<Int, Int>> { it.first }.thenBy { it.second }
        val minHeap : PriorityQueue<Pair<Int, Int>> = PriorityQueue(compareByNum)
        val numToString = num.toString()
        val originalArray = IntArray(numToString.length)

        for (i in 0 .. numToString.length-1){
            if (i == 0){
                var pair = Pair(-numToString[i].digitToInt(), i)
                minHeap.add(pair)
            }
            else {
                var pair = Pair(-numToString[i].digitToInt(), i)
                minHeap.add(pair)
            }

            originalArray[i] = numToString[i].digitToInt()
        }

        var i = 0
        while (i < originalArray.size){

            var maxPair = minHeap.poll()
            var maxIndex = maxPair.second
            var maxValue = maxPair.first * -1
            if (maxIndex != i){
                var valueToSwap = originalArray[i]
                originalArray[i] = maxValue
                originalArray[maxIndex] = valueToSwap
                break
            }
            i += 1

        }

        var result = ""
        for (i in 0 until originalArray.size){
            result += originalArray[i].digitToChar()
        }

        return result.toInt()

    }

}

fun main(){

    val testClass = Meta()
    var nums = intArrayOf(5,7,7,8,8,10)
    var target = 8
    var num = 98368

    println(testClass.maximumSwap(num))
    //val result = testClass.searchRangeBS(nums, target).forEach { print("[${it}]->") }
}