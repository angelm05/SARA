package com.example.angela.sara.Fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.angela.sara.R;
import com.example.angela.sara.util.AdaptadorDeMonitor;
import com.example.angela.sara.vo.Monitor;

import java.util.ArrayList;

/**
 * Fragmento que contiene la lista de monitores
 * @author Angela Londono
 * @author Rodrigo Ramirez
 * @author Cristian Agudelo
 * A simple {@link Fragment} subclass.
 */
public class ListaDeMonitoresFragment extends Fragment implements AdaptadorDeMonitor.OnClickAdaptadorDeMonitor{

    /**
     * creación de un AdaptadorDeMonitor
     */
    private AdaptadorDeMonitor adaptador;
    /**
     * creación de un RecyclerView
     */
    private RecyclerView listadoDeMonitores;
    /**
     * creación de un ArrayList<Monitor>
     */
    private ArrayList<Monitor> monitores;
    /**
     * creación de un OnMonitorSeleccionadoListener
     */
    private OnMonitorSeleccionadoListener listener;


    /**
     * Método constructor de la clase ListaDeMonitoresFragment
     */
    public ListaDeMonitoresFragment() {
        // Required empty public constructor
    }

    /**
     * Crea la conexion entre el fragmento y su parte grafica
     */
    public interface OnMonitorSeleccionadoListener {
        void onMonitorSeleccionado(int position);
    }


    /**
     * Método al que se le asigna un layout determinado.
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_de_monitores, container, false);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        adaptador = new AdaptadorDeMonitor(monitores, this);
        listadoDeMonitores = (RecyclerView) getView().findViewById(R.id.listaMonitores);
        listadoDeMonitores.setAdapter(adaptador);
        listadoDeMonitores.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }


    /**
     * Método que permite verifiar que opcion se eleige en el menu
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.cambiar_idioma) {
            Log.i("Menu", "Pulsada cambiar idioma");
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onClickPosition(int pos) {
        listener.onMonitorSeleccionado(pos);
    }

    /**
     * Método para fijar una referencia a la Activity,
     * el cual debe implementar la interfaz OnMonitorSeleccionadoListener,
     * que luego podemos utilizar para pasar objetos del Fragment
     * a la Activity
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity;
        if (context instanceof Activity){
            activity = (Activity) context;
            try {
                listener = (OnMonitorSeleccionadoListener) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString() + " debe implementar la interfaz OnMonitorSeleccionadoListener");
            }
        }
    }

    public void setMonitores(ArrayList<Monitor> monitores) {
        this.monitores = monitores;
    }
}
