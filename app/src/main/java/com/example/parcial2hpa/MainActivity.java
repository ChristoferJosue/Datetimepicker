package com.example.parcial2hpa;
//@Christofer autor

import androidx.appcompat.app.AppCompatActivity;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.Period;

import android.content.Intent;
import android.widget.ImageButton;
import android.widget.Toast;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
//declaración de controles
    Button datePicker1,GuardarDatos;
    ImageButton BtnSiguiente,BtnSalir;


    EditText EtNombre,EtApellido;

    //declaración de variables globales
    int edad;
    int anioPersona,mesPersona,diaPersona;
    String nombrePersona,apellidoPersona,fechaNacimiento;
    int anioDif = 0,mesDif=0,diaDif=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //recibe el valor de todos los controles utilizados
        datePicker1=findViewById(R.id.BtnDatePicker);
        GuardarDatos=(Button) findViewById(R.id.Guardar);
        BtnSiguiente=(ImageButton) findViewById(R.id.BtnSiguiente);
        EtNombre=(EditText)findViewById(R.id.EtNombre);
        EtApellido=(EditText)findViewById(R.id.EtApellido);
        BtnSalir=(ImageButton) findViewById(R.id.btnSalir);



        BtnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //finaliza todo
                finishAffinity();
            }
        });


        datePicker1.setOnClickListener(new View.OnClickListener() {//se acciona al hacer click en la fecha de nacimiento
            @Override
            public void onClick(View view) {
              FechaInicial();
            }
        });
        BtnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //para pasar a la otra actividad
                Intent PackageContext;
                Intent btnsig=new Intent(MainActivity.this, MainActivity2.class);
                btnsig.putExtra("Nombre", nombrePersona);//pasa el valor del nombre
                btnsig.putExtra("Apellido", apellidoPersona);//pasa el valor del apellido
                btnsig.putExtra("Anio", anioDif);//pasa el valor de fecha de nacimiento
                btnsig.putExtra("Mes", mesDif);//pasa el valor de fecha de nacimiento
                btnsig.putExtra("Dia", diaDif);//pasa el valor de fecha de nacimiento

                startActivity(btnsig);
            }
        });
        GuardarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GuardarDatosPersona();
                if (nombrePersona.isEmpty() || apellidoPersona.isEmpty())//si esta vacio despliega este ,mensaje
                {
                    Toast.makeText(MainActivity.this,"El Nombre o el apellido estan vacios ",Toast.LENGTH_SHORT).show();


                }
                else{
                    CalcularEdad();
                }


            }
        });

    }
    private void FechaInicial(){
       //
        DatePickerDialog dialog= new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                //guardar los datos que la persona puso en variables para su posterior uso
                anioPersona=year;
                mesPersona=month+1;
                diaPersona=day;
                datePicker1.setText(String.valueOf(day)+"/"+String.valueOf(month+1)+"/"+String.valueOf(year));//texto que se va mostrar ;
            }
        }, 2023, 9, 28);
        dialog.show();
    }
    public void GuardarDatosPersona()
    {
        //guardar datos de las personas
        nombrePersona=EtNombre.getText().toString();
        apellidoPersona=EtApellido.getText().toString();
        fechaNacimiento=datePicker1.getText().toString();

    }

    private void CalcularEdad() {
        //guardando fecha actual para su posterior comparació\
        LocalDate fechaActual = LocalDate.now();

        int anioActual = fechaActual.getYear();
        int mesActual = fechaActual.getMonthValue();
        int diaActual = fechaActual.getDayOfMonth();
      // Toast.makeText(MainActivity.this,diaActual+"/"+mesActual+"/"+anioActual,Toast.LENGTH_SHORT).show();//muestra la fecha actual

        //comparando fechas actuales con la fecha de nacimiento
        LocalDate fechaNacimiento = LocalDate.of(anioPersona, mesPersona, diaPersona);

        //comparación de fechas
        Period periodo = Period.between(fechaNacimiento, fechaActual);
         anioDif = periodo.getYears();
         mesDif = periodo.getMonths();
         diaDif = periodo.getDays();

        //Toast.makeText(MainActivity.this,anioDif+"/"+mesDif+"/"+diaDif,Toast.LENGTH_SHORT).show();//muestra la fecha actual
        if(anioDif>=18) {//si los añós de diferencia son mas grandes que 18 automaticamente es mayor de edad
            Toast.makeText(MainActivity.this, "PUEDES CONTINUAR, ERES MAYOR DE EDAD, CLICK SIGUIENTE", Toast.LENGTH_LONG).show();//muestra la fecha actual
            BtnSiguiente.setVisibility(View.VISIBLE); //Poner visible la  imagen

            }
        else {//si es menor que 18
            BtnSiguiente.setVisibility(View.INVISIBLE); //Poner invisible la  imagen
            Toast.makeText(MainActivity.this, "NO PUEDES CONTINUAR, ERES MENOR DE EDAD", Toast.LENGTH_SHORT).show();//muestra la fecha actual

        }




    }

}