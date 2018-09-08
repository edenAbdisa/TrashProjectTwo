package com.example.mx.projecttwo;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
public class CommentRecyclerAdapter extends RecyclerView.Adapter <CommentRecyclerAdapter.CommentViewHolder>{
    private int numberComment;
    private int commentPosition=0;
    private int indexerForArray;
    final private CommentClickListener commentOnClickListener;
    public CommentRecyclerAdapter(int numberComment,CommentClickListener listener){
        this.numberComment=numberComment;
        commentOnClickListener=listener;
        indexerForArray =0;
    }
    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_comment_view,viewGroup,false);
        CommentViewHolder viewHolder=new CommentViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder commentViewHolder, int i) {
        commentViewHolder.bindCommentDataToLayout(i);
    }

    @Override
    public int getItemCount() {
        return numberComment;
    }
    public interface CommentClickListener{
        void  onCommentClick(int clickedCommentIndex);
    }
    class CommentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textViewCommentorUsername;
        TextView textViewCommentDate;
        TextView textViewCommentContent;
        Button buttonDeleteComment;
        public CommentViewHolder(View itemView) {
            super(itemView);
            textViewCommentorUsername=itemView.findViewById(R.id.textView_commentor_username);
            textViewCommentDate=itemView.findViewById(R.id.textView_comment_date);
            textViewCommentContent=itemView.findViewById(R.id.textView_comment_content);
            buttonDeleteComment=itemView.findViewById(R.id.button_delete_comment);
            itemView.setOnClickListener(this);
        }
        public void bindCommentDataToLayout(int Position){
            commentPosition=Position;
            CommentDatabase commentDataObject=new CommentDatabase();
            ArrayList<String> commentsDataInArray=new ArrayList<>();
            Cursor commentCursor=commentDataObject.getAllCommentRow();
            if(commentCursor.getCount()==0){
                commentsDataInArray.add("no value inserted yet" );
            }
            while(commentCursor.moveToNext()){
                commentsDataInArray.add(commentCursor.getString(1));
                commentsDataInArray.add(commentCursor.getString(2));
                commentsDataInArray.add(commentCursor.getString(6));
                commentsDataInArray.add(commentCursor.getString(3));
                commentsDataInArray.add(commentCursor.getString(5));
            }
            try {
                textViewCommentorUsername.setText(commentsDataInArray.get(indexerForArray).toString());
                indexerForArray +=1;
                textViewCommentDate.setText(commentsDataInArray.get(indexerForArray).toString());
                indexerForArray +=1;
                textViewCommentContent.setText(commentsDataInArray.get(indexerForArray).toString());
                indexerForArray +=1;
            }
            catch(Exception ex){

            }
        }
        @Override
        public void onClick(View view) {
            commentOnClickListener.onCommentClick(getAdapterPosition());
        }
    }
}

