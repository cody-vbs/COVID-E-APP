package com.example.corona_emergencyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapView extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    private GoogleMap mMap;
    private ArrayList<LatLng> latLngArrayList = new ArrayList<>();
    private ArrayList<String> countryNames = new ArrayList<>();
    private MarkerOptions options = new MarkerOptions();
    static double lat;
    static double longi;
    private SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_view);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = mMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.style));

            if (!success) {
                Log.e("Mapview", "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e("Mapview", "Can't find style. Error: ", e);
        }
        mMap.setOnMarkerClickListener(MapView.this);
        mMap.animateCamera(CameraUpdateFactory.zoomBy(2));
        latLngArrayList.add(new LatLng(35.86166,104.195397));
        latLngArrayList.add(new LatLng(41.87194,12.56738));
        latLngArrayList.add(new LatLng(37.09024,-95.712891));
        latLngArrayList.add(new LatLng(40.463667,-3.74922));
        latLngArrayList.add(new LatLng(51.165691,10.451526));
        latLngArrayList.add(new LatLng(32.427908,53.688046));
        latLngArrayList.add(new LatLng(46.227638,2.213749));
        latLngArrayList.add(new LatLng(40.339852,127.510093));
        latLngArrayList.add(new LatLng(46.818188,8.227512));
        latLngArrayList.add(new LatLng(55.378051,-3.435973));
        latLngArrayList.add(new LatLng(52.132633,5.291266));
        latLngArrayList.add(new LatLng(-25.274398,133.775136));
        latLngArrayList.add(new LatLng(50.503887,4.469936));
        latLngArrayList.add(new LatLng(60.472024,8.468946));
        latLngArrayList.add(new LatLng(60.128161,18.643501));
        latLngArrayList.add(new LatLng(47.516231,14.550072));
        latLngArrayList.add(new LatLng(39.399872,-8.224454));
        latLngArrayList.add(new LatLng(-14.235004,-51.92528));
        latLngArrayList.add(new LatLng(56.130366,-106.346771));
        latLngArrayList.add(new LatLng(56.26392,9.501785));
        latLngArrayList.add(new LatLng(4.210484,101.975766));
        latLngArrayList.add(new LatLng(38.963745,35.243322));
        latLngArrayList.add(new LatLng(49.817492,15.472962));
        latLngArrayList.add(new LatLng(36.204824,138.252924));
        latLngArrayList.add(new LatLng(31.046051,34.851612));
        latLngArrayList.add(new LatLng(53.41291,-8.24389));
        latLngArrayList.add(new LatLng(49.815273,6.129583));
        latLngArrayList.add(new LatLng(-1.831239,-78.183406));
        latLngArrayList.add(new LatLng(51.919438,19.145136));
        latLngArrayList.add(new LatLng(-35.675147,-71.542969));
        latLngArrayList.add(new LatLng(61.92411,25.748151));
        latLngArrayList.add(new LatLng(39.074208,21.824312));
        latLngArrayList.add(new LatLng(15.870032,100.992541));
        latLngArrayList.add(new LatLng(64.963051,-19.020835));
        latLngArrayList.add(new LatLng(-0.789275,113.921327));
        latLngArrayList.add(new LatLng(23.885942,45.079162));
        latLngArrayList.add(new LatLng(25.354826,51.183884));
        latLngArrayList.add(new LatLng(1.352083,103.819836));
        latLngArrayList.add(new LatLng(45.943161,24.96676));
        latLngArrayList.add(new LatLng(46.151241,14.995463));
        latLngArrayList.add(new LatLng(20.593684,78.96288));
        latLngArrayList.add(new LatLng(12.879721,121.774017));
        latLngArrayList.add(new LatLng(61.52401,105.318756));
        latLngArrayList.add(new LatLng(-9.189967,-75.015152));
        latLngArrayList.add(new LatLng(25.930414,50.637772));
        latLngArrayList.add(new LatLng(26.820553,30.802498));
        latLngArrayList.add(new LatLng(58.595272,25.013607));
        latLngArrayList.add(new LatLng(22.396428,114.109497));
        latLngArrayList.add(new LatLng(8.537981,-80.782127));
        latLngArrayList.add(new LatLng(-30.559482,22.937506));
        latLngArrayList.add(new LatLng(-38.416097,	-63.616672));
        latLngArrayList.add(new LatLng(45.1,15.2));
        latLngArrayList.add(new LatLng(23.634501,	-102.552784 ));
        latLngArrayList.add(new LatLng(33.223191,43.679291));
        latLngArrayList.add(new LatLng(4.570868,-74.297333));
        latLngArrayList.add(new LatLng(44.016521,21.005859));
        latLngArrayList.add(new LatLng(18.735693,-70.162651));
        latLngArrayList.add(new LatLng(28.033886,1.659626));
        latLngArrayList.add(new LatLng(40.069099,45.038189));
        latLngArrayList.add(new LatLng(29.31166,47.481766));
        latLngArrayList.add(new LatLng(42.733883,25.48583));
        latLngArrayList.add(new LatLng(48.669026,19.699024));
        latLngArrayList.add(new LatLng(43.94236,12.457777));
        latLngArrayList.add(new LatLng(23.69781,120.960515));
        latLngArrayList.add(new LatLng(-32.522779,-55.765835));
        latLngArrayList.add(new LatLng(23.424076,53.847818));
        latLngArrayList.add(new LatLng(55.169438,23.881275));
        latLngArrayList.add(new LatLng(56.879635,24.603189));
        latLngArrayList.add(new LatLng(9.748917,-83.753428	));
        latLngArrayList.add(new LatLng(47.162494	,19.503304));
        latLngArrayList.add(new LatLng(43.915886,17.679076));
        latLngArrayList.add(new LatLng(31.791702,-7.09262));
        latLngArrayList.add(new LatLng(41.608635,21.745275));//macedonia
        latLngArrayList.add(new LatLng(14.058324,108.277199));
        latLngArrayList.add(new LatLng(30.585164,30.585164));
        latLngArrayList.add(new LatLng(-40.900557,174.885971));
        latLngArrayList.add(new LatLng(35.126413,33.429859));
        latLngArrayList.add(new LatLng(35.937496,14.375416));
        latLngArrayList.add(new LatLng(41.153332,67.709953));
        latLngArrayList.add(new LatLng(33.93911,20.168331));
        latLngArrayList.add(new LatLng(4.535277,114.727669));
        latLngArrayList.add(new LatLng(12.565679,104.990963));
        latLngArrayList.add(new LatLng(7.873054,80.771797));
        latLngArrayList.add(new LatLng(53.709807,27.953389));
        latLngArrayList.add(new LatLng(33.886917,9.537499));
        latLngArrayList.add(new LatLng(48.379433,31.16558));
        latLngArrayList.add(new LatLng(6.42375,-66.58973	));
        latLngArrayList.add(new LatLng(14.497401,-14.452362));
        latLngArrayList.add(new LatLng(40.143105,47.576927));
        latLngArrayList.add(new LatLng(-21.115141,55.536384));
        latLngArrayList.add(new LatLng(48.019573,66.923684));
        latLngArrayList.add(new LatLng(31.952162,35.233154));
        latLngArrayList.add(new LatLng(16.995971,-62.067641));
        latLngArrayList.add(new LatLng(21.512583,55.923255));
        latLngArrayList.add(new LatLng(42.315407,43.356892));
        latLngArrayList.add(new LatLng(10.691803,-61.222503));
        latLngArrayList.add(new LatLng(14.641528,-61.024174));
        latLngArrayList.add(new LatLng(41.377491,64.585262));
        latLngArrayList.add(new LatLng(33.93911,67.709953));
        latLngArrayList.add(new LatLng(7.369722,12.354722));
        latLngArrayList.add(new LatLng(47.166,9.555373));
        latLngArrayList.add(new LatLng(21.521757,-77.781167));
        latLngArrayList.add(new LatLng(-4.038333,21.758664));
        latLngArrayList.add(new LatLng(9.081999,8.675277));
        latLngArrayList.add(new LatLng(-20.348404,57.552152));
        latLngArrayList.add(new LatLng(23.684994,90.356331));
        latLngArrayList.add(new LatLng(13.444304,144.793731));
        latLngArrayList.add(new LatLng(15.199999,-86.241905));
        latLngArrayList.add(new LatLng(-16.290154,-63.588653));
        latLngArrayList.add(new LatLng(7.946527,-1.023194));
        latLngArrayList.add(new LatLng(18.220833,-66.590149));
        latLngArrayList.add(new LatLng(43.750298,7.412841));
        latLngArrayList.add(new LatLng(-23.442503,	-58.443832));
        latLngArrayList.add(new LatLng(22.198745,113.543873));
        latLngArrayList.add(new LatLng(42.7087,19.3744));//montenegro
        latLngArrayList.add(new LatLng(15.783471,-90.230759));
        latLngArrayList.add(new LatLng(4.860416,-58.93018));
        latLngArrayList.add(new LatLng(18.109581,-77.297508));
        latLngArrayList.add(new LatLng(-1.940278,29.873888));
        latLngArrayList.add(new LatLng(3.933889,-53.125782));
        latLngArrayList.add(new LatLng(-17.679742,-149.406843));
        latLngArrayList.add(new LatLng(8.619543,0.824782));
        latLngArrayList.add(new LatLng(36.137741,-5.345374));
        latLngArrayList.add(new LatLng(-0.023559,37.906193));
        latLngArrayList.add(new LatLng(13.193887,-59.543198));
        latLngArrayList.add(new LatLng(7.5400,5.5471));
        latLngArrayList.add(new LatLng(41.20438,74.766098));
        latLngArrayList.add(new LatLng(3.202778,73.22068));
        latLngArrayList.add(new LatLng(-6.369028,34.888822));
        latLngArrayList.add(new LatLng(9.145,40.489673));
        latLngArrayList.add(new LatLng(-12.8275,45.166244));
        latLngArrayList.add(new LatLng(46.862496,103.846656));
        latLngArrayList.add(new LatLng(12.52111,-69.968338));
        latLngArrayList.add(new LatLng(-4.679574,55.491977));
        latLngArrayList.add(new LatLng(32.321384,-64.75737));
        latLngArrayList.add(new LatLng(1.650801,10.267895));
        latLngArrayList.add(new LatLng(18.335765,-64.896335));
        latLngArrayList.add(new LatLng(54.236107,	-4.548056));
        latLngArrayList.add(new LatLng(18.0708, 63.0501));//maarten
        latLngArrayList.add(new LatLng(3.919305	,	-56.027783));
        latLngArrayList.add(new LatLng(25.03428,-77.39628));
        latLngArrayList.add(new LatLng(71.706936,-42.604303));
        latLngArrayList.add(new LatLng(	-20.904305,165.618042));
        latLngArrayList.add(new LatLng(26.5225,31.4659)); //eswatini
        latLngArrayList.add(new LatLng(19.513469,-80.566956	));
        latLngArrayList.add(new LatLng(12.1696,68.9900));//curacao
        latLngArrayList.add(new LatLng(16.002082	,-24.013197));
        latLngArrayList.add(new LatLng(6.6111,20.9394));//CAR
        latLngArrayList.add(new LatLng(-0.228021,15.827659));//congo republic
        latLngArrayList.add(new LatLng(-4.038333,21.758664));//congo drc
        latLngArrayList.add(new LatLng(13.794185,-88.89653));
        latLngArrayList.add(new LatLng(6.428055,-9.429499));
        latLngArrayList.add(new LatLng(-18.766947,46.869107));
        latLngArrayList.add(new LatLng(-22.95764,18.49041));
        latLngArrayList.add(new LatLng(17.9000,62.8333));//st barth
        latLngArrayList.add(new LatLng(-13.133897,27.849332));
        latLngArrayList.add(new LatLng(	-19.015438,29.154857));
        latLngArrayList.add(new LatLng(12.862807,30.217636	));
        latLngArrayList.add(new LatLng(-11.202692,17.873887));
        latLngArrayList.add(new LatLng(9.30769,2.315834));
        latLngArrayList.add(new LatLng(27.514162,90.433601));
        latLngArrayList.add(new LatLng(-16.578193,179.414413));
        latLngArrayList.add(new LatLng(9.945587,-9.696645));
        latLngArrayList.add(new LatLng(18.971187,-72.285215));
        latLngArrayList.add(new LatLng(21.00789,-10.940835));
        latLngArrayList.add(new LatLng(12.865416,-85.207229));
        latLngArrayList.add(new LatLng(17.607789,8.081666));
        latLngArrayList.add(new LatLng(13.909444,-60.978893));
        latLngArrayList.add(new LatLng(28.394857,84.124008));
        latLngArrayList.add(new LatLng(17.060816,-61.796428));
        latLngArrayList.add(new LatLng(15.454166,18.732207));
        latLngArrayList.add(new LatLng(11.825138,42.590275));
        latLngArrayList.add(new LatLng(15.414999,-61.370976));
        latLngArrayList.add(new LatLng(15.179384,39.782334));
        latLngArrayList.add(new LatLng(13.443182,-15.310139));
        latLngArrayList.add(new LatLng(12.1165,61.6790));//grenada
        latLngArrayList.add(new LatLng(41.902916,12.453389));
        latLngArrayList.add(new LatLng(16.742498,-62.187366));
        latLngArrayList.add(new LatLng(	-18.665695,35.529562));
        latLngArrayList.add(new LatLng(-6.314993,143.95555));
        latLngArrayList.add(new LatLng(12.984305,-61.287228));
        latLngArrayList.add(new LatLng(18.0425,63.0548));//sint maarten
        latLngArrayList.add(new LatLng(5.152149,46.199616));
        latLngArrayList.add(new LatLng(34.802075,38.996815));
        latLngArrayList.add(new LatLng(-8.874217,125.727539));
        latLngArrayList.add(new LatLng(1.373333,32.290275));









//
//        countryNames.add("Andorra");
//        countryNames.add("United Arab Emirates");
//        countryNames.add("Afghanistan");
//        countryNames.add("Antigua and Barbuda");
//        countryNames.add("Anguilla");

        for (LatLng point : latLngArrayList) {
            options.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_icon));
            options.position(point);
            googleMap.addMarker(options);
        }


    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        LatLng position = marker.getPosition();
        lat= position.latitude;
        longi = position.longitude;
        finish();
        startActivity(new Intent(MapView.this,CountryMonitor.class));

        return false;
    }

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(MapView.this,MonitorMenu.class));
    }
}
