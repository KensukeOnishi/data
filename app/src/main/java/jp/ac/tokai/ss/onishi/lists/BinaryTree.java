package jp.ac.tokai.ss.onishi.lists;

import android.support.annotation.NonNull;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by onishi on 2017/08/29.
 */

public class BinaryTree {
    protected Bnode root;
    protected Bnode z;

    protected String order="";
    protected ArrayList <Bnode> list = new ArrayList<Bnode>();

    Bnode getRoot(){ return root; }
    Bnode getZ(){ return z; }
    String getOrder(){ return order; }
    void initOrder(){ order=""; list.clear();}

    BinaryTree(){
        z = new Bnode();              // 終端ノード
        z.setLeft(z); z.setRight(z);  // 終端ノードの子は, 終端ノード
        root = new Bnode();           // 根ノード
        root.setKey(0);
        root.setLeft(z); root.setRight(z); // 根ノードの子は, 終端ノード
    }

    public ArrayList<Bnode> preorder(Bnode node){ // 実行前に initNode()を実行すること.
        if (node != z) {
            list.add(node);
            order = order + node.getInfo() + ", ";
            preorder(node.getLeft());
            preorder(node.getRight());
        }
        return list;
    }

    public ArrayList<Bnode> inorder(Bnode node){
        if (node != z) {
            inorder(node.getLeft());
            list.add(node);
            order = order + node.getInfo() + ", ";
            inorder(node.getRight());
        }
        return list;
    }

    public ArrayList<Bnode> postorder(Bnode node){
        if (node != z) {
            postorder(node.getLeft());
            postorder(node.getRight());
            list.add(node);
            order = order + node.getInfo() + ", ";
        }
        return list;
    }

/* 後回し.
    void breathSearch(Bnode node){
        if (node != z){
            order = order + node.getInfo() + ", ";

        }
    }
    */
}
