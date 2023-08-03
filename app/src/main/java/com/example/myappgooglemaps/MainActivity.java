package com.example.myappgooglemaps;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener{

    GoogleMap mapa;
    List<LatLng> lista;
    PolylineOptions linea;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager()
                        .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        lista = new ArrayList<>();
        /*linea = new PolylineOptions();
        linea.width(8);
        linea.color(Color.RED);
        mapa.addPolyline(linea);*/
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapa = googleMap;
        //Ya seta conectado el mapa
        mapa.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mapa.getUiSettings().setZoomControlsEnabled(true);
        //Mover mapa a una ubicacion
        //mapa.getUiSettings().setZoomControlsEnabled(true);

        /*
        CameraUpdate camUpd1 = CameraUpdateFactory
                        .newLatLngZoom(new LatLng(
                                40.689838118503765, -74.04504323453487),
                                18);
        mapa.moveCamera(camUpd1);
        */

        LatLng madrid = new LatLng(40.689838118503765, -74.04504323453487);
        CameraPosition camPos = new CameraPosition.Builder()
                .target(madrid)
                .zoom(19)
                .bearing(45) //noreste arriba
                .tilt(70) //punto de vista de la cámara 70 grados
                .build();
        CameraUpdate camUpd3 =
                CameraUpdateFactory.newCameraPosition(camPos);
        mapa.animateCamera(camUpd3);
        mapa.setOnMapClickListener(this);

    }


    @Override
    public void onMapClick(LatLng latLng) {

        LatLng punto = new LatLng(latLng.latitude, latLng.longitude);
        MarkerOptions marcador = new MarkerOptions();
        marcador.position(latLng);
        marcador.title("punto");
        //mapa.addMarker(new MarkerOptions().position(punto) .title("Marker in Sydney"));
        mapa.addMarker(marcador);

        lista.add(latLng);
        if (lista.size()==6){
            PolylineOptions lineas = new
                    PolylineOptions()
                    .add(new LatLng(lista.get(0).latitude, lista.get(0).longitude))
                    .add(new LatLng(lista.get(1).latitude, lista.get(1).longitude))
                    .add(new LatLng(lista.get(2).latitude, lista.get(2).longitude))
                    .add(new LatLng(lista.get(3).latitude, lista.get(3).longitude))
                    .add(new LatLng(lista.get(4).latitude, lista.get(4).longitude))
                    .add(new LatLng(lista.get(5).latitude, lista.get(5).longitude))
                    .add(new LatLng(lista.get(0).latitude, lista.get(0).longitude));
            lineas.width(8);
            lineas.color(Color.RED);
            mapa.addPolyline(lineas);
            lista.clear();
        }

/*
        linea.add(latLng);
        if (linea.getPoints().size()==6){
            linea.add(linea.getPoints().get(0));
            mapa.addPolyline(linea);
            linea.getPoints().clear();
        }
        */
    }
}