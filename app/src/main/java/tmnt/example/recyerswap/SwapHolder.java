package tmnt.example.recyerswap;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by tmnt on 2017/9/17.
 */

public class SwapHolder extends RecyclerView.ViewHolder {
    private TextView mText;
    private OnTextClickLlistener mOnTextClickLlistener;
    private boolean isCanMove;

    public SwapHolder(View itemView,boolean isCanMove) {
        super(itemView);
        mText = (TextView) itemView.findViewById(R.id.tv_text);
        this.isCanMove=isCanMove;
    }

    public void setOnTextClickLlistener(OnTextClickLlistener onTextClickLlistener) {
        mOnTextClickLlistener = onTextClickLlistener;
    }

    public void setData(String title) {
        mText.setText(title);
    }

    public void setOperation(final int position, final ItemDragHelperCallback itemDragHelperCallback) {
        mText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnTextClickLlistener != null) {
                    mOnTextClickLlistener.onClick(v, position);
                }
            }
        });
        mText.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnTextClickLlistener != null) {
                    mOnTextClickLlistener.onLongClick(v, position);
                    if (isCanMove)
                        itemDragHelperCallback.setLongPressEnabled(true);
                }
                return true;
            }
        });
    }
}
