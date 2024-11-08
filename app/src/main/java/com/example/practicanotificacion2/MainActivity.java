package com.example.practicanotificacion2;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button boton1;
    static final String CANAL_ID = "canalutch";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel canal = new NotificationChannel(
                    CANAL_ID,
                    "Ejemplo",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            canal.setDescription("Canal de ejemplo");
            notificationManager.createNotificationChannel(canal);
        }
        boton1 = (Button)findViewById(R.id.boton1);
        String texto = "Clase del lunes 4 de noviembre";

        Drawable drawable = ResourcesCompat.getDrawable(getResources(),R.drawable.solid, null);
        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
        Bitmap iconoLargo = bitmapDrawable.getBitmap();

        Drawable drawable1 = ResourcesCompat.getDrawable(getResources(),R.drawable.utchjpg, null);
        BitmapDrawable bitmapDrawable1 = (BitmapDrawable) drawable1;
        Bitmap utch = bitmapDrawable1.getBitmap();

        boton1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://developer.android.com/develop/ui/views/notifications/build-notification?hl=es-419#java"));
                PendingIntent p1 = PendingIntent.getActivity(MainActivity.this,0,intent,PendingIntent.FLAG_IMMUTABLE);

                NotificationCompat.Builder notif = new NotificationCompat.Builder(MainActivity.this,CANAL_ID)
                        .setSmallIcon(R.drawable.icon1)
                        .setLargeIcon(iconoLargo)
                        .setContentTitle("Lanucz")
                        .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(utch))
                        .addAction(R.drawable.icon2,"Visitar",p1)
                        .setContentText(texto);
                notificationManager.notify(100,notif.build());

            }
        });
    }
}