package com.example.keith.deletemeclass;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        lv = (ListView)findViewById(R.id.lv);

        CustomAdapter myAdapter = new CustomAdapter(this);
        lv.setAdapter(myAdapter);
    }
}
