package com.hkw.assassins;

import com.google.android.maps.MapActivity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MainActivity extends MapActivity { 
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up the action bar.
        final ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        
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
