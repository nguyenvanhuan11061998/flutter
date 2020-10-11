package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.project1.inputStudent.view.InputInforStudentFragment;
import com.example.project1.studenmanager.view.StudentManagerFragment;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    FragmentTransaction transaction;

    private InputInforStudentFragment inputInforStudentFragment;
    private StudentManagerFragment studentManagerFragment;

    public InputInforStudentFragment getInputInforStudentFragment() {
        return inputInforStudentFragment;
    }

    public StudentManagerFragment getStudentManagerFragment() {
        return studentManagerFragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initFragment();
    }

    private void initFragment() {
        transaction = getSupportFragmentManager().beginTransaction();

        if (inputInforStudentFragment == null) {
            inputInforStudentFragment = new InputInforStudentFragment();
        }

        if (studentManagerFragment == null) {
            studentManagerFragment = new StudentManagerFragment();
        }

        transaction.add(R.id.panel, studentManagerFragment);
        transaction.commitNowAllowingStateLoss();
    }

    public void showFragment(Fragment fragment) {
//        if (fragment != null) {
//            transaction.show(fragment);
//        } else {
            transaction.add(R.id.panel, fragment);
            transaction.commitNowAllowingStateLoss();
//        }
    }
}