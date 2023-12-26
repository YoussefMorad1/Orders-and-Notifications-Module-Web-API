package com.apiproject.ordersandnotificationsmanagement.orders.models.inputs;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
@Getter
@Setter
@AllArgsConstructor
public class CompoundOrderInput extends OrderInput{
    String commonLocation;
    ArrayList<SimpleOrderInput> orders;
}
/**
 {
    "type": "compound",
    "commonLocation": "dokki",
    "orders": [
        {
            "type": "simple",
            "userName": "youssef1",
            "location": "dokki1",
            "productsIDs": [
                "1",
                "2"
            ]
        },
        {
           "type": "simple",
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