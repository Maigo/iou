package com.maigo.iou.model.meta;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MetaObject {

    private String name;
    private String data_type;
    private boolean isNullable;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData_type() {
        return data_type;
    }

    public void setDataType(String data_type) {
        this.data_type = data_type;
    }

    public boolean isNullable() {
        return isNullable;
    }

    public void setNullable(boolean isNullable) {
        this.isNullable = isNullable;
    }

}
