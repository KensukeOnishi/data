package jp.ac.tokai.ss.onishi.lists;

import java.util.Locale;

/**
 * Created by onishi on 2017/08/29.
 */

public class Bnode {
    private int key;
    private String info;
    private Bnode left, right;

    public void setKey(int key){ this.key = key; }
    public void setInfo(String info){ this.info = info; }
    public void setLeft(Bnode left){ this.left = left; }
    public void setRight(Bnode right){ this.right = right; }
    public int getKey() { return key; }
    public String getInfo(){ return info; }
    public Bnode getLeft(){ return left; }
    public Bnode getRight(){ return right; }

    @Override
    public String toString(){
 //       return String.format(Locale.JAPAN, "Node: %x, Key: %x, Info: %s, Left: %x, Right: %x",
 //               this.hashCode(), key, info, left.hashCode(), right.hashCode());
        return String.format(Locale.JAPAN, "N: %x, K: %d, L: %x, R: %x",
                this.hashCode(), key, left.hashCode(), right.hashCode());
    }
}
