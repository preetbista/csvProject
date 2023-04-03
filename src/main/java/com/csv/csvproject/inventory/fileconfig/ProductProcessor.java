package com.csv.csvproject.inventory.fileconfig;

import com.csv.csvproject.inventory.model.Products;
import org.springframework.batch.item.ItemProcessor;
public class ProductProcessor implements ItemProcessor<Products, Products> {

    @Override
    public Products process(Products item) throws Exception {
        return item;
    }
}
