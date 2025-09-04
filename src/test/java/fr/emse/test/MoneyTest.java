package fr.emse.test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MoneyTest {

    private Money m12CHF;
    private Money m14CHF;

    
    //exécuté avant chaque test
    @Before
    public void setUp() {
        m12CHF = new Money(12, "CHF");
        m14CHF = new Money(14, "CHF");
    }

    @Test
    public void testSimpleAdd() {
        Money expected = new Money(26, "CHF");
        Money result = (Money) m12CHF.add(m14CHF);
        assertTrue(expected.equals(result));
    }

    @Test
    public void testEquals() {
        Money another12CHF = new Money(12, "CHF");
        assertTrue(m12CHF.equals(another12CHF));
        assertFalse(m12CHF.equals(m14CHF));
    }
    
    @Test
    public void testMixedSimpleAdd() {
        Money m7USD = null;
		Money[] bag = { m12CHF, m7USD };
        MoneyBag expected = new MoneyBag(bag);
        assertEquals(expected, m12CHF.add(m7USD));  // addition Money + Money retourne MoneyBag
    }
}
