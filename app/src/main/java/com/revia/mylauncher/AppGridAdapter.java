package com.revia.mylauncher;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

final class AppGridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context mContext;
    private final LayoutInflater mInflater;

    private List<AppMetaData> mApps;
    AppGridAdapter(Context mContext) {
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    void setAllApps(List<AppMetaData> apps){
        mApps = apps;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.app_item, parent, false);
        return new AppItemViewHolder(view, mContext);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        AppMetaData app = mApps.get(position);
        ((AppItemViewHolder) holder).bind(app);
    }

    @Override
    public int getItemCount() {
        return mApps.size();
    }
}
