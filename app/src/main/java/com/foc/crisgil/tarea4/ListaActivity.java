package com.foc.crisgil.tarea4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.foc.crisgil.tarea4.adapters.AdapterItem;
import com.foc.crisgil.tarea4.model.Alumno;
import com.foc.crisgil.tarea4.repositorio.Repositorio;

import java.util.ArrayList;

public class ListaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        ListView lista=findViewById(R.id.listView);
        Button btnVolver=findViewById(R.id.btnVolver);

        Repositorio repo=new Repositorio(this);

        AdapterItem adapter = new AdapterItem(this, (ArrayList<Alumno>) repo.getAllAlumnos());

        lista.setAdapter(adapter);

btnVolver.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        finish();
    }
});
    }
}
