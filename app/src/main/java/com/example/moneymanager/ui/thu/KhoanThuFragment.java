package com.example.moneymanager.ui.thu;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.moneymanager.R;
import com.example.moneymanager.adapter.ItemClickListener;
import com.example.moneymanager.adapter.ThuRecyclerviewADapter;
import com.example.moneymanager.dialog.LoaiThuDetailDialog;
import com.example.moneymanager.dialog.LoaiThuDialog;
import com.example.moneymanager.dialog.ThuDetailDialog;
import com.example.moneymanager.dialog.ThuDialog;
import com.example.moneymanager.entity.LoaiThu;
import com.example.moneymanager.entity.Thu;

import java.util.List;

public class KhoanThuFragment extends Fragment {
    private KhoanThuViewModel mViewModel;
    private RecyclerView mRv;
    private ThuRecyclerviewADapter mAdapter;
    public static KhoanThuFragment newInstance() {
        return new KhoanThuFragment();
    }

    public KhoanThuViewModel getViewModel() {
        return mViewModel;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRv = view.findViewById(R.id.recyclerView);
        mAdapter = new ThuRecyclerviewADapter(getActivity());
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRv.setAdapter(mAdapter);
        final KhoanThuFragment currentFragment = this;
        mAdapter.setOnItemEditClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Thu thu = mAdapter.getItem(position);
                ThuDialog dialog = new ThuDialog(getActivity(), currentFragment, thu);
                dialog.show();
            }
        });
        mAdapter.setOnItemViewclickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Thu thu = mAdapter.getItem(position);
                ThuDetailDialog dialog = new ThuDetailDialog(getActivity(),currentFragment,thu);
                dialog.show();
            }
        });
        ItemTouchHelper helper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0,
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT
                ) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                        return false;
                    }
                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        int position = viewHolder.getAdapterPosition();
                        Thu thu = mAdapter.getItem(position);

                        Toast.makeText(getActivity(),"Khoản thu đã được xoá",Toast.LENGTH_SHORT).show();
                        mViewModel.delete(thu);
                    }
                }
        );
        helper.attachToRecyclerView(mRv);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_khoan_thu, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(KhoanThuViewModel.class);
        mViewModel.getAllThu().observe(getActivity(), new Observer<List<Thu>>() {
            @Override
            public void onChanged(List<Thu> thus) {
                mAdapter.setList(thus);
            }
        });
    }

}