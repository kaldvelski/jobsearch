package com.craigb.bg;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class ArrayFinderTest {
    @Test
    public void testNullArraysFails() throws Exception {
        ArrayFinder arrayFinder = new ArrayFinder(new int[1]);
        assertThat("Null search array should fail", arrayFinder.findStartingPositionWithin(null), equalTo(-1));
    }

    @Test
    public void testEmptyArraysFails() throws Exception {
        ArrayFinder arrayFinder = new ArrayFinder(new int[1]);
        assertThat("Empty search array should fail", arrayFinder.findStartingPositionWithin(new int[0]), equalTo(-1));
    }

    @Test
    public void testSearchArrayLongerThanContainerFails() throws  Exception {
        ArrayFinder arrayFinder = new ArrayFinder(new int[2]);
        assertThat("Larger search array should fail", arrayFinder.findStartingPositionWithin(new int[4]), equalTo(-1));
    }

    @Test
    public void testSearchArrayNotExistingIsntFound() throws Exception {
        ArrayFinder arrayFinder = new ArrayFinder(new int[] {1, 2});
        assertThat("Search array shouldn't be found if doesn't exist",
                arrayFinder.findStartingPositionWithin(new int[] {3, 4}), equalTo(-1));
    }

    @Test
    public void testSimpleFind() throws Exception {
        ArrayFinder arrayFinder = new ArrayFinder(new int[] {2, 3, 4, 5});
        assertThat("Search array should be found in simple scenario",
                arrayFinder.findStartingPositionWithin(new int[] {4, 5}), equalTo(2));
    }

    @Test
    public void testFindWithMoreThanOnePotentialStart() throws Exception {
        ArrayFinder arrayFinder = new ArrayFinder(new int[] {2, 4, 3, 4, 5});
        assertThat("Search array should be found with more than one possible starting position",
                arrayFinder.findStartingPositionWithin(new int[] {4, 5}), equalTo(3));
    }

    @Test
    public void testFindWithBranchingOptions() throws Exception {
        ArrayFinder arrayFinder = new ArrayFinder(new int[] {2, 3, 4, 7, 3, 4, 5, 3, 4});
        assertThat("Search array should be found with several possible branches",
                arrayFinder.findStartingPositionWithin(new int[] {3, 4, 5}), equalTo(4));
    }
}
