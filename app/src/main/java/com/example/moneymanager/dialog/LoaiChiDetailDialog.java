package com.example.moneymanager.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.moneymanager.R;
import com.example.moneymanager.entity.LoaiChi;
import com.example.moneymanager.ui.chi.LoaiChiFragment;
import com.example.moneymanager.ui.chi.LoaiChiViewModel;

public class LoaiChiDetailDialog {
    private LoaiChiViewModel mViewModel;
    private LayoutInflater mLayoutInflater;
    private AlertDialog mDialog;

    private TextView tvId, tvName;

    public LoaiChiDetailDialog(Context context, LoaiChiFragment fragment, LoaiChi LoaiChi){
        mViewModel = fragment.getViewModel();
        mLayoutInflater = LayoutInflater.from(context);
        View view = mLayoutInflater.inflate(R.layout.dialog_detail_loai_thu,null);
        tvId = view.findViewById(R.id.tvId);
        tvName = view.findViewById(R.id.tvName);

        tvId.setText(""+LoaiChi.cid);
        tvName.setText(""+LoaiChi.ten);

        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setView(view)
                .setNegativeButton("Đóng", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mDialog.dismiss();
                    }
                });
        mDialog = builder.create();
    }
    public void show(){
        mDialog.show();
    }
}
