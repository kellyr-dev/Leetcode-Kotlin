import java.lang.Math.max


class Amazon {

    // 3. Longest Substring Without Repeating Characters
    fun lengthOfLongestSubstring(s: String): Int {

        val exists = HashSet<Char>()

        var l = 0
        var r = 0
        var maxLength = 0
        // a a b a a b ! b b
        // ^
        //   ^

        while (r < s.length){

            if (exists.contains(s[r])){
                // reducing windows
                while (l < r){
                    if (s[l] != s[r]){
                        exists.remove(s[l])
                        l++
                    }else{
                        l++
                        break
                    }
                }
            } else {
                exists.add(s[r])
                maxLength = max(maxLength, exists.size)
            }

            r++
        }

        return maxLength
    }

    // 2. Add Two Numbers
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {

        var list1 = l1
        var list2 = l2
        var head = ListNode(-1)
        val result = head
        var carry = 0

        while (list1 != null && list2 != null){

            val value = list1.value + list2.value + carry

            if ( value < 10){
                head.next = ListNode(value)
                list1 = list1.next
                list2 = list2.next
                head = head.next!!
                carry = 0
            } else {
                var resto = (value % 10)
                head.next = ListNode(resto)
                head = head.next!!
                list1 = list1.next
                list2 = list2.next
                carry = 1
            }

        }

        while (list1 != null){
            var value = (list1!!.value + carry)

            if (value < 10){
                head.next = ListNode(value)
                list1 = list1!!.next
                head = head.next!!
                carry = 0
            } else {

                head.next = ListNode(value % 10)
                list1 = list1.next
                head = head.next!!
                carry = 1
            }
        }

        while (list2 != null){
            var value = (list2.value + carry)

            if (value < 10){
                head.next = ListNode(value)
                list2 = list2.next
                head = head.next!!
                carry = 0
            } else {

                head.next = ListNode(value % 10)
                list2 = list2.next
                head = head.next!!
                carry = 1
            }
        }

        if (carry == 1){
            head.next = ListNode(1)
        }

        return result.next
    }

    // 1. Two Sum
    fun twoSum(nums: IntArray, target: Int): IntArray {
        val table = HashMap<Int, Int>()

        for (i in 0 until nums.size){

            val zero = target - nums[i]

            if (table.containsKey(zero)){
                return intArrayOf(i, table[zero]!!)
            } else {
                table[nums[i]] = i
            }
        }
    }
}

fun main(args : Array<String>){


    val AmznTEst = Amazon()
    var s = "aabaab!bb"

    println(AmznTEst.lengthOfLongestSubstring(s))

}