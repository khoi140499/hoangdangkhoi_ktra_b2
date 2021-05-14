package com.example.hoangdangkhoi_ktra2_bai2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton btfl;
    private RecyclerView recyclerView;
    private TextView tt;
    private ReAdapter adapter;
    private  SQLiteQG db;
    private QuyenGop sinhVien;
    private List<QuyenGop> qg;
    private List<QuyenGop> timKiem;
    private androidx.appcompat.widget.SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        qg=new ArrayList<>();
        timKiem=new ArrayList<>();
        db=new SQLiteQG(this);

        btfl=findViewById(R.id.khoi_floatingActionButton);
        recyclerView=findViewById(R.id.khoi_recyclerView);
        tt=findViewById(R.id.tongtien_re);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new ReAdapter(MainActivity.this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        qg = db.getAllQuyenGop();
        long tong=0;
        for(QuyenGop as:qg){
            long tt= as.getSotien();
            tong+=tt;
        }
        tt.setText("Tong tien :"+tong);
        adapter.themList(qg);
        recyclerView.setAdapter(adapter);


        btfl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent as=new Intent(MainActivity.this, AddQG.class);
                startActivity(as);
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (androidx.appcompat.widget.SearchView) menu.findItem(R.id.khoi_action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnCloseListener(new androidx.appcompat.widget.SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                adapter.themList(qg);
                adapter.notifyDataSetChanged();
                return false;
            }
        });
        searchView.setIconifiedByDefault(true);
        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                timKiem = db.timKiem(query);
                adapter.themList(timKiem);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }
}