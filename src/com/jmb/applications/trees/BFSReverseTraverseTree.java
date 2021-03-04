package com.jmb.applications.trees;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a binary tree, populate an array to represent its level-by-level traversal in reverse
 * order, i.e., the lowest level comes first. You should populate the values of all nodes in each
 * level from left to right in separate sub-arrays.
 *
 * Example:
 *
 *      1
 *   2    3
 * 4  5  6  7
 *
 * Result should be traversal level by level in the following manner (left to right):
 * [[4,5,6,7],[2,3], [1]]
 *
 * This is very similar to traversing a node with BFS logic, key relies on the structure to store
 * the traversing nodes, which should offer saving them in 'reverse' order
 */
public class BFSReverseTraverseTree {

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
    Queue<TreeNode> q = new LinkedList<>();

    //edge case, empty tree, return same empty list of traversed levels
    if(root == null)
      return result;

    //Set the starting node to visit on the queue and other tracking vars
    int levelSize = 0;
    q.add(root);

    //Start traversing following BFS, replace adjacency list for q per level's nodes
    while(!q.isEmpty()) {
      levelSize = q.size();
      List<Integer> currentLevel = new LinkedList<>();

      //Add current level nodes' next ones, keep track with level size var
      for(int i=0; i<levelSize; i++) {
        TreeNode curr = q.remove();
        if(curr.left != null) q.add(curr.left);
        if(curr.right != null) q.add(curr.right);
        currentLevel.add(curr.val);
      }

      result.add(0, currentLevel);
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
    List<List<Integer>> result = BFSReverseTraverseTree.traverse(root);
    System.out.println("Reverse level order traversal: " + result);
    List<Integer> level1 = Arrays.asList(9,10,5);
    List<Integer> level2 = Arrays.asList(7,1);
    List<Integer> level3 = Arrays.asList(12);
    List<List<Integer>> expected = Arrays.asList(level1, level2, level3);
    assert result.equals(expected);
  }
}
