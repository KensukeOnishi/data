package jp.ac.tokai.ss.onishi.lists;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Random;

public class HashActivity extends AppCompatActivity implements View.OnClickListener{

    int maxsize;
    TextView storeText[];
    EditText intext;
    Button bin;
    //Button bout;
    TextView hashNum;
    EditText skipEdit;

    int textSize = 25;
    //Stack stack;
    //Heap heap;
    Hash hash;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    //private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        hash = new Hash();
        //heap = new Heap();
        //stack = new Stack();
        maxsize = hash.getMaxsize();
        storeText = new TextView[maxsize];
        Log.d("Maxsize", "大きさ"+maxsize);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        intext = new EditText(this);
        intext.setHint("入力する文字列");
        intext.setTextSize(textSize);
        intext.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        intext.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        layout.addView(intext);

        LinearLayout line = new LinearLayout(this);
        line.setOrientation(LinearLayout.HORIZONTAL);

        bin = new Button(this);
        bin.setText("入力");
        bin.setTextSize(textSize);
        bin.setOnClickListener(this);
        line.addView(bin);

        hashNum = new TextView(this);
        hashNum.setText("ハッシュ値: , ");
        hashNum.setTextSize(textSize);
        //bout.setOnClickListener(this);
        line.addView(hashNum);


        TextView topView = new TextView(this);
        topView.setText(" スキップ : ");
        topView.setTextSize(textSize);
        line.addView(topView);


        skipEdit = new EditText(this);
        skipEdit.setText("1");
        //top.setText(String.valueOf(hash.getSize()));
        skipEdit.setTextSize(textSize);
        line.addView(skipEdit);

        layout.addView(line);

        TextView stackView = new TextView(this);
        stackView.setText("ハッシュ内部");
        stackView.setTextSize(textSize);
        layout.addView(stackView);

        for (int i = 0; i < maxsize; i++) {
            //Log.d("addEachView", i+"th adding start");
            LinearLayout lines = new LinearLayout(this);
            lines.setOrientation(LinearLayout.HORIZONTAL);
            Random rnd = new Random();
            lines.setBackgroundColor(Color.argb(128, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));

            TextView textNum = new TextView(this);
            textNum.setTextSize(textSize);
            textNum.setText(String.valueOf(i) + " : ");
            lines.addView(textNum);

            storeText[i] = new TextView(this);
            storeText[i].setTextSize(textSize);
            lines.addView(storeText[i]);

            layout.addView(lines);
            //Log.d("addEachView", i+"th adding end");
        }

        setContentView(layout);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
       // client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onClick(View v) {
        if (v != null) {
            if (v == bin) {
                String str = intext.getText().toString();
                int skip = Integer.parseInt(skipEdit.getText().toString());
                Log.d("Heap.Insert Btn", "Input String: "+str);


                if (str != null && !str.equals("")){
                    //int num = Integer.parseInt(str);
                    String msg;
                    if (hash.insert(str, skip)){
                        msg = str+"を入力しました";
                        int index = hash.hashFunc(str);
                        hashNum.setText("ハッシュ値: "+index+", ");
                        //storeText[heap.getSize()-1].setText(str);
                        //stack.push(str);
                    } else {
                        msg = "入力できませんでした";
                    }
                    finishInput(v, msg);
                }
                //intext.setText("");
            }
/*
            if (v == bout) {

                int num = heap.remove();
                String str;
                if (num != Integer.MIN_VALUE){str = String.valueOf(num);}
                else { str = null; }
                if (str != null)
                    intext.setText(str, TextView.BufferType.NORMAL);
            }
            //top.setText(String.valueOf(heap.getSize()));
*/
        }
        changeListArray();

    }
    public void finishInput(View v, String msg){ // 入力が終わった後の初期化
        intext.setText("");
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void changeListArray(){
        for (int i = 0; i < maxsize; i++){
            //Log.d("ChangeList", i+"th start");
            String str = hash.peek(i);
            if (str != null) storeText[i].setText(str);
        }
    }


}
