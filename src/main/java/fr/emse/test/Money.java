package fr.emse.test;

import java.util.Objects;

class Money {
    private int fAmount;
    private String fCurrency;

    public Money(int amount, String currency) {
        fAmount = amount;
        fCurrency = currency;
    }

    public int amount() {
        return fAmount;
    }

    public String currency() {
        return fCurrency;
    }

    public Money add(Money m) {
        if (!currency().equals(m.currency())) {
            throw new IllegalArgumentException("Devises différentes !");
        }
        return new Money(amount() + m.amount(), currency());
    }
  
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // même référence
        if (obj == null || getClass() != obj.getClass()) return false;
        Money money = (Money) obj;
        return fAmount == money.fAmount && fCurrency.equals(money.fCurrency);
    }

    
}
