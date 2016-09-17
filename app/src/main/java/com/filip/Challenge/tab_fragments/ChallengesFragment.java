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
import com.filip.Challenge.io.network_communication.GetListChallenges;

import java.util.LinkedList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ChallengesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ChallengesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChallengesFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    private View challengesFragmentView;
    private GetListChallenges getListChallenges;
    private SwipeRefreshLayout swipeRefreshLayout;

    public ChallengesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
     * @return A new instance of fragment ChallengesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChallengesFragment newInstance() {
        ChallengesFragment fragment = new ChallengesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        Live challenge view-ovi se prikazuju u Now tabu
        challengesFragmentView = inflater.inflate(R.layout.fragment_challenges, container, false);

        swipeRefreshLayout = (SwipeRefreshLayout) challengesFragmentView.findViewById(R.id.swipeContainerList);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            getListChallenges = new GetListChallenges(this, getContext());
            clearUI(R.id.mainContentChallenges);
            getListChallenges.execute();
            swipeRefreshLayout.setRefreshing(false);
        });

        getListChallenges = new GetListChallenges(this, getContext());
        getListChallenges.execute();

        return challengesFragmentView;
    }

    public void populateUI(LinkedList<View> listChallengesViews) {
        LinearLayout listChallengesContainer = (LinearLayout) this.getView().findViewById(R.id.mainContentChallenges);

        for (int i = 0; i < listChallengesViews.size(); i++) {
            listChallengesContainer.addView(listChallengesViews.get(i));
        }
    }

    public void clearUI(int id) {
        LinearLayout liveChallengesContainer = (LinearLayout) this.getView().findViewById(id);
        liveChallengesContainer.removeAllViews();
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
