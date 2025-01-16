class TreeNode(var value : Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class Solution {

    // 1554. Strings Differ by One Character
    fun differByOne(dict: Array<String>): Boolean {

        for (i in 0.. dict.size-1){

            var word = dict[i]
            for (j in 0.. dict.size-1){

                var auxWord = dict[j]
                var cont = 0
                for (k in 0.. word.length-1){

                    if (word[k] != auxWord[k]){
                        cont+= 1
                    }

                    if (cont > 1){
                        break
                    }
                }

                if ((cont == 1) && (word.length) > 1){
                    return true
                }
            }

        }

        return false
    }

    // 219. Contains Duplicate II
    fun containsNearbyDuplicate(nums: IntArray, k: Int): Boolean {
        var findSet = HashSet<Int>()

        for (i in 0 .. nums.size-1){

            if (findSet.contains(nums[i])) return true

            findSet.add(nums[i])

            if (findSet.size > k){
                findSet.remove(nums[i-k])
            }

        }
        return false
    }

    // 415. Add Strings
    fun addStrings(num1: String, num2: String): String {

        var i = num1.length - 1
        var j = num2.length - 1
        var carry = 0
        var result = ""

        while ((i >= 0) && (j >= 0)) {

            println("value1: ${num1.get(i).digitToInt()}")
            println("value2: ${num2.get(j).digitToInt()}")
            var value = num1.get(i).digitToInt() + num2.get(j).digitToInt() + carry


            if (value >= 10) {
                value = value % 10
                carry = 1
                // 14 % 10 = 1.4
            }else{
                carry = 0
            }
            result = value.toString() + result
            println("result: ${result}")
            i -= 1
            j -= 1
        }

        while (i >= 0) {
            var value = num1.get(i).digitToInt() + carry
            if (value >= 10) {
                value = value % 10
                carry = 1
            }else{
                carry = 0
            }
            i -= 1
            result = value.toString() + result

        }

        while (j >= 0) {

            var value = num2.get(j).digitToInt() + carry
            if (value >= 10) {
                value = value % 10
                carry = 1
            }else{
                carry =0
            }
            j -= 1
            result = value.toString() + result

        }


        if (carry >= 1) {

            result = carry.toString() + result

        }

        return result
    }

    // 136. Single Number
    fun singleNumber(nums: IntArray): Int {

        var tableMap = HashSet<Int>()

        for (i in 0 .. nums.size-1){
            if (tableMap.contains(nums[i])){
                tableMap.remove(nums[i])
            }else{
                tableMap.add(nums[i])
            }
        }
        println(tableMap)

        var result = tableMap.toList()

        return result[0]

    }


}

fun main(args : Array<String>){

    val testClass = Solution()
    var nums = intArrayOf(0,1,2,3,4,5)
    println(testClass.singleNumber(nums))

}