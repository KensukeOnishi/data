package jp.ac.tokai.ss.onishi.lists;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Random;

public class ListArrayActivity extends AppCompatActivity implements View.OnClickListener {

    int maxsize;
    TextView storeText[];
    EditText intext;
    Button bin;
    Button bout;
    Button bsearch;
    TextView top;

    int textSize = 25;
    ListArray listArray;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        listArray = new ListArray();
        maxsize = listArray.getMaxsize()+1; // maxsizeを 1増やしているのは, 番兵分の値をとっているため.
        storeText = new TextView[maxsize];
        Log.d("Maxsize", "大きさ" + maxsize);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        intext = new EditText(this);
        intext.setHint("入力する文字列");
        intext.setTextSize(textSize);
        intext.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        layout.addView(intext);

        LinearLayout line = new LinearLayout(this);
        line.setOrientation(LinearLayout.HORIZONTAL);

        bin = new Button(this);
        bin.setText("入力");
        bin.setTextSize(textSize);
        bin.setOnClickListener(this);
        line.addView(bin);

        bsearch = new Button(this);
        bsearch.setText("検索");
        bsearch.setTextSize(textSize);
        bsearch.setOnClickListener(this);
        line.addView(bsearch);

        bout = new Button(this);
        bout.setText("削除");
        bout.setTextSize(textSize);
        bout.setOnClickListener(this);
        line.addView(bout);

        TextView topView = new TextView(this);
        topView.setText(" TOP: ");
        topView.setTextSize(textSize);
        line.addView(topView);

        top = new TextView(this);
        top.setText(String.valueOf(listArray.getTop()));
        top.setTextSize(textSize);
        line.addView(top);

        layout.addView(line);

        TextView stackView = new TextView(this);
        stackView.setText("リスト内部");
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
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onClick(View v) {
        if (v != null) {
            if (v == bin) {
                String str = intext.getText().toString();
                if (!str.equals("")){// && listArray.insert(str)) {
                    if(!listArray.insert(str)){
                        String msg = "配列がいっぱいです";
                        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
                intext.setText("");
            }

            if (v == bsearch){
                String str = intext.getText().toString();
                if (!str.equals("")) {
                    int found = listArray.search(str);
                    String msg;
                    if (found != listArray.notfound) {
                        msg = found+"番目で見つかりました";
                    } else {
                        msg = "\""+str +"\" は見つかりません";
                    }

                    finishInput(v, msg);

                }
            }

            if (v == bout) {
                String str = intext.getText().toString();
                if (!str.equals("")) {
                    boolean found = listArray.delete(str);
                    String msg;
                    if (found) {
                        msg = "\""+str +"\" を削除しました";
                    } else {
                        msg = "\""+str +"\" は見つかりません";
                    }
                    finishInput(v, msg);
                }
            }
            changeListArray();
        }

    }

    public void finishInput(View v, String msg){ // 入力が終わった後の初期化
        intext.setText("");
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void changeListArray(){
        top.setText(String.valueOf(listArray.getTop()));
        for (int i = 0; i < maxsize; i++){
            Log.d("ChangeList", i+"th start");
            String str = listArray.peek(i);
            if (str != null) storeText[i].setText(str);
        }
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Stack Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}

