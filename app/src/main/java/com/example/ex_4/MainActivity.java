package com.example.ex_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private String[] cityname;
    private TypedArray citypic;
    private List<Map<String, Object>> listData;
    private ListView listView_city_data;
    private SimpleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intview();
        listview_ac();
    }
    private void intview() {
        cityname = getResources().getStringArray(R.array.city_name);
        citypic = getResources().obtainTypedArray(R.array.city_picture);
        listData = new ArrayList<Map<String,Object>>();
        for(int i=0; i < cityname.length;i++){
            Map<String,Object> data = new HashMap<>();
            data.put("name",cityname[i]);
            data.put("picture",citypic.getResourceId(i,0));
            listData.add(data);
        }
    }

    private void listview_ac() {
        listView_city_data = findViewById(R.id.listView_citydata);
        adapter = new SimpleAdapter(MainActivity.this,
                listData,
                R.layout.item_city_layout,
                new String[]{"name","picture"},
                new int[]{R.id.item_cityname,R.id.item_cityimage});
        listView_city_data.setAdapter(adapter);

        listView_city_data.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Map<String,Object> item = (Map<String, Object>) parent.getItemAtPosition(position);
                String name = item.get("name").toString();
                Log.v("lee","main: "+name);
                int picNum = (int) item.get("picture");
                Intent intent = new Intent(MainActivity.this,DisplayActivity.class);
                intent.putExtra("cityname",name);
                intent.putExtra("citypicture",picNum);
                startActivity(intent);
            }
        });
    }
}