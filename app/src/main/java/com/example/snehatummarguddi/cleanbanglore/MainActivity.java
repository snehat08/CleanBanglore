package com.example.snehatummarguddi.cleanbanglore;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Course> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        data = DataProvider.getData();

        ArrayAdapter<Course> courseArrayAdapter=
                new ArrayAdapter<Course>(this,android.R.layout.simple_list_item_1,data);
        ListView listView = (ListView) findViewById(android.R.id.list);
        listView.setAdapter(courseArrayAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Course course = data.get(i);
                displayDetail(course);
            }
        });


    }

    private void displayDetail(Course course) {
        Log.d("MainActivity","Check "+course.getTitle());
    }

    public void page2onclickhandler(View view) {
        Intent detailintent = new Intent(this,detailActivity.class);
       startActivity(detailintent);

    }

    public void registeronclickhandler(View view) {
        Uri webPage = Uri.parse("http://www.google.com");
        Intent webintent = new Intent(Intent.ACTION_VIEW,webPage);
        startActivity(webintent);
    }
}
