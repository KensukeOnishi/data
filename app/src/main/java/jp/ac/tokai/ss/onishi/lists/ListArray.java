package jp.ac.tokai.ss.onishi.lists;

/**
 * Created by onishi on 2016/12/20.
 */

public class ListArray {

    protected int maxsize = 10;
    protected int top;
    protected String store[];

    public int notfound = -1;

    public ListArray(){
        store = new String[maxsize+1];
        top = 0;
    }

    public boolean full(){
        if (top >= maxsize){ return true; }
        else { return false; }
    }

    public boolean insert(String str){
        if (!full()){
            store[top]= str;
            top++;
            return true;
        } else {
            return false;
        }
    }

    public int search(String str){
        int here = -1;
        store[top] = str;
        do {
           here++;
        } while (!store[here].equals(str));
        if (here != top){
            return here;
        } else {
            return notfound;
        }
    }

    public boolean delete(String str){
        int here = search(str);
        if (here != notfound){
            top--;
            for (int move = here; move < top; move++){
                store[move]=store[move+1];
            }
            return true;
        } else {
            return false;
        }
    }

    public String peek(int num){
        String str;
        if (num < maxsize) str = store[num]; else str = "";
        return str;
    }

    public int getMaxsize(){ return maxsize; }
    public int getTop(){ return top; }
}
