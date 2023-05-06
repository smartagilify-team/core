package com.smartagilify.core.entities.base;

public class HibernateStaticValues {

    private HibernateStaticValues() {
    }

    //MainBaseEntity👇
    public static final String ID_SEQ_GENERATOR = "seq_generator";
    public static final String ID = "id";

    //BaseEntity👇
    public static final String CREATE_DATE = "CREATE_DATE";
    public static final String CREATE_BY_ID = "CREATE_BY_ID";
    public static final String UPDATE_DATE = "UPDATE_DATE";
    public static final String UPDATE_BY_ID = "UPDATE_BY_ID";
    public static final String STATE = "STATE";
    public static final String ROW_LEVEL_ID = "ROW_LEVEL_ID";
    public static final String PRIORITY = "PRIORITY";
}
