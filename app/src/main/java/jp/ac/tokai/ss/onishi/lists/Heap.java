package jp.ac.tokai.ss.onishi.lists;

import android.util.Log;

/**
 * Created by onishi on 2017/10/06.
 */

public class Heap {
    private int maxsize = 10;
    private int store[];
    private int size;
    private int notfound = Integer.MIN_VALUE;

    public Heap(){
        size=0;
        store = new int[maxsize+1];
        store[0]= Integer.MAX_VALUE;
    }

    private void upheap(int index){
        int value = store[index];
        while (store[index/2] <= value){
            Log.d("UpHeap", "Index: "+index);
            store[index] = store[index/2];
            index = index/2;
        }
        store[index] = value;
    }

    public boolean insert(int num){
        Log.d("Heap.insert", "Size: "+size);
        if (size < maxsize) {
            size++; store[size]=num;
            upheap(size);
            return true;
        }else{
            return false;
        }

        /*
        if (n > maxsize){
            return false;
        } else {
            store[n] = num;
            //n++;
            int i = n;
            while ((i>0) && (num > store[i])){
                store[i] = store[i/2];
                i = i / 2;
            }
            store[i] = num;
            n++;
            return true;
        }
        */
    }

    private void downheap(int index){
        int ret = store[index];
        while (index <= size/2){
            int child = 2*index;
            if (child < size) { if (store[child] < store[child+1]) { child++; }}
            if (ret >= store[child]) break;
            store[index] = store[child]; index = child;
        }
        store[index] = ret;
    }

    public int remove(){
        if (size>0) {
            int ret = store[1];
            store[1] = store[size];
            size--;
            downheap(1);
            return ret;
        } else {
            return notfound;
        }
    }

    public String peek(int num){
        String str;
        if (num <= maxsize){ str = String.valueOf(store[num]); } else { str = null;};
        return str;
    }

    public int getMaxsize() { return maxsize; }
    public int getSize() { return size;}

}
