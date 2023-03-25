// --== CS400 Spring 2023 File Header Information ==--
// Name: Alan Liang
// Email: aliang26@wisc.edu
// Team: CI
// TA: Karan Grover
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>

import java.util.LinkedList;
import java.util.Stack;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;

/**
 * Red-Black Tree implementation with a Node inner class for representing the nodes of the tree.
 * Currently, this implements a Binary Search Tree that we will turn into a red black tree by
 * modifying the insert functionality. In this activity, we will start with implementing rotations
 * for the binary search tree insert algorithm.
 */
public class RedBlackTree<T extends Comparable<T>> implements SortedCollectionInterface<T> {

  /**
   * This class represents a node holding a single value within a binary tree.
   */
  protected static class Node<T> {
    public T data;
    // The context array stores the context of the node in the tree:
    // - context[0] is the parent reference of the node,
    // - context[1] is the left child reference of the node,
    // - context[2] is the right child reference of the node.
    // The @SupressWarning("unchecked") annotation is used to supress an unchecked
    // cast warning. Java only allows us to instantiate arrays without generic
    // type parameters, so we use this cast here to avoid future casts of the
    // node type's data field.
    @SuppressWarnings("unchecked")
    public Node<T>[] context = (Node<T>[]) new Node[3];
    public int blackHeight; // tracks the black height of the current node (not the full tree)
                                // 0 = red, 1 = black, 2 = double black
    public Node(T data) {
      this.data = data;
      blackHeight = 0; // insert red nodes only
    }

    /**
     * @return true when this node has a parent and is the right child of that parent, otherwise
     *         return false
     */
    public boolean isRightChild() {
      return context[0] != null && context[0].context[2] == this;
    }

  }

  protected Node<T> root; // reference to root node of tree, null when empty
  protected int size = 0; // the number of values in the tree

  /**
   * Performs a naive insertion into a binary search tree: adding the input data value to a new node
   * in a leaf position within the tree. After this insertion, no attempt is made to restructure or
   * balance the tree. This tree will not hold null references, nor duplicate data values.
   * 
   * @param data to be added into this binary search tree
   * @return true if the value was inserted, false if not
   * @throws NullPointerException     when the provided data argument is null
   * @throws IllegalArgumentException when data is already contained in the tree
   */
  public boolean insert(T data) throws NullPointerException, IllegalArgumentException {
    // null references cannot be stored within this tree
    if (data == null)
      throw new NullPointerException("This RedBlackTree cannot store null references.");

    Node<T> newNode = new Node<>(data);
    if (this.root == null) {
      // add first node to an empty tree
      root = newNode;
      size++;
      enforeRedBlackPropertiesAfterInsert(newNode);
      return true;
    } else {
      // insert into subtree
      Node<T> current = this.root;
      while (true) {
        int compare = newNode.data.compareTo(current.data);
        if (compare == 0) {
          throw new IllegalArgumentException(
              "This RedBlackTree already contains value " + data.toString());
        } else if (compare < 0) {
          // insert in left subtree
          if (current.context[1] == null) {
            // empty space to insert into
            current.context[1] = newNode;
            newNode.context[0] = current;
            this.size++;
            enforeRedBlackPropertiesAfterInsert(newNode);
            return true;
          } else {
            // no empty space, keep moving down the tree
            current = current.context[1];
          }
        } else {
          // insert in right subtree
          if (current.context[2] == null) {
            // empty space to insert into
            current.context[2] = newNode;
            newNode.context[0] = current;
            this.size++;
            enforeRedBlackPropertiesAfterInsert(newNode);
            return true;
          } else {
            // no empty space, keep moving down the tree
            current = current.context[2];
          }
        }
      }
    }
  }

  /**
   * Performs the rotation operation on the provided nodes within this tree. When the provided child
   * is a left child of the provided parent, this method will perform a right rotation. When the
   * provided child is a right child of the provided parent, this method will perform a left
   * rotation. When the provided nodes are not related in one of these ways, this method will throw
   * an IllegalArgumentException.
   * 
   * @param child  is the node being rotated from child to parent position (between these two node
   *               arguments)
   * @param parent is the node being rotated from parent to child position (between these two node
   *               arguments)
   * @throws IllegalArgumentException when the provided child and parent node references are not
   *                                  initially (pre-rotation) related that way
   */
  private void rotate(Node<T> child, Node<T> parent) throws IllegalArgumentException {
    if (child == null || parent == null) {
      throw new IllegalArgumentException("Null node(s) were passed");
    }
    if (parent.context[1] != null && parent.context[1].equals(child)) { // if left child
                                                                        // relationship
      Node<T> temp = child.context[2]; // store child
      if (!root.equals(parent)) { // if not the root
        child.context[0] = parent.context[0];// child's parent becomes the parent of the old parent
        if (parent.isRightChild()) {
          parent.context[0].context[2] = child;
        } else {
          parent.context[0].context[1] = child;
        }
      } else {
        child.context[0] = null; // becomes root of tree
        this.root = child;
      }
      child.context[2] = parent;
      parent.context[0] = child;

      parent.context[1] = temp; // give child's old right child to parent
      try {
        temp.context[0] = parent; // if temp is null this will throw NPE
      } catch (NullPointerException npe) { // in case temp doesnt exist

      }


    } else if (parent.context[2] != null && parent.context[2].equals(child)) { // if right child
                                                                               // relationship
      Node<T> temp = child.context[1]; // store child
      if (!root.equals(parent)) {
        child.context[0] = parent.context[0]; // child's parent becomes the parent of the old parent
        if (parent.isRightChild()) {
          parent.context[0].context[2] = child;
        } else {
          parent.context[0].context[1] = child;
        }
      } else {
        child.context[0] = null; // becomes root of tree
        this.root = child;
      }
      child.context[1] = parent;
      parent.context[0] = child;

      parent.context[2] = temp; // give child's old left child to parent
      try {
        temp.context[0] = parent;
      } catch (NullPointerException npe) { // in case temp doesn't exist

      }


    } else { // If not related at all
      throw new IllegalArgumentException(
          "The provided child and parent references are not" + " related that way");
    }
  }

  /**
   * Get the size of the tree (its number of nodes).
   * 
   * @return the number of nodes in the tree
   */
  public int size() {
    return size;
  }

  /**
   * Method to check if the tree is empty (does not contain any node).
   * 
   * @return true of this.size() return 0, false if this.size() > 0
   */
  public boolean isEmpty() {
    return this.size() == 0;
  }

  /**
   * Removes the value data from the tree if the tree contains the value. This method will not
   * attempt to rebalance the tree after the removal and should be updated once the tree uses
   * Red-Black Tree insertion.
   * 
   * @return true if the value was remove, false if it didn't exist
   * @throws NullPointerException     when the provided data argument is null
   * @throws IllegalArgumentException when data is not stored in the tree
   */
  public boolean remove(T data) throws NullPointerException, IllegalArgumentException {
    // null references will not be stored within this tree
    if (data == null) {
      throw new NullPointerException("This RedBlackTree cannot store null references.");
    } else {
      Node<T> nodeWithData = this.findNodeWithData(data);
      // throw exception if node with data does not exist
      if (nodeWithData == null) {
        throw new IllegalArgumentException(
            "The following value is not in the tree and cannot be deleted: " + data.toString());
      }
      boolean hasRightChild = (nodeWithData.context[2] != null);
      boolean hasLeftChild = (nodeWithData.context[1] != null);
      if (hasRightChild && hasLeftChild) {
        // has 2 children
        Node<T> successorNode = this.findMinOfRightSubtree(nodeWithData);
        // replace value of node with value of successor node
        nodeWithData.data = successorNode.data;
        // remove successor node
        if (successorNode.context[2] == null) {
          // successor has no children, replace with null
          this.replaceNode(successorNode, null);
        } else {
          // successor has a right child, replace successor with its child
          this.replaceNode(successorNode, successorNode.context[2]);
        }
      } else if (hasRightChild) {
        // only right child, replace with right child
        this.replaceNode(nodeWithData, nodeWithData.context[2]);
      } else if (hasLeftChild) {
        // only left child, replace with left child
        this.replaceNode(nodeWithData, nodeWithData.context[1]);
      } else {
        // no children, replace node with a null node
        this.replaceNode(nodeWithData, null);
      }
      this.size--;
      return true;
    }
  }

  /**
   * Checks whether the tree contains the value *data*.
   * 
   * @param data the data value to test for
   * @return true if *data* is in the tree, false if it is not in the tree
   */
  public boolean contains(T data) {
    // null references will not be stored within this tree
    if (data == null) {
      throw new NullPointerException("This RedBlackTree cannot store null references.");
    } else {
      Node<T> nodeWithData = this.findNodeWithData(data);
      // return false if the node is null, true otherwise
      return (nodeWithData != null);
    }
  }

  /**
   * Helper method that will replace a node with a replacement node. The replacement node may be
   * null to remove the node from the tree.
   * 
   * @param nodeToReplace   the node to replace
   * @param replacementNode the replacement for the node (may be null)
   */
  protected void replaceNode(Node<T> nodeToReplace, Node<T> replacementNode) {
    if (nodeToReplace == null) {
      throw new NullPointerException("Cannot replace null node.");
    }
    if (nodeToReplace.context[0] == null) {
      // we are replacing the root
      if (replacementNode != null)
        replacementNode.context[0] = null;
      this.root = replacementNode;
    } else {
      // set the parent of the replacement node
      if (replacementNode != null)
        replacementNode.context[0] = nodeToReplace.context[0];
      // do we have to attach a new left or right child to our parent?
      if (nodeToReplace.isRightChild()) {
        nodeToReplace.context[0].context[2] = replacementNode;
      } else {
        nodeToReplace.context[0].context[1] = replacementNode;
      }
    }
  }

  /**
   * Helper method that will return the inorder successor of a node with two children.
   * 
   * @param node the node to find the successor for
   * @return the node that is the inorder successor of node
   */
  protected Node<T> findMinOfRightSubtree(Node<T> node) {
    if (node.context[1] == null && node.context[2] == null) {
      throw new IllegalArgumentException("Node must have two children");
    }
    // take a steop to the right
    Node<T> current = node.context[2];
    while (true) {
      // then go left as often as possible to find the successor
      if (current.context[1] == null) {
        // we found the successor
        return current;
      } else {
        current = current.context[1];
      }
    }
  }

  /**
   * Helper method that will return the node in the tree that contains a specific value. Returns
   * null if there is no node that contains the value.
   * 
   * @return the node that contains the data, or null of no such node exists
   */
  protected Node<T> findNodeWithData(T data) {
    Node<T> current = this.root;
    while (current != null) {
      int compare = data.compareTo(current.data);
      if (compare == 0) {
        // we found our value
        return current;
      } else if (compare < 0) {
        // keep looking in the left subtree
        current = current.context[1];
      } else {
        // keep looking in the right subtree
        current = current.context[2];
      }
    }
    // we're at a null node and did not find data, so it's not in the tree
    return null;
  }

  /**
   * This method performs an inorder traversal of the tree. The string representations of each data
   * value within this tree are assembled into a comma separated string within brackets (similar to
   * many implementations of java.util.Collection, like java.util.ArrayList, LinkedList, etc).
   * 
   * @return string containing the ordered values of this tree (in-order traversal)
   */
  public String toInOrderString() {
    // generate a string of all values of the tree in (ordered) in-order
    // traversal sequence
    StringBuffer sb = new StringBuffer();
    sb.append("[ ");
    if (this.root != null) {
      Stack<Node<T>> nodeStack = new Stack<>();
      Node<T> current = this.root;
      while (!nodeStack.isEmpty() || current != null) {
        if (current == null) {
          Node<T> popped = nodeStack.pop();
          sb.append(popped.data.toString());
          if (!nodeStack.isEmpty() || popped.context[2] != null)
            sb.append(", ");
          current = popped.context[2];
        } else {
          nodeStack.add(current);
          current = current.context[1];
        }
      }
    }
    sb.append(" ]");
    return sb.toString();
  }

  /**
   * This method performs a level order traversal of the tree. The string representations of each
   * data value within this tree are assembled into a comma separated string within brackets
   * (similar to many implementations of java.util.Collection). This method will be helpful as a
   * helper for the debugging and testing of your rotation implementation.
   * 
   * @return string containing the values of this tree in level order
   */
  public String toLevelOrderString() {
    StringBuffer sb = new StringBuffer();
    sb.append("[ ");
    if (this.root != null) {
      LinkedList<Node<T>> q = new LinkedList<>();
      q.add(this.root);
      while (!q.isEmpty()) {
        Node<T> next = q.removeFirst();
        if (next.context[1] != null)
          q.add(next.context[1]);
        if (next.context[2] != null)
          q.add(next.context[2]);
        sb.append(next.data.toString());
        if (!q.isEmpty())
          sb.append(", ");
      }
    }
    sb.append(" ]");
    return sb.toString();
  }

  public String toString() {
    return "level order: " + this.toLevelOrderString() + "\nin order: " + this.toInOrderString();
  }

  /**
   * This method will check the red-black properties of the tree after insertion. If any of the properties are
   * violated, resolve them.
   * @param node the red node that was inserted
   */
  protected void enforeRedBlackPropertiesAfterInsert(Node<T> node) {
    // If the node being checked it the root, make the node black and end the code
    if (root == node) {
      node.blackHeight = 1;
      return; // exit code
    }
    Node<T> nullNode = new Node<T>(null);
    nullNode.blackHeight = 1; // Null nodes are black
    Node<T> child = node;
    Node<T> parent = child.context[0];
    Node<T> grandparent = parent.context[0];
    Node<T> aunt = nullNode;
    if (parent.blackHeight == 1) { // if the parent is black, there is no violation
      return;
    }
    if (grandparent == null) {
      return; // no violation is possible from adding a red node to a tree with only (1) a root or 
              // (2) a root and a child at the opposite end of the tree
    }
    if (parent.isRightChild() && grandparent.context[1] != null) {
      aunt = grandparent.context[1];
    }
    else if (!parent.isRightChild() && grandparent.context[2] != null){
      aunt = grandparent.context[2];
    }
    
    // red-red violation w/ black aunt
    if (aunt.blackHeight == 1) {
      // 1. Rotate
      // Align child, parent, grandparent in a line
      if (!parent.isRightChild() && child.isRightChild()) {
        rotate(child, parent);
        // swap the child + parent references so that the previous child is now the parent and 
        // the previous parent is now the child
        Node<T> temp = child;
        child = parent;
        parent = temp;
      }
      // Align child, parent, grandparent in a line (mirror configuration)
      if (parent.isRightChild() && !child.isRightChild()) {

        rotate(child, parent);
        // swap the child + parent references so that the previous child is now the
        // parent and
        // the previous parent is now the child
        Node<T> temp = child;
        child = parent;
        parent = temp;
      }
      // After alignment, rotate the parent and grandparent
      rotate(parent, grandparent);

      // 2. Colorswap
      parent.blackHeight = 1;
      grandparent.blackHeight = 0;
      // check root if root is still black; if not make it black
      if (root.blackHeight == 0) {
        root.blackHeight = 1;
      }
    }
    // red-red  violation w/ red aunt
    else if (aunt.blackHeight == 0) {
      // recolor
      parent.blackHeight = 1; 
      grandparent.blackHeight = 0;
      aunt.blackHeight = 1;
      // check upwards
      enforeRedBlackPropertiesAfterInsert(grandparent);
    }
    root.blackHeight = 1; // set root to black because it should always be black
  }

  /**
   * Main method to run tests. Comment out the lines for each test to run them.
   * 
   * @param args
   */
  public static void main(String[] args) {
    // blank
  }

  @Test
  /**
   * Tests the root to be black and adding three increasingly large values and
   * checking
   * for rotation when there is a null (black) aunt but the red nodes are aligned.
   * 
   * Checks the state of the tree using toLevelOrderString() and the color of the
   * root
   */
  void test1() {
    RedBlackTree<Character> tree = new RedBlackTree<>();
    tree.insert('B');
    tree.insert('C');
    tree.insert('D');
    assertEquals("[ C, B, D ]", tree.toLevelOrderString()); // ensures that the tree is in the right order
    assertEquals(tree.findNodeWithData('C').blackHeight, 1); // ensures that the root is black
  }

  @Test
  /**
   * Tests recolor operation when a red node is added with a red aunt
   * 
   * Checks the state of the tree using toLevelOrderString() and the color of the
   * nodes
   * 
   */
  void test2() {
    RedBlackTree<Integer> tree = new RedBlackTree<>();
    tree.insert(2);
    tree.insert(1);
    tree.insert(3);
    tree.insert(4);
    assertEquals(tree.findNodeWithData(4).blackHeight, 0); // ensures that the new node stays red
    assertEquals(tree.findNodeWithData(2).blackHeight, 1); // ensures that the root is recolored to black
                                                           // after recolored to red since it is the root
    assertEquals(tree.findNodeWithData(1).blackHeight, 1); // ensures that this node becomes black
    assertEquals(tree.findNodeWithData(3).blackHeight, 1); // ensures that this node becomes black
    assertEquals("[ 2, 1, 3, 4 ]", tree.toLevelOrderString()); // ensures that the tree is in the right order

  }

  @Test
  /**
   * tests the rotation operation when a red node is added with a black aunt and
   * the red nodes are not aligned
   * 
   * Checks the state of the tree using toLevelOrderString() and the color of
   * the nodes
   * 
   */
  void test3() {
    RedBlackTree<Integer> tree = new RedBlackTree<>();
    tree.insert(50);
    tree.insert(35); // Left of 50
    tree.insert(40); // will be on the right of 35
    assertEquals(tree.findNodeWithData(40).blackHeight, 1); // ensures that 40 is black (root)
    assertEquals(tree.findNodeWithData(50).blackHeight, 0); // ensures that 50 is red
    assertEquals(tree.findNodeWithData(35).blackHeight, 0); // ensures that 35 is red
    assertEquals("[ 40, 35, 50 ]", tree.toLevelOrderString()); // ensures that the tree is in the right order

  }

}
