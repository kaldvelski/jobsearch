package com.craigb.bg;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class IndexedIntArrayTest {

    @Test(expected = IllegalArgumentException.class)
    public void testNullSourceArrayIsRejected() throws Exception {
        new IndexedIntArray(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptySourceArrayIsRejected() throws Exception {
        new IndexedIntArray(new int[0]);
    }


    @Test
    public void testGetPositionsForSingleValue() throws Exception {
        IndexedIntArray indexedIntArray = new IndexedIntArray(new int[] {1,2,3});
        assertThat("Position should be found", indexedIntArray.getPositionsForValue(1).size(), equalTo(1));
        assertThat("Incorrect position for value", indexedIntArray.getPositionsForValue(1).get(0), equalTo(0));
    }

    @Test
    public void testGetPositionsForMultipleValue() throws Exception {
        IndexedIntArray indexedIntArray = new IndexedIntArray(new int[] {1,2,1});
        assertThat("Position should be found", indexedIntArray.getPositionsForValue(1).size(), equalTo(2));
        assertThat("Incorrect position 1 for value", indexedIntArray.getPositionsForValue(1).get(0), equalTo(0));
        assertThat("Incorrect position 1 for value", indexedIntArray.getPositionsForValue(1).get(1), equalTo(2));
    }

    @Test
    public void testGetPositionsForNoExistentValue() throws Exception {
        IndexedIntArray indexedIntArray = new IndexedIntArray(new int[] {1,2,1});
        assertThat("Position should not be found", indexedIntArray.getPositionsForValue(3).size(), equalTo(0));
    }

    @Test
    public void testSize() throws Exception {
        IndexedIntArray indexedIntArray = new IndexedIntArray(new int[] {1,2,1});
        assertThat("Size is incorrect", indexedIntArray.size(), equalTo(3));
    }
}