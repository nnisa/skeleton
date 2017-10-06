package controllers;

import api.ReceiptResponse;
import dao.ReceiptDao;
import dao.TagDao;
import generated.tables.records.ReceiptsRecord;
import org.jooq.DSLContext;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static java.util.stream.Collectors.toList;




@Path("")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class TagController {
    final TagDao tags;
    final ReceiptDao receipts;
    DSLContext dsl;
    public TagController(ReceiptDao receipts, TagDao tags) {
        this.tags = tags;
        this.receipts = receipts;
    }


    @PUT
    @Path("/tags/{tag}")
    public Response toggleTag(@PathParam("tag") String tagName, int receiptId) {
        if (!receipts.ReceiptIdExists(receiptId)) {
            throw new WebApplicationException("receipt id does not exist", Response.Status.NOT_FOUND);
        }
        Integer tagId = tags.TagIdFromName(tagName);
        if (tagId == null) {
            tagId = tags.insert(tagName);
        }
        receipts.toggleTagReceipt(receiptId, tagId);
        return Response.ok().build();
    }

    @GET
    @Path("/tags/{tag}")
    public List<ReceiptResponse> getReceipts(@PathParam("tag") String tagName) {
        Integer tagId = tags.TagIdFromName(tagName);
        List<ReceiptsRecord> receiptsRecords = receipts.ReceiptsForTag(tagId);
        return receiptsRecords.stream().map(ReceiptResponse::new).collect(toList());
    }

}
   