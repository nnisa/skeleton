package dao;
import api.ReceiptResponse;

import generated.tables.records.ReceiptsRecord;
import generated.tables.records.TagsRecord;
import generated.tables.records.ReceiptsTagsRecord;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.math.BigDecimal;
import java.util.List;

import static com.google.common.base.Preconditions.checkState;
import static generated.Tables.RECEIPTS;
import static generated.tables.Tags.TAGS;

public class TagDao {
    DSLContext dsl;

    public TagDao(Configuration jooqConfig) {
        this.dsl = DSL.using(jooqConfig);
    }

    public int insert(String TagName) {
        TagsRecord tagsRecord = dsl
                .insertInto(TAGS, TAGS.TAGNAME)
                .values(TagName)
                .returning(TAGS.ID)
                .fetchOne();

        checkState(tagsRecord != null && tagsRecord.getId() != null, "Insert into Tags failed");
        return tagsRecord.getId();
    }

    public Integer TagIdFromName(String tagName) {
               return dsl.selectFrom(TAGS)
                .where(TAGS.TAGNAME.eq(tagName))
                .fetchOne(TAGS.ID);
    }

}




    // public List<TagsRecord> funca() {
    //     return dsl.select(TAGS.TAGID, TAGS.agname).from(TAGS).fetch();
    // }