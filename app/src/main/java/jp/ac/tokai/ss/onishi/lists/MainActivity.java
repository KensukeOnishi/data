package jp.ac.tokai.ss.onishi.lists;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.media.RatingCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

import static android.util.Log.d;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button stackA;
    Button queueA;
    Button listArrayA;
    Button listA;
    Button sortedListArrayA;
    Button bstA;
    Button heapA;
    Button hashA;


    int textSize = 25;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        stackA = new Button(this);
        stackA.setText("スタック");
        stackA.setTextSize(textSize);
        stackA.setOnClickListener(this);
        layout.addView(stackA);

        queueA = new Button(this);
        queueA.setText("キュー");
        queueA.setTextSize(textSize);
        queueA.setOnClickListener(this);
        layout.addView(queueA);

        listArrayA = new Button(this);
        listArrayA.setText("リスト(配列)");
        listArrayA.setTextSize(textSize);
        listArrayA.setOnClickListener(this);
        layout.addView(listArrayA);

        listA = new Button(this);
        listA.setText("リスト(参照型)");
        listA.setTextSize(textSize);
        listA.setOnClickListener(this);
        layout.addView(listA);

        sortedListArrayA = new Button(this);
        sortedListArrayA.setText("順序付きリスト(配列)");
        sortedListArrayA.setTextSize(textSize);
        sortedListArrayA.setOnClickListener(this);
        layout.addView(sortedListArrayA);

        bstA = new Button(this);
        bstA.setText("二分探索木");
        bstA.setTextSize(textSize);
        bstA.setOnClickListener(this);
        layout.addView(bstA);

        heapA = new Button(this);
        heapA.setText("ヒープ");
        heapA.setTextSize(textSize);
        heapA.setOnClickListener(this);
        layout.addView(heapA);

        hashA = new Button(this);
        hashA.setText("ハッシュ");
        hashA.setTextSize(textSize);
        hashA.setOnClickListener(this);
        layout.addView(hashA);

        setContentView(layout);
        Log.d("Main", "Finish Layout");
    }

    @Override
    public void onClick(View v){
        if (v != null){
            Intent intent=null;
            if (v == stackA){
                intent = new Intent(this, jp.ac.tokai.ss.onishi.lists.StackActivity.class);
                //startActivity(intent);
            }
            if (v == queueA){
                intent = new Intent(this, jp.ac.tokai.ss.onishi.lists.QueueAcitivty.class);
                //startActivity(intent);
            }
            if (v == listArrayA){
                intent = new Intent(this, jp.ac.tokai.ss.onishi.lists.ListArrayActivity.class);
                //startActivity(intent);
            }
            if (v == listA){
                intent = new Intent(this, jp.ac.tokai.ss.onishi.lists.ListActivity.class);
                //startActivity(intent);
            }
            if (v == sortedListArrayA) {
                intent = new Intent(this, jp.ac.tokai.ss.onishi.lists.SortedListArrayActivity.class);
                //startActivity(intent);
            }
            if (v == bstA){
                intent = new Intent(this, jp.ac.tokai.ss.onishi.lists.BSTActivity.class);
            }
            if (v == heapA){
                intent = new Intent(this, jp.ac.tokai.ss.onishi.lists.HeapActivity.class);
            }
            if (v == hashA){
                intent = new Intent(this, jp.ac.tokai.ss.onishi.lists.HashActivity.class);
            }

            if (intent != null){
                startActivity(intent);
            }
        }

    }

}
