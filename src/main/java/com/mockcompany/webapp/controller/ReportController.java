package com.mockcompany.webapp.controller;

import com.mockcompany.webapp.api.SearchReportResponse;
import com.mockcompany.webapp.model.ProductItem;
import com.mockcompany.webapp.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import java.util.*;
import java.util.regex.Pattern;

/**
 * The ReportController generates a daily report containing the number of products that match
 * predefined search terms. This is required for business intelligence and decision-making.
 */
@RestController
public class ReportController {

    private final EntityManager entityManager;
    private final SearchService searchService;

    /**
     * Constructor-based dependency injection for SearchService and EntityManager.
     */
    @Autowired
    public ReportController(EntityManager entityManager, SearchService searchService) {
        this.entityManager = entityManager;
        this.searchService = searchService;
    }

    /**
     * Generates a report for products matching predefined search terms.
     *
     * @return A response object containing product counts and term-based matches.
     */
    @GetMapping("/api/products/report")
    public SearchReportResponse runReport() {
        Map<String, Integer> hits = new HashMap<>();
        SearchReportResponse response = new SearchReportResponse();
        response.setSearchTermHits(hits);

        // Fetch total product count
        int count = entityManager.createQuery("SELECT item FROM ProductItem item").getResultList().size();
        response.setProductCount(count);

        // Search terms for report
        List<String> searchTerms = Arrays.asList("Cool", "Kids", "Amazing", "Perfect");

        for (String term : searchTerms) {
            int matchCount = searchService.search(term).size();
            hits.put(term, matchCount);
        }

        return response;
    }
}
