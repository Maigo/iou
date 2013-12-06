package com.maigo.iou.service;

import java.util.List;

import com.maigo.iou.model.meta.MetaObject;

public interface MetaDAO {

    // data read access
    public List<MetaObject> getGroupMetaData();

}
