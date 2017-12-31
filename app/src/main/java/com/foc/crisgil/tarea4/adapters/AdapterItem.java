package com.foc.crisgil.tarea4.adapters;



        import android.app.Activity;
        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.ImageView;
        import android.widget.TextView;

        import com.foc.crisgil.tarea4.R;
        import com.foc.crisgil.tarea4.model.Alumno;

        import java.util.ArrayList;


public class AdapterItem extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<Alumno> items;

    public AdapterItem (Activity activity, ArrayList<Alumno> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    public void clear() {
        items.clear();
    }

    public void addAll(ArrayList<Alumno> category) {
        for (int i = 0; i < category.size(); i++) {
            items.add(category.get(i));
        }
    }

    @Override
    public Object getItem(int arg0) {
        return items.get(arg0);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.layout_lista, null);
        }

        Alumno dir = items.get(position);

        TextView cabecera = v.findViewById(R.id.txtCabecera);
        cabecera.setText(dir.getNombre() + " "+dir.getApellidos());

        TextView edad =  v.findViewById(R.id.txtPie);
        edad.setText("Edad "+dir.getEdad()+" años");



        return v;
    }
}
