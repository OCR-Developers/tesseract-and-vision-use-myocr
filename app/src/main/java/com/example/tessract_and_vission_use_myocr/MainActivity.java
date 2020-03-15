package com.example.tessract_and_vission_use_myocr;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.karan.churi.PermissionManager.PermissionManager;
import com.theartofdev.edmodo.cropper.CropImage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private MeowBottomNavigation bottomNavigation;
    private RelativeLayout relativeLayout;
    private PermissionManager permission;


    private final static int ID_HOME = 1;
    private final static int ID_GALLERY = 2;
    private final static int ID_CAMERA = 3;
    private final static int ID_NOTIFICATION = 4;
    private final static int ID_HELP = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bottomNavigation=findViewById(R.id.meobotomnavid);
        relativeLayout=findViewById(R.id.relativeid);


        permission=new PermissionManager() {};
        permission.checkAndRequestPermissions(this);
//        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED)
//        {
//            Intent intent=new Intent();
//            intent.setAction(Intent.ACTION_GET_CONTENT);
//            intent.setType("image/*");
//            startActivityForResult(intent,1);

        //PERSONAL
//
//        }






        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        bottomNavigation.add(new MeowBottomNavigation.Model(ID_HOME, R.drawable.ic_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_GALLERY, R.drawable.ic_gallery));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_CAMERA, R.drawable.ic_camera));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_NOTIFICATION, R.drawable.ic_notifications));


        bottomNavigation.setCount(ID_NOTIFICATION, "115");


        getSupportFragmentManager().beginTransaction().replace(R.id.frameid,new HomelayoutfragmentFragment()).commit();


        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                Toast.makeText(MainActivity.this, "clicked item : " + item.getId(), Toast.LENGTH_SHORT).show();

                String name;


                switch (item.getId()) {
                    case ID_HOME:
                        name = "HOME";

                        break;
                    case ID_GALLERY:
                        name = "GALLERY";

                        break;
                    case ID_CAMERA:
                        name = "CAMERA";


                        break;
                    case ID_NOTIFICATION:
                    {
                        name = "NOTIFICATION";


                    }
                    default:
                    {
                        name = "";
                    }

                }
            }
        });




        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener()
        {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Toast.makeText(MainActivity.this, "showing item : " + item.getId(), Toast.LENGTH_SHORT).show();

                String name;
                Fragment fragment=null;

                switch (item.getId()) {
                    case ID_HOME:
                        name = "HOME";
                        fragment=new HomelayoutfragmentFragment();
                        break;
                    case ID_GALLERY:
                        name = "GALLERY";
                        fragment=new GallerylayoutfragmentFragment();
                        break;
                    case ID_CAMERA:
                        name = "CAMERA";
                        fragment=new CamerakFragment();

                        break;
                    case ID_NOTIFICATION:
                    {
                        name = "NOTIFICATION";
                        fragment=new NotificationFragment();

                    }
                    default:
                    {
                        name = "";
                    }







                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frameid,fragment).commit();

                //tvSelected.setText(getString(R.string.main_page_selected, name));
            }
        });

        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                Toast.makeText(MainActivity.this, "reselected item : " + item.getId(), Toast.LENGTH_SHORT).show();



            }
        });



        bottomNavigation.setCount(ID_NOTIFICATION, "5");

        bottomNavigation.show(ID_NOTIFICATION,true);









    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        ArrayList<String> granted=permission.getStatus().get(0).granted;
        ArrayList<String> denied=permission.getStatus().get(0).denied;
    }



    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
