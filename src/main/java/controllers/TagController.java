package controllers;

//import api.CreateReceiptRequest;
//import api.ReceiptResponse;

import dao.ReceiptDao;
import dao.TagDao;
import generated.tables.records.TagsRecord;
import static generated.Tables.RECEIPTS_TAGS;

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
    public TagController(TagDao tags) {
        this.tags = tags;
    }

    @PUT
    public void toggleTag(@PathParam("tag") String tagName, Integer receiptId) {
    //check if receipt id exists or not
        //if not throw an error 404
        //if exists check the tagName exist or not
        // if yes [remove it] receipt tag table
        // if not [add it]
    }

}



