package com.example.gre.Fragments;

import android.app.ProgressDialog;
import android.location.GnssAntennaInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gre.Adapters.MeaningAdapter;
import com.example.gre.Adapters.PhoneticsAdapter;
import com.example.gre.Models.APIResponse;
import com.example.gre.OnFetchDataListener;
import com.example.gre.R;
import com.example.gre.RequestManager;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SecondFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SecondFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    SearchView searchView;
    PhoneticsAdapter phoneticsAdapter;
    MeaningAdapter meaningAdapter;
    TextView textView_word;
    RecyclerView recyclerView_phonetics;
    ProgressDialog progressDialog;
    RecyclerView recyclerView_meanings;

    public SecondFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Second_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SecondFragment newInstance(String param1, String param2) {
        SecondFragment fragment = new SecondFragment();
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
            mParam2 = getArguments().getString(ARG_PARAM2);}

            searchView = getView().findViewById(R.id.search_view);
            textView_word=getView().findViewById(R.id.textView_word);
            recyclerView_phonetics=getView().findViewById(R.id.recycler_phonetics);
            recyclerView_meanings=getView().findViewById(R.id.recycler_meanings);
            ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("loading");
        progressDialog.show();
        RequestManager manager = new RequestManager(getActivity());
        manager.getWordMeaning(listener,"hello");
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    progressDialog.setTitle("fetching response for"+ query);
                    progressDialog.show();
                    RequestManager manager = new RequestManager(getActivity());
                    manager.getWordMeaning(listener,query);

                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });

    }
    private final OnFetchDataListener listener = new OnFetchDataListener() {
        @Override
        public void onFetchData(APIResponse apiResponse, String message) {
            progressDialog.dismiss();
            if(apiResponse==null){
                Toast.makeText(getContext(),"No data found",Toast.LENGTH_SHORT).show();
                return;
            }
            showData(apiResponse);
        }

        @Override
        public void onError(String message) {
            progressDialog.dismiss();
            Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();

        }
    };

    private void showData(APIResponse apiResponse) {
        textView_word.setText("Word:"+apiResponse.getWord());
        recyclerView_phonetics.setHasFixedSize(true);
        recyclerView_phonetics.setLayoutManager(new GridLayoutManager(getActivity(),1));
        phoneticsAdapter = new PhoneticsAdapter(getActivity(),apiResponse.getPhonetics());
        recyclerView_phonetics.setAdapter(phoneticsAdapter);
        recyclerView_meanings.setHasFixedSize(true);
        recyclerView_meanings.setLayoutManager(new GridLayoutManager(getActivity(),1));
        meaningAdapter = new MeaningAdapter(getActivity(),apiResponse.getMeanings());
        recyclerView_meanings.setAdapter(meaningAdapter);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second_fragment, container, false);


    }
}