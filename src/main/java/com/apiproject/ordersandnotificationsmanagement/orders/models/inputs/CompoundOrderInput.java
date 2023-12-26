package com.apiproject.ordersandnotificationsmanagement.orders.models.inputs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class CompoundOrderInput extends OrderInput{
    String commonLocation;
    ArrayList<SimpleOrderInput> orders;

//    @Override
//    public Map.Entry<String, ArrayList<SimpleOrderInput>> getOrderInputWithCommonLocation() {
//        return new AbstractMap.SimpleEntry<>(commonLocation, orders);
//    }
}
/**
 {
    "commonLocation": "dokki",
    "orders": [
        {
            "userName": "youssef1",
            "location": "dokki1",
            "productsIDs": [
                "1",
                "2"
            ]
        },
        {
            "userName": "youssef2",
            "location": "dokki2",
            "productsIDs": [
                "1",
                "2"
            ]
        }
    ]
 }
 */