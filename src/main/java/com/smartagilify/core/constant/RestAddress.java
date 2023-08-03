package com.smartagilify.core.constant;

public class RestAddress {
    private RestAddress() {
    }
    public static final String REST_PREFIX = "/rest";

    public static final String SAVE = "/save";
    public static final String UPDATE = "/update";
    public static final String DELETE_BY_ID = "/delete-by-id/{id}";
    public static final String FIND_BY_ID = "/find-by-id/{id}";
    public static final String FIND_ALL = "/find-all";

    //--------- person end point 👇 ---------
    public static final String PERSON = REST_PREFIX + "/person";
}
