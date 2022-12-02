package cn.edu.usst.cognitive.model;

import java.io.Serializable;

public class Route implements Serializable {

    private String text;
    private Class cls;

    public Route(String text, Class cls) {
        this.text = text;
        this.cls = cls;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Class getCls() {
        return cls;
    }

    public void setCls(Class cls) {
        this.cls = cls;
    }


}
