package com.project.ditha.callapi;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String answer;
    ExpandableHeightGridView gridView;
    RecyclerView recyclerView;
    int[] images;
    String[] placeNames;
    String[] placeGuide;

    String[] itemNames = {"Handphone","Camera","TV","Laptop","Elektronik","Furniture"};
    int[] itemImages = {R.drawable.smartphone,R.drawable.camera,R.drawable.tv,
            R.drawable.laptop,R.drawable.fridge,R.drawable.couch};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //finding listview
        gridView = findViewById(R.id.gridview);
        gridView.setExpanded(true);

        CustomAdapter customAdapter = new CustomAdapter();
        gridView.setAdapter(customAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(getApplicationContext(),fruitNames[i],Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),Activity_Grid_Item.class);
                intent.putExtra("name",itemNames[i]);
                intent.putExtra("image",itemImages[i]);
                startActivity(intent);

            }
        });

        //connection
        ConnectivityManager cm = (ConnectivityManager) getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork) {
            if(activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                answer="You are connected to a WiFi Network";
            if(activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                answer="You are connected to a Mobile Network";
        }
        else
            answer = "No internet Connectivity";
        Toast.makeText(getApplicationContext(), answer, Toast.LENGTH_LONG).show();

        //recycler
        recyclerView = findViewById(R.id.recyclerView);

        images = new int[]{R.drawable.hongkong, R.drawable.card, R.drawable.europe};
        placeNames = new String[]{"Article title", "Article title", "Article title"};
        placeGuide = new String[]{"https://jalanbarengyukblog.wordpress.com/2018/06/28/3-wisata-asik-untuk-liburanmu-ke-hong-kong/\n",
                "https://economy.okezone.com/read/2018/01/26/320/1850726/10-contoh-cara-bijak-menggunakan-kartu-kredit-apa-saja",
                "https://www.holidaystoeurope.com.au/home/europe-travel-blog/europe-travel-blog/1052-what-to-budget-for-a-4-week-holiday-in-europe-by-car-or-train"};

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this,
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        MyAdapter myAdapter = new MyAdapter(MainActivity.this, images, placeNames, placeGuide);
        recyclerView.setAdapter(myAdapter);

    }

    private class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return itemImages.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View view1 = getLayoutInflater().inflate(R.layout.row_data,null);
            //getting view in row_data
            TextView name = view1.findViewById(R.id.item);
            ImageView image = view1.findViewById(R.id.images);

            name.setText(itemNames[i]);
            image.setImageResource(itemImages[i]);
            return view1;

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;

    }
}
