

class Alarm {

    // Problem 1
    /*
        There 4 district: Mystic, Valor, Forbidden and Instinct
        get a phone number formatted by a String as : xxx-xxx-xxxx

        if first three digits are 101 e.g(101-123-4467) phone number is "Mystic"
        if first three digits sum is 5 e.g(212-123-4467) phone number "Valor"
        if all digits are prime phone number is "Forbidden"
        if any of above phone number is "Instinct"
     */

    fun parseDistrict(phonenum : String): String {

        val digits = phonenum.filter {
            it.isDigit()
        }

        val primes = setOf('2', '3', '5', '7')
        val threDigits = digits.take(3)

        var sumaOfthree= 0
        for (i in 0..threDigits.length-1){
            sumaOfthree += threDigits[i].digitToInt()
        }

        if (digits.all { it in primes }) {
            return "Forbidden"
        } else if (sumaOfthree == 5){
            return "Valor"
        } else if (digits.startsWith("101")){
            return "Mystic"
        } else {
            return "Instinct"
        }

    }

    // Problem 2
    /*
        fill badges starting in x = -1
        -> it takes one action to move one unit of the x-axis
        -> it takes one action to refill the "filler"
        -> it takes one action to fill "badge"
        e.g [2,2,3,3]. capacity = 5
        [action 1 => move to i=0] [action 2 => filled[i], capacity = (5-2) = 3]
        [action 3 => move to i=1] [action 4 => filled[i], capacity = (3-2) = 1]
        since capacity = 1, move to i=-1 action = 5,6
        capacity = 5.
        and so on...
     */

    fun minimunAction(potholes: IntArray, capacity: Int) : Int{

        var actions = 0
        var currentCapacity = capacity
        var currentPos = -1

        for (i in potholes.indices){
            if (currentCapacity < potholes[i]){

                actions += (currentPos + 1)
                actions++

                currentCapacity = capacity
                currentPos = -1

            }
            actions += ( i - currentPos)
            currentPos = i
            actions ++
            currentCapacity -= potholes[i]
        }
        return actions
    }

    // Problem 3
    /*
        You get a sudoku of 6x6 with values until 100 that contains positive numbers below 100
        need to validate if it is a ValidSudoku
        -> if all numbers within each row have a common divisor
        -> if all numbers within each column have a common divisor
        -> for a given row or column, any common divisor "d" satisfies the condition for that row or column
     */

    fun validSudoku(puzzle: Array<IntArray>) : Boolean{

        for (row in puzzle){
            if (!hasCommonDivisor(row)) return false
        }

        for (c in 0 until 6){
            val column = IntArray(6) {
                println("${it} to ${c}")
                puzzle[it][c]
            }
            println()
            column.forEach { print("${it}->") }
            if (!hasCommonDivisor(column)) return false
        }

        return true
    }

    private fun hasCommonDivisor(arr: IntArray): Boolean {

        for (d in 2..99){
           // println("d -> ${d}")

            if (arr.all { it % d == 0 }){ // for each digit in the array check if all element are common divisor of d
                return true
            }

        }
        return true
    }
}

fun main(args : Array<String>){

    val testClass = Alarm()
    val phoneNum = "101-123-4467"
    val puzzle = arrayOf(
        intArrayOf(2,4,8,16,32,64),
        intArrayOf(2,4,8,16,32,64),
        intArrayOf(5,10,20,25,50,50),
        intArrayOf(2,4,8,16,32,64),
        intArrayOf(5,10,20,25,50,50),
        intArrayOf(5,10,20,25,50,50)
    )
    var potholes = intArrayOf(2,2,3,3,)
    var capacity = 5
    println(testClass.minimunAction(potholes, capacity))

}