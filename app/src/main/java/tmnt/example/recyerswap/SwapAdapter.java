package tmnt.example.recyerswap;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

/**
 * Created by tmnt on 2017/9/17.
 */

public class SwapAdapter extends RecyclerView.Adapter implements ItemDragHelperCallback.OnItemMoveListener {

    private Context mContext;
    private List<String> mData;
    private OnTextClickLlistener mOnTextClickLlistener;
    private ItemDragHelperCallback mItemDragHelperCallback;
    private boolean isCanMove;

    public SwapAdapter(Context context, List<String> data, boolean isCanMove) {
        mContext = context;
        mData = data;
        this.isCanMove = isCanMove;
    }

    public void setItemDragHelperCallback(ItemDragHelperCallback itemDragHelperCallback) {
        mItemDragHelperCallback = itemDragHelperCallback;
    }

    public void setOnTextClickLlistener(OnTextClickLlistener onTextClickLlistener) {
        mOnTextClickLlistener = onTextClickLlistener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_swap, parent, false);
        SwapHolder holder = new SwapHolder(view, isCanMove);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SwapHolder swapHolder = (SwapHolder) holder;
        swapHolder.setData(mData.get(position));
        swapHolder.setOperation(position, mItemDragHelperCallback);
        swapHolder.setOnTextClickLlistener(mOnTextClickLlistener);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mData, fromPosition, toPosition);//利用Collections对数据源交换
        notifyItemMoved(fromPosition, toPosition);//RecyclerView中的方法，可以交换item
        return true;
    }
}
