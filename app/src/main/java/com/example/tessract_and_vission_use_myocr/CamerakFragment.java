package com.example.tessract_and_vission_use_myocr;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class CamerakFragment extends Fragment {

    private static final int CAMER_REQUEST_CODE =1 ;
    private View view;
    private Uri image_uri;
    private Button camerabutton;

    public CamerakFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_camerak, container, false);
        camerabutton=view.findViewById(R.id.camerabuttonid);
        camerabutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA)==PackageManager.PERMISSION_GRANTED)
                {
//            ContentValues values=new ContentValues();
//            values.put(MediaStore.Images.Media.TITLE,"new pic");
//            values.put(MediaStore.Images.Media.DESCRIPTION,"image to text");




                    Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent,CAMER_REQUEST_CODE);
                }
                else {
                    Toast.makeText(getContext(), "please permision access", Toast.LENGTH_SHORT).show();
                }


            }
        });



        return view;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==CAMER_REQUEST_CODE)
        {
            if (resultCode==RESULT_OK)
            {

                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .start((Activity) getContext());



            }
        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
               Uri resultUri = result.getUri();





            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }

    }
}
