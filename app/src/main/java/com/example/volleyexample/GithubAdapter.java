package com.example.volleyexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.bumptech.glide.Glide;

public class GithubAdapter extends RecyclerView.Adapter<GithubAdapter.GithubViewHolder> {

    Context context;
    User[] data;
    ImageLoader imageLoader;

    public GithubAdapter(Context context, User[] data, ImageLoader imageLoader) {
        this.context = context;
        this.data = data;
        this.imageLoader = imageLoader;
    }

    @NonNull
    @Override
    public GithubViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_layout, parent, false);
        return new GithubViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GithubViewHolder holder, int position) {
        User user = data[position];
        holder.tvUser.setText(user.getLogin());
        //Glide.with(holder.imgUser.getContext()).load(user.getAvatarUrl()).into(holder.imgUser);
        holder.imgUser.setImageUrl(user.getAvatarUrl(), imageLoader);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class GithubViewHolder extends RecyclerView.ViewHolder{
        TextView tvUser;
        NetworkImageView imgUser;
        public GithubViewHolder(@NonNull View itemView) {
            super(itemView);

            tvUser = (TextView) itemView.findViewById(R.id.tvName);
            imgUser = (NetworkImageView) itemView.findViewById(R.id.imgUser);
        }
    }
}
