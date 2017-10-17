package com.example.who.dcangs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class LihatMap extends android.support.v4.app.Fragment implements OnMapReadyCallback{

    GoogleMap mGoogleMap;
    MapView mMapView;
    View mView;

    public LihatMap(){

    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.activity_lihat_map, container, false);


        return mView;
    }

    @Override
    public void onViewCreated(View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mMapView = (MapView) mView.findViewById(R.id.mapid);
        if(mMapView != null){
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mGoogleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        googleMap.addMarker(new MarkerOptions().position(new LatLng(-7.277970, 112.792204))
                .title("Warung Outlet 1 Sistem Informasi").snippet("Silahkan pesan disini"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(-7.283178, 112.786242))
                .title("Warung Outlet 2 Gebang").snippet("Silahkan pesan disini"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(-7.288700, 112.791919))
                .title("Warung Outlet 3 Asrama Mahasiswa ITS").snippet("Silahkan pesan disini"));

        CameraPosition outlet1 = CameraPosition.builder().target(new LatLng(-7.277970, 112.792204)).zoom(15).bearing(0).tilt(45).build();
        CameraPosition outlet2 = CameraPosition.builder().target(new LatLng(-7.283178, 112.786242)).zoom(15).bearing(0).tilt(45).build();
        CameraPosition outlet3 = CameraPosition.builder().target(new LatLng(-7.288700, 112.791919)).zoom(15).bearing(0).tilt(45).build();

        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(outlet3));

    }
}
