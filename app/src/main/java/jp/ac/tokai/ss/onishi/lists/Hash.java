package jp.ac.tokai.ss.onishi.lists;

import android.util.Log;

/**
 * Created by onishi on 2017/11/04.
 */

public class Hash {
    private int maxsize = 11;
    private String store[];
    // private int size;

    Hash(){
        store = new String[maxsize];
        for (int i =0; i<store.length; i++){
            store[i] = new String();
        }
    }

    public int hashFunc(String str){
        int h = str.charAt(0) % maxsize;
        for (int i=1; i<str.length(); i++){
            //Log.d("hash", "Hash: "+h);
            h= (h*128+str.charAt(i)) % maxsize;
        }
        return h;
    }

    public boolean insert(String str, int skip){
        int index = hashFunc(str);
        Log.d("hash", "Hash Last: "+index);
        while(!store[index].isEmpty()){
        //while(!store[index].equals("")){
            Log.d("hash", "store["+index+"]"+store[index]);
            index = (index + skip) % maxsize;
        }
        if (store[index].isEmpty() || (index != hashFunc(str))){
            store[index] = str;
            return true;
        }else{
            return false;
        }
    }

    public int getMaxsize(){
        return maxsize;
    }
    public String peek(int num){
        return store[num];
    }
}
