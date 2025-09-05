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

    void appendMoney(Money m) {
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
    public IMoney addMoney(Money m) {
        MoneyBag result = new MoneyBag(fMonies.toArray(new Money[0])); // copie
        result.appendMoney(m);
        return result;
    }

    @Override
    public IMoney addMoneyBag(MoneyBag mb) {
        MoneyBag result = new MoneyBag(fMonies.toArray(new Money[0])); // copie
        for (Money m : mb.getMonies()) result.appendMoney(m);
        return result;
    }


    public Money[] getMonies() {
        return fMonies.toArray(new Money[0]);
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MoneyBag other = (MoneyBag) obj;
        if (fMonies.size() != other.fMonies.size()) return false;
        for (Money m : fMonies) {
            boolean found = false;
            for (Money o : other.fMonies) {
                if (m.equals(o)) {
                    found = true;
                    break;
                }
            }
            if (!found) return false;
        }
        return true;
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
