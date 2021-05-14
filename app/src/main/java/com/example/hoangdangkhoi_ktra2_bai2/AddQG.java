package com.example.hoangdangkhoi_ktra2_bai2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddQG extends AppCompatActivity {
    private Button btnBack, btAdd;
    private EditText txtName,txtDate, txtSoTien;
    private Spinner sp;
    private  SQLiteQG db;
    private QuyenGop sinhVien;
    private List<QuyenGop> sv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_qg);

        sv=new ArrayList<>();
        db=new SQLiteQG(this);
        btnBack=findViewById(R.id.btnTroVe);

        txtDate=findViewById(R.id.txtDate);
        txtName=findViewById(R.id.txthoten);
        txtSoTien=findViewById(R.id.txtsotien);
        sp=findViewById(R.id.spthanhpho);
        btAdd=findViewById(R.id.btnThem);

        String[] tpho={"Ha Noi", "HCM", "Hue", "Da Nang"};
        ArrayAdapter<String> arctr=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tpho);
        arctr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(arctr);
        txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal=Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month  = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog a=new DatePickerDialog(AddQG.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        txtDate.setText(year+"/"+(month)+"/"+dayOfMonth);
                    }
                }, year, month ,day);
                a.show();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddQG.this,MainActivity.class));
            }
        });

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=txtName.getText().toString();
                String tp=sp.getSelectedItem().toString();
                String ngay=txtDate.getText().toString();
                Long sotien=Long.parseLong(txtSoTien.getText().toString());
                QuyenGop a=new QuyenGop(name, tp, ngay, sotien);
                long r=db.themQG(a);
                if(r>0){
                    Toast.makeText(AddQG.this, "Thanh cong", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(AddQG.this, "That bai", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}