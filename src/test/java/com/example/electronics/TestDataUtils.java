package com.example.electronics;


import com.example.electronics.model.Product;

public class TestDataUtils {

    private TestDataUtils() {

    }

    public static Product createTestProductA() {
        return Product.builder()
                .productName("prod1")
                .productDescription("some rando description")
                .price(100.10F)
                .stock(10)
                .build();
    }

    public static Product createTestProductB() {
        return Product.builder()
                .productName("prod2")
                .productDescription("some rando second description")
                .price(150.45F)
                .stock(13)
                .build();
    }

    public static Product createTestProductC(){
        return Product.builder()
                .productName("prod3")
                .productDescription("some rando third description")
                .price(245.76F)
                .stock(34)
                .build();
    }

    public static Product createTestProductD(){
        return Product.builder()
                .productName("prod4")
                .productDescription("some rando fourth description")
                .price(123.56F)
                .stock(23)
                .build();
    }

    public static Product createTestProductE() {
        return Product.builder()
                .productName("prod5")
                .productDescription("some rando fifth description")
                .price(113.46F)
                .stock(27)
                .build();
    }
}

