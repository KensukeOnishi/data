package jp.ac.tokai.ss.onishi.lists;

import android.util.Log;

/**
 * Created by onishi on 2017/08/29.
 */

public class BinarySearchTree extends BinaryTree {

    public boolean search(int key){
        Bnode node = BSTsearch(key, root); //getRoot());
        if (node == z){
            return false;
        }else{
            return true;
        }
    }

    public Bnode BSTsearch(int key, Bnode node){ // 返り値が z なら見つからなかった.
        z.setKey(key);
        do {
            if (key < node.getKey()){
                node = node.getLeft();
            } else {
                node = node.getRight();
            }
        }while( key != node.getKey());
        if (node == z){ //getZ()){
            return z; //getZ();
        } else {
            return node;
        }
    }

    public Boolean insert(int key){
        Bnode node = BSTinsert(key, root) ;
        if (node != null){
            return true;
        } else {
            return false;
        }
    }

    public Bnode BSTinsert(int key, Bnode node){
        Bnode parent;
        do {
            parent = node;
            if (key < node.getKey()) {
                node = node.getLeft();
            } else {
                node = node.getRight();
            }
        } while (node != z);
        node = new Bnode();
        if (node != null){
            node.setKey(key); node.setLeft(z); node.setRight(z);
            if (key < parent.getKey()){
                parent.setLeft(node);
            }else{
                parent.setRight(node);
            }
        }
        return node;
    }

    public Boolean delete(int key){
        Bnode node;
        node = BSTdelete(key, root);
        if (node == z){
            return false;
        }else{
            return true;
        }
    }

    public Bnode BSTdelete(int key, Bnode node){
        Bnode parent, child, found;
        z.setKey(key);
        do {
            parent = node;
            if (key < node.getKey()){
                node = node.getLeft();
            }else{
                node = node.getRight();
            }
        }while(key != node.getKey());

        if(node == z){
            return z;
        } else {
            found = node;
            if (node.getLeft() == z) {
                node = node.getRight();
            } else {
                child = node.getLeft();
                if (child.getRight() == z){
                    child.setRight(found.getRight());
                    node = child;
                }else {
                    while (child.getRight().getRight() != z) {
                        child = child.getRight();
                    }
                    node = child.getRight();
                    child.setRight(node.getLeft());
                    node.setLeft(found.getLeft());
                    node.setRight(found.getRight());
                }
            }
            Log.d("BSTDele","Node: "+node);
            Log.d("BSTDele","Found: "+found+", Parent: "+parent);
            if (found.getKey() < parent.getKey()) {
                parent.setLeft(node);
            } else {
                parent.setRight(node);
            }
            return found;
        }
    }

    public Bnode RotR(Bnode node){
        Bnode tmp;
        tmp = node.getLeft();
        node.setLeft(tmp.getRight());
        tmp.setRight(node);

        return tmp;
    }

}
