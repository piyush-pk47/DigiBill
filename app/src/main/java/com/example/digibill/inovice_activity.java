package com.example.digibill;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.graphics.pdf.PdfDocument;
import android.os.Environment;
import android.os.StrictMode;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;


import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.example.digibill.MainActivity.cartList;

public class inovice_activity extends AppCompatActivity {
    Bitmap bmp,scaleable;

    Date dateObj;
    DateFormat dateFormat;
    int pageWidth=1200;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        DialogPlus dialog = DialogPlus.newDialog(this)
                .setGravity(Gravity.CENTER)
                .setMargin(50,0,50,0)
                .setContentHolder(new ViewHolder(R.layout.contactinfo))
                .setExpanded(false)  // This will enable the expand feature, (similar to android L share dialog)
                .create();

        View currview=dialog.getHolderView();
        EditText nameTxt,phoneTxt;
        nameTxt=currview.findViewById(R.id.nameEdit);
        phoneTxt=currview.findViewById(R.id.phoneEdit);
        Button contact=currview.findViewById(R.id.contactInfoBtn);


        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateObj = new Date();
                bmp = BitmapFactory.decodeResource(getResources(),R.drawable.paymentback);
                scaleable = Bitmap.createScaledBitmap(bmp,1200,470,false);
                final user cur=new user();
                //firebase data processing
                FirebaseDatabase db=FirebaseDatabase.getInstance();
                DatabaseReference root=db.getReference().child("main").child(MainActivity.key+"personal");
                root.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot dataSnapshot:snapshot.getChildren()) {
                            final user u= dataSnapshot.getValue(user.class);
                            cur.setName(u.getName());
                            cur.setInvoice(u.getInvoice());
                            cur.setMobile_number(u.getMobile_number());
                        }


                        ActivityCompat.requestPermissions(inovice_activity.this,new String[]{
                                Manifest.permission.WRITE_EXTERNAL_STORAGE , Manifest.permission.READ_EXTERNAL_STORAGE
                        }, PackageManager.PERMISSION_GRANTED);

                        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                        StrictMode.setVmPolicy(builder.build());

                        PdfDocument myPdf = new PdfDocument();
                        Paint namePaint = new Paint();
                        Paint titlePaint = new Paint();

                        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(pageWidth, 2010, 1).create();
                        PdfDocument.Page myPage1 = myPdf.startPage(pageInfo);
                        Canvas canvas = myPage1.getCanvas();

                        canvas.drawBitmap(scaleable,0,0,namePaint);

                        titlePaint.setTextAlign(Paint.Align.CENTER);
                        titlePaint.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.BOLD_ITALIC));
                        titlePaint.setTextSize(70);


                        canvas.drawText(cur.getName(),pageWidth/2,230,titlePaint);

                        namePaint.setColor(Color.rgb(255,13,13));
                        namePaint.setTextSize(30f);
                        namePaint.setTextAlign(Paint.Align.RIGHT);
                        canvas.drawText("Call: "+cur.getMobile_number(),pageWidth-60,40,namePaint);

                        namePaint.setTextAlign(Paint.Align.LEFT);
                        namePaint.setTextSize(35f);
                        namePaint.setColor(Color.BLACK);
                        canvas.drawText("Customer Name: "+ nameTxt.getText().toString(),20,590,namePaint);
                        canvas.drawText("Contact No: "+ phoneTxt.getText().toString(),20,640,namePaint);

                        namePaint.setTextAlign(Paint.Align.RIGHT);
                        canvas.drawText("Invoice No: "+cur.getInvoice(),pageWidth-20,590,namePaint);

                        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        canvas.drawText("Date: " + dateFormat.format(dateObj),pageWidth-20,640,namePaint);

                        namePaint.setStyle(Paint.Style.STROKE);
                        namePaint.setStrokeWidth(2);
                        canvas.drawRect(20,780,pageWidth-20,860,namePaint);

                        namePaint.setTextAlign(Paint.Align.LEFT);
                        namePaint.setStyle(Paint.Style.FILL);

                        canvas.drawText("Sr. No.",40,830,namePaint);
                        canvas.drawText("Item Description",200,830,namePaint);
                        canvas.drawText("Price",700,830,namePaint);
                        canvas.drawText("Qty.",900,830,namePaint);
                        canvas.drawText("Total",1050,830,namePaint);

                        canvas.drawLine(180,790,180,850,namePaint);
                        canvas.drawLine(680,790,680,850,namePaint);
                        canvas.drawLine(880,790,880,850,namePaint);
                        canvas.drawLine(1030,790,1030,850,namePaint);

                        int n = cartList.size();
                        String nstr = String.valueOf(n);

                        int[] total = new int[n];
                        int startHeight = 950;
                        for(int i=0;i < n;i++) {
                            canvas.drawText(Integer.toString(i+1) + ".", 40, startHeight, namePaint);
                            canvas.drawText(cartList.get(i).getName(), 200, startHeight, namePaint);
                            canvas.drawText(cartList.get(i).getPrice(), 700, startHeight, namePaint);
                            canvas.drawText(cartList.get(i).getQuantity(), 900, startHeight, namePaint);
                            total[i] = Integer.parseInt(cartList.get(i).getQuantity()) * Integer.parseInt(cartList.get(i).getPrice());
                            namePaint.setTextAlign(Paint.Align.RIGHT);
                            canvas.drawText(String.valueOf(total[i]), pageWidth - 40,startHeight,namePaint);
                            namePaint.setTextAlign(Paint.Align.LEFT);
                            startHeight += 100;
                        }

                        int grandTotal = 0;
                        for(int i=0;i<n;i++) {
                            grandTotal += total[i];
                        }

                        startHeight += 150;


                        canvas.drawLine(680,startHeight,pageWidth-20,startHeight,namePaint);
                        startHeight += 50;
                        canvas.drawText("Sub total",700,startHeight,namePaint);
                        canvas.drawText(":",900,startHeight,namePaint);
                        namePaint.setTextAlign(Paint.Align.RIGHT);
                        canvas.drawText(String.valueOf(grandTotal),pageWidth-40,startHeight,namePaint);

                        startHeight += 50;
                        namePaint.setTextAlign(Paint.Align.LEFT);
                        canvas.drawText("GST(6%)",700,startHeight,namePaint);
                        canvas.drawText(":",900,startHeight,namePaint);
                        namePaint.setTextAlign(Paint.Align.RIGHT);
                        canvas.drawText(String.valueOf(grandTotal*6/100.00),pageWidth-40,startHeight,namePaint);

                        startHeight += 50; // 1350
                        namePaint.setTextAlign(Paint.Align.LEFT);
                        namePaint.setColor(Color.rgb(247,147,30));
                        canvas.drawRect(680,startHeight,pageWidth-20,startHeight+100,namePaint);
                        namePaint.setColor(Color.BLACK);
                        namePaint.setTextSize(50f);
                        namePaint.setTextAlign(Paint.Align.LEFT);
                        canvas.drawText("Total",700,startHeight + 65 ,namePaint);
                        namePaint.setTextAlign(Paint.Align.RIGHT);
                        canvas.drawText(String.valueOf(grandTotal + (grandTotal*6)/100),pageWidth-40,startHeight + 65 ,namePaint);

                        myPdf.finishPage(myPage1);

                        String baseDir = Environment.getExternalStorageDirectory().getAbsolutePath();
                        String fileName = cur.getInvoice()+".pdf";

                        File f = new File(getExternalFilesDir(null)+"/"+fileName);

                        try {
                            myPdf.writeTo(new FileOutputStream(f));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        myPdf.close();

//        File outputFile = new File(Environment.getExternalStoragePublicDirectory
//                (Environment.DIRECTORY_DOWNLOADS), "InvoiceForWA.pdf");
//        Uri uri = Uri.fromFile(outputFile);


                        Intent share = new Intent(Intent.ACTION_SEND);
                        share.setType("application/pdf");
                        share.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://" + f));
                        startActivity(Intent.createChooser(share,"Share the file..."));


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });


        dialog.dismiss();
        dialog.show();




    }
}