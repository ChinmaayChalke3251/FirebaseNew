package com.example.daddy.firebasenew;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


    public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

        Context context;
        List<ProductDetails> MainImageUploadInfoList;

        public RecyclerViewAdapter(Context context, List<ProductDetails> TempList) {

            this.MainImageUploadInfoList = TempList;

            this.context = context;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_items, parent, false);

            ViewHolder viewHolder = new ViewHolder(view);

            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            ProductDetails studentDetails = MainImageUploadInfoList.get(position);

            holder.StudentNameTextView.setText(studentDetails.getproductname());

            holder.StudentNumberTextView.setText(studentDetails.getProductrate());

        }

        @Override
        public int getItemCount() {

            return MainImageUploadInfoList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            public TextView StudentNameTextView;
            public TextView StudentNumberTextView;

            public ViewHolder(View itemView) {

                super(itemView);

                StudentNameTextView = (TextView) itemView.findViewById(R.id.ShowStudentNameTextView);

                StudentNumberTextView = (TextView) itemView.findViewById(R.id.ShowStudentNumberTextView);
            }
        }
    }

