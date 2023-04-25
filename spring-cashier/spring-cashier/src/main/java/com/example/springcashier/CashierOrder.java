package com.example.springcashier;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class CashierOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String drink;
    private String milk;
    private String size;
    private String total;
    private String register;
    private String status;
    private String store;

    public static CashierOrder GetNewOrder() {
        String[] DRINK_OPTIONS = { "Caffe Latte", "Caffe Americano", "Caffe Mocha", "Espresso", "Cappuccino" };
        String[] MILK_OPTIONS = { "Whole Milk", "2% Milk", "Nonfat Milk", "Almond Milk", "Soy Milk" };
        String[] SIZE_OPTIONS = { "Short", "Tall", "Grande", "Venti" };

        Random random = new Random();

        String randomDrink = DRINK_OPTIONS[random.nextInt(DRINK_OPTIONS.length)];
        String randomMilk = MILK_OPTIONS[random.nextInt(MILK_OPTIONS.length)];
        String randomSize = SIZE_OPTIONS[random.nextInt(SIZE_OPTIONS.length)];

        CashierOrder cashierOrder = new CashierOrder();
        cashierOrder.setDrink(randomDrink);
        cashierOrder.setMilk(randomMilk);
        cashierOrder.setSize(randomSize);
        cashierOrder.setTotal(calculateTotal(randomDrink, randomSize));
        cashierOrder.setStatus("New");

        return cashierOrder;
    }

    private static String calculateTotal(String drink, String size) {
        Map<String, Map<String, Double>> prices = new HashMap<>();

        Map<String, Double> caffeLattePrices = new HashMap<>();
        caffeLattePrices.put("Tall", 2.95);
        caffeLattePrices.put("Grande", 3.65);
        caffeLattePrices.put("Venti", 3.95);
        prices.put("Caffe Latte", caffeLattePrices);

        Map<String, Double> caffeAmericanoPrices = new HashMap<>();
        caffeAmericanoPrices.put("Tall", 2.25);
        caffeAmericanoPrices.put("Grande", 2.65);
        caffeAmericanoPrices.put("Venti", 2.95);
        prices.put("Caffe Americano", caffeAmericanoPrices);

        Map<String, Double> caffeMochaPrices = new HashMap<>();
        caffeMochaPrices.put("Tall", 3.45);
        caffeMochaPrices.put("Grande", 4.15);
        caffeMochaPrices.put("Venti", 4.45);
        prices.put("Caffe Mocha", caffeMochaPrices);

        Map<String, Double> cappuccinoPrices = new HashMap<>();
        cappuccinoPrices.put("Tall", 2.95);
        cappuccinoPrices.put("Grande", 3.65);
        cappuccinoPrices.put("Venti", 3.95);
        prices.put("Cappuccino", cappuccinoPrices);

        Map<String, Double> espressoPrices = new HashMap<>();
        espressoPrices.put("Short", 1.75);
        espressoPrices.put("Tall", 1.95);
        prices.put("Espresso", espressoPrices);

        double total = prices.get(drink).get(size);
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(total);

    }

}


/*

https://priceqube.com/menu-prices/%E2%98%95-starbucks

var DRINK_OPTIONS = [ "Caffe Latte", "Caffe Americano", "Caffe Mocha", "Espresso", "Cappuccino" ];
var MILK_OPTIONS  = [ "Whole Milk", "2% Milk", "Nonfat Milk", "Almond Milk", "Soy Milk" ];
var SIZE_OPTIONS  = [ "Short", "Tall", "Grande", "Venti", "Your Own Cup" ];

Caffè Latte
=============
tall 	$2.95
grande 	$3.65
venti 	$3.95 (Your Own Cup)

Caffè Americano
===============
tall 	$2.25
grande 	$2.65
venti 	$2.95 (Your Own Cup)

Caffè Mocha
=============
tall 	$3.45
grande 	$4.15
venti 	$4.45 (Your Own Cup)

Cappuccino
==========
tall 	$2.95
grande 	$3.65
venti 	$3.95 (Your Own Cup)

Espresso
========
short 	$1.75
tall 	$1.95

 */



