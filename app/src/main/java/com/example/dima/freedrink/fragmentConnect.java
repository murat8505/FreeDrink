package com.example.dima.freedrink;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by dima on 31.07.15.
 */
public class fragmentConnect extends Fragment implements View.OnClickListener {
    Button connect,create;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activityfragment,container,false);

//        подключение кнопок и посадка слушателя
        connect = (Button)view.findViewById(R.id.connect);
        create = (Button)view.findViewById(R.id.create);
        create.setOnClickListener(this);
        connect.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.connect:
                Toast.makeText(getActivity().getApplicationContext(),
                        "ты подключенец", Toast.LENGTH_SHORT).show();


                break;
            case R.id.create:
                Toast.makeText(getActivity().getApplicationContext(),
                        "ты создатель", Toast.LENGTH_SHORT).show();

//                Group group = new Group();
//                group.creatGroup();

                FragmentManager fm = getFragmentManager();
                Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);
                fm.beginTransaction().remove(getFragmentManager().findFragmentByTag("old")).commit();

                fm.beginTransaction().add(R.id.fragmentContainer,fragment).commit();


//                Log.d("commit","fragment");
//                if (fragment == null)
//                {
//                    fragment = new Group();
//                    fm.beginTransaction()
//                            .add(R.id.fragmentMap,fragment)
//                            .commit();
//                    Log.d("commit","ok");
//                }

                break;
        }
    }
}
