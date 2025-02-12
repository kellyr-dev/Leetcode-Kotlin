import java.util.Collections
import java.util.Comparator
import java.util.PriorityQueue

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

    }
}

fun main(args : Array<String>){

    val testClass = Heap()
    val nums = intArrayOf(3,2,1,5,6,4)
    val k = 8
    val matrix = arrayOf(intArrayOf(1,5,9), intArrayOf(10,11,13), intArrayOf(12,13,15))
    // matrix = [[],[],[]], k = 8
    println(testClass.kthSmallest(matrix, k))

}

data class Point(
    val x: Int,
    val y: Int
)