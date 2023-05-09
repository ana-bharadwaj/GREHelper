package com.example.gre;

import com.example.gre.Models.APIResponse;

public interface OnFetchDataListener {
    void onFetchData(APIResponse apiResponse,String message);
    void onError (String message);
}
