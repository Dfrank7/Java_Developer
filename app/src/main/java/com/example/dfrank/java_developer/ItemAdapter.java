package com.example.dfrank.java_developer;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dfrank.java_developer.controller.Detail_Activity;
import com.example.dfrank.java_developer.model.Item;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * Created by dfrank on 6/13/17.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private List<Item> items;
    private Context context;
    public ItemAdapter(Context context, List<Item> items){
        this.context = context;
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(items.get(position).getLogin());
        Picasso.with(context).load(items.get(position).getAvatar()).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title, githublink;
        private ImageView image;
        public ViewHolder(View view){
            super(view);
            title = (TextView) view.findViewById(R.id.text);
            image = (ImageView) view.findViewById(R.id.imageview);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos!=RecyclerView.NO_POSITION) {
                        Item clickeData = items.get(pos);
                        Intent intent = new Intent(context, Detail_Activity.class);
                        intent.putExtra("login", items.get(pos).getLogin());
                        intent.putExtra("avatar", items.get(pos).getAvatar());
                        intent.putExtra("url", items.get(pos).getHtml_url());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                }
            });

        }
    }
}
