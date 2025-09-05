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
        if (m.amount() == 0) return; // ignore 0

        for (int i = 0; i < fMonies.size(); i++) {
            Money existing = fMonies.get(i);
            if (existing.currency().equals(m.currency())) {
                int newAmount = existing.amount() + m.amount();
                if (newAmount == 0) {
                    fMonies.remove(i); // supprime si somme = 0
                } else {
                    fMonies.set(i, new Money(newAmount, m.currency()));
                }
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
        return result.simplify();
    }

    @Override
    public IMoney addMoneyBag(MoneyBag mb) {
        MoneyBag result = new MoneyBag(fMonies.toArray(new Money[0])); // copie
        for (Money m : mb.getMonies()) result.appendMoney(m);
        return result.simplify();
    }

    // Ajoute cette méthode dans MoneyBag
    IMoney simplify() {
        fMonies.removeIf(m -> m.amount() == 0);
        
        if (fMonies.size() == 0) {
            return new Money(0, "USD"); // mettre une devise par défaut
        } else if (fMonies.size() == 1) {
            return fMonies.get(0);
        } else {
            return this;
        }
    }



    public Money[] getMonies() {
        return fMonies.toArray(new Money[0]);
    }

    

    @Override
    public boolean equals(Object obj) {
        IMoney simplified = this.simplify(); // simplifie avant comparaison
        if (simplified instanceof Money) {
            return simplified.equals(obj); // compare avec Money si un seul élément
        }
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
