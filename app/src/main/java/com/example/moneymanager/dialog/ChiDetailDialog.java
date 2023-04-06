package com.example.moneymanager.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.moneymanager.R;
import com.example.moneymanager.entity.Chi;
import com.example.moneymanager.ui.chi.ChiViewModel;
import com.example.moneymanager.ui.chi.KhoanChiFragment;
import com.example.moneymanager.ui.chi.KhoanChiViewModel;

public class ChiDetailDialog {
    private KhoanChiViewModel mViewModel;
    private LayoutInflater mLayoutInflater;
    private AlertDialog mDialog;

    private TextView tvId, tvName,tvMoney,tvNote;

    public ChiDetailDialog(Context context, KhoanChiFragment fragment, Chi chi){
        mViewModel = fragment.getViewModel();
        mLayoutInflater = LayoutInflater.from(context);
        View view = mLayoutInflater.inflate(R.layout.dialog_detail_chi,null);
        tvId = view.findViewById(R.id.tvId);
        tvName = view.findViewById(R.id.tvName);
        tvMoney = view.findViewById(R.id.tvMoney);
        tvNote = view.findViewById(R.id.tvNote);
        tvId.setText(""+chi.cid);
        tvName.setText(""+chi.ten);
        tvMoney.setText(""+chi.sotien);
        tvNote.setText(""+chi.ghichu);
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
