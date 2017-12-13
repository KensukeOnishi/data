package jp.ac.tokai.ss.onishi.lists;

/**
 * Created by onishi on 2016/12/13.
 */

public class List {

    private Node head;
    private Node z;

    public List(){
        head = new Node();
        z = new Node();

        head.setNext(z);
        z.setNext(z);
    }

    public String addNode(String str){
        Node node = new Node();
        if (node != null){
            node.setInfo(str);
            node.setNext(head.getNext());
            head.setNext(node);
            return str;
        }else {
            return null;
        }
    }

    public boolean search(String str){
        Node node = head;
        z.setInfo(str);
        do {
            node = node.getNext();
        } while(!str.equals(node.getInfo()));
        if (node == z){
            return false;
        } else {
            return true;
        }
    }

    public boolean delete(String str){
        Node prev; // = head;
        Node node = head; //prev.getNext();
        z.setInfo(str);
        do {
            prev = node;
            node = node.getNext();
        } while (!str.equals(node.getInfo()));

        if(node != z){
            prev.setNext(node.getNext());
            return true;
        } else {
            return false;
        }
    }

    public Node getHead(){
        return head;
    }
    public Node getZ(){
        return z;
    }

}
