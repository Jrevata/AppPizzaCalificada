package com.jrevata.apppizzacalificada;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class DeliveryActivity extends AppCompatActivity {

    private CheckBox checkBox;
    private Spinner spinner1;
    private RadioGroup radioGroup;
    private Double precio = 0.0;
    private String masa = "";
    private String pizza = "";
    private Double precioComplemento = 0.0;
    private EditText editTextDireccion;
    private Double total = 0.0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);

        checkBox = (CheckBox) findViewById(R.id.checkBox);
        radioGroup =  (RadioGroup) findViewById(R.id.radioGroup);
        spinner1 = (Spinner) findViewById(R.id.spinner);
        editTextDireccion = (EditText) findViewById(R.id.direccion);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                if(parent.getItemAtPosition(pos).toString().equals("Americana")){
                    precio = 38.0;
                    pizza = "Americana";
                }
                else if(parent.getItemAtPosition(pos).toString().equals("Peperoni")){
                    precio = 42.0;
                    pizza = "Peperoni";
                }
                else if(parent.getItemAtPosition(pos).toString().equals("Hawaiana")){
                    precio = 36.0;
                    pizza = "Hawaiana";
                }
                else if(parent.getItemAtPosition(pos).toString().equals("Meat Lover")){
                    precio = 56.0;
                    pizza = "Meat Lover";
                }
                Toast.makeText(parent.getContext(),
                        "La pizza sola cuesta: " + precio,
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg) {

            }

        });


    }



    public void radioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        // This check which radio button was clicked
        switch (view.getId()) {
            case R.id.radioButton1:
                if (checked)
                    masa = "Masa Gruesa";
                    //Toast.makeText(getApplicationContext(), masa, Toast.LENGTH_SHORT).show();
                break;

            case R.id.radioButton2:
                masa = "Masa Delgada";
                //Toast.makeText(getApplicationContext(), masa, Toast.LENGTH_SHORT).show();
                break;

            case R.id.radioButton3:
                masa = "Masa Artesanal";
                //Toast.makeText(getApplicationContext(), masa, Toast.LENGTH_SHORT).show();
                break;


        }
    }


    public void androidCheckBoxClicked(View view) {
        // action for checkbox click
        switch (view.getId()) {
            case R.id.checkBox:
                CheckBox checkBox = (CheckBox) view;
                if(checkBox.isChecked()){
                    precioComplemento = precioComplemento + 4.0;


                }else{
                    if(precioComplemento != 0){
                        precioComplemento = precioComplemento - 4.0;
                    }

                }
                Toast.makeText(this, precioComplemento.toString(), Toast.LENGTH_LONG).show();
                break;
            case R.id.checkBox2:
                CheckBox checkBox2 = (CheckBox) view;
                if(checkBox2.isChecked()){
                    precioComplemento = precioComplemento + 8.0;


                }else{
                    if(precioComplemento != 0){
                        precioComplemento = precioComplemento - 8.0;
                    }

                }
                Toast.makeText(this, precioComplemento.toString(), Toast.LENGTH_LONG).show();
                break;

        }
    }


    public void ordenarPedido(View view){
        total = precio + precioComplemento;

        Toast.makeText(this, total.toString(), Toast.LENGTH_LONG).show();
        new AlertDialog.Builder(this)
                .setTitle("Antes de continuar")
                .setMessage("Su pedido de " + pizza + " con "  + masa + " a " + total + " IGV está en proceso de envío" )
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        Toast.makeText(DeliveryActivity.this, "Su envío se está procesando", Toast.LENGTH_SHORT).show();


                    }})
                .setNegativeButton(android.R.string.no, null).show();

        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        // Notification
        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("Pizza Revata")
                .setContentText("Servida")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setColor(ContextCompat.getColor(this, R.color.colorPrimary))
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();


        // Notification manager
        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(10000, notification);
    }

}



