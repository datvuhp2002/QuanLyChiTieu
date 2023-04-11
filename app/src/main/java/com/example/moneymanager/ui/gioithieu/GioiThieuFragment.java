package com.example.moneymanager.ui.gioithieu;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.moneymanager.R;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GioiThieuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GioiThieuFragment extends Fragment {
    private TextView txtGioiThieu;
    public GioiThieuFragment() {
        // Required empty public constructor
    }
    public static GioiThieuFragment newInstance() {
        GioiThieuFragment fragment = new GioiThieuFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gioi_thieu, container, false);
        txtGioiThieu = view.findViewById(R.id.txtGioiThieu);
        txtGioiThieu.setText("Ứng dụng quản lý chi tiêu là một công cụ hữu ích giúp người dùng theo dõi, phân loại và kiểm soát các khoản chi tiêu hàng ngày. Với sự phát triển của công nghệ, ứng dụng quản lý chi tiêu đã trở thành một giải pháp hiệu quả để giúp người dùng đưa ra quyết định tài chính thông minh hơn.\n" +
                "\n" +
                "Với ứng dụng này, người dùng có thể dễ dàng ghi lại các khoản chi tiêu hàng ngày, tháng hoặc năm và theo dõi chi tiêu của mình theo các danh mục khác nhau, ví dụ như thực phẩm, điện thoại, giải trí và nhiều hơn nữa. Điều này giúp người dùng có cái nhìn tổng quan về tình hình tài chính của mình, giúp họ có thể quản lý chi tiêu một cách khoa học và hiệu quả hơn.\n" +
                "\n" +
                "Ngoài ra, ứng dụng quản lý chi tiêu cũng cung cấp các công cụ hữu ích như bảng tổng hợp chi tiêu, biểu đồ phân tích chi tiêu và cảnh báo khi chi tiêu vượt quá mức cho phép. Nhờ đó, người dùng có thể đưa ra các quyết định tài chính thông minh hơn và tránh tình trạng lãng phí tiền bạc.\n" +
                "\n" +
                "Tóm lại, ứng dụng quản lý chi tiêu là một công cụ hữu ích giúp người dùng kiểm soát và quản lý chi tiêu một cách hiệu quả hơn. Nó giúp người dùng có cái nhìn tổng quan về tình hình tài chính của mình và đưa ra các quyết định tài chính thông minh hơn");
        return view;
    }
}