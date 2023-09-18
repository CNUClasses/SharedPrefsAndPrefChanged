package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

//note the interface we are implementing
public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    private static final String TAG = "prefdemo";
    private static final String SIG = "signature";
    private static final String DEFAULT_SIG = "UNKNOWN";
    private SharedPreferences settings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set up shared prefs, the pref fragment will save prefs in a file called this.getPackageName()+ "_preferences"
        //so register for changes to preferences in this file
        settings = getSharedPreferences(this.getPackageName()+ "_preferences", Context.MODE_PRIVATE);
        settings.registerOnSharedPreferenceChangeListener(this);
    }

    public void doPref(View view) {
        Intent mi=new Intent(this, MainActivity2.class);
        startActivity(mi);
    }

    //this is the callback for the haredPreferences.OnSharedPreferenceChangeListener
    //android will call it whenever one of the prefs in this.getPackageName()+ "_preferences"
    //changes.  s will hold the key of the changed preference
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        Log.d(TAG, "onSharedPreferenceChanged: Preference "+s+" changed!");

        //if SIG changed say so
        if(s.equals(SIG))
            Toast.makeText(this, SIG+" changed! it now is "+settings.getString(SIG, DEFAULT_SIG), Toast.LENGTH_SHORT).show();
    }
}