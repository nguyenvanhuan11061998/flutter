package com.example.project1.studenmanager.viewModel;

import android.content.Context;
import android.util.Log;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.MutableLiveData;

import com.example.project1.database.StudentDatabase;
import com.example.project1.inputStudent.model.InforStudent;
import com.example.project1.inputStudent.view.InputInforStudentFragment;

import java.util.List;

public class StudentManagerViewModel {
    private Context context;

    public StudentManagerViewModel(Context context) {
        this.context = context;
    }

    private MutableLiveData<List<InforStudent>> listStudent = new MutableLiveData<>();

    public MutableLiveData<List<InforStudent>> getListStudent() {
        return listStudent;
    }

    public void setListStudent(MutableLiveData<List<InforStudent>> listStudent) {
        this.listStudent = listStudent;
    }

    public void initData() {
        List<InforStudent> listStudentDatabase = StudentDatabase.getInstance(context).getStudentDao().getAllStudent();
        listStudent.postValue(listStudentDatabase);
    }

    public void showViewAddStudent(FragmentManager fragmentManager) {
        InputInforStudentFragment.getInstance().setBackChangeInforStudent(new OnBackChangeInforStudent() {
            @Override
            public void onChangeInfoStudent() {
                initData();
            }
        }, null);
        InputInforStudentFragment.getInstance().show(fragmentManager, "");
    }

    public void editInfoStudent(FragmentManager fragmentManager, InforStudent student) {
        InputInforStudentFragment.getInstance().setBackChangeInforStudent(new OnBackChangeInforStudent() {
            @Override
            public void onChangeInfoStudent() {
                initData();
            }
        }, student);
        InputInforStudentFragment.getInstance().show(fragmentManager, "");
    }

    public void deleteStudent(FragmentManager fragmentManager, InforStudent student) {
        StudentDatabase.getInstance(context).getStudentDao().delete(student);
        initData();
    }

    public interface OnBackChangeInforStudent {
        void onChangeInfoStudent();
    }
}
