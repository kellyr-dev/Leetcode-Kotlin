import kotlin.contracts.contract

class TreeNode(var value : Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}


class TreeLeetcode {

    internal fun printingTre(root : TreeNode?){

        if (root == null){
            print("null->")
            return
        }
        print("${root.value}->")
        printingTre(root.left)
        printingTre(root.right)
    }

    // 104. Maximum Depth of Binary Tree
    fun maxDepth(root: TreeNode?): Int {

        if (root == null){
            return 0
        }
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right))
    }

    // 110. Balanced Binary Tree
    fun isBalanced(root: TreeNode?): Boolean {

        if (root == null){
            return true
        }

        fun helper(root: TreeNode?): Int{

            if (root == null){
                return 0
            }

            var leftSide = helper(root.left)
            var rightSide = helper(root.right)

            println("leftSide: ${leftSide}")
            println("rightSide: ${rightSide}")
            println("root: ${root.value}")

            if (leftSide == -1 || rightSide == -1){
                return -1
            }

            if (Math.abs(leftSide - rightSide) > 1){
                return -1
            }

            return 1 + Math.max(leftSide, rightSide)

        }

        if (helper(root) == -1) return false else return true

    }

    // 98. Validate Binary Search Tree
    fun isValidBST(root: TreeNode?): Boolean {

        if ((root?.left == null) && (root?.right == null)){
            return true
        }
        fun helper(root: TreeNode?, minValue: Double, maxValue: Double) : Boolean{
            if (root == null){
                return true
            }
            if (!( (root.value > minValue) && (root.value < maxValue)) ){
                return false
            }
            return helper(root.left, minValue, root.value.toDouble()) && helper(root.right, root.value.toDouble(), maxValue)
        }
        return helper(root, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY)
    }

    // 257. Binary Tree Paths
    fun binaryTreePaths(root: TreeNode?): List<String> {

        var result = ArrayList<String>()
        var sb = StringBuilder()

        fun helper(root: TreeNode?) {

            if (root == null){
                result.add(sb.toString())
                return
            }

            sb.append(root.value.toString())
            sb.append("->")
            helper(root.left)
            helper(root.right)
        }
        helper(root)
        return result
    }

}

fun main(args : Array<String>){

    val testClass = TreeLeetcode()

    var root = TreeNode(1)
    root.left = TreeNode(2)
    root.right = TreeNode(3)
    root.left!!.left = null
    root.left!!.right = TreeNode(5)

    // [5,4,6,null,null,3,7]

    testClass.printingTre(root)
    println()
    println(testClass.binaryTreePaths(root))

}