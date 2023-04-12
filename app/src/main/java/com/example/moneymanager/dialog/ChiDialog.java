package com.example.moneymanager.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.lifecycle.Observer;

import com.example.moneymanager.R;
import com.example.moneymanager.adapter.LoaiChiSpinnerAdapter;
import com.example.moneymanager.entity.LoaiChi;
import com.example.moneymanager.entity.Chi;
import com.example.moneymanager.ui.chi.KhoanChiFragment;
import com.example.moneymanager.ui.chi.KhoanChiViewModel;
import com.google.android.material.textfield.TextInputEditText;

import java.text.NumberFormat;
import java.util.List;

public class ChiDialog {
    private KhoanChiViewModel mViewModel;
    private LayoutInflater mLayoutInflater;
    private AlertDialog mDialog;

    private TextInputEditText etId, etName, etAmout, etNote;
    private Spinner spType;
    private boolean mEditMode;
    private LoaiChiSpinnerAdapter mAdapter;

    public ChiDialog(final Context context, KhoanChiFragment fragment, Chi... Chi){
        mViewModel = fragment.getViewModel();
        mLayoutInflater = LayoutInflater.from(context);
        View view = mLayoutInflater.inflate(R.layout.dialog_chi,null);
        etId = view.findViewById(R.id.etid);
        etName = view.findViewById(R.id.etName);
        etAmout = view.findViewById(R.id.etAmount);
        etNote = view.findViewById((R.id.etNote));
        spType = view.findViewById(R.id.spType);
        mAdapter = new LoaiChiSpinnerAdapter(fragment.getActivity());
        mViewModel.getAllLoaiChi().observe(fragment.getActivity(), new Observer<List<LoaiChi>>() {
            @Override
            public void onChanged(List<LoaiChi> LoaiChis) {
                mAdapter.setList(LoaiChis);
            }
        });
        spType.setAdapter(mAdapter);
        if(Chi != null && Chi.length>0){
            etId.setText(""+Chi[0].cid);
            etName.setText(""+Chi[0].ten);
            etAmout.setText("" +Chi[0].sotien);
            etNote.setText(""+Chi[0].ghichu);
            mEditMode = true;
        }else{
            mEditMode = false;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setView(view)
                .setNegativeButton("Đóng", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mDialog.dismiss();
                    }
                })
                .setPositiveButton("Lưu", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(TextUtils.isEmpty(etName.getText().toString())  ) {
                            etName.setError("Không được để trống");
                            Toast.makeText(context, "Lưu không thành công do thiếu dữ liệu", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if(TextUtils.isEmpty(etAmout.getText().toString())){
                            etAmout.setError("Không được để trống");
                            Toast.makeText(context, "Lưu không thành công do thiếu dữ liệu", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if(!TextUtils.isEmpty(etAmout.getText().toString()) && !TextUtils.isEmpty(etName.getText().toString())) {
                            Chi lc = new Chi();
                            lc.ten = etName.getText().toString();
                            float amount = Float.parseFloat(etAmout.getText().toString());
                            NumberFormat formatter = NumberFormat.getInstance();
                            String formattedAmount = formatter.format(amount);
                            lc.sotien = Float.parseFloat(formattedAmount);
                            lc.ghichu = etNote.getText().toString();
                            lc.lcid = ((LoaiChi) mAdapter.getItem(spType.getSelectedItemPosition())).cid;
                            if (mEditMode) {
                                lc.cid = Integer.parseInt(etId.getText().toString());
                                mViewModel.update(lc);
                            } else {
                                mViewModel.insert(lc);
                                Toast.makeText(context, "Loại chi được lưu", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
        mDialog = builder.create();
    }
    public void show(){
        mDialog.show();
    }
}
