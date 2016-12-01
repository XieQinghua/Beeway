package com.thvc.beeway.utils;

/**
 * Created by Administrator on 2015/10/14.
 */
public class RequestParam  {

//    implements Comparable
    private String name;
    private String value;

    public RequestParam(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

//    @Override
//    public int compareTo(Object object) {
//        RequestParam rp = (RequestParam) object;
//        return Integer.parseInt(rp.getName()) - Integer.parseInt(this.getName());
//    }
}
