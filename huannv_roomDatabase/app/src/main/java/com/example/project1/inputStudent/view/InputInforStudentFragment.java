package com.example.project1.inputStudent.view;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;

import com.example.project1.MainActivity;
import com.example.project1.R;
import com.example.project1.inputStudent.model.InforStudent;
import com.example.project1.inputStudent.viewModel.InputInforStudentViewModel;
import com.example.project1.studenmanager.viewModel.StudentManagerViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InputInforStudentFragment extends DialogFragment {
    @BindView(R.id.edt_msv)
    EditText msvEditText;
    @BindView(R.id.edt_name)
    EditText nameEditText;
    @BindView(R.id.edt_sex)
    EditText sexEditText;
    @BindView(R.id.edt_class)
    EditText classEditText;
    @BindView(R.id.edt_match_point)
    EditText matchPointEditText;
    @BindView(R.id.edt_chemistry_point)
    EditText chemistryPointEditText;
    @BindView(R.id.edt_physical_point)
    EditText physicalPointEditText;
    @BindView(R.id.btn_back)
    ImageView backImageView;
    @BindView(R.id.tv_title)
    TextView titleTextView;

    private InputInforStudentViewModel viewModel;
    private MainActivity mActivity;
    private StudentManagerViewModel.OnBackChangeInforStudent backChangeInforStudent;
    private static InputInforStudentFragment inputInforStudentFragment;
    private InforStudent studentEdit;

    public static InputInforStudentFragment getInstance() {
        if (inputInforStudentFragment == null) {
            inputInforStudentFragment = new InputInforStudentFragment();
        }
        return inputInforStudentFragment;
    }

    public void setBackChangeInforStudent(StudentManagerViewModel.OnBackChangeInforStudent backChangeInforStudent, InforStudent student) {
        this.backChangeInforStudent = backChangeInforStudent;
        this.studentEdit = student;
    }

    public InputInforStudentViewModel getViewModel() {
        return viewModel;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_input_infor_student, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initLiveData();
        initView();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // the content
        final RelativeLayout root = new RelativeLayout(getActivity());
        root.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        // creating the fullscreen dialog
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(root);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        return dialog;
    }

    private void initView() {
        mActivity = (MainActivity) getActivity();

        if (studentEdit != null) {
            titleTextView.setText(getString(R.string.thay_doi_thong_tin));
            viewModel.initData(studentEdit);
        } else {
            titleTextView.setText(getString(R.string.them_sinh_vien));
        }
    }

    private void initLiveData() {
        viewModel = new InputInforStudentViewModel(getContext());

        viewModel.getStudentEdit().observe(this, new Observer<InforStudent>() {
            @Override
            public void onChanged(InforStudent student) {
                if (student != null) {
                    msvEditText.setText(student.getMsv());
                    nameEditText.setText(student.getName());
                    classEditText.setText(student.getClassRoom());
                    sexEditText.setText(student.getSex());
                    matchPointEditText.setText(student.getMatchPoint());
                    chemistryPointEditText.setText(student.getChemistryPoint());
                    physicalPointEditText.setText(student.getPhysicalPoint());
                }
            }
        });
    }

    @OnClick({R.id.btn_save_infor, R.id.btn_back})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_save_infor:
                boolean validateInfor = viewModel.validateInfor(msvEditText.getText().toString()
                        , nameEditText.getText().toString(), classEditText.getText().toString());
                if (!validateInfor){
                    Toast.makeText(getContext(), getString(R.string.nhap_thong_tin_chua_day_du), Toast.LENGTH_LONG).show();
                    return;
                }
                InforStudent student = new InforStudent(msvEditText.getText().toString()
                        , nameEditText.getText().toString(), classEditText.getText().toString()
                        , sexEditText.getText().toString(), matchPointEditText.getText().toString()
                , chemistryPointEditText.getText().toString(), physicalPointEditText.getText().toString());

                viewModel.saveInforStudent(student);
                Toast.makeText(getContext(), getString(R.string.da_luu_thong_tin_sinhvien), Toast.LENGTH_LONG).show();
                reloadView();
                break;

            case R.id.btn_back:
                dismiss();
                break;

        }
        if (backChangeInforStudent != null) {
            backChangeInforStudent.onChangeInfoStudent();
        }
    }

    private void reloadView() {
        msvEditText.setText("");
        nameEditText.setText("");
        sexEditText.setText("");
        classEditText.setText("");
        matchPointEditText.setText("");
        chemistryPointEditText.setText("");
        physicalPointEditText.setText("");
    }
}
