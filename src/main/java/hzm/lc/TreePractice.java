package hzm.lc;

import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class TreePractice {

    public static void main(String[] args) {

    }

    //二叉树的中序遍历
    //(1)递归法
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorder(root, res);
        return res;
    }

    public void inorder(TreeNode root, List<Integer> res) {
        if(root == null) {
            return;
        }
        inorder(root.left, res);
        res.add(root.val);
        inorder(root.right, res);
    }
    //二叉树的中序遍历(2)迭代法
    public List<Integer> inorderTraversalItera(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        while(root != null || !stack.empty()) {
            while (root != null) {
                stack.add(root);
                root = root.left;
            }
            TreeNode pop = stack.pop();
            res.add(pop.val);
            root = pop.right;
        }
        return res;
    }

    //二叉树的最大深度
    //(1)DFS，深度优先搜索
    public int maxDepth(TreeNode root) {
        if(root == null) {
            return 0;
        } else {
            int leftDepth = maxDepth(root.left);
            int rightDepth = maxDepth(root.right);
            return Math.max(leftDepth, rightDepth) + 1;
        }
    }
    //二叉树的最大深度，(2)BFS，广度优先搜索
    public int maxDepthBfs(TreeNode root) {
        if(root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int ans = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                TreeNode poll = queue.poll();
                if(poll.left != null) {
                    queue.offer(poll.left);
                }
                if(poll.right != null) {
                    queue.offer(poll.right);
                }
                size--;
            }
            ans++;
        }
        return ans;
    }

}
