package com.myfood.controller;

import java.text.NumberFormat;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;

import com.myfood.model.*;

@Controller
public class CartController {

	ArrayList<SelectedItems> cart = new ArrayList<SelectedItems>();
    private int itemCount;      // total number of items in the cart
    private double totalPrice;  // total price of items in the cart

    public CartController()
    {
      itemCount = 0;
      totalPrice = 0.0;
    }

    public void addToCart(int itemId,int restaurantId,String itemName,String itemCategory,double itemCost,int itemCalories,int itemQuantity)
    { 

        SelectedItems temp = new SelectedItems(itemId,restaurantId,itemName,itemCategory,itemCost,itemCalories,itemQuantity);
        totalPrice += (itemCost * itemQuantity);
        itemCount += itemQuantity;
        cart.add(temp);
        
    }
    
    public String toString()
    {
      NumberFormat fmt = NumberFormat.getCurrencyInstance();

      String contents = "\nShopping Cart\n";
      contents += "\nItem\t\tUnit Price\tQuantity\tTotal\n";

      for (SelectedItems items:cart)
          contents += items.toString() + "\n";

      contents += "\nTotal Price: " + fmt.format(totalPrice);
      contents += "\n";

      return contents;
    }
    
}
