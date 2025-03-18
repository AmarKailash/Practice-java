package com.mockcompany.webapp.controller;

import com.mockcompany.webapp.model.ProductItem;
import com.mockcompany.webapp.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * This class is the entry point for the /api/products/search API.
 * It is annotated with @RestController, telling Spring Boot that it will provide API endpoints.
 */
@RestController
public class SearchController {

    private final SearchService searchService;

    /**
     * Constructor injection of SearchService using @Autowired.
     * Spring will automatically inject an instance of SearchService when creating this controller.
     */
    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    /**
     * Handles GET requests for searching products.
     * The @RequestParam annotation captures the query parameter from the URL.
     * Example: /api/products/search?query=laptop
     *
     * @param query The search query string.
     * @return A collection of filtered ProductItem objects matching the search criteria.
     */
    @GetMapping("/api/products/search")
    public Collection<ProductItem> search(@RequestParam("query") String query) {
        return searchService.search(query);
    }
}
