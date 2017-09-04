package controllers;

//import api.CreateReceiptRequest;
//import api.ReceiptResponse;

import dao.TagDao;
import generated.tables.records.TagsRecord;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import static java.util.stream.Collectors.toList;


@Path("/tags/{tag}")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TagController {
    final TagDao tags;

    public void toggleTag(@PathParam("tag") String tagName) {
    this.tags = tags;
    }

}
