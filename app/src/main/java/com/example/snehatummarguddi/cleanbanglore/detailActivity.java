package com.example.snehatummarguddi.cleanbanglore;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.List;

public class detailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("detailActivity", "onCreate ");





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem item = menu.add(Menu.NONE,Menu.NONE,103,"newitem");
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Toast.makeText(detailActivity.this,"you chose item",Toast.LENGTH_LONG).show();
                return false;

            }
        });
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("detailActivity", "onStart");
    }

    public void OnClickHandler(View view) {
        String imageName = "change";
        int res = getResources().getIdentifier(imageName, "drawable",getPackageName());
        ImageView pic = (ImageView) findViewById(R.id.button1);
        pic.setImageResource(res);

        Log.d("detailActivity", "OnClickHandler ");
    }

    public void OnClick2(View view) {
        String imageName="another.jpg";
        ImageView pic2 = (ImageView) findViewById(R.id.button1);
        try {
            InputStream stream = getAssets().open(imageName);
            Drawable drawable = Drawable.createFromStream(stream, null);
            pic2.setImageDrawable(drawable);
        }catch(Exception e){
            Log.e("detailActivity", "OnClick2: ");
        }

    }

    public void changeimageclickhandler(MenuItem item) {
        String imageName = "change";
        int res = getResources().getIdentifier(imageName, "drawable",getPackageName());
        ImageView pic = (ImageView) findViewById(R.id.button1);
        pic.setImageResource(res);
    }

    public void changeimage2clickhandler(MenuItem item) {
        String imageName="another.jpg";
        ImageView pic2 = (ImageView) findViewById(R.id.button1);
        try {
            InputStream stream = getAssets().open(imageName);
            Drawable drawable = Drawable.createFromStream(stream, null);
            pic2.setImageDrawable(drawable);
        }catch(Exception e){
            Log.e("detailActivity", "OnClick2: ");
        }

    }
    public void buttononclickhandler3(View view) {
        Intent mapintent = new Intent(this,MapsActivity.class);
        startActivity(mapintent);
    }
}
