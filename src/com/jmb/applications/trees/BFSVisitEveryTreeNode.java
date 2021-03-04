package com.jmb.applications.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a binary tree, populate an array to represent its level-by-level traversal. You should
 * populate the values of all nodes of each level from left to right in separate sub-arrays.
 *
 * Example:
 *
 *      1
 *   2    3
 * 4  5  6  7
 *
 * Result should be traversal level by level in the following manner (left to right):
 * [[1],[2,3],[4,5,6,7]]
 *
 * (Result is a LinkedList<List<Integer values>>)
 */
public class BFSVisitEveryTreeNode {



  static class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
      this.val = val;
    }
  }

  public static List<List<Integer>> traverse(TreeNode root) {

    List<List<Integer>> result = new ArrayList<>();
    //check edge case
    if(root == null)
      return result;

    //to separate results per level (idiotic, but problem requires this output)
    int levelSize = 0 ;
    //declare helper structures for BFS
    Queue<TreeNode> q = new LinkedList<>();
    //Apply actual algorithm, adapted to a BTree, root is the initial graph vertex
    q.add(root);
    //Traverse, left to right, and visit the nodes
    while(!q.isEmpty()) {
      //dequeue
      levelSize = q.size();
      List<Integer> currentLevel = new ArrayList<>(levelSize);
      //Loop through levels as represented by the queue size
      for (int i=0; i<levelSize; i++) {
        TreeNode current = q.remove();
        currentLevel.add(current.val);

        if(current.left!= null) {
          q.add(current.left);
        }
        if(current.right!= null) {
          q.add(current.right);
        }
      }
      result.add(currentLevel);
    }

    return result;
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(12);
    root.left = new TreeNode(7);
    root.right = new TreeNode(1);
    root.left.left = new TreeNode(9);
    root.right.left = new TreeNode(10);
    root.right.right = new TreeNode(5);
    List<List<Integer>> result = BFSVisitEveryTreeNode.traverse(root);
    System.out.println("Level order traversal: " + result);
  }

}
