package fr.emse.test;

import java.util.Vector;

public class MoneyBag implements IMoney {
    private Vector<Money> fMonies = new Vector<>();

    public MoneyBag(Money m1, Money m2) {
        appendMoney(m1);
        appendMoney(m2);
    }

    public MoneyBag(Money[] bag) {
        for (Money m : bag) appendMoney(m);
    }

    private void appendMoney(Money m) {
        for (int i = 0; i < fMonies.size(); i++) {
            Money existing = fMonies.get(i);
            if (existing.currency().equals(m.currency())) {
                fMonies.set(i, new Money(existing.amount() + m.amount(), m.currency()));
                return;
            }
        }
        fMonies.add(m);
    }

    @Override
    public IMoney add(IMoney aMoney) {
        return aMoney.addMoneyBag(this);
    }

    @Override
    public IMoney addMoney(Money m) { // doit correspondre à IMoney
        appendMoney(m);
        return this;
    }

    @Override
    public IMoney addMoneyBag(MoneyBag mb) { // doit correspondre à IMoney
        for (Money m : mb.fMonies) appendMoney(m);
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MoneyBag other = (MoneyBag) obj;
        return fMonies.equals(other.fMonies);
    }

    @Override
    public int hashCode() {
        return fMonies.hashCode();
    }

    @Override
    public String toString() {
        return fMonies.toString();
    }
}
