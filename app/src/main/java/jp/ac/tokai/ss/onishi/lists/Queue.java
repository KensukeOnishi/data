package jp.ac.tokai.ss.onishi.lists;

/**
 * Created by onishi on 2016/11/22.
 */

public class Queue {
    private int maxsize = 10;
    private String store[] = new String[maxsize];
    private int front, rear;

    public Queue() {
        front = 0; //maxsize-1;
        rear = 0;
    }

    public String offer(String data){
        if (!full()) {
            store[rear] = data;
            rear = (rear+1) % maxsize;
            return data;
        }else{
            return null;
        }
    }

    public String poll(){
        if (!empty()) {
            String str = store[front];
            front = (front + 1) % maxsize;
            return str;
        } else {
            return null;
        }
    }

    public boolean empty(){
       // if ((front+1) % maxsize == rear){ return true; }
        if ((rear) % maxsize == front){ return true; }
        else { return false; }
    }

    public boolean full(){
       // if ((front+2) % maxsize == rear){ return true; }
        if ((rear+1) % maxsize == front){ return true; }
        else { return false; }
    }

    public int getMaxsize(){ return maxsize; };
    public int getFront(){ return front; };
    public int getRear(){ return rear; };
}
