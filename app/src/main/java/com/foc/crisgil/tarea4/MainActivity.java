package com.foc.crisgil.tarea4;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.foc.crisgil.tarea4.model.Alumno;
import com.foc.crisgil.tarea4.repositorio.Repositorio;

public class MainActivity extends AppCompatActivity {

    private EditText txNombre,txEdad,txApellido;
    private Repositorio repositorio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        repositorio=new Repositorio(this);

        txNombre=findViewById(R.id.txtNombre);
        txApellido=findViewById(R.id.txtApellidos);
        txEdad=findViewById(R.id.txtEdad);

        Button btnAdd=findViewById(R.id.btnNuevo);
        Button btnList=findViewById(R.id.btnListado);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addAlumno();
            }
        });

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, ListaActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });

    }

    private void addAlumno(){

        String errores="";

        String nombre=txNombre.getText().toString();
        String apellidos=txApellido.getText().toString();
        Integer edad=null;
        try{
            edad=Integer.parseInt(txEdad.getText().toString());
        }
        catch (Exception e){

        }



        if(nombre.isEmpty()){
            errores+="El nombre esta vacio \n";

        }
        if(apellidos.isEmpty()){
            errores+="El apellido esta vacio \n";

        }
        if(edad==null || edad<0){
            errores+="La edad es incorrecta \n";

        }

        if(! errores.equals("")){

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(errores)
                    .setTitle("Error!!!!!");
            AlertDialog dialog = builder.create();
            dialog.show();
            return;

        }

        Alumno al=new Alumno("",nombre,apellidos,edad);
        repositorio.addAlumno(al);

        txNombre.setText("");
        txApellido.setText("");
        txEdad.setText("0");

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Alumno creado con exito")
                .setTitle("Correcto");
        AlertDialog dialog = builder.create();
        dialog.show();
        return;


    }

}
