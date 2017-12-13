package jp.ac.tokai.ss.onishi.lists;

import android.util.Log;

/**
 * Created by onishi on 2016/11/22.
 */

public class Stack {
    private int maxsize = 10;
    private int top;
    private String store[];

    public Stack() {
        top = 0;
        store = new String[maxsize];
        Log.d("Stack", "make Stack size="+maxsize);
    }

    public boolean push(String data){
        if (!full()) {
            store[top] = data;
            top++;
            return true;
        }else{
            return false;
        }
    }

    public String get(){
        if (!empty()) {
            top--;
            return store[top];
        } else {
            return null;
        }
    }

    public String peek(){
        if (!empty()) {
            return store[top];
        } else {
            return null;
        }
    }

    public boolean empty(){
        if (top == 0){ return true; }
        else { return false; }
    }

    public boolean full(){
        if (top == maxsize){ return true; }
        else { return false; }
    }

    public int getTop(){ return top;};
    public int getMaxsize(){ return maxsize; };
}
