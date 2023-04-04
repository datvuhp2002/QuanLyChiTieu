package com.example.moneymanager.ui.thongke;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.moneymanager.R;
import com.example.moneymanager.adapter.ThongKeLoaiThuRecycleViewAdapter;
import com.example.moneymanager.entity.ThongKeLoaiThu;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThongKeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThongKeFragment extends Fragment {
    private ThongKeViewModel mThongKeViewModel;
    private EditText mEtTongThu;

    private RecyclerView rvThongKeLoaiThu;
    private ThongKeLoaiThuRecycleViewAdapter mThongKeLoaiThuAdapter;



    public ThongKeFragment() {
        // Required empty public constructor
    }

    public static ThongKeFragment newInstance() {
        ThongKeFragment fragment = new ThongKeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thong_ke, container, false);
        mEtTongThu = view.findViewById(R.id.etTongThu);
        rvThongKeLoaiThu = view.findViewById(R.id.rvThongKeLoaiThu);

        mThongKeViewModel = new ViewModelProvider(this).get(ThongKeViewModel.class);

        mThongKeLoaiThuAdapter = new ThongKeLoaiThuRecycleViewAdapter(getActivity());
        rvThongKeLoaiThu.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvThongKeLoaiThu.setAdapter(mThongKeLoaiThuAdapter);
        mThongKeViewModel.getTongThu().observe(getActivity(), new Observer<Float>() {
            @Override
            public void onChanged(Float tong) {
                mEtTongThu.setText(""+ tong);
            }
        });
        mThongKeViewModel.getThongKeLoaiThus().observe(getActivity(), new Observer<List<ThongKeLoaiThu>>() {
            @Override
            public void onChanged(List<ThongKeLoaiThu> thongKeLoaiThus) {
                mThongKeLoaiThuAdapter.setList(thongKeLoaiThus);
            }
        });
        return view;
    }
}