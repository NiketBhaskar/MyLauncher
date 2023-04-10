package com.revia.mylauncher;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AppItemViewHolder extends RecyclerView.ViewHolder {
    private final Context mContext;
    private View mAppItem;
    private ImageView mAppIconView;
    private TextView mAppNameView;
    public AppItemViewHolder(@NonNull View itemView, Context mContext) {
        super(itemView);
        this.mContext = mContext;
        mAppItem = itemView.findViewById(R.id.app_item);
        mAppIconView = itemView.findViewById(R.id.app_icon);
        mAppNameView = itemView.findViewById(R.id.app_name);
    }
    public void bind(AppMetaData app){
        mAppIconView.setImageDrawable(null);
        mAppNameView.setText(null);

        if(app == null)
            return ;
        mAppIconView.setImageDrawable(app.getIcon());
        mAppNameView.setText(app.getDisplayName());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mAppItem.setOnClickListener(view -> app.getLaunchCallback().accept(mContext));
        }

    }
}
