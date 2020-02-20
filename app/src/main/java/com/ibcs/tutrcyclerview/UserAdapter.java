package com.ibcs.tutrcyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ibcs.tutrcyclerview.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {

    private List<User> userList;
    private LayoutInflater inflater;
    private Context context;
    private UserClickHandler handler;

    public UserAdapter(Context context,UserClickHandler handler) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.userList = new ArrayList<>();
        this.handler = handler;
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //return null;
        View view = inflater.inflate(R.layout.item_user,parent,false);
        UserHolder userHolder = new UserHolder(view);
        return userHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {

        User user = userList.get(position);
        holder.bind(user);


    }

    @Override
    public int getItemCount() {
        return userList.size();
    }


    public void addUser(User user){
        userList.add(user);
        //notifyDataSetChanged();
        int position = userList.indexOf(user);
        notifyItemInserted(position);
    }

    public void removeUser(User user){
      int position = getPosition(user);

      if(position!=-1){
          notifyItemRemoved(position);
      }
    }

    public void removeUser(int position){
        userList.remove(position);
        notifyItemRemoved(position);
    }


    private int getPosition(User user){
        for (User x:userList){
            if(x.getName().equals(user.getName()) && x.getEmail().equals(user.getEmail())){
                return userList.indexOf(x);
            }
        }
        return -1;
    }



    class UserHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvName,tvEmail;
        Button btnDelete;

        public UserHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.name);
            tvEmail = itemView.findViewById(R.id.email);
            btnDelete = itemView.findViewById(R.id.delete);

            itemView.setOnClickListener(this);
            btnDelete.setOnClickListener(this);

        }

        public void bind(User user){
            tvName.setText(user.getName());
            tvEmail.setText(user.getEmail());
        }

        @Override
        public void onClick(View v) {
            if(v==itemView){
                User user = userList.get(getAdapterPosition());
                handler.onItemClick(user);
            }else if(v==btnDelete){
                handler.onDeleteClick(getAdapterPosition());
            }


        }
    }
}
