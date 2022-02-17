package com.example.recycleview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ShopAdapter shopAdapter;
    RecyclerView recyclerView;
    ArrayList<String> stringArrayList = new ArrayList<>();
    CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        enableSwipeToDeleteAndUndo();
    }

    private void enableSwipeToDeleteAndUndo() {
        SwipeToDeleteCallback swipeToDeleteCallback = new SwipeToDeleteCallback(this) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

                final int position = viewHolder.getAdapterPosition();
                final DataShop item = shopAdapter.getDataShops().get(position);

                shopAdapter.remove(position);

                shopAdapter.notifyDataSetChanged();


//                Snackbar snackbar = Snackbar
//                        .make(coordinatorLayout, "Item was removed from the list.", Snackbar.LENGTH_LONG);
//                snackbar.setAction("UNDO", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//                        shopAdapter.restoreItem(item, position);
//                        recyclerView.scrollToPosition(position);
//                    }
//                });
//
//                snackbar.setActionTextColor(Color.YELLOW);
//                snackbar.show();

            }
        };

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchhelper.attachToRecyclerView(recyclerView);
    }




//    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
//        @Override
//        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//            return false;
//        }
//
//        @Override
//        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//            int position = viewHolder.getAdapterPosition();
//            if (direction == ItemTouchHelper.LEFT) {
//                shopAdapter.notifyItemChanged(position);
//                Toast.makeText(getApplicationContext(), "Swipe left", Toast.LENGTH_SHORT).show();
//            } else if (direction == ItemTouchHelper.RIGHT) {
//                shopAdapter.notifyItemChanged(position);
//                Toast.makeText(getApplicationContext(), "Swipe right", Toast.LENGTH_SHORT).show();
//            }
//        }
//
//        @Override
//        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
//            if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
//                View itemView = viewHolder.itemView;
//                int height = itemView.getBottom() - itemView.getTop();
//                float width = height / 3;
//                Paint p = new Paint();
//
//                if (dX < 0) {
//                    p.setColor(Color.RED);
//                    RectF background = new RectF(itemView.getRight() + dX, itemView.getTop(), itemView.getRight(), itemView.getBottom());
//                    c.drawRect(background, p);
//                    Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_delete);
//                    float margin = (float) ((dX / 5 - width) / 2);
//                    RectF iconDest = new RectF(itemView.getRight() + margin, itemView.getTop() + width, itemView.getRight() + (margin + width), itemView.getBottom() - width);
//                    c.drawBitmap(icon, null, iconDest, p);
//                }
//                if (dX > 0) {
//                    p.setColor(Color.BLUE);
//                    RectF background = new RectF(itemView.getLeft(), itemView.getTop(), itemView.getLeft() + dX, itemView.getBottom());
//                    c.drawRect(background, p);
//                    Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_check);
//                    float margin = (dX / 5 - width) / 2;
//                    RectF iconDest = new RectF(margin, itemView.getTop() + width, margin + width, itemView.getBottom() - width);
//                    c.drawBitmap(icon, null, iconDest, p);
//                }
//            } else {
//                c.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
//            }
//            super.onChildDraw(c, recyclerView, viewHolder, dX / 5, dY, actionState, isCurrentlyActive);
//        }
//    };

    public void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        //DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, gridLayoutManager.getOrientation());
        //recyclerView.addItemDecoration(dividerItemDecoration);
        ArrayList<DataShop> arrayList = new ArrayList<>();
        arrayList.add(new DataShop(R.drawable.htc, "HTC"));
        arrayList.add(new DataShop(R.drawable.sony, "Sony"));
        arrayList.add(new DataShop(R.drawable.iphone, "Iphone"));
        arrayList.add(new DataShop(R.drawable.huawei, "Huawei"));
        arrayList.add(new DataShop(R.drawable.oppo, "Oppo"));
        arrayList.add(new DataShop(R.drawable.samsung, "Samsung"));

        shopAdapter = new ShopAdapter(arrayList,getApplicationContext());
        recyclerView.setAdapter(shopAdapter);

//        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
//        itemTouchHelper.attachToRecyclerView(recyclerView);
    }
}