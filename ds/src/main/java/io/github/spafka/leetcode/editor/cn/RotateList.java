package io.github.spafka.leetcode.editor.cn;
//给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,4,5], k = 2
//输出：[4,5,1,2,3]
// 
//
// 示例 2： 
//
// 
//输入：head = [0,1,2], k = 4
//输出：[2,0,1]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目在范围 [0, 500] 内 
// -100 <= Node.val <= 100 
// 0 <= k <= 2 * 109 
// 
// Related Topics 链表 双指针 
// 👍 586 👎 0


/**
 * 2021-07-08 23:06:33
 */
public class RotateList {
    public static void main(String[] args) {
        Solution solution = new RotateList().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    class Solution {
        public ListNode rotateRight(ListNode head, int k) {

            int l = 0;

            var dummy = new ListNode();
            dummy.next = head;

            while (dummy.next != null) {
                l++;
                dummy=dummy.next;
            }

            if (l==0){
                return head;
            }

            dummy.next = head;

            int r = l - k % l;

            while (r-- > 0) {
                dummy = dummy.next;
            }

            var res = dummy.next;
            dummy.next = null;

            return res;


        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}