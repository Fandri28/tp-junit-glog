package fr.emse.test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MoneyBagTest {

    private Money m12CHF;
    private Money m14CHF;
    private Money m7USD;
    private Money m21USD;

    private MoneyBag mb1; // {12CHF, 7USD}
    private MoneyBag mb2; // {14CHF, 21USD}

    @Before
    public void setUp() {
        m12CHF = new Money(12, "CHF");
        m14CHF = new Money(14, "CHF");
        m7USD  = new Money(7, "USD");
        m21USD = new Money(21, "USD");

        mb1 = new MoneyBag(m12CHF, m7USD);
        mb2 = new MoneyBag(m14CHF, m21USD);
    }


    @Test
    public void testBagSimpleAdd() {
        // {[12 CHF][7 USD]} + [14 CHF] == {[26 CHF][7 USD]}
        Money m14CHF = new Money(14, "CHF");
        Money[] bag = { new Money(26, "CHF"), new Money(7, "USD") };
        MoneyBag expected = new MoneyBag(bag);

        IMoney result = (new MoneyBag(new Money[]{ m12CHF, new Money(7, "USD") })).add(m14CHF);

        assertEquals(expected, result);
    }

    @Test
    public void testSimpleBagAdd() {
        // [14 CHF] + {[12 CHF][7 USD]} == {[26 CHF][7 USD]}
        Money m14CHF = new Money(14, "CHF");
        Money[] bag = { new Money(26, "CHF"), new Money(7, "USD") };
        MoneyBag expected = new MoneyBag(bag);

        IMoney result = m14CHF.add(new MoneyBag(new Money[]{ m12CHF, new Money(7, "USD") }));
        assertEquals(expected, result);
    }

    @Test
    public void testBagBagAdd() {
        // {[12 CHF][7 USD]} + {[14 CHF][21 USD]} == {[26 CHF][28 USD]}
        Money[] bag1 = { m12CHF, new Money(7, "USD") };
        Money[] bag2 = { new Money(14, "CHF"), new Money(21, "USD") };
        MoneyBag mb1 = new MoneyBag(bag1);
        MoneyBag mb2 = new MoneyBag(bag2);

        Money[] expectedBag = { new Money(26, "CHF"), new Money(28, "USD") };
        MoneyBag expected = new MoneyBag(expectedBag);

        IMoney result = mb1.add(mb2);

        assertEquals(expected, result);
    }

    @Test
    public void testSimplifySingleMoney() {
       
        MoneyBag bag = new MoneyBag(new Money[]{ m12CHF, m7USD });
        Money negative12CHF = new Money(-12, "CHF");


        IMoney result = bag.add(negative12CHF);

        // Vérifie que le résultat est simplifié en Money
        Money expected = m7USD;
        assertEquals(expected, result);
    }


}
