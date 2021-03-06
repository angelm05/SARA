package com.example.angela.sara.util;

import android.support.v4.app.Fragment;
import android.util.Log;

import com.example.angela.sara.vo.Cita;
import com.example.angela.sara.vo.Monitor;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;

/**
 * Created by andres on 13/11/17.
 */

public class ManagerFireBase {

    private FirebaseDatabase database;
    private DatabaseReference databaseRef;
    private static ManagerFireBase instancia;
    private Fragment fragment;
    private OnActualizarAdaptadorListener listener;
    private Monitor monitores;
    List<Cita> citas;

    public ManagerFireBase() {
    }

    private ManagerFireBase(Fragment fragment){
        database = FirebaseDatabase.getInstance();
        databaseRef = database.getReference();
        this.fragment = fragment;
        listener = (OnActualizarAdaptadorListener) fragment;
        citas = new ArrayList<Cita>();
    }
    public static ManagerFireBase instanciar(Fragment fragment) {
        if (instancia == null) {
            instancia = new ManagerFireBase(fragment);
        }
        return instancia;
    }

    public void insertarMonitor(Monitor monitor){
        databaseRef.push().setValue(monitor);

    }

    public void agregarCitaMonitor(Cita cita, Monitor monitor){

        ArrayList<Cita> citas = monitor.getCitas();
        citas.add(cita);
        databaseRef.child(monitor.getId()).child("citas").setValue(citas);



        //Map<String, Object> hopperUpdates = new HashMap<String, Object>();
        //hopperUpdates.put("nickname", "Amazing Grace");
        //databaseRef.updateChildren(hopperUpdates);
    }

    public void agregarCitasMonitor(List<Cita> citas, String id){

        //databaseRef.child(id).push().setValue(citas);

        Log.e("Monitor ID", id);


        //Map<String, Object> hopperUpdates = new HashMap<String, Object>();
        //hopperUpdates.put("nickname", "Amazing Grace");
        //databaseRef.updateChildren(hopperUpdates);
    }


    public void escucharEventoFireBase(){

        databaseRef.addChildEventListener(new ChildEventListener()
        {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.v(TAG,"agregado");
                Monitor monitor = dataSnapshot.getValue(Monitor.class);
                monitor.setId(dataSnapshot.getKey());

                listener.actualizarAdaptador(monitor);

            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Log.v(TAG,"cambiado");
                Monitor monitor = dataSnapshot.getValue(Monitor.class);
                monitor.setId(dataSnapshot.getKey());

                listener.actualizarAdaptador(monitor);

            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.v(TAG,"eliminado");
            }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                Log.v(TAG,"moved");
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.v(TAG,"cancelar");
            }
        });
    }

    public interface OnActualizarAdaptadorListener{
        public void actualizarAdaptador(Monitor monitor);
    }

    public void eliminarMonitor(String id){
        databaseRef.child(id).removeValue();
    }

    public static ManagerFireBase getInstancia() {
        return instancia;
    }
}
