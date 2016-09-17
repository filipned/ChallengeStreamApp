package com.filip.Challenge.tab_fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.filip.Challenge.R;
import com.filip.Challenge.io.network_communication.GetLiveChallenges;

import java.util.LinkedList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NowFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NowFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NowFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    private View nowFragmentView;
    private GetLiveChallenges getLiveChallenges;
    private SwipeRefreshLayout swipeRefreshLayout;

    public NowFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment NowFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NowFragment newInstance() {
        NowFragment fragment = new NowFragment();

        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        nowFragmentView = inflater.inflate(R.layout.fragment_now, container, false);

        swipeRefreshLayout = (SwipeRefreshLayout) nowFragmentView.findViewById(R.id.swipeContainerLive);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            getLiveChallenges = new GetLiveChallenges(this, getContext());
            clearUI(R.id.mainContentNow);
            getLiveChallenges.execute();
            swipeRefreshLayout.setRefreshing(false);

        });


        getLiveChallenges = new GetLiveChallenges(this, getContext());
        getLiveChallenges.execute();

        return nowFragmentView;
    }

    public void populateUI(LinkedList<View> liveChallengesViews) {

        LinearLayout liveChallengesContainer = (LinearLayout) this.getView().findViewById(R.id.mainContentNow);

        for (int i = 0; i < liveChallengesViews.size(); i++) {
            liveChallengesContainer.addView(liveChallengesViews.get(i));
        }
    }

    public void clearUI(int id) {
        LinearLayout liveChallengesContainer = (LinearLayout) this.getView().findViewById(id);
        liveChallengesContainer.removeAllViews();
    }


//    private Dialog drawStreamDialog() {
//        Context context =  getContext();
//        Dialog streamDialog = new Dialog(context);
//        streamDialog.setTitle("Stream this challenge?");
//        streamDialog.setContentView(R.layout.stream_dialog);
//        streamDialog.show();
//        return streamDialog;
//    }

//    Ova metoda otvara stream view
//public void openStreamDialog(View view) {
//    Dialog streamDialog = drawStreamDialog();
//
//    Button streamNow = (Button) streamDialog.findViewById(R.id.streamDialogStreamNow);
//    Button cancel = (Button) streamDialog.findViewById(R.id.streamDialogCancel);
//
//    streamNow.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
////              SLANJE PODATAKA
//
////                OVAKO UZIMAMO CHALLENGE NAME
////                TextView textView = (TextView) ((ViewGroup) view).getChildAt(0);
////                textView.getText();
//        }
//    });
//
//    cancel.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            streamDialog.cancel();
//        }
//    });
//}


    @Override
    public void onStop() {
        super.onStop();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
