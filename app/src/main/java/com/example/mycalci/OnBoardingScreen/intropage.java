
    package com.example.mycalci.OnBoardingScreen;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.mycalci.MainActivity;
import com.example.mycalci.R;

    public class intropage extends AppCompatActivity {


        private ViewPager viewPager;
        private LinearLayout mDotsLayout;

        private TextView[] mDots;

        private SliderAdapterIntro sliderAdapterIntro;


        Button back,next_finish;
        int currentPage;

        @SuppressLint("ResourceType")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_intropage);
    //        getSupportActionBar().hide();
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

            viewPager=findViewById(R.id.viewpager);
            mDotsLayout=findViewById(R.id.dontslinear_layout);
            back=findViewById(R.id.backBtn);
            next_finish=findViewById(R.id.next_finishBtn);
            sliderAdapterIntro=new SliderAdapterIntro(this);
            viewPager.setAdapter(sliderAdapterIntro);
            addDotsIndicator(0);
            viewPager.addOnPageChangeListener(viewListner);





            //onclick
            next_finish.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (currentPage==mDots.length-1){
                        //redirect to Main Screen and save in shredphrence that user completed intro view
                        SharedPreferences.Editor editor;
                        editor= PreferenceManager.getDefaultSharedPreferences(intropage.this).edit();
                        editor.putString("isIntroShow", "1");//1 value will note as user as seen intro
                        editor.apply();
                        startActivity(new Intent(intropage.this, MainActivity.class));
                    }
                    else {
                        viewPager.setCurrentItem(currentPage + 1);
                    }
                }
            });
            //onbackclick
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewPager.setCurrentItem(currentPage-1);
                }
            });

            //permission

                /*to notification
                new AlertDialog.Builder(this,4)
                        .setTitle("Notification Permission")
                        .setMessage("Allow Notification permission To get notify ")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent settingsIntent = new Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS)
                                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                        .putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName())
                                        .putExtra(Settings.EXTRA_CHANNEL_ID, R.string.NEWS_CHANNEL_ID);
                                startActivity(settingsIntent);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .create()
                        .show();

                 */
            // return;

        }
         /*
            //request permission
            Dexter.withActivity(this).withPermission(Manifest.permission.CAMERA)
                    .withListener(new PermissionListener() {
                        @Override
                        public void onPermissionGranted(PermissionGrantedResponse response) {


                        }

                        @Override
                        public void onPermissionDenied(PermissionDeniedResponse response) {

                            Toast.makeText(introPage.this, "acces deined", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

                        }
                    }).check();

            //phone permission
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                new AlertDialog.Builder(this,4)
                        .setTitle("Required Location Permission")
                        .setMessage("You have to give this permission to acess this feature")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ActivityCompat.requestPermissions(introPage.this,
                                        new String[]{Manifest.permission.CALL_PHONE},
                                        1);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .create()
                        .show();
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                //return;
            }

    */



        public void addDotsIndicator(int position){
            mDots=new TextView[sliderAdapterIntro.getCount()];
            mDotsLayout.removeAllViews();
            for (int i=0;i<mDots.length;i++){
                mDots[i]=new TextView(this);
                mDots[i].setText(Html.fromHtml("&#8226"));
                mDots[i].setTextSize(25f);
                mDots[i].setTextColor(getResources().getColor(R.color.purple_200));
                mDotsLayout.addView(mDots[i]);
            }
            if(mDots.length>0){
                mDots[position].setTextColor(getResources().getColor(R.color.purple_200));
            }
        }

        ViewPager.OnPageChangeListener viewListner=new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int i) {
                addDotsIndicator(i);
                currentPage=i;

                if(i==0){
                    next_finish.setEnabled(true);
                    back.setEnabled(false);
                    back.setVisibility(View.INVISIBLE);

                    next_finish.setText("Next");
                    back.setText("");
                }
                else if(i==mDots.length-1){
                    next_finish.setEnabled(true);
                    back.setEnabled(true);
                    back.setVisibility(View.VISIBLE);

                    next_finish.setText("Finish");
                    back.setText("Back");
                }
                else {
                    next_finish.setEnabled(true);
                    back.setEnabled(true);
                    back.setVisibility(View.VISIBLE);

                    next_finish.setText("Next");
                    back.setText("Back");

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };

        @Override
        protected void onStart() {
            super.onStart();
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);


    //        final FirebaseAuth mAuth=FirebaseAuth.getInstance();
            String introShow=prefs.getString("isIntroShow","");

            if(introShow.equals("")) {
                //  Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
            }
            else {
                //will exicute this if user already shown this intro and it will redirect to mainscreen
                startActivity(new Intent(intropage.this, MainActivity.class));

            }


        }





    }
