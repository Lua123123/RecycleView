package com.example.recycleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    public void initView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        ArrayList<DataShop> arrayList = new ArrayList<>();
        arrayList.add(new DataShop(R.drawable.htc, "HTC"));
        arrayList.add(new DataShop(R.drawable.sony, "Sony"));
        arrayList.add(new DataShop(R.drawable.iphone, "Iphone"));
        arrayList.add(new DataShop(R.drawable.huawei, "Huawei"));
        arrayList.add(new DataShop(R.drawable.oppo, "Oppo"));
        arrayList.add(new DataShop(R.drawable.samsung, "Samsung"));

        ShopAdapter shopAdapter = new ShopAdapter(arrayList,getApplicationContext());
        recyclerView.setAdapter(shopAdapter);
    }
}