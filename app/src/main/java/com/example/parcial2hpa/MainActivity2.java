package com.example.parcial2hpa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;import java.text.DecimalFormat;
import androidx.core.content.ContextCompat;



public class MainActivity2 extends AppCompatActivity {
    //declarar controles
    ImageView Peliculafinal;
    EditText ETcant;
    TextView NombreApellido,Edad,Total;
    ImageButton BtnAtras;
    CheckBox chGTR,chSX,ch,chFF;
    Button Calcular;
    double precio,total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //recibe los valores del otro activity
        String nombrePersona=getIntent().getExtras().getString("Nombre");;//recibe nombre
        String apellidoPersona=getIntent().getExtras().getString("Apellido");//rcibe apellido
        int anioDif=getIntent().getIntExtra("Anio", 0);//pasa el valor dle anioNacimiento
        int mesDif=getIntent().getIntExtra("Mes", 0);//pasa el valor dle anioNacimiento
        int diaDif=getIntent().getIntExtra("Dia", 0);//pasa el valor dle anioNacimiento

       //declaración y identificación de controles
        ETcant=(EditText)findViewById(R.id.ETcantidad);
        Peliculafinal=(ImageView)findViewById(R.id.peliculafinal);
        NombreApellido=(TextView) findViewById(R.id.TvNombreYApellido);
        Total=(TextView)findViewById(R.id.TvPago);
        Calcular=(Button)findViewById(R.id.BtnCalcular);
        Total=(TextView)findViewById(R.id.TvPago);
        BtnAtras=(ImageButton)findViewById(R.id.btnAtras);
        chGTR=(CheckBox)findViewById(R.id.CBGranturismo);
        chSX=(CheckBox)findViewById(R.id.CBSawx);
        chFF=(CheckBox)findViewById(R.id.CBFiven);
        NombreApellido.setText(nombrePersona+" "+apellidoPersona); //muestra en pantalla
        Edad=(TextView)findViewById(R.id.Tvedad);//muestra en pantalla
        Edad.setText("Edad: "+anioDif+" años: "+mesDif+" meses: "+diaDif+" dias");//muestra en pantalla

        //al presionar el boton calcular
        Calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int cantidad = Integer.parseInt(ETcant.getText().toString());
                total=precio*cantidad;
                //para solo mostrar 2 digitos despues del . decimal
                DecimalFormat decimalFormat = new DecimalFormat("0.00");
                String totalFormateado = decimalFormat.format(total);

                Total.setText("Pagar: $"+totalFormateado);

            }
        });



        BtnAtras.setOnClickListener(new View.OnClickListener() {//si le das click regresa a la main activity
            @Override
            public void onClick(View view) {
                Intent packageContext;
                Intent intent1= new Intent(MainActivity2.this, MainActivity.class);//regresa a la actividad anterior, osea a la main
                startActivity(intent1);
            }
        });
        chGTR.setOnClickListener(new View.OnClickListener() {//cuando se hace click en la opcion se quitan los otros
            @Override
            public void onClick(View view) {
                if(chGTR.isChecked())
                {
                    chFF.setChecked(false);
                    chSX.setChecked(false);
                    precio=2.99;
                    Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.onesheet);
                    Peliculafinal.setImageDrawable(drawable);

                }
            }
        });
        chFF.setOnClickListener(new View.OnClickListener() {//cuando se hace click en la opcion se quitan los otros
            @Override
            public void onClick(View view) {
                if(chFF.isChecked())
                {
                    chGTR.setChecked(false);
                    chSX.setChecked(false);
                    precio=5.99;
                    Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.mv5bnwi3zmy4nmitmgq4my00odjllwjlntktyjk2nzrkodu3ytnlxkeyxkfqcgdeqxvymtkxnjuynq____v1_fmjpg_ux1000_);
                    Peliculafinal.setImageDrawable(drawable);
                }
            }
        });
        chSX.setOnClickListener(new View.OnClickListener() {//cuando se hace click en la opcion se quitan los otros
            @Override
            public void onClick(View view) {
                if(chSX.isChecked())
                {
                    chFF.setChecked(false);
                    chGTR.setChecked(false);
                    precio=3.99;
                    Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.mv5bmmjhyjbkmzgtzgiwmc00ytazlwe4ntqtyzvkndvmyjizodi0xkeyxkfqcgdeqxvyodqxmti4mjm___v1_fmjpg_ux1000_);
                    Peliculafinal.setImageDrawable(drawable);
                }
            }
        });

    }


}