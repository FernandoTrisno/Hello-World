package com.example.helloworld;

import android.app.NotificationManager;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
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

import static android.content.Context.JOB_SCHEDULER_SERVICE;

public class FragmentKelas extends Fragment {
    private static final String TAG = FragmentKelas.class.getSimpleName();
    Button btnNotifTrigger;
    Button btnMulai = null;
    Button btnBerhenti = null;
    private Button btnCamera;
    private Button btnOrienation;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnMulai = getView().findViewById(R.id.mulai);
        btnBerhenti = getView().findViewById(R.id.berhenti);
        btnCamera = getView().findViewById(R.id.btnCamera);
        btnOrienation = getView().findViewById(R.id.btnOrientation);
        btnMulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scheduleJob(v);
            }
        });
        btnBerhenti.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                cancelJob(v);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView Fragment kelas kiri");
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

    public void scheduleJob(View v) {
        ComponentName componentName = new ComponentName(getActivity(), ContohJobScheduler.class);
        JobInfo info = new JobInfo.Builder(123, componentName)
                .setRequiresCharging(true)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .setPersisted(true)
                .setPeriodic(15 * 60 * 1000)
                .build();
        JobScheduler scheduler = (JobScheduler) getActivity().getSystemService(JOB_SCHEDULER_SERVICE);
        int resultCode = scheduler.schedule(info);
        if (resultCode == JobScheduler.RESULT_SUCCESS) {
            Log.d(TAG, "Job scheduled");
        } else {
            Log.d(TAG, "Job scheduling failed");
        }
    }
    public void cancelJob(View v) {
        JobScheduler scheduler = (JobScheduler) getActivity().getSystemService(JOB_SCHEDULER_SERVICE);
        scheduler.cancel(123);
        Log.d(TAG, "Job cancelled");
    }

}
