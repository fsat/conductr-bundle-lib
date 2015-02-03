/*
 * Copyright © 2014-2015 Typesafe, Inc. All rights reserved. No information contained herein may be reproduced or
 * transmitted in any form or by any means without the express written permission of Typesafe, Inc.
 */

package com.typesafe.conductr.bundlelib;

import java.net.URL;

/**
 * Class representing an HttpPayload used to communicate to a Typesafe ConductR Server.
 */
public class HttpPayload {

    private final URL url;
    private final String requestMethod;
    private final boolean followRedirects;

    // valid HTTP methods
    private static final String[] requestMethods = {
        "GET", "POST", "HEAD", "OPTIONS", "PUT", "DELETE", "TRACE"
    };

    HttpPayload(URL url, String requestMethod, boolean followRedirects) {
        String rm = requestMethod.toUpperCase();
        boolean validMethod = false;
        for (String vm: requestMethods) {
            if (rm.equals(vm)) {
                validMethod = true;
                break;
            }
        }
        if (!validMethod)
            throw new IllegalArgumentException("Invalid request method " + requestMethod);
        this.url = url;
        this.requestMethod = rm;
        this.followRedirects = followRedirects;
    }

    HttpPayload(URL url, String requestMethod) {
        this(url, requestMethod, false);
    }

    HttpPayload(URL url) {
        this(url, "GET", false);
    }

    /**
     * @return The URL to connect to
     */
    public URL getUrl() {
        return url;
    }

    /**
     * @return The request method to use
     */
    public String getRequestMethod() {
        return requestMethod;
    }

    /**
     * @return Should the request follow redirects
     */
    public boolean getFollowRedirects() {
        return followRedirects;
    }
}
