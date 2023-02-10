package com.sunlife.vn.common.constant;

public class AppConstant {
    public static final String DATETIME_YYYYMMDD_HHMMSS = "yyyy-MM-dd HH:mm:ss";

    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static enum DATASOURCE {
        driverClassName, url, userName, password, poolSize, idleTimeOut, minIdle, maxLifetime, leakDetectionThreshold, connectionTimeout
    }

    public static enum ANNUAL_LETTER {
        TYPE_EE("typeEE"), TYPE_FE("typeFE"), TYPE_UL("typeUL"), TYPE_ILP("typeILP"), TYPE_PA_TERM("typePaTerm"), POLID("polId"), CUS_EMAIL("cusEmail"),
        CUS_PHONE("cusPhone"), EXPIRY_DATE("expiryDate"), CLIENT_NAME("clientName"), CLIENT_ID("clientId"), CURRENT_DATE("currentDate"), TOKEN_ID("evtMastrId"),
        ;

        public final String value;

        private ANNUAL_LETTER(String value) {

            this.value = value;
        }
    }

    public static enum DOCUMENT_GENERATE {
        DOC_TYPE("docType"), TEMPLATE_PATH("templatePath"), FILE_PATH("filePath"), NAME_FORMAT("nameFormat"), ENGINE("engine"),
        ACT_SIGN("actSign"), ACT_UPLOAD("actUpload"), ACT_EMAIL("actEmail"),  ACT_NOTI("actNoti"), ACT_SMS("actSms"),
        FILES("files"), DOC_CONFIG("docConfig"), EXTENSION("ext"), ROOT_NODE("rootNode"),
        EMAIL_TYPE("mailType"), SMS_TYPE("smsType"), NOTI_TYPE("notiType"), TO("to"), SMS_TO("smsTo"), USER_ID("userId");

        public final String value;

        private DOCUMENT_GENERATE(String value) {
            this.value = value;
        }
    }
}
