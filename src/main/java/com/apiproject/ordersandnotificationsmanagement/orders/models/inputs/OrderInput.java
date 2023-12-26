package com.apiproject.ordersandnotificationsmanagement.orders.models.inputs;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.ArrayList;
import java.util.Map;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CompoundOrderInput.class, name = "compound"),
        @JsonSubTypes.Type(value = SimpleOrderInput.class, name = "simple")
})
public abstract class OrderInput {
//    Map.Entry<String, ArrayList<SimpleOrderInput>> getOrderInputWithCommonLocation();
}

