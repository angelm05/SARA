package com.example.angela.sara.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.angela.sara.R;

/**
 * Fragmento que contiene la lista de monitores
 * @author Angela Londono
 * @author Rodrigo Ramirez
 * @author Cristian Agudelo
 * A simple {@link Fragment} subclass.
 */
public class ListaDeMonitoresFragment extends Fragment {


    public ListaDeMonitoresFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lista_de_monitores, container, false);
    }

}
