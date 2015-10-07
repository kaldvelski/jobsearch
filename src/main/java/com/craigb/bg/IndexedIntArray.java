package com.craigb.bg;

import java.util.*;

/**
 * Represents an array that is 'reverse indexed' so that
 * values can be mapped to their position in the array
 */
public class IndexedIntArray {
    //Each array value can exist in multiple positions within the array
    //So need to map between the value and a list of positions
    private final Map<Integer, List<Integer>> indexedArray = new TreeMap<>();
    private final int originalArrayLength;

    public IndexedIntArray(int[] arrayToIndex) {
        if(arrayToIndex == null || arrayToIndex.length == 0) {
            throw new IllegalArgumentException("Array must be initialized with values");
        }

        for (int idx = 0; idx < arrayToIndex.length; idx++) {
            int arrayValue = arrayToIndex[idx];

            if (!indexedArray.containsKey(arrayValue)) {
                indexedArray.put(arrayValue, new LinkedList<>());
            }
            indexedArray.get(arrayValue).add(idx);
        }

        originalArrayLength = arrayToIndex.length;
    }

    public List<Integer> getPositionsForValue(int value) {
        if(indexedArray.containsKey(value)) {
            return indexedArray.get(value);
        } else {
            return new ArrayList<>();
        }
    }

    public int size() {
        return originalArrayLength;
    }
}
