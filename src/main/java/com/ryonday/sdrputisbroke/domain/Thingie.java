package com.ryonday.sdrputisbroke.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

@Data
@Accessors(chain = true)
@DynamoDBTable(tableName = "thingies")
public class Thingie {

    @Id
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
//    @DynamoDBIgnore
    private ThingieId id = new ThingieId();

    @JsonProperty("some_data")
    @JsonAlias("someData")
    private String someData;

    @JsonProperty("more_data")
    @JsonAlias("moreData")
    private String moreData;


    @DynamoDBHashKey
    public String getFoo() {
        return id.getFoo();
    }

    public Thingie setFoo(String foo) {
        this.id.setFoo(foo);
        return this;
    }

    @DynamoDBRangeKey
    public String getBar() {
        return id.getBar();
    }

    public Thingie setBar(String bar) {
        this.id.setBar(bar);
        return this;
    }

//    @DynamoDBIgnore
//    @JsonIgnore
//    public ThingieId getCompositeId() {
//        return id;
//    }
//
//    @JsonIgnore
//    public void setCompositeId(ThingieId id) {
//        this.id = id;
//    }
}
