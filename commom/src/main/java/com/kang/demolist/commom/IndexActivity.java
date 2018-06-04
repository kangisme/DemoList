package com.kang.demolist.commom;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

/**
 * @author created by kangren on 2018/6/4 16:10
 */
public abstract class IndexActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        BaseAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getList());
        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(IndexActivity.this, getIndexClass(position));
                startActivity(intent);
            }
        });
    }

    protected abstract Class<?> getIndexClass(int position);

    protected abstract String[] getList();
}
