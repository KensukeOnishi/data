package jp.ac.tokai.ss.onishi.lists;

import android.util.Log;

/**
 * Created by onishi on 2017/01/03.
 */

public class SortedListArray extends ListArray {
    @Override
    public boolean insert(String str){
        if(!full()){
            int here=0;
            while ((store[here] != null) &&
                    (str.compareTo(store[here]) > 0)){ here++; }
            for (int move=top; move >= here; move--){
                store[move+1] = store[move];
            }
            store[here]=str;
            top++;
            return true;
        }else{
            return false;
        }
    }

    public int binarySearch(String str){
        int low = 0;
        int high = top-1;
        int mid;
        while (low <= high) {
            mid = low+(high - low)/2;
            int comp = str.compareTo(store[mid]);
            Log.d("Binary ", "Low: "+String.valueOf(low)+", Mid: "+String.valueOf(mid)+
                    ", High: "+String.valueOf(high)+", Comp: "+String.valueOf(comp));
            if (comp == 0){
                return mid;
            }else if (comp > 0){
                low = mid+1;
            }else {
                high = mid-1;
            }
        }
        return notfound;
    }
}
