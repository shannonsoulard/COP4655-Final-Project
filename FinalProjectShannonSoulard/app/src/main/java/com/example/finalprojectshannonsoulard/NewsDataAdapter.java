package com.example.finalprojectshannonsoulard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NewsDataAdapter /*extends RecyclerView.Adapter<NewsDataAdapter.ViewHolder>*/ {
    private ArrayList<News> articles = new ArrayList<>();
    private Context context;

    public NewsDataAdapter(ArrayList<News> articles, Context context) {
        this.context = context;
        this.articles = articles;
    }

   /* @Override
    public NewsDataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsDataAdapter.ViewHolder viewHolder, final int i) {

        final String image = articles.get(i).getImage();
        final String headline = articles.get(i).getHeadline();

        viewHolder.image.setText(image);
        viewHolder.headline.setText(headline);

    }

    @Override
    public int getItemCount() {
        return articles.size();
    }*/

    /*public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView image, headline;


        public ViewHolder(View view) {
            super(view);
            image = (TextView)view.findViewById(R.id.image);
            headline = (TextView)view.findViewById(R.id.headline);
        }
    }*/
}
