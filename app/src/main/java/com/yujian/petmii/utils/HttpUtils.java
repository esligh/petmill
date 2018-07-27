package com.yujian.petmii.utils;

/**
 * Created by fr on 2018/7/20.
 */

public class HttpUtils {
    //        HashMap<String,String> paramsMap = new HashMap<>();
//        paramsMap.put("username",account);
//        paramsMap.put("password", MD5Utils.md5(new StringBuilder(pwd).append("_").append(Constants.Config.MD5_KEY).toString()));
//        paramsMap.put("verf_code",vcode);
//        String strEntity = gson.toJson(paramsMap);
//        L.d(Constants.Tag,"register params==>"+strEntity);
//        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"),strEntity);

    //        try{
//            JSONObject params = new JSONObject();
//            params.put("username",account);
//            params.put("password", MD5Utils.md5(new StringBuilder(pwd).append("_").append(Constants.Config.MD5_KEY).toString()));
//            params.put("verf_code",vcode);
//            JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST,
//                    "http://160.124.223.107:18088/project/reg.php", params,
//                    new Response.Listener<JSONObject>() {
//                        @Override
//                        public void onResponse(JSONObject response) {
//                            Log.d(Constants.Tag, "response -> " + response.toString());
//                        }
//                    }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    Log.e(Constants.Tag, error.getMessage(), error);
//                }
//            }) {
//                @Override
//                public Map<String, String> getHeaders() {
//                    HashMap<String, String> headers = new HashMap<String, String>();
//                    headers.put("Accept", "application/json");
//                    headers.put("Content-Type", "application/json; charset=UTF-8");
//                    return headers;
//                }
//            };
//            PetmiiApplication.addToRequestQueue(jsonRequest);
//        }catch (JSONException e){}

//        StringRequest sr = new StringRequest(Request.Method.POST,"http://160.124.223.107:18088/project/reg.php", new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//                    JSONObject obj = new JSONObject(response);
//                    Log.d(Constants.Tag, "response -> " + obj.toString());
//                    ToastUtils.shortShow(response);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e(Constants.Tag, error.getMessage(), error);
//            }
//        }){
//            @Override
//            protected Map<String,String> getParams(){
//                Map<String,String> params = new HashMap<String, String>();
//                params.put("username",account);
//                params.put("password", MD5Utils.md5(new StringBuilder(pwd).append("_").append(Constants.Config.MD5_KEY).toString()));
//                params.put("verf_code",vcode);
//
//                return params;
//            }
//
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String,String> params = new HashMap<String, String>();
//                params.put("Content-Type","application/x-www-form-urlencoded");
//                return params;
//            }
//        };
//        PetmiiApplication.addToRequestQueue(sr);
}
