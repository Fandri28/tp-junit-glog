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
        // Money + MoneyBag
        MoneyBag expected = new MoneyBag(new Money[]{ m12CHF, m14CHF, m21USD });
        IMoney result = m12CHF.add(mb2);
        assertEquals(expected, result);
    }

    @Test
    public void testSimpleBagAdd() {
        // MoneyBag + Money
        MoneyBag expected = new MoneyBag(new Money[]{ m12CHF, m7USD, m14CHF });
        IMoney result = mb1.add(m14CHF);
        assertEquals(expected, result);
    }

    @Test
    public void testBagBagAdd() {
        // MoneyBag + MoneyBag
        MoneyBag expected = new MoneyBag(new Money[]{ m12CHF, m14CHF, m7USD, m21USD });
        IMoney result = mb1.add(mb2);
        assertEquals(expected, result);
    }
}
