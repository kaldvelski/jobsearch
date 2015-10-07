package com.craigb.bg;

/**
 * Utility class used to search arrays using other arrays
 */
public class ArrayFinder {
    private final IndexedIntArray indexedArray;

    public ArrayFinder(int[] arrayToSearch) {
        indexedArray = new IndexedIntArray(arrayToSearch);
    }

    /**
     * Find the starting position of one array within an another array
     * i.e. [2,3,4,5] and [4,5] should return 2.
     *
     * @param arrayToFind
     * @return the index of the starting position, -1 if not found
     */
    public int findStartingPositionWithin(int[] arrayToFind) {
        int startingPosition = -1;

        //Some basic input checking before we proceed
        if(arrayToFind == null || arrayToFind.length == 0 || arrayToFind.length > indexedArray.size()) {
            return startingPosition;
        }

        //Find all possible starting positions
        int firstItemToFind = arrayToFind[0];

        for(int possibleStart : indexedArray.getPositionsForValue(firstItemToFind)) {
            if(nextItemIsConsecutive(arrayToFind, 1, possibleStart)) {
                startingPosition = possibleStart;
                break;
            }
        }

        return startingPosition;
    }

    //Recursive method looking if the next item to be found is found in a consecutive position (index+1)
    private boolean nextItemIsConsecutive(int[] arrayToFind, int currentPosition, int startingPosition) {
        boolean isConsecutive = false;

        for(int location : indexedArray.getPositionsForValue(arrayToFind[currentPosition])) {
            isConsecutive = (location == (startingPosition + currentPosition));

            if (isConsecutive && !isLastElementInArray(currentPosition, arrayToFind)) {
                isConsecutive = nextItemIsConsecutive(arrayToFind, currentPosition + 1, startingPosition);
            }

            //Don't check next value if we've found a positive match
            if (isConsecutive) {
                break;
            }
        }

        return isConsecutive;
    }

    private boolean isLastElementInArray(int index, int[] array) {
        return index == array.length -1;
    }


}
