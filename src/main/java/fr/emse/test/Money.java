package fr.emse.test;

import java.util.Objects;

public class Money implements IMoney {
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

    @Override
    public IMoney add(IMoney m) {
        return m.addMoney(this);
    }

    @Override
    public IMoney addMoney(Money m) {
        if (m.currency().equals(currency())) {
            return new Money(amount() + m.amount(), currency());
        } else {
            return new MoneyBag(new Money[]{this, m});
        }
    }

    @Override
    public IMoney addMoneyBag(MoneyBag mb) {
        // Création d'un nouveau MoneyBag avec les valeurs du MoneyBag existant + ce Money
        MoneyBag result = new MoneyBag(mb.getMonies());
        result.appendMoney(this);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Money money = (Money) obj;
        return fAmount == money.fAmount && fCurrency.equals(money.fCurrency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fAmount, fCurrency);
    }

    @Override
    public String toString() {
        return fAmount + " " + fCurrency;
    }
}
