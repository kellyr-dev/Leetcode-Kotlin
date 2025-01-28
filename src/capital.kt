
class Capital {

    // 3043. Find the Length of the Longest Common Prefix
    fun longestCommonPrefix(arr1: IntArray, arr2: IntArray): Int {

        val hash1 = HashSet<String>()
        val hash2 = HashSet<String>()

        for (num in arr1){
            var aux = num.toString()

            var stringAux = ""
            for (i in 0.. aux.length-1){
                stringAux += aux[i]
             //   println("stringAux: ${stringAux}")
                if (!hash1.contains(stringAux)){
                    hash1.add(stringAux)
                }
            }

        }

        for (num in arr2){
            var aux = num.toString()
            var stringAux = ""
            for (i in 0.. aux.length-1){
                stringAux += aux[i]
             //   println("stringAux2: ${stringAux}")
                if (!hash2.contains(stringAux)){
                    hash2.add(stringAux)
                }
            }
        }
        var newArray1 = hash1.toList()
        var newArray2 = hash2.toList()

        var maxLenght1 = 0
        var maxLenght2 = 0

        for (num in newArray1){
            var aux = num
            if (hash2.contains(aux)){
                maxLenght1 = Math.max(maxLenght1, aux.length)
            }

        }

        for (num in newArray2){
            var aux = num
            if (hash1.contains(aux)){
                maxLenght2 = Math.max(maxLenght2, aux.length)
            }

        }

    return Math.max(maxLenght1, maxLenght2)
    }

    // 435. Non-overlapping Intervals
    fun eraseOverlapIntervals(intervals: Array<IntArray>): Int {

        intervals.sortBy { it[1]}
        intervals.forEach { print("(${it[0]},${it[1]}) ") }

        var ans = 0
        var k = Int.MIN_VALUE

        for (i in 0 .. intervals.size-1){

            if (intervals[i][0] >= k){
                k = intervals[i][1]
            } else {
                ans += 1
            }
        }

        return ans
    }
}

fun main(args : Array<String>){

    val testClass = Capital()
    val arr1 = intArrayOf(1,10,100)
    val arr2 = intArrayOf(1000)
    val intervals = arrayOf(intArrayOf(1,2), intArrayOf(2,3), intArrayOf(3,4), intArrayOf(1,3))
    println(testClass.eraseOverlapIntervals(intervals))


}