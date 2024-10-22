
//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Bus Stop Tree
// Course: CS 300 Fall 2023
//
// Author: Joseph Ho
// Email: jho29@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: N/A
// Partner Email: N/A
// Partner Lecturer's Name: N/A
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: NA
// Online Sources: NA
//
///////////////////////////////////////////////////////////////////////////////


import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Class that contains all the testers for methods in the BusStopTree class.
 */
public class BusStopTreeTester {

  /**
   * Tests that compareTo returns the correct value when comparing a bus with a different arrival.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testBusCompareToDifferentArrivalTime() {
    int[] stopIds = {1, 2, 3, 4, 5};
    String[] stopTimes1 = {"01:00", "02:00", "03:00", "04:00", "05:00"};
    String[] stopTimes2 = {"06:00", "07:00", "08:00", "09:00", "10:00"};
    Bus bus1 = new Bus(BusStop.getStop(2),
        BusRoute.dummyRoute("ROUTE A", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes1));
    Bus bus2 = new Bus(BusStop.getStop(2),
        BusRoute.dummyRoute("ROUTE A", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes2));

    // compare bus1 to bus2 and vice versa
    boolean correctComparison1 = bus1.compareTo(bus2) < 0; // negative value
    boolean correctComparison2 = bus2.compareTo(bus1) > 0; // positive value

    // test passes if both comparisons return expected
    return correctComparison1 && correctComparison2;
  }

  /**
   * For two buses with the same arrival time but different routes, test that compareTo returns the
   * correct value.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testBusCompareToSameArrivalTimeDifferentRoute() {
    // setup
    int[] stopIds = {1, 2, 3};
    String[] stopTimes = {"01:00", "02:00", "03:00"};

    Bus bus1 = new Bus(BusStop.getStop(1),
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes));
    Bus bus2 = new Bus(BusStop.getStop(1),
        BusRoute.dummyRoute("ROUTE 2", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes));

    boolean x = (bus1.compareTo(bus2) < 0); // negative
    boolean y = (bus2.compareTo(bus1) > 0); // positive

    return x && y;
  }

  /**
   * For two buses with the same arrival time and route name, but different directions, test that
   * compareTo returns the correct value.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testBusCompareToSameArrivalTimeSameRouteDifferentDirection() {
    // setup
    int[] stopIds = {1, 2, 3};
    String[] stopTimes = {"01:00", "02:00", "03:00"};

    Bus outgoing = new Bus(BusStop.getStop(2),
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes));
    Bus incoming = new Bus(BusStop.getStop(2),
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.INCOMING, stopIds, stopTimes));

    // compare bus1 to bus2 and vice versa, a is less than b
    boolean x = outgoing.compareTo(incoming) < 0; // negative
    boolean y = incoming.compareTo(outgoing) > 0; // positive

    // test passes if both comparisons return
    return x && y;
  }

  /**
   * Tests that compareTo returns the correct value (0) when comparing a bus with the same arrival
   * time, route name, and direction.
   * 
   * @return true if the test passes, false otherwise.
   */
  private static boolean testBusCompareToSameBus() {
    int[] stopIds1 = {1, 2, 3, 4, 5};
    String[] stopTimes1 = {"05:00", "07:00", "09:00", "11:00", "13:00"};
    Bus bus1 = new Bus(BusStop.getStop(2),
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1));
    Bus bus2 = new Bus(BusStop.getStop(2),
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1));

    // compare bus1 to bus2 and vice versa
    boolean x = bus1.compareTo(bus2) == 0; // 0
    boolean y = bus2.compareTo(bus1) == 0; // 0

    // test passes if both comparisons return 0
    return x && y;
  }

  /**
   * Tests that isValidBST returns true for an empty BST.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testIsValidBSTEmpty() {
    // empty BST
    BusStopTree busList = new BusStopTree(1);
    // should return true as empty BST is valid
    return BusStopTree.isValidBST(busList.getRoot());
  }

  /**
   * Tests that isValidBST returns false for an invalid BST. Should use a tree with depth > 2. Make
   * sure to include a case where the left subtree contains a node that is greater than the right
   * subtree. (See the example in the spec for more details.)
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testIsValidBSTInvalid() {
    int[] stopIds = {1, 2, 3, 4, 5};
    String[] stopTimes = {"01:00", "02:00", "03:00", "04:00", "05:00"};

    Bus bus1 = new Bus(BusStop.getStop(1),
        BusRoute.dummyRoute("ROUTE A", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes));
    Bus bus2 = new Bus(BusStop.getStop(2),
        BusRoute.dummyRoute("ROUTE B", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes));
    Bus bus3 = new Bus(BusStop.getStop(3),
        BusRoute.dummyRoute("ROUTE C", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes));
    Bus bus4 = new Bus(BusStop.getStop(3),
        BusRoute.dummyRoute("ROUTE D", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes));
    Bus bus5 = new Bus(BusStop.getStop(4),
        BusRoute.dummyRoute("ROUTE E", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes));

    Node<Bus> root = new Node<Bus>(bus3);
    Node<Bus> one = new Node<Bus>(bus1);
    Node<Bus> two = new Node<Bus>(bus2);
    Node<Bus> four = new Node<Bus>(bus4);
    Node<Bus> five = new Node<Bus>(bus5);
    // create invalid bst as two is the right child of three
    root.setRight(two);
    root.setLeft(one);
    two.setRight(four);
    two.setLeft(five);
    // should return !false which is true
    return !BusStopTree.isValidBST(root);
  }

  /**
   * Tests that isValidBST returns true for a valid BST. Should use a tree with depth > 2.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testIsValidBSTValid() {
    // setup
    int[] stopIds1 = {1, 2, 3, 4};
    String[] stopTimes1 = {"01:00", "02:00", "03:00", "04:00"};
    BusRoute route1 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    Bus bus1 = new Bus(BusStop.getStop(1), route1);
    Bus bus2 = new Bus(BusStop.getStop(2), route1);
    Bus bus3 = new Bus(BusStop.getStop(3), route1);
    Bus bus4 = new Bus(BusStop.getStop(4), route1);

    Node<Bus> root = new Node<Bus>(bus3);
    Node<Bus> one = new Node<Bus>(bus1);
    Node<Bus> two = new Node<Bus>(bus2);
    Node<Bus> four = new Node<Bus>(bus4);
    // creates BST with 1<2<4 which is valid
    root.setLeft(two);
    root.setRight(four);
    two.setLeft(one);

    // Expecting true
    return BusStopTree.isValidBST(root);
  }


  /**
   * Tests that addBus correctly adds a bus to an empty BST.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testAddBusEmpty() {
    // setup
    BusStopTree busList = new BusStopTree(1);
    int[] stopIds = {1, 2, 3};
    String[] stopTimes1 = {"01:00", "02:00", "03:00"};
    BusRoute route1 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes1);
    Bus bus1 = new Bus(BusStop.getStop(1), route1);

    if (busList.isEmpty()) { // only run if busList is empty
      busList.addBus(bus1); // adds bus
      if (busList.getFirstBus() == bus1) { // first bus should be bus1
        return true;
      } else {
        return false;
      }

    } else { // busList isn't empty
      return false;
    }
  }


  /**
   * Tests that addBus correctly adds a bus to a non-empty BST. Each time you add a bus, make sure
   * that 1) addBus() returns true, 2) the BST is still valid, 3) the BST size has been incremented.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testAddBus() {
    // Initialization
    BusStopTree busList = new BusStopTree(1);
    int[] stopIds = {1, 2, 3};
    String[] stopTimes1 = {"01:00", "02:00", "03:00"};
    String[] stopTimes2 = {"04:00", "05:00", "06:00"};
    String[] stopTimes3 = {"07:00", "08:00", "09:00"};

    Bus bus1 = new Bus(BusStop.getStop(1),
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes1));
    Bus bus2 = new Bus(BusStop.getStop(1),
        BusRoute.dummyRoute("ROUTE 2", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes2));
    Bus bus3 = new Bus(BusStop.getStop(1),
        BusRoute.dummyRoute("ROUTE 3", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes3));

    // Result values
    boolean isFirstBusAdded = true;
    boolean isSecondBusAdded = true;
    boolean isThirdBusAdded = true;

    // Try adding bus1
    if (busList.addBus(bus1)) {
      busList.addBus(bus1);
      if ((!BusStopTree.isValidBST(new Node<Bus>(bus1))) && (busList.size() != 1)) { // checks for
                                                                                     // valid bst
                                                                                     // and size
        isFirstBusAdded = false;
      }
    } else {
      isFirstBusAdded = false;
    }

    // Try adding bus2
    if (busList.addBus(bus2)) {
      busList.addBus(bus2);
      if ((!busList.isValidBST(new Node<Bus>(bus1))) && (busList.size() != 2)) {
        isSecondBusAdded = false;
      }
    } else {
      isSecondBusAdded = false;
    }

    // Try adding bus3
    if (busList.addBus(bus3)) {
      busList.addBus(bus3);
      if ((!busList.isValidBST(new Node<Bus>(bus1))) && (busList.size() != 3)) {
        isThirdBusAdded = false;
      }
    } else {
      isThirdBusAdded = false;
    }

    // Return true if all buses were added successfully
    return isFirstBusAdded && isSecondBusAdded && isThirdBusAdded;
  }



  /**
   * Tests that addBus returns false when adding a duplicate bus. The BST should not be modified
   * (same size).
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testAddBusDuplicate() {
    // Initialization
    BusStopTree busList = new BusStopTree(1);
    int[] stopIds = {1, 2, 3};
    String[] stopTimes1 = {"01:00", "02:00", "03:00"};

    Bus bus1 = new Bus(BusStop.getStop(1),
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes1));

    boolean isDuplicateCheck = true;

    busList.addBus(bus1);

    // Try adding bus1 again (duplicate)
    if (busList.addBus(bus1)) {
      isDuplicateCheck = false;
      return isDuplicateCheck;
    }

    // Check if the size was modified
    if (busList.size() != 1) {
      isDuplicateCheck = false;
    }

    return isDuplicateCheck;
  }



  /**
   * Tests that contains returns true when the BST contains the Bus, and false otherwise.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testContains() {
    // Initialization
    int[] stopIds = {1, 2, 3, 4, 5};
    String[] stopTimes = {"01:00", "02:00", "03:00", "04:00", "05:00"};
    BusStopTree busList = new BusStopTree(1);
    Bus bus1 = new Bus(BusStop.getStop(1),
        BusRoute.dummyRoute("ROUTE A", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes));
    Bus bus2 = new Bus(BusStop.getStop(2),
        BusRoute.dummyRoute("ROUTE A", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes));
    Bus bus3 = new Bus(BusStop.getStop(1),
        BusRoute.dummyRoute("ROUTE A", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes));

    busList.addBus(bus1);
    busList.addBus(bus3);

    // Perform tests
    boolean containsBus1 = busList.contains(bus1);
    boolean doesNotContainBus2 = !busList.contains(bus2);
    boolean containsBus3 = busList.contains(bus3);

    return containsBus1 && doesNotContainBus2 && containsBus3;
  }


  /**
   * Tests that getFirstNodeAfter returns the correct Node when the correct Node is the node passed
   * in as the root node parameter.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testGetFirstNodeAfterRoot() {
    // Initialization
    BusStopTree busList = new BusStopTree(1);
    int[] stopIds = {1, 2, 3};
    String[] stopTimes1 = {"06:00", "03:00", "02:00"};
    String[] stopTimes2 = {"02:00", "05:00", "07:00"};
    String[] stopTimes3 = {"16:00", "08:00", "10:00"};

    Bus bus1 = new Bus(BusStop.getStop(1),
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes1));
    Bus bus2 = new Bus(BusStop.getStop(1),
        BusRoute.dummyRoute("ROUTE 2", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes2));
    Bus bus3 = new Bus(BusStop.getStop(1),
        BusRoute.dummyRoute("ROUTE 3", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes3));

    busList.addBus(bus1);
    busList.addBus(bus2);
    busList.addBus(bus3);

    Node<Bus> resultNode = busList.getFirstNodeAfter(LocalTime.parse("05:00"), busList.getRoot()); // should
                                                                                                   // be
                                                                                                   // bus1

    // Check if the result node equals bus1
    return resultNode.getValue().equals(bus1);
  }


  /**
   * Tests that getFirstNodeAfter returns the correct Node when the correct Node is in the left
   * subtree.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testGetFirstNodeAfterLeftSubtree() {
    // Initialization
    BusStopTree busList = new BusStopTree(1);
    int[] stopIds = {1, 2, 3};
    String[] stopTimes1 = {"04:00", "02:00", "04:00"};
    String[] stopTimes2 = {"03:00", "04:00", "06:00"};
    String[] stopTimes3 = {"10:00", "08:00", "04:00"};

    Bus bus1 = new Bus(BusStop.getStop(1),
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes1));
    Bus bus2 = new Bus(BusStop.getStop(1),
        BusRoute.dummyRoute("ROUTE 2", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes2));
    Bus bus3 = new Bus(BusStop.getStop(1),
        BusRoute.dummyRoute("ROUTE 3", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes3));

    busList.addBus(bus1);
    busList.addBus(bus2);
    busList.addBus(bus3);

    Node<Bus> resultNode = busList.getFirstNodeAfter(LocalTime.parse("02:00"), busList.getRoot()); // should
                                                                                                   // be
                                                                                                   // bus2

    // Check if the result node equals bus2
    return resultNode.getValue().equals(bus2);
  }


  /**
   * Tests that getFirstNodeAfter returns the correct Node when the correct Node is in the right
   * subtree.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testGetFirstNodeAfterRightSubtree() {
    BusStopTree busList = new BusStopTree(1);
    int[] stopIds = {1, 2, 3};
    String[] stopTimes1 = {"03:00", "01:00", "03:00"};
    String[] stopTimes2 = {"02:00", "05:00", "03:00"};
    String[] stopTimes3 = {"10:00", "09:00", "05:00"};

    Bus bus1 = new Bus(BusStop.getStop(1),
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes1));
    Bus bus2 = new Bus(BusStop.getStop(1),
        BusRoute.dummyRoute("ROUTE 2", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes2));
    Bus bus3 = new Bus(BusStop.getStop(1),
        BusRoute.dummyRoute("ROUTE 3", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes3));

    busList.addBus(bus1);
    busList.addBus(bus2);
    busList.addBus(bus3);

    Node<Bus> resultNode = busList.getFirstNodeAfter(LocalTime.parse("09:00"), busList.getRoot()); // should
                                                                                                   // be
                                                                                                   // bus3

    // Check if the result node is null and if it equals bus3
    return resultNode.getValue().equals(bus3);
  }


  /**
   * Tests that removeBus correctly removes a Bus that is a LEAF NODE. Make sure that 1) removeBus
   * returns the removed Bus, 2) the BST is still valid, 3) the BST size has been decremented.
   * 
   * @return true if the test passes, false otherwise
   */
  public static boolean testRemoveBusLeaf() {
    return false;
  }

  /**
   * Tests that removeBus correctly removes a Bus that is a non-leaf node with ONE child. Make sure
   * that 1) removeBus returns the removed Bus, 2) the BST is still valid, 3) the BST size has been
   * decremented.
   * 
   * @return true if the test passes, false otherwise
   */
  public static boolean testRemoveBusNodeOneChild() {
    return false;
  }

  /**
   * Tests that removeBus correctly removes a Bus that is a non-leaf node with TWO children. Make
   * sure that 1) removeBus returns the removed Bus, 2) the BST is still valid, 3) the BST size has
   * been decremented.
   * 
   * @return true if the test passes, false otherwise
   */
  public static boolean testRemoveBusNodeTwoChildren() {
    return false;
  }

  /**
   * Tests that removeBus returns false when removing a Bus that is not in the BST. The BST should
   * not be modified.
   * 
   * @return true if the test passes, false otherwise
   */
  public static boolean testRemoveBusNodeNotInBST() {
    return false;
  }

  /**
   * Tests the creation of an BusFilteredIterator where NONE of the buses go to the destination.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testGetNextBusesToEmpty() {
    // Initialization
    ArrayList<Bus> busList = new ArrayList<Bus>();
    int[] stopIds = {1, 2, 3, 4, 5};
    String[] stopTimes = {"01:00", "02:00", "03:00", "04:00", "05:00"};

    Bus departingBus1 = new Bus(BusStop.getStop(1),
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes));
    Bus departingBus2 = new Bus(BusStop.getStop(2),
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes));

    busList.add(departingBus1);
    busList.add(departingBus2);

    try {
      // create an iterator for non existent stop
      BusFilteredIterator iteratorForNonexistentStop =
          new BusFilteredIterator(busList.iterator(), BusStop.getStop(6));

      // get next bus,should throw exception
      iteratorForNonexistentStop.next();

      // If no exception is thrown, return false
      return false;
    } catch (Exception e) { // Expect an exception
      return true;
    }
  }


  /**
   * Tests the creation of an BusFilteredIterator where SOME of the buses go to the destination.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testGetNextBusesToSome() {
    // Initialization
    ArrayList<Bus> busList = new ArrayList<Bus>();
    int[] stopIds = {1, 2, 3, 4};
    String[] stopTimes = {"01:00", "02:00", "03:00", "04:00"};

    Bus departingBus1 = new Bus(BusStop.getStop(1),
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes));
    Bus departingBus2 = new Bus(BusStop.getStop(2),
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes));
    Bus departingBus3 = new Bus(BusStop.getStop(3),
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes));

    busList.add(departingBus1);
    busList.add(departingBus2);
    busList.add(departingBus3);

    try {
      // creates iterators for one existing spot and one nonexistent
      BusFilteredIterator iteratorForStop =
          new BusFilteredIterator(busList.iterator(), BusStop.getStop(1));
      BusFilteredIterator iteratorForNoStop =
          new BusFilteredIterator(busList.iterator(), BusStop.getStop(7));

      // Perform tests
      boolean hasBusesForStop = iteratorForStop.hasNext(); // should return true
      boolean noBusStop = !iteratorForNoStop.hasNext(); // should return !false (there is no 7 stop)

      return hasBusesForStop && noBusStop;
    } catch (NoSuchElementException e) {
      return false;
    }
  }


  /**
   * Tests the creation of an BusFilteredIterator where ALL of the buses go to the destination.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testGetNextBusesToAll() {
    // Setup
    ArrayList<Bus> busList = new ArrayList<Bus>();
    int[] stopIds = {1, 2, 3, 4};
    String[] stopTimes = {"01:00", "02:00", "03:00", "04:00"};

    boolean hasNextElement = false;


    Bus firstBus = new Bus(BusStop.getStop(1),
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes));
    Bus secondBus = new Bus(BusStop.getStop(2),
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes));
    Bus thirdBus = new Bus(BusStop.getStop(3),
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes));
    Bus fourthBus = new Bus(BusStop.getStop(4),
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes));

    busList.add(firstBus);
    busList.add(secondBus);
    busList.add(thirdBus);
    busList.add(fourthBus);

    try {
      BusFilteredIterator busIterator =
          new BusFilteredIterator(busList.iterator(), BusStop.getStop(1));
      hasNextElement = busIterator.hasNext(); // should return true
    } catch (NoSuchElementException e) {
      return false; // Return false if NoSuchElementException is caught
    }

    return hasNextElement;
  }


  /**
   * Main method to run all the testers
   * 
   * @param args
   */
  public static void main(String args[]) {
    BusStop.createDummyStops();
    System.out.println(
        testBusCompareToDifferentArrivalTime() + " --> testBusCompareToDifferentArrivalTime ");
    System.out.println(testBusCompareToSameArrivalTimeDifferentRoute()
        + " --> testBusCompareToSameArrivalTimeDifferentRoute ");
    System.out.println(testBusCompareToSameArrivalTimeSameRouteDifferentDirection()
        + " --> testBusCompareToSameArrivalTimeSameRouteDifferentDirection");
    System.out.println(testBusCompareToSameBus() + " --> testBusCompareToSameBus");
    System.out.println(testIsValidBSTEmpty() + " --> testIsValidBSTEmpty");
    System.out.println(testIsValidBSTInvalid() + " --> testIsValidBSTInvalid");
    System.out.println(testIsValidBSTValid() + " --> testIsValidBSTValid");
    System.out.println(testAddBusEmpty() + " --> testAddBusEmpty");
    System.out.println(testAddBus() + " --> testAddBus");
    System.out.println(testAddBusDuplicate() + " --> testAddBusDuplicate");
    System.out.println(testContains() + " --> testContains");
    System.out.println(testGetFirstNodeAfterRoot() + " --> testGetFirstNodeAfterRoot");
    System.out
        .println(testGetFirstNodeAfterLeftSubtree() + " --> testGetFirstNodeAfterLeftSubtree");
    System.out
        .println(testGetFirstNodeAfterRightSubtree() + " --> testGetFirstNodeAfterRightSubtree");
    System.out.println(testGetNextBusesToEmpty() + " --> testGetNextBusesToEmpty");
    System.out.println(testGetNextBusesToSome() + " --> testGetNextBusesToSome");
    System.out.println(testGetNextBusesToAll() + " --> testGetNextBusesToAll");

  }
}
