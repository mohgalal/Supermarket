package com.example.supermarketloop;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    Button okBtn;
    EditText produEt;
    TextView subtotalTv,percdiscTv,discvalueTv,disctotalTv,taxperecTv,taxvalueTv,finaltotalTv,deliveryTv;
    Switch delivery;
    double sum=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            delivery= findViewById(R.id.delivary_sw);
            deliveryTv=findViewById(R.id.delivary_tv);
            subtotalTv = findViewById(R.id.subtotal_tv);
            percdiscTv = findViewById(R.id.percdisc_tv);
            discvalueTv = findViewById(R.id.discvalue_tv);
            disctotalTv = findViewById(R.id.disctotal_tv);
            taxperecTv = findViewById(R.id.taxperec_tv);
            taxvalueTv = findViewById(R.id.taxvalue_tv);
            finaltotalTv = findViewById(R.id.finaltotal_tv);
            okBtn = findViewById(R.id.ok_btn);
            produEt = findViewById(R.id.produ_et);


            okBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (produEt.getText().toString().equals(""))
                        produEt.setError("Please Inter No of Product");
                    else {
                        int product = Integer.parseInt(produEt.getText().toString());
                        for (int i = 1; i <= product; i++) {
                            //السطر ده عشان اعمل layout تظهر على الشاشة
                            LayoutInflater lf = LayoutInflater.from(MainActivity.this);
                            // خزنت هنا الكلام ده في View لان ال Alertdialog لايتعامل مع Layout
                            View prompet = lf.inflate(R.layout.input_daylog, null);
                            final AlertDialog.Builder ad = new AlertDialog.Builder(MainActivity.this);

                            final EditText quntEt = prompet.findViewById(R.id.qunt_et);
                            final EditText priceEt = prompet.findViewById(R.id.price_et);

                            ad.setView(prompet);
                            ad.setCancelable(false);
                            ad.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @SuppressLint("SetTextI18n")
                                @Override
                                public void onClick(DialogInterface dialog, int which) {


                                    double q, p, subsum,percedisc, discvalue,disctotal,taxvalue,tdelivery,finaltotal;
                                  String p1, q1;
                                   final double taxperec=0.14;
                                    DecimalFormat df =new DecimalFormat("00.00");
                                    p1 =priceEt.getText().toString();
                                    q1 =quntEt.getText().toString();
                                    if (p1.isEmpty()||q1.isEmpty()){
                                        Toast.makeText(MainActivity.this, "please set the value", Toast.LENGTH_SHORT).show();
                                    }else {
                                    q = Double.parseDouble(q1);
                                    p = Double.parseDouble(p1);

                                        subsum = q * p;
                                        sum += subsum;
                                        subtotalTv.setText("subtotal" + sum);
                                        if (sum < 500)
                                            percedisc = 0;
                                        else if (sum < 1000)
                                            percedisc = 0.10;
                                        else if (sum < 1500)
                                            percedisc = 0.20;
                                        else
                                            percedisc = 0.25;
                                        percdiscTv.setText("perdisc:" + percedisc * 100 + "%");
                                        discvalue = sum * percedisc;
                                        discvalueTv.setText("discv:" + discvalue);
                                        disctotal = sum - discvalue;
                                        disctotalTv.setText("disct:" + disctotal);
                                        taxperecTv.setText("taxp:" + df.format(taxperec * 100) + "%");
                                        taxvalue = disctotal * taxperec;
                                        taxvalueTv.setText("taxv:" + df.format(taxvalue));
                                        if (delivery.isChecked())
                                            deliveryTv.setText("30");
                                        else
                                            deliveryTv.setText("0");
                                        tdelivery = Double.parseDouble(deliveryTv.getText().toString());
                                        finaltotal = disctotal + taxvalue + tdelivery;
                                        finaltotalTv.setText("final:" + df.format(finaltotal));


                                    }
                                }
                            });

                            ad.create();
                            ad.show();
                            }
                    }
                }
            });

    } }
