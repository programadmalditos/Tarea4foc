package com.foc.crisgil.tarea4.repositorio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.foc.crisgil.tarea4.model.Alumno;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luisgillamaignere on 31/12/17.
 */

public class Repositorio extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "Alumnos";

    // Contacts table name
    private static final String TABLE_NAME = "alumno";


    public Repositorio(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ALUMNOS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + "id INTEGER PRIMARY KEY, nombre TEXT, apellido TEXT, edad INTEGER"
                + ")";
        db.execSQL(CREATE_ALUMNOS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public void addAlumno(Alumno alumno) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nombre", alumno.getNombre()); // Contact Name
        values.put("apellido", alumno.getApellidos()); // Contact Name
        values.put("edad", alumno.getEdad()); // Contact Name

        // Inserting Row
        db.insert(TABLE_NAME, null, values);
        db.close(); // Closing database connection
    }

    public List<Alumno> getAllAlumnos() {
        List<Alumno> alumnosList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Alumno alumno = new Alumno();
                alumno.setId(cursor.getString(0));
                alumno.setNombre(cursor.getString(1));
                alumno.setApellidos(cursor.getString(2));
                alumno.setEdad(cursor.getInt(3));
                // Adding contact to list
                alumnosList.add(alumno);
            } while (cursor.moveToNext());
        }

        // return contact list
        return alumnosList;
    }

}