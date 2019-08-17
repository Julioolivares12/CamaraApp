package com.julio.camaraapp.vistas;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.julio.camaraapp.Adaptador.AdaptadorFotos;
import com.julio.camaraapp.Foto;
import com.julio.camaraapp.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class FotosFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    private RecyclerView lv_fotosList;

    public FotosFragment() {
        // Required empty public constructor
    }


    public static FotosFragment newInstance(String param1, String param2) {
        FotosFragment fragment = new FotosFragment();
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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fotos, container, false);
        lv_fotosList = view.findViewById(R.id.lv_fotosList);


        List<Foto> list = new ArrayList<>();//arreglo donde se almacenara la lista de imagenes
        String path =getActivity().getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)+ File.separator;//obtiene la ruta donde estan las imagenes de app

        File f = new File(path); //abre un archivo con la ruta obtenida
        File[]files = f.listFiles(); //crea un array con las imagenes

        //recorro el array de archivos y creo un objeto de tipo foto
        for (int i =0; i<files.length;i++){
            Foto foto = new Foto();
            foto.setNombre(files[i].getName());
            foto.setPath(Uri.fromFile(files[i]));
            list.add(foto);//agrego el objeto a la lista
        }
        AdaptadorFotos adaptadorFotos = new AdaptadorFotos(list);//creo un adaptador personalizado y le paso la lista
       /*
       con listview
       *  ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                getActivity().getApplicationContext(),
                android.R.layout.simple_list_item_1,
                list
        );
*/
        //lv_fotosList.setAdapter();


        lv_fotosList.setHasFixedSize(true);
        lv_fotosList.setLayoutManager(new GridLayoutManager(getContext(),2));//muesta recycler como gridview
        lv_fotosList.setAdapter(adaptadorFotos);//establece el adaptador personalizado
        return view;
    }


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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
