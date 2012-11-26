package com.hkw.assassins;

import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;

import android.app.ActionBar;
import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.google.android.gcm.GCMRegistrar;

public class MainActivity extends MapActivity { 
	
	private MapView mapView;
    private String TAG = "MainActivity";
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up the action bar.
        final ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        
        initMap();
        
        GCMRegistrar.checkDevice(this);
        GCMRegistrar.checkManifest(this);
        final String regId = GCMRegistrar.getRegistrationId(this);
        if (regId.equals("")) {
            Log.v(TAG, "Registering");
          GCMRegistrar.register(this, "138142482844");
        } else {
          Log.v(TAG, "Already registered");
        }
    }
    /**
     * Initialise the map and adds the zoomcontrols to the LinearLayout.
     */
    private void initMap() {
        mapView = (MapView) findViewById(R.id.mapView);

        View zoomView = mapView.getZoomControls();
        mapView.displayZoomControls(true);
        mapView.getController().setZoom(17);
    }
    
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
    	
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
    	
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
        	case R.id.menu_playersinfo:
           	 DialogFragment playerslistFragment = new PlayersListDialog();
            	playerslistFragment.show(getFragmentManager(), "missiles");
        	return true;
        	case R.id.menu_targetinfo:
        	 DialogFragment targetinfoFragment = new TargetInfoDialog();
        	  targetinfoFragment.show(getFragmentManager(), "missiles");
        	 return true;
            case R.id.menu_settings:
                return true;
            case R.id.menu_share:
            	Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            	sharingIntent.setType("text/plain");
            	String shareBody = "Here is the share content body";
            	sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
            	sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            	startActivity(Intent.createChooser(sharingIntent, "Share via"));
                return true;
            case R.id.menu_startgame:
           	 DialogFragment startgameFragment = new StartGameDialog();
           	startgameFragment.show(getFragmentManager(), "missiles");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected boolean isRouteDisplayed(){
    	return false;
    }
}
