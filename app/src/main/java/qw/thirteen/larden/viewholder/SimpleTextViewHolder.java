package qw.thirteen.larden.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import qw.thirteen.larden.R;

public class SimpleTextViewHolder extends RecyclerView.ViewHolder {
    public TextView mTv;
    public SimpleTextViewHolder(@NonNull View itemView) {
        super(itemView);
        mTv = (TextView) itemView.findViewById(R.id.textView);
    }
}
