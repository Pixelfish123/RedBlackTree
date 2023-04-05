// --== CS400 Spring 2023 File Header Information ==--
// Name: Joshua LeRoy
// Email: jdleroy2@wisc.edu
// Team: CI
// TA: Karan Grover
// Lecturer: Florian
// Notes to Grader:

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Red-Black Tree implementation with a Node inner class for representing
 * the nodes of the tree. Currently, this implements a Binary Search Tree that
 * we will turn into a red black tree by modifying the insert functionality.
 * In this activity, we will start with implementing rotations for the binary
 * search tree insert algorithm.
 */
public class EnhancedRedBlackTreeAE<T extends Comparable<T>> {

    /**
     * This class represents a node holding a single value within a binary tree.
     */
    protected static class Node<T> {
        public T data;
        // stores the color of the node (1 for black, 0 for red)
        public int blackHeight;
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

        public Node(T data) {
            this.data = data;
            this.blackHeight = 0;
        }

        /**
         * @return true when this node has a parent and is the right child of
         *         that parent, otherwise return false
         */
        public boolean isRightChild() {
            return context[0] != null && context[0].context[2] == this;
        }
    }

    protected Node<T> root; // reference to root node of tree, null when empty
    protected int size = 0; // the number of values in the tree

    /**
     * Performs a naive insertion into a binary search tree: adding the input
     * data value to a new node in a leaf position within the tree. After
     * this insertion, no attempt is made to restructure or balance the tree.
     * This tree will not hold null references, nor duplicate data values.
     * 
     * @param data to be added into this binary search tree
     * @return true if the value was inserted, false if not
     * @throws NullPointerException     when the provided data argument is null
     * @throws IllegalArgumentException when data is already contained in the tree
     */
    public boolean insert(T data) throws NullPointerException, IllegalArgumentException {
        // null references cannot be stored within this tree
        if (data == null)
            throw new NullPointerException(
                    "This RedBlackTree cannot store null references.");

        Node<T> newNode = new Node<>(data);
        if (this.root == null) {
            // add first node to an empty tree
            root = newNode;
            size++;
            enforceRBTreePropertiesAfterInsert(newNode);
            return true;

        } else {
            // insert into subtree
            Node<T> current = this.root;
            while (true) {
                int compare = newNode.data.compareTo(current.data);
                if (compare == 0) {
                    throw new IllegalArgumentException("This RedBlackTree already contains value " + data.toString());
                } else if (compare < 0) {
                    // insert in left subtree
                    if (current.context[1] == null) {
                        // empty space to insert into
                        current.context[1] = newNode;
                        newNode.context[0] = current;
                        this.size++;
                        enforceRBTreePropertiesAfterInsert(newNode);
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
                        enforceRBTreePropertiesAfterInsert(newNode);
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
     * Performs the rotation operation on the provided nodes within this tree.
     * When the provided child is a left child of the provided parent, this
     * method will perform a right rotation. When the provided child is a
     * right child of the provided parent, this method will perform a left rotation.
     * When the provided nodes are not related in one of these ways, this method
     * will throw an IllegalArgumentException.
     * 
     * @param child  is the node being rotated from child to parent position
     *               (between these two node arguments)
     * @param parent is the node being rotated from parent to child position
     *               (between these two node arguments)
     * @throws IllegalArgumentException when the provided child and parent
     *                                  node references are not initially
     *                                  (pre-rotation) related that way
     */
    private void rotate(Node<T> child, Node<T> parent) throws IllegalArgumentException {
        // finds which type of rotation should be done before child's parent is messed
        // up
        boolean doRightRotation = child.isRightChild() ? false : true;
        // checks if there is an adult present
        if (child.context[0] == null || !child.context[0].equals(parent)) {
            throw new IllegalArgumentException();
        }

        // sets the grandparent's child to the proper value
        if (parent.context[0] != null) {
            if (!parent.isRightChild()) {
                parent.context[0].context[1] = child;
                child.context[0] = parent.context[0];
            }
            if (parent.isRightChild()) {
                parent.context[0].context[2] = child;
                child.context[0] = parent.context[0];
            }
        }

        else {
            this.root = child;
        }

        // case for right child (left rotation)
        if (!doRightRotation) {
            // make the old parent acknowledge the child
            child.context[0] = parent.context[0];
            parent.context[0] = child;
            // temp store the old left child
            Node<T> temp = child.context[1];
            // make the old parent the left child
            child.context[1] = parent;
            // set the old left child to the old parent's right child
            parent.context[2] = temp;
            if (temp != null) {
                temp.context[0] = parent;
            }
        }

        // case for left child (right rotation)
        else {
            // make the old parent acknowledge the child
            child.context[0] = parent.context[0];
            parent.context[0] = child;
            // temp store the old right child
            Node<T> temp = child.context[2];
            // make the old parent the right child
            child.context[2] = parent;
            // set the old right child to the old parent's left child
            parent.context[1] = temp;
            if (temp != null) {
                temp.context[0] = parent;
            }
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
     * Removes the value data from the tree if the tree contains the value.
     * This method will not attempt to rebalance the tree after the removal and
     * should be updated once the tree uses Red-Black Tree insertion.
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
     * Helper method that will replace a node with a replacement node. The
     * replacement
     * node may be null to remove the node from the tree.
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
     * Helper method that will return the inorder successor of a node with two
     * children.
     * 
     * @param node the node to find the successor for
     * @return the node that is the inorder successor of node
     */
    protected Node<T> findMinOfRightSubtree(Node<T> node) {
        if (node.context[1] == null && node.context[2] == null) {
            throw new IllegalArgumentException("Node must have two children");
        }
        // take a step to the right
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
     * Helper method that will return the node in the tree that contains a specific
     * value. Returns null if there is no node that contains the value.
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
     * This method performs an inorder traversal of the tree. The string
     * representations of each data value within this tree are assembled into a
     * comma separated string within brackets (similar to many implementations
     * of java.util.Collection, like java.util.ArrayList, LinkedList, etc).
     * 
     * @return string containing the ordered values of this tree (in-order
     *         traversal)
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
     * This method performs a level order traversal of the tree. The string
     * representations of each data value
     * within this tree are assembled into a comma separated string within
     * brackets (similar to many implementations of java.util.Collection).
     * This method will be helpful as a helper for the debugging and testing
     * of your rotation implementation.
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
        return "level order: " + this.toLevelOrderString() +
                "\nin order: " + this.toInOrderString();
    }

    /**
     * A method to ensure that the tree is still a valid red black tree after a new
     * node is inserted
     */
    protected void enforceRBTreePropertiesAfterInsert(Node<T> node) {
        // ensures that the root is black
        Node<T> currentCheckedNode = node;
        while (currentCheckedNode.context[0] != null) {
            currentCheckedNode = currentCheckedNode.context[0];
        }

        currentCheckedNode.blackHeight = 1;

        // checks if the node has a red parent, if the node has a red parent it also
        // must have a grandparent
        if (node.context[0] != null && node.context[0].blackHeight == 0 && node.blackHeight == 0) {
            // if the parent node is the right child, the uncle node is the left one
            Node<T> uncleNode = node.context[0].isRightChild() ? node.context[0].context[0].context[1]
                    : node.context[0].context[0].context[2];

            // if the uncle is red, then executes the color swaps
            if (uncleNode != null && uncleNode.blackHeight == 0) {
                // set the parent, uncle and grandparent nodes then check the parent node
                node.context[0].blackHeight = 1;
                uncleNode.blackHeight = 1;
                node.context[0].context[0].blackHeight = 0;
                enforceRBTreePropertiesAfterInsert(node.context[0].context[0]);
            }

            // if uncle is black
            else {
                // ensures that the child is on the correct side of the parent
                if ((node.isRightChild() && !node.context[0].isRightChild())
                        || (!node.isRightChild() && node.context[0].isRightChild())) {
                    node.blackHeight = 1;
                    node.context[0].context[0].blackHeight = 0;
                    rotate(node, node.context[0]);
                    // now perform final rotate on parent
                    rotate(node, node.context[0]);
                }

                else {
                    node.context[0].blackHeight = 1;
                    node.context[0].context[0].blackHeight = 0;

                    rotate(node.context[0], node.context[0].context[0]);
                }
            }
        }
    }

    /**
     * Gets the previous node from a given value
     * 
     * @param value: the value that you're trying to find the previous node for
     * @return the largest node smaller than the value
     * @throws Exception if there's no previous node
     */
    private Node<T> getPrevious(Node<T> value) throws IllegalStateException {
        Node<T> current = null;
        // previous case if the node has a left child. The previous node will be the far
        // right leaf of the left child's branch
        if (value.context[1] != null) {
            current = value.context[1];
            while (current.context[2] != null) {
                current = current.context[2];
            }
        }

        // case if the node has a parent that it's less than and doesn't have any
        // smaller children
        else if (value.isRightChild()) {
            current = value.context[0];
        }

        // case to find where on parent branch is a
        else {
            current = value.context[0];
            while (current.context[0] != null) {
                if (current.isRightChild() && current.context[0] != null) {
                    if (current.context[0].data.compareTo(value.data) > 0) {
                        throw new IllegalStateException("no previous exists");
                    }
                    return current.context[0];
                }
                current = current.context[0];
            }
            throw new IllegalStateException("no previous exists");
        }
        return current;
    }

    /**
     * method to get the last values of the list
     * 
     * @param numValues: the number of values to return
     * @return the end of the list
     */
    public T[] getLast(int numValues) {
        try {
            T[] returnValue = (T[]) Array.newInstance(this.root.data.getClass(), numValues);
            Node<T> current = root;

            // find the last node
            while (current.context[2] != null) {
                current = current.context[2];
            }

            // add to the array until it's full
            int numRemaining = numValues;
            while (numRemaining > 1) {
                returnValue[numValues - numRemaining] = current.data;
                current = getPrevious(current);
                numRemaining--;
            }
            returnValue[numValues - numRemaining] = current.data;
            return returnValue;
        }

        catch (IllegalStateException e) {
            throw new IllegalArgumentException("not enough elements in the tree");
        }
    }

    /**
     * Gets the next node from a given value
     * 
     * @param value: the value that you're trying to find the next node for
     * @return the smallest node larger than the value
     * @throws Exception if there's no next node
     */
    private Node<T> getNext(Node<T> value) throws IllegalStateException {
        Node<T> current = null;

        // next case if the node has a right child. The next node will be the far left
        // leaf of the right child's branch
        if (value.context[2] != null) {
            current = value.context[2];
            while (current.context[1] != null) {
                current = current.context[1];
            }
        }

        // case if the node has a parent that it's greater than and doesn't have any
        // larger children
        else if (!value.isRightChild()) {
            current = value.context[0];
        }

        // case to find where on parent branch is a
        else {
            current = value.context[0];
            while (current.context[0] != null) {
                if (!current.isRightChild() && current.context[0] != null) {
                    if (current.context[0].data.compareTo(value.data) < 0) {
                        throw new IllegalStateException("no next exists");
                    }
                    return current.context[0];
                }
                current = current.context[0];
            }
            throw new IllegalStateException("no next exists");
        }
        return current;
    }

    /**
     * method to get the last values of the list
     * 
     * @param numValues: the number of values to return
     * @return the end of the list
     */
    public T[] getFirst(int numValues) {
        try {
            T[] returnValue = (T[]) Array.newInstance(this.root.data.getClass(), numValues);
            Node<T> current = root;
            // find the first node
            while (current.context[1] != null) {
                current = current.context[1];
            }

            // add to the array until it's full
            int numRemaining = numValues;
            while (numRemaining > 0) {
                returnValue[numValues - numRemaining] = current.data;
                current = getNext(current);
                numRemaining--;
            }
            return returnValue;
        }

        catch (IllegalStateException e) {
            throw new IllegalArgumentException("not enough elements in the tree");
        }
    }

    /**
     * A method to get all of the values greater than the first and less than the
     * second parameter
     * 
     * @param minimum: the lowest value that is in the range
     * @param maximum: the maximum value in the range
     * 
     * @return an array of all values in the array
     * @throws IllegalArgumentException if the maximum value is less than the
     *                                  minimum
     */
    public T[] getRange(T minimum, T maximum) throws IllegalArgumentException {
        // throws proper error if the max value is less than the minimum
        if (maximum.compareTo(minimum) < 0) {
            throw new IllegalArgumentException("maximum value is less than mimimum value");
        }

        // array list to store the elements that fit in the list
        ArrayList<T> values = new ArrayList<T>();
        T[] returnArray = (T[]) Array.newInstance(this.root.data.getClass(), 0);
        // if there's nothing in the tree return nothing
        if (root == null) {
            return returnArray;
        }

        // finds the first value in the tree
        Node<T> currentNode = root;
        while (currentNode.context[1] != null) {
            currentNode = currentNode.context[1];
        }

        // gets to minimum
        while (currentNode != null && minimum.compareTo(currentNode.data) > 0) {
            try {
                currentNode = getNext(currentNode);
            }
            // if there's no next node and the min is still greater than the current node,
            // then it should return empty
            catch (Exception e) {
                return returnArray;
            }
        }

        // min to max
        while (maximum.compareTo(currentNode.data) > 0) {
            try {
                values.add(currentNode.data);
                currentNode = getNext(currentNode);
            }
            // if there's no next node
            catch (Exception e) {
                // populate and return the array once it's done
                returnArray = (T[]) Array.newInstance(this.root.data.getClass(), values.size());
                for (int i = 0; i < values.size(); i++) {
                    returnArray[i] = values.get(i);
                }
                return returnArray;
            }
        }
        values.add(currentNode.data);
        // if nothing's been returned, return this
        returnArray = (T[]) Array.newInstance(this.root.data.getClass(), values.size());
        for (int i = 0; i < values.size(); i++) {
            returnArray[i] = values.get(i);
        }
        return returnArray;
    }
}
