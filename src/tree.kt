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

}

fun main(args : Array<String>){

    val testClass = TreeLeetcode()

    var root = TreeNode(1)
    root.left = TreeNode(2)
    root.right = TreeNode(2)
    root.left!!.left = TreeNode(3)
    root.left!!.right = TreeNode(3)
    root.left!!.left!!.left = TreeNode(4)
    root.left!!.left!!.right = TreeNode(4)

//    var root = TreeNode(1)
//    root.left = null
//    root.right = TreeNode(3)
//    root.right!!.left = TreeNode(2)

    testClass.printingTre(root)
    println()
    println(testClass.isBalanced(root))

}