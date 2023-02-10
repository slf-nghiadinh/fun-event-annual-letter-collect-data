package com.sunlife.vn.models.properties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationIntegrationProperties {

    private FCRMProperties fcrm;
    private OKATAProperties okta;

    @Getter
    @Setter
    public static class FCRMProperties {
        private String url;
        private String traceabilityId;
        private String correlationId;
    }

    @Getter
    @Setter
    public static class OKATAProperties {
        private String url;
        private String clientId;
        private String clientSecrets;
        private String scope;
    }
}
