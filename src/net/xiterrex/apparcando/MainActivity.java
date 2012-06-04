package net.xiterrex.apparcando;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ToggleButton;

public class MainActivity extends Activity {
    private ParkingStatus parkingStatus = ParkingStatus.I_PARK;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        this.updateToogleStatus();
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
        ToggleButton imLookingToggleButton = ((ToggleButton) findViewById(R.id.im_looking));
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
}
