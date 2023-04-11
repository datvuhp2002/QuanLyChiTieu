package com.example.moneymanager.ui.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.moneymanager.MainActivity;
import com.example.moneymanager.R;
import com.example.moneymanager.adapter.ThongKeLoaiChiRecycleViewAdapter;
import com.example.moneymanager.adapter.ThongKeLoaiThuRecycleViewAdapter;
import com.example.moneymanager.databinding.ActivityMainBinding;
import com.example.moneymanager.entity.ThongKeLoaiChi;
import com.example.moneymanager.entity.ThongKeLoaiThu;
import com.example.moneymanager.ui.chi.KhoanChiFragment;
import com.example.moneymanager.ui.chi.LoaiChiFragment;
import com.example.moneymanager.ui.thu.KhoanThuFragment;
import com.example.moneymanager.ui.thu.LoaiThuFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    private HomeViewModel mHomeViewModel;
    private EditText etThu,etChi,etTotal;

    private void updateTotal() {
        Float tongThu = mHomeViewModel.getTongThu().getValue();
        Float tongChi = mHomeViewModel.getTongChi().getValue();
        if (tongThu != null && tongChi != null) {
            etTotal.setText("" + (tongThu - tongChi));
        }
    }

    public HomeFragment() {
        // Required empty public constructor
    }
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        etThu = view.findViewById(R.id.etThu);
        etChi = view.findViewById(R.id.etChi);
        etTotal = view.findViewById(R.id.etTotal);
        etTotal.setText("0.0");

        mHomeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        mHomeViewModel.getTongThu().observe(getActivity(), new Observer<Float>() {
            @Override
            public void onChanged(Float tong) {
                if(tong != null && tong.floatValue() != 0.0f){
                    etThu.setText(""+tong);
                    updateTotal();
                }else{
                    etThu.setText("0.0");
                }
            }
        });
        mHomeViewModel.getTongChi().observe(getActivity(), new Observer<Float>() {
            @Override
            public void onChanged(Float tong) {

                if(tong != null && tong.floatValue() != 0.0f){
                    etChi.setText(""+ tong);
                    updateTotal();
                }else{
                    etChi.setText("0.0");
                }
            }
        });
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        mHomeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        mHomeViewModel.updateTongChi();
        mHomeViewModel.updateTongThu();
        updateTotal();
    }

}