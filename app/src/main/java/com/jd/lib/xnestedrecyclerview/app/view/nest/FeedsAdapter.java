package com.jd.lib.xnestedrecyclerview.app.view.nest;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jd.lib.xnestedrecyclerview.app.entity.FeedsGroup;

public class FeedsAdapter extends RecyclerView.Adapter<FeedsAdapter.MainHolder> {
    FeedsGroup model;

    public FeedsAdapter(FeedsGroup model) {
        this.model = model;
    }

    @NonNull
    @Override

    public MainHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        FeedsItemView layout = new FeedsItemView(viewGroup.getContext());
        return new MainHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull MainHolder mainHolder, int i) {
        if (mainHolder.itemView instanceof FeedsItemView) {
            ((FeedsItemView) mainHolder.itemView).update(model.getItemEntity(i));
        }
    }

    @Override
    public int getItemCount() {
        return model.getTotalCount();
    }

    class MainHolder extends RecyclerView.ViewHolder {

        public MainHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}