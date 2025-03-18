package com.mockcompany.webapp.services;

import com.mockcompany.webapp.data.ProductItemRepository;
import com.mockcompany.webapp.model.ProductItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class SearchService {

    private final ProductItemRepository productItemRepository;

    @Autowired
    public SearchService(ProductItemRepository productItemRepository) {
        this.productItemRepository = productItemRepository;
    }

    public Collection<ProductItem> search(String query) {
        Iterable<ProductItem> allItems = productItemRepository.findAll();
        List<ProductItem> itemList = new ArrayList<>();
        String lowerCaseQuery = query.toLowerCase();

        for (ProductItem item : allItems) {
            if (item.getName().toLowerCase().contains(lowerCaseQuery) ||
                item.getDescription().toLowerCase().contains(lowerCaseQuery)) {
                itemList.add(item);
            }
        }
        return itemList;
    }
}
