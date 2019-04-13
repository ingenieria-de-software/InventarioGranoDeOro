package com.example.inventariogranodeoro.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inventariogranodeoro.DAO.ArticuloDAO;
import com.example.inventariogranodeoro.Entidades.Articulo;
import com.example.inventariogranodeoro.R;

/* ****************************************************
 *                                                    *
 *   ACTIVIDAD PARA CONSULTAR PRODUCTOS POR NOMBRE    *
 *                                                    *
 ******************************************************/
public class ConsultaNombreActivity extends Activity{

    String text;
    ArticuloDAO consultaNombre;
    Articulo articulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_nombre);
        initComponents();
    }


    public void onClickConsultar(View view) {
        text = edtNombre.getText().toString();
        if(text.matches("")){                                           //Si campo esta vacio, agregar texto no valido
            text = "lol";
        }
        articulo = consultaNombre.consultaNombre(text);
            if(articulo == null){                                             //Si el resultado del query esta vacio
                btnAgregar.setEnabled(false);
                txtId.setText("Código: ");
                txtNombre.setText("Nombre: ");
                Toast.makeText(getApplicationContext(),"El articulo: "+text+"\nNo esta disponible", Toast.LENGTH_LONG).show();
            }else{
                txtId.setText("Código: " + articulo.getIdProducto());
                txtNombre.setText("Nombre: " + articulo.getNombre());
                btnAgregar.setEnabled(true);
            }
    }

    /* Metodo para regresar los datos al Acitvity anterior en este caso UsuarioActivity */
    public void onClickAgregar(View view) {
        articulo.setExistencia(Float.valueOf(np.getValue()));
        Intent intent = new Intent();
        intent.putExtra("ARTICULO", articulo);
        setResult(RESULT_OK,intent);
        finish();
    }

    private void initComponents() {
        consultaNombre = new ArticuloDAO();
        edtNombre = findViewById(R.id.edtnProducto);
        btnConsulta = findViewById(R.id.btnConsultar);
        btnAgregar = findViewById(R.id.btnAgregar);
        txtId = findViewById(R.id.txtId);
        txtNombre = findViewById(R.id.txtNombre);
        np = findViewById(R.id.np);
        np.setMinValue(1);
        np.setMaxValue(20);
        np.setWrapSelectorWheel(true);

    }

    private EditText edtNombre;
    private TextView txtId;
    private TextView txtNombre;
    private Button btnConsulta;
    private Button btnAgregar;
    private NumberPicker np;
}