package com.example.mx.projecttwo;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
public class PostRecyclerAdapter extends RecyclerView.Adapter <PostRecyclerAdapter.PostViewHolder>{
    private int numberPost;
    private int postPosition=0;
    private static int viewHolderCount;
    private int indexerForArray;
    final private PostClickListener postOnClickListener;
    public PostRecyclerAdapter(int numberPost, PostClickListener listener){
        this.numberPost=numberPost;
        postOnClickListener =listener;
        indexerForArray =0;
    }
    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_post_view,viewGroup,false);
        PostViewHolder viewHolder=new PostViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull PostViewHolder postViewHolder, int i) {
        postViewHolder.bindPostedItemToLayout(i);
    }

    @Override
    public int getItemCount() {
        return numberPost;
    }
    public interface PostClickListener{
        void  onPostClick(int clickedPostIndex);
    }
    class PostViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textViewPosterUsername;
        ImageView imageViewPostedItemImage;
        ImageView imageViewLikePostedItemSymbol;
        TextView textViewPostedItemNumberOfLike;
        TextView textViewPostedItemNumberOfComment;
        TextView textViewPostedItemDescription;
        public PostViewHolder(View itemView) {
            super(itemView);
            textViewPosterUsername =itemView.findViewById(R.id.textView_poster_username);
            imageViewPostedItemImage =itemView.findViewById(R.id.imageView_posted_item_image);
            imageViewLikePostedItemSymbol=itemView.findViewById(R.id.imageView_like_posted_item_symbol);
            textViewPostedItemNumberOfLike=itemView.findViewById(R.id.textView_posted_item_number_of_like);
            textViewPostedItemNumberOfComment=itemView.findViewById(R.id.textView_posted_item_number_of_comment);
            textViewPostedItemDescription=itemView.findViewById(R.id.textView_posted_item_description);
            itemView.setOnClickListener(this);
        }
        public void bindPostedItemToLayout(int Position){
            postPosition=Position;
            PostDatabase postDataObject=new PostDatabase();
            ArrayList<String> postedDataInArray=new ArrayList<>();
            Cursor postCursor=postDataObject.getAllPostedRow();
            if(postCursor.getCount()==0){
                postedDataInArray.add("no value inserted yet" );
            }
            while(postCursor.moveToNext()){
                postedDataInArray.add(postCursor.getString(1));
                postedDataInArray.add(postCursor.getString(2));
                postedDataInArray.add(postCursor.getString(6));
                postedDataInArray.add(postCursor.getString(3));
                postedDataInArray.add(postCursor.getString(5));
            }
            try {
                textViewPosterUsername.setText(postedDataInArray.get(indexerForArray).toString());
                indexerForArray +=1;
                imageViewPostedItemImage.setImageResource(android.R.drawable.alert_dark_frame);
                indexerForArray +=1;
                textViewPostedItemNumberOfLike.setText(postedDataInArray.get(indexerForArray).toString());
                indexerForArray +=1;
                textViewPostedItemNumberOfComment.setText(postedDataInArray.get(indexerForArray).toString());
                indexerForArray +=1;
                textViewPostedItemDescription.setText(postedDataInArray.get(indexerForArray).toString());
                indexerForArray +=1;
            }
            catch(Exception ex){
            }
        }
        @Override
        public void onClick(View view) {
            postOnClickListener.onPostClick(getAdapterPosition());
        }
    }
}

