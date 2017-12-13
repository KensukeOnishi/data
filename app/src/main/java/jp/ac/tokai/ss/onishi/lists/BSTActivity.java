package jp.ac.tokai.ss.onishi.lists;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

import java.util.ArrayList;

public class BSTActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    EditText intext;
    Button bin;
    Button bout;
    Button bsearch;
    TextView top;

    ListView listView;
    ArrayList<Bnode> allNodes;
    ArrayAdapter<Bnode> nodeAdapter;
    RadioGroup radioGroup;

    int textSize = 25;
//    List list;
    BinarySearchTree bst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bst = new BinarySearchTree();
        //list = new List();

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
       // layout.setScrollX(ActionBar.LayoutParams.MATCH_PARENT+100);

        intext = new EditText(this);
        intext.setHint("入力する整数");
        intext.setTextSize(textSize);
        intext.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        intext.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_SIGNED);
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

        Context context = getApplicationContext();
        radioGroup = new RadioGroup(context);
        RadioButton radioPre = new RadioButton(context);
        radioPre.setTextColor(Color.BLACK);
        radioPre.setText("Preorder");
        radioGroup.addView(radioPre);
        RadioButton radioIn = new RadioButton(context);
        radioIn.setTextColor(Color.BLACK);
        radioIn.setText("Inorder");
        radioGroup.addView(radioIn);
        RadioButton radioPost = new RadioButton(context);
        radioPost.setTextColor(Color.BLACK);
        radioPost.setText("Postorder");
        radioGroup.addView(radioPost);
        radioGroup.check(radioPre.getId());
        radioGroup.setOnCheckedChangeListener(this);
        line.addView(radioGroup);

/*
        TextView topView = new TextView(this);
        topView.setText(" TOP: ");
        topView.setTextSize(textSize);
        line.addView(topView);*/

        layout.addView(line);

        TextView titleView = new TextView(this);
        titleView.setText("二分木の内部");
        titleView.setTextSize(textSize);
        layout.addView(titleView);

        Log.d("BST", "Finish views");
        allNodes = new ArrayList<Bnode>();
        nodeAdapter = new <Bnode> ArrayAdapter(this, R.layout.list_item_view, allNodes);
        // ArrayAdapter<Node> adapter = new <Node>ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, allNodes);

        listView = new ListView(this);
        listView.setAdapter(nodeAdapter); //adapter);
        layout.addView(listView);

        setContentView(layout);

        changeListArray();

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        // どれも選択されていなければidには-1が入ってくる
        if (-1 != checkedId) {
            changeListArray();
        }
    }

    @Override
    public void onClick(View v) {
        if (v != null) {
            if (v == bin) {
                String str = intext.getText().toString();
                if (isNum(str)) {
                    int num = Integer.parseInt(str);
                    bst.insert(num);
                    intext.setText("");
                }else{
                    str = "整数を入力してください";
                    finishInput(v, str);
                }
            }

            if (v == bsearch){
                String str = intext.getText().toString();
                if (isNum(str)) {
                    int num = Integer.valueOf(str);
                    boolean found=bst.search(num);
                    if (found) {
                        str = "\""+num +"\" を見つけました";
                    } else {
                        str = "\""+num +"\" は見つかりません";
                    }
                }else{
                    str = "整数を入力してください";
                }
                finishInput(v, str);
            }

            if (v == bout) {
                String str = intext.getText().toString();
                if (isNum(str)) {
                    int num = Integer.valueOf(str);
                    boolean found = bst.delete(num);
                    if (found) {
                        str = "\"" + num + "\" を削除しました";
                    } else {
                        str = "\"" + num + "\" は見つかりません";
                    }
                }else {
                    str = "整数を入力してください";
                }
                finishInput(v, str);
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

    public void changeListArray() {
        nodeAdapter.clear();
        bst.initOrder();
        //allNodes.clear();

        int id = radioGroup.getCheckedRadioButtonId();
        String checked = ((RadioButton)findViewById(id)).getText().toString();
        if (checked == "Preorder") {
            allNodes = bst.preorder(bst.getRoot());
        } else if (checked == "Inorder"){
            allNodes = bst.inorder(bst.getRoot());
        } else if (checked == "Postorder"){
            allNodes = bst.postorder(bst.getRoot());
        } else {
            Log.d("DBG","No order!");
        }

        allNodes.add(bst.getZ());
        if (allNodes != null) {
            nodeAdapter.addAll(allNodes);
        }
    }

    public boolean isNum(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException nfex) {
            return false;
        }
    }

}
