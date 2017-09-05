package dao;

import api.ReceiptResponse;

import generated.tables.records.ReceiptsRecord;
import generated.tables.records.ReceiptsTagsRecord;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.jooq.Record3;
import org.jooq.Result;
import java.util.ArrayList;
import java.math.BigDecimal;

import java.util.List;

import static com.google.common.base.Preconditions.checkState;
import static generated.Tables.RECEIPTS;
import static generated.Tables.RECEIPTS_TAGS;


public class ReceiptDao {
    DSLContext dsl;

    public ReceiptDao(Configuration jooqConfig) {
        this.dsl = DSL.using(jooqConfig);
    }

    public int insert(String merchantName, BigDecimal amount) {
        ReceiptsRecord receiptsRecord = dsl
                .insertInto(RECEIPTS, RECEIPTS.MERCHANT, RECEIPTS.AMOUNT)
                .values(merchantName, amount)
                .returning(RECEIPTS.ID)
                .fetchOne();

        checkState(receiptsRecord != null && receiptsRecord.getId() != null, "Insert failed");

        return receiptsRecord.getId();
    }

    public List<ReceiptsRecord> getAllReceipts() {
        return dsl.selectFrom(RECEIPTS).fetch();
    }

//    public boolean ifExists (int receipt_id){
//        ReceiptsRecord receiptsRecord = dsl.selectFrom(RECEIPTS)
//                .where(RECEIPTS.ID.eq(receipt_id));
//    }

    public boolean ReceiptIdExists(int receiptId){
        return dsl.fetchExists(RECEIPTS, RECEIPTS.ID.eq(receiptId));
        }

    public void toggleTagReceipt(int receiptId, int tagId) {

        List<ReceiptsTagsRecord> receiptsTagsRecords = dsl.selectFrom(RECEIPTS_TAGS)
                .where(RECEIPTS_TAGS.RECEIPTID.eq(receiptId).and(RECEIPTS_TAGS.TAGID.eq(tagId))).fetch();


        if (receiptsTagsRecords.size() > 0) {
            dsl.delete(RECEIPTS_TAGS)
                    .where(RECEIPTS_TAGS.RECEIPTID.eq(receiptId))
                    .and(RECEIPTS_TAGS.TAGID.eq(tagId))
                    .execute();
                }
        else {
            dsl.insertInto(RECEIPTS_TAGS, RECEIPTS_TAGS.RECEIPTID, RECEIPTS_TAGS.TAGID)
                    .values(receiptId, tagId).execute();
        }
    }

    public List<ReceiptsRecord> ReceiptsForTag(int tagId) {
        Result<Record3<Integer, String, BigDecimal>> result = dsl.select(RECEIPTS.ID, RECEIPTS.MERCHANT, RECEIPTS.AMOUNT).from(RECEIPTS)
                .innerJoin(RECEIPTS_TAGS).on(RECEIPTS.ID.eq(RECEIPTS_TAGS.RECEIPTID))
                .where(RECEIPTS_TAGS.TAGID.eq(tagId))
                .fetch();

                List<ReceiptsRecord> receiptsRecords = new ArrayList<>();

        for (Record3 r: result) {
            ReceiptsRecord receiptsRecord = new ReceiptsRecord();
            receiptsRecord.setId((Integer)r.getValue(0));
            receiptsRecord.setMerchant((String)r.getValue(1));
            receiptsRecord.setAmount((BigDecimal) r.getValue(2));
            receiptsRecords.add(receiptsRecord);
            }

        return receiptsRecords;
        }
}

