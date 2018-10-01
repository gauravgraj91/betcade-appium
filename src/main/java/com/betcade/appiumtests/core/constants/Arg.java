package com.betcade.appiumtests.core.constants;

import io.appium.java_client.service.local.flags.ServerArgument;

/**
 * Created by Gaurav on 5/22/2017.
 */

public enum Arg implements ServerArgument {

    TIMEOUT("--command-timeout"),
    LOCATL_TIME_ZONE("--local-timezone"),
    LOG_LEVEL("--log-level");

    private final String arg;

    Arg(String arg){
        this.arg = arg;
    }

    @Override
    public String getArgument() {
        return arg;
    }
}
