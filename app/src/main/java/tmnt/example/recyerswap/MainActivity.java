package tmnt.example.recyerswap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerViewCanSwap;
    private RecyclerView mRecyclerViewCanNotSwap;

    private SwapAdapter canAdapter;
    private SwapAdapter notAdapter;

    private List<String> up = new ArrayList<>();
    private List<String> down = new ArrayList<>();
    private List<String> all = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerViewCanSwap = (RecyclerView) findViewById(R.id.news_channel_mine_rv);
        mRecyclerViewCanNotSwap = (RecyclerView) findViewById(R.id.news_channel_more_rv);
        add();

        canAdapter = new SwapAdapter(this, up, true);
        notAdapter = new SwapAdapter(this, down, false);

        mRecyclerViewCanSwap.setLayoutManager(new GridLayoutManager(this, 4, LinearLayoutManager.VERTICAL, false));
        mRecyclerViewCanSwap.setItemAnimator(new DefaultItemAnimator());
        mRecyclerViewCanSwap.setAdapter(canAdapter);


        mRecyclerViewCanNotSwap.setLayoutManager(new GridLayoutManager(this, 4, LinearLayoutManager.VERTICAL, false));
        mRecyclerViewCanNotSwap.setItemAnimator(new DefaultItemAnimator());
        mRecyclerViewCanNotSwap.setAdapter(notAdapter);

        initItemDragHelper();


        canAdapter.setOnTextClickLlistener(new OnTextClickLlistener() {
            @Override
            public void onClick(View v, int position) {
                down.add(up.get(position));
                up.remove(position);
                notifity();

            }

            @Override
            public void onLongClick(View v, int position) {

            }
        });
        notAdapter.setOnTextClickLlistener(new OnTextClickLlistener() {
            @Override
            public void onClick(View v, int position) {
                up.add(down.get(position));
                down.remove(position);
                notifity();
            }

            @Override
            public void onLongClick(View v, int position) {

            }
        });

    }

    private void initItemDragHelper() {
        ItemDragHelperCallback itemDragHelperCallback = new ItemDragHelperCallback(canAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemDragHelperCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerViewCanSwap);

        canAdapter.setItemDragHelperCallback(itemDragHelperCallback);
    }

    private void notifity() {
        canAdapter.notifyDataSetChanged();
        notAdapter.notifyDataSetChanged();
    }

    private void add() {
        up.add("car");
        up.add("movie");
        up.add("jun");
        up.add("game");
        up.add("nba");
        up.add("detail");
        down.add("model");
        down.add("education");
        down.add("lvyou");
        down.add("phone");
        down.add("jiaju");
        down.add("house");
        down.add("fashion");
        down.add("money");
        down.add("install");
        down.add("football");
        down.add("sport");
        down.add("baoxue");
        all.addAll(up);
        all.addAll(down);

    }

}
