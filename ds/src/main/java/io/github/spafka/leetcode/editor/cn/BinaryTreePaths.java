package io.github.spafka.leetcode.editor.cn;
//给定一个二叉树，返回所有从根节点到叶子节点的路径。 
//
// 说明: 叶子节点是指没有子节点的节点。 
//
// 示例: 
//
// 输入:
//
//   1
// /   \
//2     3
// \
//  5
//
//输出: ["1->2->5", "1->3"]
//
//解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3 
// Related Topics 树 深度优先搜索 字符串 二叉树 
// 👍 528 👎 0


import io.github.spafka.algorithm.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 2021-07-11 23:28:58
 */
public class BinaryTreePaths {
    public static void main(String[] args) {
        Solution solution = new BinaryTreePaths().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */
    class Solution {
        public List<String> binaryTreePaths(TreeNode root) {

            List<String> res = new ArrayList<>();
            travse(res, "", root);
            return res;
        }


        public void travse(List<String> res, String current, TreeNode root) {

            if (root==null){
                return;
            }
            current+=root.val;
            if (root.left == null && root.right == null) {
                res.add(current);
            } else {
                travse(res, current + "->" + root.val, root.left);
                travse(res, current + "->" + root.val, root.right);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}