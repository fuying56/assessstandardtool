package com.kingc.assessstandardtool.springmvc.model;

import java.util.HashMap;

/**
 * @author Administrator
 */
public class ValidateFormAjaxUrl extends HashMap<String, Object> {

    public static ResponseContent validateFormAjaxUrl(int count, String successMsg, String errorMessage) {

        ResponseContent responseContent = new ResponseContent();
        switch (count) {
            case 0: {
                responseContent.put("info", successMsg);
                responseContent.put("status", "y");
                break;
            }
            default: {
                responseContent.put("info", errorMessage);
                responseContent.put("status", "n");
            }
        }
        return responseContent;
    }

    public static ResponseContent validateFormAjaxUrlSuccess(String successMsg) {
        return new ResponseContent() {{
            put("info", successMsg);
            put("status", "y");
        }};
    }

    public static ResponseContent validateFormAjaxUrlError(String errorMsg) {
        return new ResponseContent() {{
            put("info", errorMsg);
            put("status", "n");
        }};
    }


}
