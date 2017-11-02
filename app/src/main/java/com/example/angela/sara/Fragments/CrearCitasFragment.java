package com.example.angela.sara.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract.Events;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.angela.sara.R;

/**
 * @author Angela Londono
 * @author Rodrigo Ramirez
 * @author Cristian Agudelo
 * A simple {@link Fragment} subclass.
 */
public class CrearCitasFragment extends Fragment {

    /**
     * creación de un Button
     */
    private Button btnCrearCita;
    /**
     * creación de un ImageButton
     */
    private ImageButton btnImagen;

    /**
     * Contructor vacio de la clase CrearCitaFragment
     */
    public CrearCitasFragment() {
        // Required empty public constructor
    }


    /**
     * Metodo que permite inicar el fragment_crear_cita
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_crear_citas, container, false);

        Button btnCrearCita = (Button) view.findViewById(R.id.btn_agregar_cita);
        btnCrearCita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarMensaje(getResources().getString(R.string.msg_btn_crear_cita));
            }
        });

        /**
         * configuración del btnImagen
         */
        btnImagen = (ImageButton) view.findViewById(R.id.button_horario_cita);
        btnImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_INSERT);
                intent.setType("vnd.android.cursor.item/event");

                intent.putExtra(Events.TITLE, "Monitor 1");
                intent.putExtra(Events.DESCRIPTION,  getResources().getString(R.string.msg_calendario_estado));
                intent.putExtra(Events.EVENT_LOCATION, getResources().getString(R.string.msg_universidad));
                intent.putExtra(Events.RRULE, "FREQ=YEARLY");

                startActivity(intent);
            }
        }); //utiliza el onclick listener global


        // Inflate the layout for this fragment
        return view;
    }


    /**
     * Método para mostrar un mensaje en un evento de botón
     *
     * @param message
     */
    public void mostrarMensaje(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
