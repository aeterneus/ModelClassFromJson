package com.kuldiegor.model_class_from_json;

public class Field {
    private String mType;
    private String mJsonName;
    //private String mName;

    public Field(String type, String jsonName/*, String name*/) {
        mType = type;
        mJsonName = jsonName;
      //  mName = name;
    }

    public Field() {
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public String getJsonName() {
        return mJsonName;
    }

    public void setJsonName(String jsonName) {
        mJsonName = jsonName;
    }

    /*public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }*/
}
