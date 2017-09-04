package dao;

import api.ReceiptResponse;
import generated.tables.records.ReceiptsRecord;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.math.BigDecimal;
import java.util.List;

import static com.google.common.base.Preconditions.checkState;
import static generated.Tables.RECEIPTS;

public class TagDao {
    DSLContext dsl;

    public TagDao(Configuration jooqConfig) {
        this.dsl = DSL.using(jooqConfig);
    }

    public int insert(String TagName) {
        TagsRecord tagsRecord = dsl
                .insertInto(TAGS, TAGS.NAME)
                .values(tagName)
                .returning(TAGS.ID)
                .fetchOne();

        checkState(tagsRecord != null && tagsRecord.getId() != null, "Insert into Tags failed");
        return tagsRecord.getId();
    }

    public List<TagsRecord> getAllTag() {
        return dsl.selectFrom(TAGS).fetch();
    }
    public Integer getTagIdFromName(String tagName) {
        return dsl.selectFrom(TAGS)
                .where(TAGS.NAME.eq(tagName))
                .fetchOne(TAGS.ID);
    }
}