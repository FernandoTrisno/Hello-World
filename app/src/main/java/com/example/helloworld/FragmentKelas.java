package com.example.helloworld;

import android.app.NotificationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentKelas extends Fragment {
    private static final String TAG = FragmentKelas.class.getSimpleName();
    Button btnNotifTrigger;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView Fragment kelas");
        View view = inflater.inflate(R.layout.fragemen_kelas, container, false);
       /* btnNotifTrigger = view.findViewById(R.id.btnLogin);
        btnNotifTrigger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
        return view;
    }

   /* private void onNotificationBtnClicked() {
        String CHANNEL_ID = "MY_NOTIF_CHANNEL";
        NotificationCompat.Builder builder = new NotificationCompat.Builder(requireContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("My Notification")
                .setContentText("My Notification Content")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(requireContext());
        int notificationID = 1;
        notificationManager.notify(notificationID, builder.build());

    }*/
}
