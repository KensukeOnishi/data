package jp.ac.tokai.ss.onishi.lists;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class QueueAcitivty extends AppCompatActivity implements View.OnClickListener{

    int maxsize;
    TextView storeText[];
    EditText intext;
    Button bin;
    Button bout;
    TextView front;
    TextView rear;

    int textSize = 25;
    Queue queue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        queue = new Queue();
        maxsize = queue.getMaxsize();
        storeText = new TextView[maxsize];

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

        bout = new Button(this);
        bout.setText("出力");
        bout.setTextSize(textSize);
        bout.setOnClickListener(this);
        line.addView(bout);


        TextView frontView = new TextView(this);
        frontView.setText(" FRONT: ");
        frontView.setTextSize(textSize);
        line.addView(frontView);

        front = new TextView(this);
        front.setText(String.valueOf(queue.getFront()));
        front.setTextSize(textSize);
        line.addView(front);

        TextView rearView = new TextView(this);
        rearView.setText(", REAR: ");
        rearView.setTextSize(textSize);
        line.addView(rearView);

        rear = new TextView(this);
        rear.setText(String.valueOf(queue.getRear()));
        rear.setTextSize(textSize);
        line.addView(rear);

        layout.addView(line);

        TextView stackView = new TextView(this);
        stackView.setText("キュー内部");
        stackView.setTextSize(textSize);
        layout.addView(stackView);

        for (int i = 0; i < maxsize; i++) {
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
        }

        setContentView(layout);

    }

    @Override
    public void onClick(View v) {
        if (v != null) {
            if (v == bin) {
                String str = intext.getText().toString();
                if (!str.equals("") && queue.offer(str) != null) {
                    int setpos = (queue.getRear()+maxsize-1) % maxsize;
                    storeText[setpos].setText(str);
                    //queue.offer(str);
                }
                intext.setText("");
            }
            if (v == bout) {
                String str = queue.poll();
                if (str != null)
                    intext.setText(str, TextView.BufferType.NORMAL);
            }
            rear.setText(String.valueOf(queue.getRear()));
            front.setText(String.valueOf(queue.getFront()));

        }

    }

}