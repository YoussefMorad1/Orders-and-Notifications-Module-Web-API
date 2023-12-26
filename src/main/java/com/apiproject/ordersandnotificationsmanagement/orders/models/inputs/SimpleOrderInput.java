package com.apiproject.ordersandnotificationsmanagement.orders.models.inputs;
import com.fasterxml.jackson.annotation.JsonTypeName;
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