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

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * An iterator that only returns buses from another iterator that go to a particular destination.
 */
public class BusFilteredIterator implements Iterator<Bus> {

  private Iterator<Bus> baseIterator; // The iterator we are filtering.
  private BusStop destination; // The destination BusStop we are filtering by. We only return Bus-es
                               // that stop at this stop.
  private Bus next; // The next Bus to be returned, or null if there aren't any more.


  /**
   * Construct a new BusFilteredIterator that filters the given iterator to return only Bus-es that
   * stop at the given destination.
   * 
   * @param iterator    - the iterator we are filtering
   * @param destination - the desired destination
   */
  public BusFilteredIterator(Iterator<Bus> iterator, BusStop destination) {
    this.baseIterator = iterator;
    this.destination = destination;
    advanceToNext();
  }

  /**
   * Private helper method that advances this iterator. It will iterate over `this.iterator` until
   * it reaches a Bus that stops at destination. Then, it will store that Bus in `next`.
   */
  private void advanceToNext() {
    while (baseIterator.hasNext()) { // if iterator still has a next bus
      Bus curr = baseIterator.next();
      if (curr.goesTo(destination)) { // if it goes to the destination
        next = curr;
        return;
      }
    }
    next = null; // stops
  }

  /**
   * Returns true if there is another Bus (that goes to the destination) in this iterator, or false
   * otherwise. This method should not change any of the fields of the iterator.
   * 
   * @return true if a call to next() will return another Bus; false otherwise.
   */
  public boolean hasNext() {
    // nothing in next
    if (next != null) {
      return true;
    }
    return false;
  }

  /**
   * Returns the `next` bus and advances the iterator until the next bus it will return.
   * 
   * @return Buses from the iterator baseIterator that go to the destination stop.
   * @throws NoSuchElementException - if called when there is no next Bus. Note that you get this
   *                                for free from the baseIterator. You do not need to import
   *                                anything or throw anything yourself.
   */
  public Bus next() throws NoSuchElementException {
    if (this.hasNext() == false) {
      throw new NoSuchElementException("No next bus");
    }
    Bus x = next;
    advanceToNext(); // advances to next bus
    return x;
  }

}
