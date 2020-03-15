package com.example.tessract_and_vission_use_myocr;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class GallerylayoutfragmentFragment extends Fragment {
    private static final int GALLARY_REQUEST_CODE = 1;
    private View view;
    private ImageView imageView;
    private Button choseimage;



    public GallerylayoutfragmentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_gallerylayoutfragment, container, false);
        imageView=view.findViewById(R.id.imageid);
        choseimage=view.findViewById(R.id.chosebuttonid);

        choseimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED)
                {
                    Intent intent=new Intent();
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/*");
                    startActivityForResult(intent,GALLARY_REQUEST_CODE);

                }
                else {
                    Toast.makeText(getActivity(), "permision nedded", Toast.LENGTH_SHORT).show();
                }

            }
        });


        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==GALLARY_REQUEST_CODE )
        {
            if (resultCode==RESULT_OK)
            {
                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .start((Activity) getContext());
            }
            if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE)
            {
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                if (resultCode == RESULT_OK) {
                    Uri resultUri = result.getUri();
                    Picasso.with(getContext()).load(resultUri).into(imageView);
                } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                    Exception error = result.getError();
                }
            }
        }


    }
}
