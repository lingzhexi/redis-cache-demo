package com.codeling.rediscache.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {

    private Long id;

    private String name;

    private Integer category;

    private String description;

    private Integer stock;

}