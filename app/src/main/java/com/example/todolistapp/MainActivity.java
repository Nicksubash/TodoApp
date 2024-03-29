package com.example.todolistapp;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.appcompat.app.AlertDialog;
import  android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import  android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    Button add;
    AlertDialog dialog;
    LinearLayout layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        add=findViewById(R.id.btn);
        layout=findViewById(R.id.container);

        buildDialog();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }

        });

        public void buildDialog() {
            AlertDialog.Builder builder= new AlertDialog.Builder(this);
            View view= getLayoutInflater().inflate(R.layout.dialog, null);

            final EditText name= view.findViewById(R.id.nameEdit);
            builder.setView(view);
            builder.setTitle("Enter your tasks").setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    addCard(name.getText().toString());
                }
            }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    System.out.println("Deleting);
                }
            });
                    dialog=builder.create();

        }
        private void addCard(String name){
            final View view=getLayoutInflater().inflate(R.layout.card,null);


            TestView nameView= view.findViewById(R.id.name);
            Button delete=view.findViewById(R.id.btn_del);
            nameView.setText(name);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    layout.removeView(view);
                }
            });
            layout.addView(view);

        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }
    protected void sendNotification(){
        Button button;
        button=findViewById(R.id.btn_notifig);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
    public void makeNotifcation(){
        String ChanelID= "CHANNEL_ID_NOTIFICATION";
        NotificationCompat.Builder builder= new NotificationCompat.Builder(getApplicationContext(),ChanelID);
        builder.setSmallIcon(R.drawable.icon_circle_notifications_24);
        builder.setContentTitle("Notificaiton Title");
        builder.setContentText("Notification  will shown here");
        builder.setAutoCancel(true);
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Intent intent= new Intent(getApplicationContext(),Notification.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("data", "Some vale will be pass here");

        PendingIntent pendingIntent=PendingIntent.getActivity(getApplicationContext(),0,intent,PendingIntent.FLAG_MUTABLE);
        builder.setContentIntent(pendingIntent);
        NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if(android.as.Build.VERSION.SDK>INT>= android.as.Build.VERSION.CODES.O){
            NotificationChannel notificationChannel=
                    notificationManager.getNotificationChannel(chanel);       }

    }
}