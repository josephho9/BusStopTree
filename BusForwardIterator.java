
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * An iterator that does an <b>in-order traversal</b> of the BST starting at the given node and
 * iterates forward until we get to the latest bus. In other words, we iterate over all Bus-es in
 * sorted order.
 */
public class BusForwardIterator implements Iterator<Bus> {

  /**
   * Use a stack to keep track of where we are in the iteration. The top of the stack is the next
   * node we need to return.
   */
  private Stack<Node<Bus>> stack;

  /**
   * Construct a new BusForwardIterator that iterates over all nodes in the tree with root `root`
   * starting from the node `node`.
   * 
   * @param root the root of the BST we are iterating.
   * @param node the starting point for the iteration. In other words, this is the first node
   *             returned. The iterator will return this node and every node after it, in sorted
   *             order.
   */
  public BusForwardIterator(Node<Bus> root, Node<Bus> node) {
    stack = new Stack<>();
    if (root == null || node == null) {
      return;
    }

    // build the initial stack by pushing all nodes on the path from the root to the node.
    BusStopTree.pathToNode(stack, root, node);
  }

  /**
   * Returns true if there is another Bus in the iterator.
   * 
   * @return true if a call to next() will return another Bus; otherwise, false.
   */
  @Override
  public boolean hasNext() {
    return !stack.isEmpty();
  }

  /**
   * Returns the next Bus and advances the iterator appropriately.
   * 
   * @return the next Bus in the BST.
   */
  @Override
  public Bus next() {
    // If stack is empty, we are done.
    if (stack.isEmpty()) {
      throw new NoSuchElementException();
    }

    // We return the current element on the top of the stack.
    // Then, we need to decide where to go from here.
    // - If this node has a right child, descend into it.
    // - If this node has no right child,
    // -- If this node IS it's parent's left child, go to the parent.
    // -- If this node IS it's parent's right child, go to the first ancestor greater than the
    // current node.
    Node<Bus> toReturn = stack.pop();

    Node<Bus> right = toReturn.getRight();
    if (right != null) {
      Node<Bus> min = BusStopTree.getFirstBusHelper(right);
      BusStopTree.pathToNode(stack, toReturn, min);
    } else if (stack.peek() == null) {
      // Done!
    } else {
      if (stack.peek().getLeft() == toReturn) {
        // Go to parent... nothing to do.
      } else {
        while (stack.peek().getValue().compareTo(toReturn.getValue()) <= 0) {
          stack.pop(); // Go up
        }
      }
    }

    return toReturn.getValue();
  }
}
