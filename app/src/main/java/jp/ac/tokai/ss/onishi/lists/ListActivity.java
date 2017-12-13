package jp.ac.tokai.ss.onishi.lists;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity implements View.OnClickListener{

    EditText intext;
    Button bin;
    Button bout;
    Button bsearch;
    TextView top;
    ListView listView;
    ArrayList<Node> allNodes;
    ArrayAdapter<Node> nodeAdapter;

    int textSize = 25;
    List list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        list = new List();

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
/*
        TextView topView = new TextView(this);
        topView.setText(" TOP: ");
        topView.setTextSize(textSize);
        line.addView(topView);*/

        layout.addView(line);

        TextView titleView = new TextView(this);
        titleView.setText("リスト内部");
        titleView.setTextSize(textSize);
        layout.addView(titleView);

        allNodes = new ArrayList<Node>();
        nodeAdapter = new <Node> ArrayAdapter(this, R.layout.list_item_view, allNodes);
       // ArrayAdapter<Node> adapter = new <Node>ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, allNodes);

        listView = new ListView(this);
        listView.setAdapter(nodeAdapter); //adapter);
        layout.addView(listView);

        setContentView(layout);

        changeListArray();

    }

    @Override
    public void onClick(View v) {
        if (v != null) {
            if (v == bin) {
                String str = intext.getText().toString();
                if (!str.equals("")){
                    list.addNode(str);
                }
                intext.setText("");
            }

            if (v == bsearch){
                String str = intext.getText().toString();
                if (!str.equals("")) {
                    boolean found = list.search(str);
                    String msg;
                    if (found) {
                        msg = "\""+str +"\" を見つけました";
                    } else {
                        msg = "\""+str +"\" は見つかりません";
                    }
                    finishInput(v, msg);
                }
            }

            if (v == bout) {
                String str = intext.getText().toString();
                if (!str.equals("")) {
                    boolean found = list.delete(str);
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
        nodeAdapter.clear();
        Node node = list.getHead();
        do {
            nodeAdapter.add(node);
            node = node.getNext();
        } while (node != list.getZ());
        nodeAdapter.add(node);
    }

}
