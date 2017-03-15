package com.example.ex1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final List<String> chat = new ArrayList<>();
    private ListAdapter adapter;
    private ListView listView;
    private ImageView send;
    private EditText editText;
    private final SimpleDateFormat time = new SimpleDateFormat("HH:mm", Locale.getDefault());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("on create");
        send = (ImageView) findViewById(R.id.send);

//        List<String> chat = new ArrayList<String>();
//        if(this.chat == null)
//            this.chat = new ArrayList<String>();
        chat.add("Hello");
        if(this.adapter == null)
            this.adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, chat);
//        ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, chat);
        this.listView = (ListView) findViewById(R.id.list_view);
//        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
//        TextView title = (TextView) findViewById(R.id.textView);
//        title.setText(timeString);

        editText = (EditText) findViewById(R.id.editText);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    String msg = String.valueOf(editText.getText());
                    if(msg.length() > 0)
                    {
//                        updateListView(time.format(new Date() +"\n")+msg);
                        updateListView(msg);
                        return true;
                    }
                }
                return false;
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = String.valueOf(editText.getText());
                if(msg.length() > 0)
                    updateListView(msg);
            }
        });
    }

    private void updateListView(String message)
    {
        chat.add(this.time.format(new Date()) + '\t'+"  " +message);
        adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, chat);
        listView.setAdapter(adapter);
        editText.setText("");
    }



    @Override
    protected void onDestroy() {

        super.onDestroy();
    }
}

