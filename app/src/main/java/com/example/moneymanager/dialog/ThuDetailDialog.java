package com.example.moneymanager.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.moneymanager.R;
import com.example.moneymanager.entity.Thu;
import com.example.moneymanager.ui.thu.KhoanThuFragment;
import com.example.moneymanager.ui.thu.KhoanThuViewModel;

public class ThuDetailDialog {
    private KhoanThuViewModel mViewModel;
    private LayoutInflater mLayoutInflater;
    private AlertDialog mDialog;

    private TextView tvId, tvName,tvMoney,tvNote;

    public ThuDetailDialog(Context context, KhoanThuFragment fragment, Thu thu){
        mViewModel = fragment.getViewModel();
        mLayoutInflater = LayoutInflater.from(context);
        View view = mLayoutInflater.inflate(R.layout.dialog_detail_thu,null);
        tvId = view.findViewById(R.id.tvId);
        tvName = view.findViewById(R.id.tvName);
        tvMoney = view.findViewById(R.id.tvMoney);
        tvNote = view.findViewById(R.id.tvNote);
        tvId.setText(""+thu.tid);
        tvName.setText(""+thu.ten);
        tvMoney.setText(""+thu.sotien);
        tvNote.setText(""+thu.ghichu);
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
