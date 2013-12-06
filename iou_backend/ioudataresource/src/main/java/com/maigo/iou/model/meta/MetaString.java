package com.maigo.iou.model.meta;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MetaString extends MetaObject {

    private int characterMaximumLength;
    private String characterSetName;
    private String collationName;

    public int getCharacterMaximumLength() {
        return characterMaximumLength;
    }

    public void setCharacterMaximumLength(int characterMaximumLength) {
        this.characterMaximumLength = characterMaximumLength;
    }

    public String getCharacterSetName() {
        return characterSetName;
    }

    public void setCharacterSetName(String characterSetName) {
        this.characterSetName = characterSetName;
    }

    public String getCollationName() {
        return collationName;
    }

    public void setCollationName(String collationName) {
        this.collationName = collationName;
    }

}