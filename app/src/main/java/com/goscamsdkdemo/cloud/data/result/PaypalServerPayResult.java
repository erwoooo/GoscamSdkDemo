package com.goscamsdkdemo.cloud.data.result;

import com.gos.platform.api.contact.PlatCode;
import com.gos.platform.api.result.PlatResult;

import org.json.JSONException;
import org.json.JSONObject;

public class PaypalServerPayResult extends PlatResult {
    public PaypalServerPayResult(int code, String json) {
        super(PlatCmd.paypalServerPay, 0, code, json);
    }
    @Override
    protected void response(String json) {
        if (responseCode == 200) {
            try {
                JSONObject bodyJsonObject = new JSONObject(json);
                String code = bodyJsonObject.optString("code");
                if ("0".equals(code)) {
                    responseCode = PlatCode.SUCCESS;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
