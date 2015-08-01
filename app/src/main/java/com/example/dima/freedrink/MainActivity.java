package com.example.dima.freedrink;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.VKSdkVersion;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;


public class MainActivity extends Activity {

    private static final String LOG_TAG = "MainActivity";
    private static String sTokenKey = "ACCESS_TOKEN";
    private static int aid = 4646205;
    public static final String[] PERMISSIONS = new String[] {
            VKScope.FRIENDS,
            VKScope.GROUPS,
            VKScope.WALL,
            VKScope.PHOTOS,
            VKScope.MESSAGES,
            VKScope.NOTIFICATIONS, VKScope.NOTIFY
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VKSdk.initialize(getApplicationContext());

        VKSdk.login(this, PERMISSIONS);

        FragmentManager fm = getFragmentManager();

        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);

        if (fragment == null)
        {
            fragment = new Group();
            fm.beginTransaction()
                    .add(R.id.fragmentContainer, fragment,"old")
                    .commit();
        }
    }


}
