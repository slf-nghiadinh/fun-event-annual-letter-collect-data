package com.sunlife.vn.models.properties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationAwsProperties {

    private StepFunctionProperties sfn;

    @Getter
    @Setter
    public static class StepFunctionProperties {
        private String arn;
        private String region;
    }
}
