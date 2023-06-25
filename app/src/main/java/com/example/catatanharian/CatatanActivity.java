package com.example.catatanharian;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;

public class CatatanActivity extends AppCompatActivity {

    EditText etNamaFile, etCatatan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catatan);
        etNamaFile = findViewById(R.id.etNamaFile);
        etCatatan = findViewById(R.id.etCatatan);
        final Button btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(view -> simpanData());
    }
    private void simpanData(){
        String namaFile = etNamaFile.getText().toString();
        if (TextUtils.isEmpty(namaFile)){
            Toast.makeText(this, "Nama File tidak boleh kosong!", Toast.LENGTH_SHORT).show();
            return;
        }

        String catatan = etCatatan.getText().toString();
        if (TextUtils.isEmpty(catatan)){
            Toast.makeText(this, "Catatan tidak boleh kosong!", Toast.LENGTH_SHORT).show();
            return;
        }

        buatFile(namaFile, catatan);
        finish();
    }

    private void buatFile(String namaFile, String catatan){
        java.io.File file = new File(getFilesDir(), namaFile);
        FileOutputStream outputStream;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file, true);
            outputStream.write(catatan.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}