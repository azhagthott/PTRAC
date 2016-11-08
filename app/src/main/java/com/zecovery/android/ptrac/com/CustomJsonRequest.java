package com.zecovery.android.ptrac.com;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

/**
 * Created by francisco on 24-08-16.
 */

public class CustomJsonRequest extends JsonObjectRequest {

    public Request.Priority mPriority;

    /**
     * @param method:       HTTP Method:
     *                      0 = GET;
     *                      1 = POST;
     *                      2 = PUT;
     *                      3 = DELETE
     * @param url:          use Constant.VAR:
     *                      Constant.URL_GET_POINTS
     * @param jsonRequest:  null
     * @param listener      new Response.Listener<JSONObject>() {}
     * @param errorListener new Response.ErrorListener() {}
     */
    public CustomJsonRequest(int method,
                             String url,
                             JSONObject jsonRequest,
                             Response.Listener listener,
                             Response.ErrorListener errorListener) {
        super(method, url, jsonRequest, listener, errorListener);
    }

    public Request.Priority getPriority() {
        return mPriority == null ? Request.Priority.NORMAL : mPriority;
    }

    public void setPriority(Request.Priority priority) {
        mPriority = priority;
    }
}