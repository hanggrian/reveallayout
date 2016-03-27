package com.hendraanggrian.circularreveallayoutsample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hendraanggrian.circularreveallayout.view.RevealFrameLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.codetail.animation.SupportAnimator;

/**
 * Created by hendraanggrian on 06/01/16.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context ctx;
    private List<String> items;

    public RecyclerViewAdapter(Context ctx) {
        this.ctx = ctx;
        this.items = new ArrayList<>();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.revealLayout) RevealFrameLayout revealFrameLayout;
        @Bind(R.id.button1) Button button1;
        @Bind(R.id.button2) Button button2;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.revealFrameLayout.animateOpen(ctx);

        holder.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove(position);
            }
        });

        /*holder.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.revealFrameLayout.animateExit(new SupportAnimator.AnimatorListener() {
                    @Override
                    public void onAnimationStart() {
                    }

                    @Override
                    public void onAnimationEnd() {
                        remove(position);
                    }

                    @Override
                    public void onAnimationCancel() {
                    }

                    @Override
                    public void onAnimationRepeat() {
                    }
                });
            }
        });*/
    }

    public void add(String item) {
        items.add(item);
        notifyDataSetChanged();
    }

    public void remove(int pos) {
        items.remove(pos);
        notifyDataSetChanged();
    }

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }
}