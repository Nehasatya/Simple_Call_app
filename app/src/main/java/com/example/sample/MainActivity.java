package com.example.sample;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    EditText txt_phone_number;
    Button call_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        txt_phone_number = (EditText) findViewById(R.id.txt_number);
        call_btn = (Button) findViewById(R.id.call_btn);

        call_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_call= new Intent(Intent.ACTION_CALL);
                String number= txt_phone_number.getText().toString();

                if(number.trim().isEmpty()){
                    Toast.makeText(MainActivity.this, "Please Enter Your Number", Toast.LENGTH_SHORT).show();
                }
                else{
                    intent_call.setData(Uri.parse("tel:"+number));
                }
                if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(MainActivity.this,"Please Grant Permission",Toast.LENGTH_SHORT).show();
                    requestPermission();
                }
                else
                {
                    startActivity(intent_call);
                }

            }
        });


    }
    private void requestPermission(){

        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE},1);
    }
}