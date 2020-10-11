package com.example.project1.studenmanager.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project1.MainActivity;
import com.example.project1.R;
import com.example.project1.inputStudent.model.InforStudent;
import com.example.project1.inputStudent.view.InputInforStudentFragment;
import com.example.project1.studenmanager.adapter.StudentAdapter;
import com.example.project1.studenmanager.viewModel.StudentManagerViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StudentManagerFragment extends Fragment {
    @BindView(R.id.rv_list_student)
    RecyclerView listStudentView;
    @BindView(R.id.img_add_student)
    ImageView addStudentImageView;

    private MainActivity mActivity;
    private StudentManagerViewModel viewModel;
    private StudentAdapter adapter;
    private List<InforStudent> listData = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_manager, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initLayout();
        initViewModel();
    }

    private void initLayout() {
        mActivity = (MainActivity) getActivity();
        adapter = new StudentAdapter(getContext(), new StudentAdapter.OnItemClick() {
            @Override
            public void clickStudent(int position) {
                viewModel.editInfoStudent(getFragmentManager(), listData.get(position));
            }

            @Override
            public void longClickStudent(int position) {
                viewModel.deleteStudent(getFragmentManager(), listData.get(position));
                Toast.makeText(getContext(), getString(R.string.da_xoa_sinh_vien), Toast.LENGTH_LONG).show();
            }
        });
        listStudentView.setAdapter(adapter);
    }

    private void initViewModel() {
        viewModel = new StudentManagerViewModel(getContext());
        viewModel.initData();

        viewModel.getListStudent().observe(this, new Observer<List<InforStudent>>() {
            @Override
            public void onChanged(List<InforStudent> students) {
                if (students != null) {
                    listData = students;
                    adapter.setListData(listData);
                }
            }
        });
    }

    @OnClick({R.id.img_add_student})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_add_student:
                viewModel.showViewAddStudent(getFragmentManager());
                break;
        }
    }
}
