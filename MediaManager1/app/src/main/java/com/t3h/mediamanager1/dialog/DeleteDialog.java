package com.t3h.mediamanager1.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.t3h.mediamanager1.R;
import com.t3h.mediamanager1.databinding.DialogDeleteBinding;

public class DeleteDialog extends Dialog implements View.OnClickListener{
    private calbackDialog calback;

    public void setCalback(calbackDialog calback) {
        this.calback = calback;
    }

    private DialogDeleteBinding binding;

    public DeleteDialog( Context context) {
        super(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_delete,null,false);
        setContentView(binding.getRoot());

        binding.btnDeleteOK.setOnClickListener(this);
        binding.btnDeleteCancel.setOnClickListener(this);
    }

    public void setTextDialog(String mes){
        binding.tvDlDelete.setText(mes);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_Delete_OK:
                if (calback!= null){
                    calback.onClickDlDelete();
                }
                dismiss();
                break;
            case R.id.btn_Delete_Cancel:
                dismiss();
                break;
        }
    }

    public interface calbackDialog{
        void onClickDlDelete();
    }
}
