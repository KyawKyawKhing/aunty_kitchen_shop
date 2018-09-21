package com.aceplus.hackthon.notification;

/**
 * Created by kyawthetwin on 9/20/18.
 */

public class MyFireBaseMessagingService { /*extends FirebaseMessagingService {

    private int numMessages;
    private FirebaseAuth auth;

    @Override
    public void onCreate() {
        super.onCreate();
        auth = FirebaseAuth.getInstance();
    }

    ArrayList<String> newMessage = new ArrayList<>();

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (auth.getCurrentUser() != null) {
            getNotification(remoteMessage);
        }
    }

    public void getNotification(RemoteMessage message){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.ic_launcher_background);
        builder.setContentTitle(message.getNotification().getTitle());
        builder.setNumber(++numMessages);
        builder.setShowWhen(true);
        builder.setStyle(new android.support.v4.app.NotificationCompat.BigTextStyle().bigText(message.getNotification().getBody()));
        builder.setContentText(message.getNotification().getBody());

        Intent notificationIntent = new Intent(this, MainActivity.class);
        notificationIntent.putExtra("message",message.getNotification().getBody());
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (manager != null) {
            manager.notify(0, builder.build());
        }
    }*/
}
