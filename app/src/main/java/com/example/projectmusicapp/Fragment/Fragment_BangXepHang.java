package com.example.projectmusicapp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectmusicapp.Adapter.BangXepHangAdapter;
import com.example.projectmusicapp.Model.BangXepHang;
import com.example.projectmusicapp.R;
import com.example.projectmusicapp.Service.APIService;
import com.example.projectmusicapp.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Fragment_BangXepHang extends Fragment {
    View view;
    BangXepHangAdapter bangXepHangAdapter;
    RecyclerView recyclerViewbangxephang;
    TextView tenbangxephang;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_bangxephang, container, false);
        recyclerViewbangxephang = view.findViewById(R.id.recyclerviewbangxephang);
        tenbangxephang = view.findViewById(R.id.txtbangxephang);
        GetData();
        return view;
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<BangXepHang>> call = dataservice.GetBangXepHangCurrent();
        call.enqueue(new Callback<List<BangXepHang>>() {
            @Override
            public void onResponse(Call<List<BangXepHang>> call, Response<List<BangXepHang>> response) {
                ArrayList<BangXepHang> mangbangxephang = (ArrayList<BangXepHang>) response.body();
                bangXepHangAdapter = new BangXepHangAdapter(getActivity(), mangbangxephang);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerViewbangxephang.setLayoutManager(linearLayoutManager);
                recyclerViewbangxephang.setAdapter(bangXepHangAdapter);
            }

            @Override
            public void onFailure(Call<List<BangXepHang>> call, Throwable t) {

            }
        });
    }
}
