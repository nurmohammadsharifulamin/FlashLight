package com.example.flashlight;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {

    public boolean btn = false;
    String cameraid;
    LottieAnimationView lottieAnimationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lottieAnimationView = findViewById(R.id.light_animation_Id);
        lottieAnimationView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if (btn){



                    try {
                        CameraManager cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);
                        cameraid = cameraManager.getCameraIdList()[0];
                        cameraManager.setTorchMode(cameraid,false);
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }



                    lottieAnimationView.setMinAndMaxProgress(0.5f,1.0f);
                    lottieAnimationView.playAnimation();
                    btn = false;
                }else {






                    try {
                        CameraManager cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);
                        cameraid = cameraManager.getCameraIdList()[0];
                        cameraManager.setTorchMode(cameraid,true);
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }



                    lottieAnimationView.setMinAndMaxProgress(0.0f,0.5f);
                    lottieAnimationView.playAnimation();
                    btn = true;
                }
            }
        });
    }
}