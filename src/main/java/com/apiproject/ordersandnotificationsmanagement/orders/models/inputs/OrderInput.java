package com.apiproject.ordersandnotificationsmanagement.orders.models.inputs;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = SimpleOrderInput.class, name = "simple"),
        @JsonSubTypes.Type(value = CompoundOrderInput.class, name = "compound")
})
public abstract class OrderInput {
}

