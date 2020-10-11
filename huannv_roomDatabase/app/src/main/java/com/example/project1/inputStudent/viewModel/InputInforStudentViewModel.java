package com.example.project1.inputStudent.viewModel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.example.project1.database.StudentDatabase;
import com.example.project1.inputStudent.model.InforStudent;

public class InputInforStudentViewModel {

    private MutableLiveData<InforStudent> studentEdit = new MutableLiveData<>();

    public MutableLiveData<InforStudent> getStudentEdit() {
        return studentEdit;
    }

    public void setStudentEdit(MutableLiveData<InforStudent> studentEdit) {
        this.studentEdit = studentEdit;
    }

    private Context context;
    private boolean isEditStudent = false;

    public InputInforStudentViewModel(Context context) {
        this.context = context;
    }

    public void initData(InforStudent student) {
        isEditStudent = true;
        studentEdit.postValue(student);
    }

    public boolean validateInfor(String msv, String name, String classRoom) {
        if (msv.isEmpty() || name.isEmpty() || classRoom.isEmpty())
            return false;
        return true;
    }

    public void saveInforStudent(InforStudent student) {
        if (isEditStudent) {
            InforStudent student1 = studentEdit.getValue();
            student.setId(student1.getId());
            StudentDatabase.getInstance(context).getStudentDao().update(student);
        } else {
            StudentDatabase.getInstance(context).getStudentDao().insert(student);
        }
    }
}
