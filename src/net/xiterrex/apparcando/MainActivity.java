package net.xiterrex.apparcando;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ToggleButton;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

public class MainActivity extends MapActivity {
    private ParkingStatus parkingStatus = ParkingStatus.I_PARK;
    private MapView myMapView;
    private LocationManager myLocationManager;
    private LocationListener myLocationListener;
    private MapController myMapController;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        this.updateToogleStatus();
        this.myMapView = (MapView) findViewById(R.id.mapview);
        this.myMapView.setBuiltInZoomControls(true);
        this.myLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        this.myLocationListener = new MyLocationListener();
        this.myMapView.setSatellite(true); //Set satellite view
        this.myMapController = myMapView.getController();
        this.myMapController.setZoom(20); //Fixed Zoom Level
        this.myLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, myLocationListener);
    }

    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }

    public void imLookingToggle(View v) {
        ToggleButton button = ((ToggleButton) v);
        if (button.isChecked()) {
            this.parkingStatus = ParkingStatus.IM_LOOKING;
        } else {
            this.parkingStatus = ParkingStatus.IM_NOT_LOOKING;
        }
        this.updateToogleStatus();
    }

    private void updateToogleStatus() {
        ToggleButton imLookingToggleButton = (ToggleButton) findViewById(R.id.im_looking);
        imLookingToggleButton.setChecked(this.parkingStatus == ParkingStatus.IM_LOOKING);
    }

    public void iParkClick(View v) {
        this.parkingStatus = ParkingStatus.I_PARK;
        this.updateToogleStatus();
    }

    public void iLeaveClick(View v) {
        this.parkingStatus = ParkingStatus.I_LEAVE;
        this.updateToogleStatus();
    }

    private void CenterLocatio(GeoPoint centerGeoPoint) {
        myMapController.animateTo(centerGeoPoint);
    }

    private class MyLocationListener implements LocationListener {
        public void onLocationChanged(Location argLocation) {
            // TODO Auto-generated method stub
            GeoPoint myGeoPoint = new GeoPoint(
                    (int) (argLocation.getLatitude() * 1000000),
                    (int) (argLocation.getLongitude() * 1000000));

            CenterLocatio(myGeoPoint);
        }

        public void onProviderDisabled(String provider) {
            // TODO Auto-generated method stub
        }

        public void onProviderEnabled(String provider) {
            // TODO Auto-generated method stub
        }

        public void onStatusChanged(String provider,
                                    int status, Bundle extras) {
            // TODO Auto-generated method stub
        }
    }
}
