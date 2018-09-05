package com.example.user.practiceapp.webservices;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.user.practiceapp.interfaces.ApiServiceCaller;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class WebRequest {

    public static JsonObjectRequest callPostMethod(int requestType, JSONObject jsonObject, String url, final String label,
                                                   final ApiServiceCaller caller, final String token) {
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(requestType, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Gson gson = new Gson();
                    JsonResponse jsonResponse = gson.fromJson(response.toString(), JsonResponse.class);
                    caller.onAsyncSuccess(jsonResponse, label);
                } catch (Exception e) {
                    e.printStackTrace();
                    caller.onAsyncCompletelyFail("Failed", label);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse response = error.networkResponse;
                if (error instanceof ServerError && response != null) {
                    try {
                        String res = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
//                        VolleyLog.d(TAG, "Error: " + res);
                        Gson gson = new Gson();
                        JsonResponse jsonResponse = gson.fromJson(res, JsonResponse.class);
                        caller.onAsyncSuccess(jsonResponse, label);
                    } catch (UnsupportedEncodingException e1) {
                        e1.printStackTrace();
                    } catch (JsonSyntaxException je) {
                        caller.onAsyncFail(error.getMessage() != null && !error.getMessage().equals("") ? error.getMessage() : "Please Contact Server Admin", label, response);
                    }
                } else
                    caller.onAsyncFail(error.getMessage() != null && !error.getMessage().equals("") ? error.getMessage() : "Please Contact Server Admin", label, response);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("Content-Type", "application/json; charset=utf-8");
                params.put("Accept", "application/json");
                params.put("Authorization", token);
                return params;
            }
        };

        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(100000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        return jsonObjReq;
    }
}
