package com.apiproject.ordersandnotificationsmanagement.orders.models.inputs;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class SimpleOrderInput extends OrderInput {
    String userName;
    String location;
    ArrayList<String> productsIDs;

//    @Override
//    public Map.Entry<String, ArrayList<SimpleOrderInput>> getOrderInputWithCommonLocation() {
//        ArrayList<SimpleOrderInput> orders = new ArrayList<>();
//        orders.add(this);
//        return new java.util.AbstractMap.SimpleEntry<>(location, orders);
//    }
}
/**
 {
    "userName": "youssef1",
    "location": "dokki",
    "productsIDs": [
        "1",
        "2"
    ]
 }
 */