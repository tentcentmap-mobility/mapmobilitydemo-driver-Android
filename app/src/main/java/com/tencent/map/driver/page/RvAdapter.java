package com.tencent.map.driver.page;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tencent.map.driver.R;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.ViewHolder>{
    public static final String LOG_TAG = "navi";

    private ArrayList<String> mobilityDate = new ArrayList<>();

    /** 外部的点击监听*/
    private IClickListener clickListener;

    public RvAdapter(IClickListener listener) {
        this.clickListener = listener;

        if(mobilityDate.size() != 0)
            mobilityDate.clear();
        mobilityDate.add("司乘-顺风车");
        mobilityDate.add("司乘-快车");
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ls_layout_recy_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String content = mobilityDate.get(position);
        holder.tvContent.setText(content);

        holder.listener.position = position;
        holder.listener.setViewHolder(holder);
        holder.allLayout.setOnClickListener(holder.listener);
    }

    @Override
    public int getItemCount() {
        return mobilityDate.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        MyClickListener listener = new MyClickListener();

        LinearLayout allLayout;
        TextView tvContent;

        public ViewHolder(View view) {
            super(view);
            tvContent = view.findViewById(R.id.tv_recycler_item_content);
            allLayout = view.findViewById(R.id.all_layout);
        }
    }

    class MyClickListener implements View.OnClickListener {

        public WeakReference<ViewHolder> wrf;
        public int position;

        public void setViewHolder(ViewHolder viewHolder) {
            wrf = new WeakReference<>(viewHolder);
        }

        @Override
        public void onClick(View v) {
            if(wrf == null || wrf.get() == null){
                Log.e(LOG_TAG, "view holder or wrf null");
                return;
            }
            if(clickListener != null){
                clickListener.onClick(position);
            }
        }
    }

    interface IClickListener {
        void onClick(int itemPosition);
    }
}
