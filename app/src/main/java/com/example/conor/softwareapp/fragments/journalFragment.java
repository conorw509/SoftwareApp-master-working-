package com.example.conor.softwareapp.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.conor.softwareapp.R;
import com.example.conor.softwareapp.adapters.journalAdapter;
import com.example.conor.softwareapp.adapters.usersAdapter;
import com.example.conor.softwareapp.model.User;
import com.example.conor.softwareapp.model.chatList;
import com.example.conor.softwareapp.model.journalContent;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link journalFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link journalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class journalFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RecyclerView recyclerView;
    private List<journalContent> journalContents;
    private FirebaseUser firebaseUser;
    private DatabaseReference reference;
    //    private List<chatList> userList;
    private journalAdapter journalAdapter;
    private List<User> mUser;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public journalFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment journalFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static journalFragment newInstance(String param1, String param2) {
        journalFragment fragment = new journalFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_journal, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewJ);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setVerticalScrollBarEnabled(true);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        journalContents = new ArrayList<>();
        mUser = new ArrayList<>();


        reference = FirebaseDatabase.getInstance().getReference();

        Query orderByDate = reference.child("journalEntries").orderByChild("date");

        orderByDate.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                journalContents.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    journalContent chatList = snapshot.getValue(journalContent.class);
                    if(chatList.getId().equals(firebaseUser.getUid())) {
                        journalContents.add(chatList);
                    }
                }
                journalAdapter = new journalAdapter(getContext(), journalContents);
                recyclerView.setAdapter(journalAdapter);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

//        reference = FirebaseDatabase.getInstance().getReference().child("journalEntries");
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                journalContents.clear();
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    journalContent chatList = snapshot.getValue(journalContent.class);
//                    journalContents.add(chatList);
//                }
//                journalAdapter = new journalAdapter(getContext(), journalContents);
//                recyclerView.setAdapter(journalAdapter);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

        return view;
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
//
//    private void chatLists() {
//        reference = FirebaseDatabase.getInstance().getReference("Users");
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    User user = snapshot.getValue(User.class);
//                    for (chatList chatList : userList) {
//                        if (user.getId().equals(chatList.getId())) {
//                            showEntries();
//                        }
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//    }

//    private void showEntries() {
//        journalContents = new ArrayList<>();
//        reference = FirebaseDatabase.getInstance().getReference("journalEntries");
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    journalContent user = snapshot.getValue(journalContent.class);
//                    journalContents.add(user);
//                }
//                journalAdapter = new journalAdapter(getContext(), journalContents);
//                recyclerView.setAdapter(journalAdapter);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//    }


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
