package jp.ac.tokai.ss.onishi.lists;

import java.util.Locale;

/**
 * Created by onishi on 2016/12/18.
 */

public class Node {
    private String info;
    private Node next;

    public Node(){
    }

    public Node(String info, Node next){
        this.info = info;
        this.next = next;
    }

    public void setInfo(String info) { this.info = info; }
    public void setNext(Node next){ this.next = next; }
    public String getInfo() { return info; }
    public Node getNext() { return next; }

    @Override
    public String toString(){
        return String.format(Locale.JAPAN, "Node: %x, Info: %s, Next: %x",this.hashCode(), info, next.hashCode());
    }
}
