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
import com.example.moneymanager.adapter.LoaiThuSpinnerAdapter;
import com.example.moneymanager.entity.LoaiThu;
import com.example.moneymanager.entity.Thu;
import com.example.moneymanager.ui.thu.KhoanThuFragment;
import com.example.moneymanager.ui.thu.KhoanThuViewModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class ThuDialog {
    private KhoanThuViewModel mViewModel;
    private LayoutInflater mLayoutInflater;
    private AlertDialog mDialog;

    private TextInputEditText etId, etName, etAmout, etNote;
    private Spinner spType;
    private boolean mEditMode;
    private LoaiThuSpinnerAdapter mAdapter;

    public ThuDialog(final Context context, KhoanThuFragment fragment, Thu... thu){
        mViewModel = fragment.getViewModel();
        mLayoutInflater = LayoutInflater.from(context);
        View view = mLayoutInflater.inflate(R.layout.dialog_thu,null);
        etId = view.findViewById(R.id.etid);
        etName = view.findViewById(R.id.etName);
        etAmout = view.findViewById(R.id.etAmount);
        etNote = view.findViewById((R.id.etNote));
        spType = view.findViewById(R.id.spType);
        mAdapter = new LoaiThuSpinnerAdapter(fragment.getActivity());
        mViewModel.getAllLoaiThu().observe(fragment.getActivity(), new Observer<List<LoaiThu>>() {
            @Override
            public void onChanged(List<LoaiThu> loaiThus) {
                mAdapter.setList(loaiThus);
            }
        });
        spType.setAdapter(mAdapter);
        if(thu != null && thu.length>0){
            etId.setText(""+thu[0].tid);
            etName.setText(""+thu[0].ten);
            etAmout.setText("" +thu[0].sotien);
            etNote.setText(""+thu[0].ghichu);
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

                            Thu lt = new Thu();
                            lt.ten = etName.getText().toString();
                            lt.sotien = Float.parseFloat(etAmout.getText().toString());
                            lt.ghichu = etNote.getText().toString();
                            lt.ltid = ((LoaiThu) mAdapter.getItem(spType.getSelectedItemPosition())).lid;
                            if (mEditMode) {
                                lt.tid = Integer.parseInt(etId.getText().toString());
                                mViewModel.update(lt);
                            } else {
                                mViewModel.insert(lt);
                                Toast.makeText(context, "Loại thu được lưu", Toast.LENGTH_SHORT).show();
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
