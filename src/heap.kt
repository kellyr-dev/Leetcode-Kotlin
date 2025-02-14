import java.util.Collections
import java.util.PriorityQueue
import kotlin.Comparator

class Heap{

    /*
        MAX_HEAP ==> val heap : PriorityQueue<Int> = PriorityQueue(Collections.reverseOrder())

        val comparedByDistance : Comparator<Pair<Double, IntArray>> = compareBy { it.first }
        val heap : PriorityQueue<Pair<Double, IntArray>> = PriorityQueue(comparedByDistance)
     */

    // 973. K Closest Points to Origin
    fun kClosest(points: Array<IntArray>, k: Int): Array<IntArray> {

        val comparedByDistance : Comparator<Pair<Double, IntArray>> = compareBy { it.first }
        val heap : PriorityQueue<Pair<Double, IntArray>> = PriorityQueue(comparedByDistance)

        for (point in points){
            val distance = Math.sqrt( (point[0]*point[0]).toDouble() + point[1]*point[1].toDouble() )
            heap.add(Pair(distance, point))
        }

        val result = ArrayList<IntArray>()
        for (i in 0 .. k-1){
            result.add(heap.poll().second)
        }
        return result.toTypedArray()
    }

    // 215. Kth Largest Element in an Array
    fun findKthLargest(nums: IntArray, k: Int): Int {
        val heap : PriorityQueue<Int> = PriorityQueue( Collections.reverseOrder() )

        for (i in 0 .. nums.size-1){
            heap.add(nums[i])
        }

        var result = -1
        for (j in 0 .. k-1){
            result = heap.poll()
        }
        return result
    }

    // 378. Kth Smallest Element in a Sorted Matrix
    fun kthSmallest(matrix: Array<IntArray>, k: Int): Int {

        val heap : PriorityQueue<Int> = PriorityQueue()
        for (row in 0 .. matrix.size-1){
            for (col in 0 .. matrix[0].size-1){
                heap.add(matrix[row][col])
            }
        }
        var result = -1

        if (k > heap.size){
            return result
        }

        for (i in 0 .. k-1){
            result = heap.poll()
        }
        return result

    }

    // 767. Reorganize String
    fun reorganizeString(s: String): String {
        var map = HashMap<Char, Int>()
        val comparedByFreq : Comparator<Pair<Int,Char>> = compareBy{ it.first }
        val heap : PriorityQueue<Pair<Int, Char>> = PriorityQueue(comparedByFreq.reversed())
        val queue = ArrayList<Pair<Int, Char>>()
        val resultArray = ArrayList<Char>()

        for (i in 0.. s.length-1){
            map[s[i]] = map.getOrDefault(s[i], 0) + 1
        }

        for (value in map){
            var pair = Pair(value.value, value.key)
            heap.add(pair)
        }

        while (heap.size > 0){

            var current = heap.poll()

            if (resultArray.isNotEmpty() && current.second == resultArray.last()){
                queue.add(current)
                if (heap.size > 0){
                    var auxiliar = heap.poll()

                    var updated = Pair(auxiliar.first-1, auxiliar.second)
                    if (updated.first >= 1){
                        heap.add(updated)
                    }
                    resultArray.add(auxiliar.second)
                    heap.add(queue.removeLast())
                }else {
                    return "Empty"
                }
            } else {

                resultArray.add(current.second)
                var updated = Pair(current.first-1, current.second)
                if (updated.first >= 1){
                    heap.add(updated)
                }
            }

        }
        return resultArray.toString()
    }
    // 23. Merge k Sorted Lists

    fun mergeKLists(lists: Array<ListNode?>): ListNode? {
        val compareByVal : Comparator<ListNode> = compareBy{it.`val`  }
        var heap : PriorityQueue<Int> = PriorityQueue()


        for (list in lists){

            var aux = list
            while (aux != null){
                heap.add(aux.`val`)
                aux = aux.next
            }
        }

        if (heap.size <= 0){
            return null
        }

        var head = ListNode(heap.poll())
        var iterator = head
        while (heap.size > 0){
            iterator.next = ListNode(heap.poll())
            iterator = iterator.next!!
        }

        return head
    }

    fun printingList(head : ListNode){
        while(head != null){
            print("${head.`val`}->")
        }
        println()
    }

}

fun main(args : Array<String>){

    val testClass = Heap()
    val nums = intArrayOf(3,2,1,5,6,4)
    val k = 8
    val matrix = arrayOf(intArrayOf(1,5,9), intArrayOf(10,11,13), intArrayOf(12,13,15))
    val s = "blflxll"

}


