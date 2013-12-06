package com.maigo.iou.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.maigo.iou.model.meta.MetaObject;
import com.maigo.iou.service.MetaDAO;
import com.maigo.iou.service.MetaDAOImpl;

@Path("/meta")
public class MetaResource {

    // @Inject
    private MetaDAO dao = new MetaDAOImpl();

    @GET
    @Path("/group")
    @Produces({ MediaType.APPLICATION_JSON })
    // @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<MetaObject> getGroupMetaData() {
        return dao.getGroupMetaData();
    }

}
