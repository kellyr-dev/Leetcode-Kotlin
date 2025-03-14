
class Meta {

    // 34. Find First and Last Position of Element in Sorted Array
    fun searchRange(nums: IntArray, target: Int): IntArray {


        /* nums = [5,7,7,8,8,10] => 6
                              R
                         L
        *
        */
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

   

}

fun main(){

    val testClass = Meta()
    var nums = intArrayOf(5,7,7,8,8,10)
    var target = 8

    val result = testClass.searchRange(nums, target).forEach { print("[${it}]->") }





}