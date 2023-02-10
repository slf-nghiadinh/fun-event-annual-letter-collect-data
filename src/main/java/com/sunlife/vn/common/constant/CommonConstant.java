package com.sunlife.vn.common.constant;

public class CommonConstant {
    public final static String NEXT_STEP = "NEXT-STEP";
    public final static String FAIL = "FAILED";
    public final static String SUSCESS = "SUSCESS";
    public static enum PROVIDERRESPONSE {
        status, errorcode, description
    }

    public static enum SMSREQUEST {
        Authorization, from, to, text, unicode, smsId, contentId
    }
    public static enum SMSPREFIXCODE {
        VN("84"), CN("86");

        public final String value;

        private SMSPREFIXCODE(String value) {
            this.value = value;
        }
    }
}
