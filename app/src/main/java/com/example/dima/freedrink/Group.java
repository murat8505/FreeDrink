package com.example.dima.freedrink;

import android.annotation.TargetApi;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by dima on 01.08.15.
 */
public class Group extends Fragment implements OnMapReadyCallback, GoogleMap.OnMapClickListener {
    GoogleMap googleMap;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mapview, container, false);

        return view;

    }



    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public void onStart() {
        super.onStart();
        try {

            googleMap = getMapFragment().getMap();
            googleMap.setOnMapClickListener(this);
        }catch (Exception e)
        {
            Log.d("test", e.toString());
        }
        Log.d("test", "ok");

    }

    public void creatGroup(){

//        делаем запрос на создание встречи
        final VKRequest request = new VKRequest("groups.create", VKParameters.from("title", "testMe!", "type", "event"));
        request.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
//                        если создалось
                super.onComplete(response);
                try {

                    Log.d("create", "ok" + response.json.getString("response"));
//                            получаем ответ с вк
                    String input = response.json.getString("response");
//                            парсим его на наличие ид
                    JSONObject jsonObj = new JSONObject(input);
                    String id = jsonObj.getString("id");

                    VKRequest setAdress = new VKRequest("groups.editPlace",
                            VKParameters.from("group_id", id, "address", "Гвардейская ул. 16, Минск, Беларусь", "country_id", 3, "city_id", 282));
                    setAdress.executeWithListener(new VKRequest.VKRequestListener() {
                        @Override
                        public void onComplete(VKResponse response) {
                            super.onComplete(response);
                            Log.d("ok", " group create");

                        }
                    });

                    Log.d("ok", id);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(VKError error) {
                super.onError(error);
                Log.d("create", "error");
            }
        });

    }




    @Override
    public void onMapClick(LatLng latLng) {
        Log.d("what", latLng.toString());
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
    private MapFragment getMapFragment() {
        FragmentManager fm = null;



        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            Log.d("test", "using getFragmentManager");
            fm = getFragmentManager();
        } else {
            Log.d("test", "using getChildFragmentManager");
            fm = getChildFragmentManager();
        }

        return (MapFragment) fm.findFragmentById(R.id.map);
    }
}
