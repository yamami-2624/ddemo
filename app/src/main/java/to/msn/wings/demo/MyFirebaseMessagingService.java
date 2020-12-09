package to.msn.wings.demo;

import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import kotlinx.coroutines.Job;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "【Firebaseテスト中】";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.d(TAG, "From: " + remoteMessage.getFrom());


        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            //Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
            String title = remoteMessage.getNotification().getTitle();
            String message = remoteMessage.getNotification().getBody();

            Log.d(TAG, "Title:  " + title);
            Log.d(TAG,"Body:  " + message);
        }

        super.onMessageReceived(remoteMessage);
        showNotification(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());
    }

    public void showNotification(String title,String message){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"MyNotification")
                .setContentTitle(title)
                .setSmallIcon(R.drawable.ic_demo_background)
                .setAutoCancel(true)
                .setContentText(message);

        NotificationManagerCompat manager = NotificationManagerCompat.from(this);
        manager.notify(999,builder.build());
    }
}
