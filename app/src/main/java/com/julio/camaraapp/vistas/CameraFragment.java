package com.julio.camaraapp.vistas;


import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.camerakit.CameraKitView;
import com.julio.camaraapp.R;

import java.io.File;
import java.io.FileOutputStream;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class CameraFragment extends Fragment {

   private CameraKitView cameraKitView;

    public CameraFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_camera, container, false);
        cameraKitView = view.findViewById(R.id.camera);
        Button btnTakePhoto = view.findViewById(R.id.btnTakePhoto);

        btnTakePhoto.setOnClickListener(photoClickListener);
        return view;
    }

    //listener para el boton
    private View.OnClickListener photoClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            cameraKitView.captureImage(new CameraKitView.ImageCallback() {
                @Override
                public void onImage(CameraKitView cameraKitView, byte[] foto) {

                    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                    String imageFileName = "JPEG_"+ timeStamp+ "_.jpg";
                    File storageDir = getActivity().getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);//establece un directorio par fotos
                    File savedFoto = new File(storageDir,imageFileName);

                   // Environment.getExternalStorageDirectory();
                    try{
                       /*
                       *  File imagen = File.createTempFile(
                                imageFileName,".jpg",
                                storageDir
                        );*/
                        FileOutputStream outputStream = new FileOutputStream(savedFoto.getPath());
                        outputStream.write(foto);//guarda la foto en la carpeta de app
                        outputStream.close();
                    }catch (java.io.IOException e){
                        e.printStackTrace();
                    }
                }
            });
        }
    };
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        cameraKitView.onRequestPermissionsResult(requestCode,permissions,grantResults);
    }

    @Override
    public void onStart() {
        super.onStart();
        cameraKitView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        cameraKitView.onStop();
    }

    @Override
    public void onPause() {
        super.onPause();
        cameraKitView.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        cameraKitView.onResume();
    }


}
