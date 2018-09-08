package com.example.mx.projecttwo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements PostRecyclerAdapter.PostClickListener , CommentRecyclerAdapter.CommentClickListener{
    private static final int numberOfTheItemToDisplay =100;
    private CommentRecyclerAdapter commentAdapterObject;
    private PostRecyclerAdapter postAdapterObject;
    private RecyclerView recyclerObject;
    private Toast toastObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void displayComment(View v){
        recyclerObject =findViewById(R.id.recyclerView_comment);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerObject.setLayoutManager(layoutManager);
        commentAdapterObject=new CommentRecyclerAdapter(numberOfTheItemToDisplay,this);
        recyclerObject.setAdapter(commentAdapterObject);
    }
    public void displayPost(View v){
        recyclerObject =findViewById(R.id.recyclerView_post);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerObject.setLayoutManager(layoutManager);
        postAdapterObject=new PostRecyclerAdapter(numberOfTheItemToDisplay,this);
        recyclerObject.setAdapter(postAdapterObject);
    }
    @Override
    public void onPostClick(int clickedItemIndex) {
        if(toastObject !=null){
            toastObject.cancel();
        }
        String toastMessage="Post #"+ clickedItemIndex+"clicked.";
        toastObject = Toast.makeText(this,toastMessage,Toast.LENGTH_LONG);

        toastObject.show();
    }
    @Override
    public void onCommentClick(int clickedItemIndex) {
        if(toastObject !=null){
            toastObject.cancel();
        }
        String toastMessage="Post #"+ clickedItemIndex+"clicked.";
        toastObject = Toast.makeText(this,toastMessage,Toast.LENGTH_LONG);

        toastObject.show();
    }

}
