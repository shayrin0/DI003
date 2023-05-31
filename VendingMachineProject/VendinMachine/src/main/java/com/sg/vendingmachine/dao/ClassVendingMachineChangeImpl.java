
package com.sg.vendingmachine.dao;

import java.util.HashMap;
import java.util.Map;


public class ClassVendingMachineChangeImpl implements ClassVendingMachineChange{
    private final Map<String, Integer> changeCoins;

    public ClassVendingMachineChangeImpl() {
        this.changeCoins = new HashMap<>();
    }
    
    enum COINS{
        QUARTERS(25), DIMES(10), NICKELS(5), PENNIES(1);
        private final int valueCoin;

        private COINS(int change) {
            this.valueCoin = change;
        }
        
        public int valueCoin(){
            return valueCoin;
        }

    }
    
    @Override
    public Map<String, Integer> getChangeUser(int pennies){
        int quarters = 0, dimes = 0, nickels = 0;        
        
        if(pennies >= 25){
            quarters = pennies / COINS.QUARTERS.valueCoin();
            pennies = pennies % COINS.QUARTERS.valueCoin();
        }
        if(pennies >= 10){
            dimes = pennies / COINS.DIMES.valueCoin();
            pennies = pennies % COINS.DIMES.valueCoin();
        }
        if(pennies >= 5){
            nickels = pennies / COINS.NICKELS.valueCoin();
            pennies = pennies % COINS.NICKELS.valueCoin();
        }
        
        changeCoins.put(COINS.PENNIES.name(), pennies);
        changeCoins.put(COINS.NICKELS.name(), nickels);
        changeCoins.put(COINS.DIMES.name(), dimes);
        changeCoins.put(COINS.QUARTERS.name(), quarters);
        
        return changeCoins;
    }
}
