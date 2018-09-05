package com.example.user.practiceapp.ui.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.user.practiceapp.R;
import com.example.user.practiceapp.interfaces.ApiServiceCaller;
import com.example.user.practiceapp.model.JsonGSonModel;
import com.example.user.practiceapp.model.JsonModel;
import com.example.user.practiceapp.model.MyModel;
import com.example.user.practiceapp.ui.adapter.MyAdapter;
import com.example.user.practiceapp.ui.adapter.MyJsonAdapter;
import com.example.user.practiceapp.utility.App;
import com.example.user.practiceapp.webservices.ApiConstants;
import com.example.user.practiceapp.webservices.HttpHandler;
import com.example.user.practiceapp.webservices.JsonResponse;
import com.example.user.practiceapp.webservices.WebRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ApiServiceCaller {

    private Context mContext;
    private RecyclerView recyclerView;
    private ArrayList<JsonModel> jsonModels = new ArrayList<>();
    private ArrayList<JsonGSonModel> jsonModelArrayList = new ArrayList<>();

    private static String url = "http://mrbd.utility.esselgroup.com/api/get_village_data/110";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(layoutManager);

        /*for (int i = 0; i < 10; i++) {
            JsonModel jsonModel = new JsonModel();
            jsonModel.villageId = "Id " + i;
            jsonModel.villageName = "Bhugav " + i;
            jsonModel.blockName = "Kothrud " + i;
            jsonModel.panchayatName = "Pune " + i;

            jsonModels.add(jsonModel);
        }

//        MyAdapter myAdapter = new MyAdapter(mContext, jsonModels);
//        recyclerView.setAdapter(myAdapter);*/

//        new GetDetails().execute();
        getDetails();
    }


    private class GetDetails extends AsyncTask<Void, Void, Void> {
        ProgressDialog pDialog = new ProgressDialog(mContext);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    String message = jsonObj.getString("message");
                    String result = jsonObj.getString("result");

                    JSONArray data = jsonObj.getJSONArray("village_data");

//                    Log.d("sssssssssssss", "Json parsing : " + data);

                    for (int i = 0; i < data.length(); i++) {
                        JSONObject jsonObject = data.getJSONObject(i);

                        JsonModel jsonModel = new JsonModel();

                        jsonModel.villageCode = jsonObject.getString("village_code");
                        jsonModel.blockName = jsonObject.getString("block_name");
                        jsonModel.villageId = jsonObject.getString("village_id");
                        jsonModel.villageName = jsonObject.getString("village_name");
                        jsonModel.panchayatName = jsonObject.getString("panchayat_name");

                        jsonModels.add(jsonModel);
                    }

                    // looping through All Contacts
                    /*for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);

                        String id = c.getString("village_code");
                        String blockName = c.getString("block_name");
                        String villageId = c.getString("village_id");
                        String villageName = c.getString("village_name");
                        String panchayatName = c.getString("panchayat_name");

//
                    }*/
                    pDialog.dismiss();
                } catch (final JSONException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Json parsing error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });

                }
            } else {
                pDialog.dismiss();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            /*pDialog.dismiss();
            MyJsonAdapter myJsonAdapter = new MyJsonAdapter(mContext, jsonModels);
            recyclerView.setAdapter(myJsonAdapter);*/
        }
    }

    private void getDetails() {

        JsonObjectRequest request = WebRequest.callPostMethod(Request.Method.GET, null, ApiConstants.GET_DETAILS,
                ApiConstants.GET_DETAILS, this, "");
        App.getInstance().addToRequestQueue(request, ApiConstants.GET_DETAILS);
    }

    @Override
    public void onAsyncSuccess(JsonResponse jsonResponse, String label) {
        switch (label) {
            case ApiConstants.GET_DETAILS: {
                if (jsonResponse != null) {
                    if (jsonResponse.result != null) {
                        jsonModelArrayList.addAll(jsonResponse.village_data);

                        MyJsonAdapter myJsonAdapter = new MyJsonAdapter(mContext, jsonModelArrayList);
                        recyclerView.setAdapter(myJsonAdapter);

                        Log.d("asasasas","ss: "+jsonModelArrayList);
                    }
                }
            }
        }
    }

    @Override
    public void onAsyncFail(String message, String label, NetworkResponse response) {

    }

    @Override
    public void onAsyncCompletelyFail(String message, String label) {

    }
}
