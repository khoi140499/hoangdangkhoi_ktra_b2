package com.example.hoangdangkhoi_ktra2_bai2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class UpdateQG extends AppCompatActivity {
    private Button btUodate, btDele, btBack;
    private EditText txtName,txtDate, txtSoTien;
    private Spinner sp;
    private  SQLiteQG db;
    private QuyenGop sinhVien;
    private List<QuyenGop> sv;
    int id;
    String[] arr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_qg);
        Intent a=getIntent();
        String as=a.getStringExtra("qg");
        arr=as.split("noi");
        sv=new ArrayList<>();
        db=new SQLiteQG(this);
        btBack=findViewById(R.id.btnBack1);

        txtDate=findViewById(R.id.txtDate_update);
        txtName=findViewById(R.id.txthoten_update);
        txtSoTien=findViewById(R.id.txtsotien_update);
        sp=findViewById(R.id.spthanhpho_update);
        btDele=findViewById(R.id.btnDelete);
        btUodate=findViewById(R.id.btnUpdate);

        List tpho=new ArrayList();
        tpho.add("Ha Noi");
        tpho.add("HCM");
        tpho.add("Hue");
        tpho.add("Da Nang");
        ArrayAdapter<String> arctr=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tpho);
        arctr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(arctr);

        sp.setSelection(tpho.indexOf(arr[2]));

        id=Integer.parseInt(arr[0]);
        txtName.setText(arr[1]);

        txtDate.setText(arr[3]);
        txtSoTien.setText(arr[4]);

        btUodate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=txtName.getText().toString();
                String tp=sp.getSelectedItem().toString();
                String ngay=txtDate.getText().toString();
                Long sotien=Long.parseLong(txtSoTien.getText().toString());
                int changedRows = db.capNhatQG(new QuyenGop(id, name, tp, ngay, sotien));
                if (changedRows > 0) {
                    Toast.makeText(UpdateQG.this, "Thanh cong", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(UpdateQG.this, "That bai", Toast.LENGTH_LONG).show();
                }
            }
        });
        btDele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int deletedRows = db.xoaQG(id);
                if (deletedRows > 0) {
                    Toast.makeText(UpdateQG.this, "Thanh cong", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(UpdateQG.this, "That bai", Toast.LENGTH_LONG).show();
                }
//
            }
        });

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UpdateQG.this,MainActivity.class));
            }
        });

    }
}