
I have a few implementation tips . They all revolve around thinking about what indices really are.

Use half-open ranges, and think of indices as pointing between elements. In particular, to before of the element you get by accessing an array at that index (e.g. in an N-element array, 0 is the position before the first element, and N is the position after the last element)

When you partition an array, think: is the element at the partition point part of one or both of the halves? or is it completely excluded from both?

If there is an important index, think: what is the invariant that must always hold for that index?

When you divide an array in pieces, think: does it matter whether you round up or down? (i.e. to which half does the middle element belong?)

Let me give you some examples of these principles applied (in pseudo code):

Quicksort:
```dtd

quicksort := fn (arr, l, r) {
        // empty or 1 element ranges are always sorted.
        if (r-l < 2)
        return;

        pivot := arr[l];

        partition_point := partition(arr, l+1, r, pivot);

        // INVARIANT: an element is greater that the pivot iff
        // it is placed after the partition point.
        // Therefore, we should swap the pivot with the element
        // just before the partition point.

        swap(arr[l], arr[partition_point-1]);

        // NOTE: the pivot is already in the correct position,
        // (just before partition_point) so it does not belong
        // to either half.

        quicksort(arr, l, partition_point-1);
        quicksort(arr, partition_point, r);
        };

```
Binary search:

```dtd

binary_search := fn (arr, predicate) {
        // we search for the first index i such that
        // predicate(arr[i]) is true.

        lo := 0;
        hi := length(arr);

        // INVARIANT: predicate is true for everything after hi
        // INVARIANT: predicate is false for everything before lo
        // NOTE: both of those sets are initially empty

        while(hi != lo){
        mid := (lo+hi)/2;
        if(predicate(arr[mid]))
        // true for arr[mid], therefore true for
        // everything after mid
        hi := mid;
        else
        // false for arr[mid], therefore false for
        // everything before mid+1
        lo := mid+1;
        }

        return hi;
        };

        // returns index to the first element greater than
        // or equal to 0. this will be the first 0, if there
        // are any 0s
        first_zero := fn (arr) {
        return binary_search(arr, fn (x) { return x >= 0; });
        };

        // returns index previous to the first element
        // greater than 0. this will be the last 0, if there
        // are any 0s
        last_zero := fn (arr) {
        return binary_search(arr, fn (x) { return x > 0; }) - 1;
        };

```
