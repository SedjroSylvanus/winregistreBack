package com.dgi.dsi.winregistre.service.serviceIreport;


import com.dgi.dsi.winregistre.entites.modelIreport.AddressModel;
import com.dgi.dsi.winregistre.entites.modelIreport.OrderEntryModel;
import com.dgi.dsi.winregistre.entites.modelIreport.OrderModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    public OrderModel getOrderByCode(final String code) {

        return order(code);

    }

    private OrderModel order(String code) {
        return new OrderModel(code, address(), entries());
    }

    private AddressModel address() {
        return new AddressModel("Mouad",
                "EL Fakir",
                "Champs-Élysées",
                "75000",
                "Paris",
                "France");
    }

    private List<OrderEntryModel> entries() {
        return new ArrayList<OrderEntryModel>() {
            {
                add(new OrderEntryModel("Apple IPhone X", 1, 500d));
                add(new OrderEntryModel("Samsung Galaxy s8", 2, 400d));
                add(new OrderEntryModel("AA", 2, 400d));
                add(new OrderEntryModel("BB", 2, 400d));

            }
        };
    }
}
