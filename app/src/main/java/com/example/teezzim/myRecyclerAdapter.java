package com.example.teezzim;

import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teezzim.db.Scorebord;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.List;

public class myRecyclerAdapter extends RecyclerView.Adapter<myRecyclerAdapter.ViewHolder> {

    private ArrayList<Scorebord> listData = new ArrayList<>();

    @NonNull
    @Override
    public myRecyclerAdapter.ViewHolder
        onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myRecyclerAdapter.ViewHolder holder, int position) {
        holder.onBind(listData.get(position));
    }

    @Override
    public int getItemCount() {
//        Log.d("Scorebord getItemCount() ", "Size = "+listData.size());
        return listData.size();
    }

    void addItem(Scorebord scorebord){
        listData.add(scorebord);
        Log.d("Scorebord addItem ", "Size = "+listData.size());
    }

    void ClearAll(){
        listData.clear();
        Log.d("Scorebord ClearAll ", "Size = "+listData.size());
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView    tv_Datetime;
        private EditText    tv_Meno;
        private TextView    tv_Location;
        private TextView    tv_cansel;
        private ImageView   imageView_album;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Log.d("Scorebord  ", "ViewHolder()  ");
            tv_Datetime     = (TextView)itemView.findViewById(R.id.textView_datetime);
            tv_Meno         = (EditText)itemView.findViewById(R.id.editTextTextMultiLine_memo);
            tv_Meno.setFocusable(false);
            tv_Meno.setClickable(false);
            tv_Location     = (TextView)itemView.findViewById(R.id.textView_location);
            tv_cansel       = (TextView)itemView.findViewById(R.id.tv_cansel);
            imageView_album = (ImageView)itemView.findViewById(R.id.imageView_album) ;

        }
        public void onBind(Scorebord scorebord) {
            Log.d("Scorebord  ", "onBind() data = "+scorebord.toString());

            tv_Datetime.setText(scorebord.getDate()+" "+scorebord.getTime());

            if(scorebord.getMemo() != null){
                tv_Meno.setText(scorebord.getMemo());
            }

            tv_Location.setText(scorebord.getLocation());

            if(scorebord.isCansel()){
                tv_cansel.setText(scorebord.getCanselDay());
            }else{
                tv_cansel.setVisibility(View.GONE);
            }
            try {
                String album = scorebord.getJson_album();
                if(album != null){
                    Uri uri = Uri.parse(album);
                    imageView_album.setImageURI(uri);
                }

                /*
                Uri uri = Uri.parse("file:///" + Environment.getExternalStorageDirectory() + "/572/내그림/image_sample.jpg");
            imageView02.setImageURI(uri);
                 */
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
