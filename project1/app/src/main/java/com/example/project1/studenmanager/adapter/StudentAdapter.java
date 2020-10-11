package com.example.project1.studenmanager.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project1.R;
import com.example.project1.inputStudent.model.InforStudent;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentHolder> {
    private List<InforStudent> listData;
    private Context context;
    private OnItemClick onItemClick;

    public StudentAdapter(Context context,OnItemClick onItemClick) {
        this.context = context;
        this.onItemClick = onItemClick;
    }

    public void setListData(List<InforStudent> listData) {
        this.listData = listData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StudentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_student, parent, false);
        return new StudentHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentHolder holder, int position) {
        InforStudent student = listData.get(position);
        holder.binData(student);
    }

    @Override
    public int getItemCount() {
        return listData == null ? 0 : listData.size();
    }

    public class StudentHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_id_student)
        TextView idStudentTextView;
        @BindView(R.id.tv_name)
        TextView nameTextView;
        @BindView(R.id.tv_class)
        TextView classTextView;
        @BindView(R.id.tv_sex)
        TextView sexTextView;
        @BindView(R.id.tv_match_point)
        TextView matchPointTextView;
        @BindView(R.id.tv_physical_point)
        TextView physicalPointTextView;
        @BindView(R.id.tv_chemistry_point)
        TextView chemistryPointTextView;
        @BindView(R.id.cv_item)
        CardView itemView;

        public StudentHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void binData(InforStudent student) {
            idStudentTextView.setText(student.getMsv());
            nameTextView.setText(student.getName());
            classTextView.setText(student.getClassRoom());
            sexTextView.setText(student.getSex());
            matchPointTextView.setText(student.getMatchPoint());
            physicalPointTextView.setText(student.getPhysicalPoint());
            chemistryPointTextView.setText(student.getChemistryPoint());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClick != null) {
                        onItemClick.clickStudent(getAdapterPosition());
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (onItemClick != null) {
                        onItemClick.longClickStudent(getAdapterPosition());
                    }
                    return false;
                }
            });
        }
    }

    public interface OnItemClick {
        void clickStudent(int position);
        void longClickStudent(int position);
    }
}
