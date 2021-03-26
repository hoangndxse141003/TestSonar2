/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangndx.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Admin
 */
public class CartOb implements Serializable {
    private Map<String, Integer> items;

    public Map<String, Integer> getItems() {
        return items;
    }
    
    public void addBookToCart(String title){
        //1. check existed items
        if(this.items == null){
            this.items = new HashMap<>();           
        }// end if items is not existed
        //2. Check existed book
        int quantity = 1;
        if (this.items.containsKey(title)){
            quantity = this.items.get(title)+1;
        }
        this.items.put(title, quantity);
    }
    
    public void removeBookFromCart(String title){
        //1. Check existed items
        if(this.items == null) {
            return;
        }
        //2. Check existed book
        if (this.items.containsKey(title)){
            //3. remove from cart
            this.items.remove(title);
            if( this.items.isEmpty()){
                this.items = null;
            }
        }
    }
}
