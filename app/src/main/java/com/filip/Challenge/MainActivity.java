package com.filip.Challenge;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.login.LoginManager;
import com.filip.Challenge.io.network_communication.PostChallenge;
import com.filip.Challenge.io.network_communication.PostChallengeLiveItem;
import com.filip.Challenge.tab_fragments.ChallengesFragment;
import com.filip.Challenge.tab_fragments.NowFragment;
import com.filip.Challenge.tab_fragments.UpcomingFragment;

import model.ChallengeListItem;
import model.User;


public class MainActivity extends AppCompatActivity implements NowFragment.OnFragmentInteractionListener,
        UpcomingFragment.OnFragmentInteractionListener, ChallengesFragment.OnFragmentInteractionListener{

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    private View viewItem;
    private String challengeName;

    public static User user;
    private ChallengeListItem listItem;
    private PostChallengeLiveItem postChallengeLiveItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setOffscreenPageLimit(3);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        user = (User) getIntent().getSerializableExtra("User");


        FloatingActionButton logOut = (FloatingActionButton) findViewById(R.id.fab);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LoginManager.getInstance().logOut();
                Intent logInIntent = new Intent(getApplicationContext(), AndroidFacebookActivity.class);
                startActivity(logInIntent);
                finish();
            }
        });

    }



    //    Metoda za dodavanje izazova
    public void addChallenge(View view) {

        final Dialog dialog = drawAddChallengeDialog();

        Button submit = (Button) dialog.findViewById(R.id.buttonSubmitAddChallenge);
        Button cancel = (Button) dialog.findViewById(R.id.buttonCancelAddChallenge);

        final EditText text = (EditText) dialog.findViewById(R.id.editTextChallenge);

        submit.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                String challenge = text.getText().toString();
                if (!challenge.isEmpty()) {
                    listItem = new ChallengeListItem(challenge);
//                    postoji mogucnost greske na listItem
                    PostChallenge postChallenge = new PostChallenge(listItem, getApplicationContext());
                    postChallenge.execute();

                    dialog.cancel();
                } else {
                    text.setHint("Type challenge name");
                }
            }
        });
        cancel.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
    }

//  Metoda za otpocinjanje strimovanja
    public void openStreamDialog(View view, Context context) {
        Dialog streamDialog = drawStreamDialog(context);
        viewItem = view;

        Button streamNow = (Button) streamDialog.findViewById(R.id.streamDialogStreamNow);
        Button cancel = (Button) streamDialog.findViewById(R.id.streamDialogCancel);

        streamNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView textView = (TextView) ((ViewGroup) viewItem).getChildAt(0);
                challengeName = textView.getText().toString();

//                postChallengeLiveItem = new PostChallengeLiveItem(user, challengeName, streamDialog, getApplicationContext());
//                postChallengeLiveItem.execute();


            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                streamDialog.cancel();
            }
        });
    }

    private Dialog drawAddChallengeDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setTitle("Add challenge");
        dialog.setContentView(R.layout.add_challenge);
        dialog.show();
        return dialog;
    }

    private Dialog drawStreamDialog(Context context) {
        Dialog streamDialog = new Dialog(context);
        streamDialog.setTitle("Stream this challenge?");
        streamDialog.setContentView(R.layout.stream_dialog);
        streamDialog.show();
        return streamDialog;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a ChallengesFragment (defined as a static inner class below).
            switch(position) {
                case 0:
                    return NowFragment.newInstance();
                case 1:
                    return UpcomingFragment.newInstance();
                case 2:
                    return ChallengesFragment.newInstance();
                default:
                    return NowFragment.newInstance();
            }

        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Now";
                case 1:
                    return "Upcoming";
                case 2:
                    return "Challenges";
            }
            return null;
        }
    }
}
