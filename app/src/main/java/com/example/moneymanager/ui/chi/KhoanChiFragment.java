package com.example.moneymanager.ui.chi;

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
import com.example.moneymanager.adapter.ChiRecyclerviewADapter;
import com.example.moneymanager.adapter.ItemClickListener;
import com.example.moneymanager.dialog.ChiDetailDialog;
import com.example.moneymanager.dialog.ChiDialog;
import com.example.moneymanager.dialog.LoaiChiDetailDialog;
import com.example.moneymanager.entity.Chi;
import com.example.moneymanager.entity.LoaiChi;

import java.util.List;

public class KhoanChiFragment extends Fragment {
    private KhoanChiViewModel mViewModel;
    private RecyclerView mRv;
    private ChiRecyclerviewADapter mAdapter;
    public static KhoanChiFragment newInstance() {
        return new KhoanChiFragment();
    }

    public KhoanChiViewModel getViewModel() {
        return mViewModel;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRv = view.findViewById(R.id.recyclerView);
        mAdapter = new ChiRecyclerviewADapter(getActivity());
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRv.setAdapter(mAdapter);
        final KhoanChiFragment currentFragment = this;
        mAdapter.setOnItemEditClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Chi chi = mAdapter.getItem(position);
                ChiDialog dialog = new ChiDialog(getActivity(), currentFragment, chi);
                dialog.show();
            }

        });
        mAdapter.setOnItemViewclickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Chi chi = mAdapter.getItem(position);
                ChiDialog dialog = new ChiDialog(getActivity(),currentFragment,chi);
                dialog.show();
            }
        });
        mAdapter.setOnItemViewclickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Chi chi = mAdapter.getItem(position);
                ChiDetailDialog dialog = new ChiDetailDialog(getActivity(),currentFragment,chi);
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
                        Chi chi = mAdapter.getItem(position);
                        Toast.makeText(getActivity(),"Khoản chi đã được xoá",Toast.LENGTH_SHORT).show();
                        mViewModel.delete(chi);
                    }
                }
        );
        helper.attachToRecyclerView(mRv);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_khoan_chi, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(KhoanChiViewModel.class);
        mViewModel.getAllChi().observe(getActivity(), new Observer<List<Chi>>() {
            @Override
            public void onChanged(List<Chi> Chis) {
                mAdapter.setList(Chis);
            }
        });
    }

}