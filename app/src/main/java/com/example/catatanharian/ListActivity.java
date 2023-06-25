package com.example.catatanharian;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    private MyListAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        myAdapter = new MyListAdapter(namaFile -> hapusFile(namaFile));

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(myAdapter);


    }

    void hapusFile(String namaFile){
        File file = new File(getFilesDir(), namaFile);
        if (file.exists()) file.delete();
        }


    @Override
    protected void onStart() {
        super.onStart();
        //biar update data terus
        myAdapter.setData(getData());
    }

    private ArrayList<Catatan> getData(){
        ArrayList<Catatan> data = new ArrayList<>();
        // mengambil data dari storage
        File f = new File(getFilesDir().toString());
        File[] file = f.listFiles();
        for(File catatan : file){
            data.add(bacaFile(catatan));
        }
        return data;
    }

    private Catatan bacaFile(File file){
            StringBuilder text = new StringBuilder();
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = br.readLine();
                while (line != null){
                    //untuk nampilin
                    text.append(line);
                    line = br.readLine();
                }
                br.close();
            } catch (IOException e){
                System.out.println("Error "+e.getMessage());
            }
            return new Catatan(file.getName(), text.toString());
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_tambah){
            Intent intent = new Intent(this, CatatanActivity.class);
            startActivity(intent);
            return true;
        }
        return false;
    }
}