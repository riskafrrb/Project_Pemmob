 package com.example.androidsqlite;

import androidx.appcompat.app.AppCompatActivity;
//import android.app.Activity;
import android.view.*;
import java.util.ArrayList;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;

 public class MainActivity extends AppCompatActivity {
    DatabaseManager dm;
    private EditText eNm,enpm, ekode, eTb, eBb, eRiwayat;
    private Button bBaru, bSimpan, bUbah, bHapus;
    TableLayout tabel4data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dm = new DatabaseManager(this);
        tabel4data = (TableLayout) findViewById(R.id.table_data);
        ekode=(EditText)findViewById(R.id.edTextKode);
        eNm=(EditText)findViewById(R.id.edTextNama);
        enpm=(EditText)findViewById(R.id.edTextNPM);
        eBb=(EditText)findViewById(R.id.edTextBb);
        eTb=(EditText)findViewById(R.id.edTextTb);
        eRiwayat=(EditText)findViewById(R.id.edTextRiwayat);
        bSimpan=(Button)findViewById(R.id.btnSimpan);
        bUbah=(Button)findViewById(R.id.btnUbah);
        bHapus=(Button)findViewById(R.id.btnHapus);
        bBaru=(Button)findViewById(R.id.btnBaru);
        bSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpanTable();
            }
        });
        bUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ubahTable();
            }
        });
        bHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hapusTable();
            }
        });
        bBaru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kosongkanField();
            }
        });
        updateTable();
    } //Akhir void onCreate()
    @Override
    protected void onResume() {
        super.onResume();
        updateTable();
    }
    protected void simpanTable() {
        try {
            dm.addRow(eNm.getText().toString(),enpm.getText().toString(), eTb.getText().toString(), eBb.getText().toString(), eRiwayat.getText().toString());
            Toast.makeText(getBaseContext(),
                    eNm.getText().toString() + ", berhasil disimpan",
                    Toast.LENGTH_SHORT).show();
            updateTable();
            kosongkanField();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getBaseContext(), "gagal simpan, " + e.toString(),Toast.LENGTH_LONG).show();
        }
    }//Akhir void simpanTable()
    protected void ubahTable() {
        try {
            dm.UpdateRecord(Integer.parseInt(ekode.getText().toString()),eNm.getText().toString(),enpm.getText().toString(), eTb.getText().toString(), eBb.getText().toString(), eRiwayat.getText().toString());
            Toast.makeText(getBaseContext(),
                    eNm.getText().toString() + ", berhasil diubah",
                    Toast.LENGTH_SHORT).show();
            updateTable();
            kosongkanField();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getBaseContext(), "gagal ubah, " + e.toString(),Toast.LENGTH_LONG).show();
        }
    }//Akhir void ubahTable()
    protected void hapusTable() {
        try {
            dm.DeleteRecord(Integer.parseInt(ekode.getText().toString()));
            Toast.makeText(getBaseContext(),
                    "Kode "+ekode.getText().toString() + ", Data Berhasil Dihapus",
                    Toast.LENGTH_SHORT).show();
            updateTable();
            kosongkanField();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getBaseContext(), "gagal hapus, " + e.toString(),Toast.LENGTH_LONG).show();
        }
    }//Akhir void hapusTable()
    protected void kosongkanField(){
        eNm.setText("");
        enpm.setText("");
        ekode.setText("");
        eTb.setText("");
        eBb.setText("");
        eRiwayat.setText("");
    }

    protected void updateTable() {
        while (tabel4data.getChildCount() > 1) {
            tabel4data.removeViewAt(1);
        }
        ArrayList<ArrayList<Object>> data = dm.ambilSemuaBaris();
        for (int posisi = 0; posisi < data.size(); posisi++) {
            TableRow tabelBaris = new TableRow(this);
            ArrayList<Object> baris = data.get(posisi);

            TextView idTxt = new TextView(this);
            idTxt.setTextSize(18);
            idTxt.setGravity(Gravity.START);
            idTxt.setText(" "+baris.get(0).toString()+". ");
            tabelBaris.addView(idTxt);

            TextView namaTxt = new TextView(this);
            namaTxt.setTextSize(18);
            namaTxt.setGravity(Gravity.CENTER);
            namaTxt.setText(baris.get(1).toString());
            tabelBaris.addView(namaTxt);

            TextView npmTxt = new TextView(this);
            npmTxt.setTextSize(18);
            npmTxt.setGravity(Gravity.CENTER);
            npmTxt.setText(baris.get(2).toString());
            tabelBaris.addView(npmTxt);

            TextView tbTxt = new TextView(this);
            tbTxt.setTextSize(18);
            tbTxt.setGravity(Gravity.CENTER);
            tbTxt.setText(baris.get(3).toString());
            tabelBaris.addView(tbTxt);

            TextView bbTxt = new TextView(this);
            bbTxt.setTextSize(18);
            bbTxt.setGravity(Gravity.CENTER);
            bbTxt.setText(baris.get(4).toString());
            tabelBaris.addView(bbTxt);

            TextView riwayatTxt = new TextView(this);
            riwayatTxt.setTextSize(18);
            riwayatTxt.setGravity(Gravity.CENTER);
            riwayatTxt.setText(baris.get(5).toString());
            tabelBaris.addView(riwayatTxt);

            tabel4data.addView(tabelBaris);
        }
    } //Akhir void updateTable
}//Akhir Class MainActivity