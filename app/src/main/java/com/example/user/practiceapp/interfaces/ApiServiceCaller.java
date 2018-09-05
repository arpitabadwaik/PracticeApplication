package com.example.user.practiceapp.interfaces;

import com.android.volley.NetworkResponse;
import com.example.user.practiceapp.webservices.JsonResponse;

/**
 * Created by User on 27-12-2016.
 */

public interface ApiServiceCaller {
    void onAsyncSuccess(JsonResponse jsonResponse, String label);
    void onAsyncFail(String message, String label, NetworkResponse response);
    void onAsyncCompletelyFail(String message, String label);
}
