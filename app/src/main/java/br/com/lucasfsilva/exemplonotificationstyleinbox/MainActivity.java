package br.com.lucasfsilva.exemplonotificationstyleinbox;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import static br.com.lucasfsilva.exemplonotificationstyleinbox.App.CHANNEL_ID_01;
import static br.com.lucasfsilva.exemplonotificationstyleinbox.App.CHANNEL_ID_02;


public class MainActivity extends AppCompatActivity {
    private NotificationManagerCompat notificationManagerCompat;
    private EditText edtTitulo, edtMensagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notificationManagerCompat = NotificationManagerCompat.from(this);

        edtTitulo = (EditText) findViewById(R.id.edtTitulo);
        edtMensagem = (EditText) findViewById(R.id.edtMensagem);

    }

    public void enviarNoChannel01(View view) {
        String titulo = edtTitulo.getText().toString();
        String mensagem = edtMensagem.getText().toString();

        Intent activityIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, activityIntent, 0);

        Intent broadcastIntent = new Intent(this, NoticationReceiver.class);
        broadcastIntent.putExtra("toastMensagem", mensagem);
        PendingIntent actionIntent = PendingIntent.getBroadcast(this, 0, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.agua);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID_01)
                .setSmallIcon(R.drawable.ic_one)
                .setContentTitle(titulo)
                .setContentText(mensagem)
                .setLargeIcon(largeIcon)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(getString(R.string.long_dummy_text))
                        .setBigContentTitle("Título conteúdo grande")
                        .setSummaryText("Sumário do texto"))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setColor(Color.BLUE)
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .addAction(R.mipmap.ic_launcher, "Toast", actionIntent)
                .build();

        notificationManagerCompat.notify(1, notification);
    }

    public void enviarNoChannel02(View view) {
        String titulo = edtTitulo.getText().toString();
        String mensagem = edtMensagem.getText().toString();
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID_02)
                .setSmallIcon(R.drawable.ic_two)
                .setContentTitle(titulo)
                .setContentText(mensagem)
                .setStyle(new NotificationCompat.InboxStyle()
                        .addLine("Esta é a linha 01")
                        .addLine("Esta é a linha 02")
                        .addLine("Esta é a linha 03")
                        .addLine("Esta é a linha 04")
                        .addLine("Esta é a linha 05")
                        .addLine("Esta é a linha 06")
                        .addLine("Esta é a linha 07")
                        .setBigContentTitle("Título conteúdo grande")
                        .setSummaryText("Sumário do texto"))
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .build();

        notificationManagerCompat.notify(2, notification);
    }
}

