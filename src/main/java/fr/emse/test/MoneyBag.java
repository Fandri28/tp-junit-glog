package fr.emse.test;

import java.util.Vector;

class MoneyBag implements IMoney {
    private Vector<Money> fMonies = new Vector<>();

    public MoneyBag(Money m1, Money m2) {
        appendMoney(m1);
        appendMoney(m2);
    }

    public MoneyBag(Money[] bag) {
        for (Money m : bag) {
            appendMoney(m);
        }
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
        if (aMoney instanceof Money) {
            appendMoney((Money) aMoney);
        } else if (aMoney instanceof MoneyBag) {
            MoneyBag other = (MoneyBag) aMoney;
            for (Money m : other.fMonies) {
                appendMoney(m);
            }
        } else {
            throw new IllegalArgumentException("Type inconnu");
        }
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
