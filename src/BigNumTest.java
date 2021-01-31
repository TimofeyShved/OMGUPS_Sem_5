import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BigNumTest{
    @Test
    public void shift() throws Exception {
        BigNum x = new BigNum(7);
        x.shift(1);
        assertEquals(x.toString(), "700");
    }

    @Test
    public void isLarger() throws Exception {
        BigNum x = new BigNum(7);
        BigNum y = new BigNum(4);
        assertEquals(x.isLarger(y), true);
        assertEquals(y.isLarger(x), false);
    }

    @Test
    public void mul() throws Exception {
        BigNum x = new BigNum(3);
        BigNum y = new BigNum(230);
        x.mul(y);
        assertEquals(x.toString(), "690");
    }

    @Test
    public void sub() throws Exception {
        BigNum x = new BigNum(6);
        BigNum y = new BigNum(4);
        x.sub(y);
        assertEquals(x.toString(), "2");
        y.sub(new BigNum(1));
        assertEquals(y.toString(), "3");
    }

    @Test
    public void add() throws Exception {
        BigNum x = new BigNum(44);
        x.add(x);
        assertEquals(x.toString(), "88");
        x.add(new BigNum(9999));
        assertEquals(x.toString(), "10087");
    }

    @Test
    public void toStringTest() throws Exception {
        assertEquals(new BigNum(13).toString(), "13");
        assertEquals(new BigNum(9999).toString(), "9999");
        assertEquals(new BigNum(78569).toString(), "78569");
        BigNum x = new BigNum(1);
        long y = Integer.parseInt(x.toString());
        assertEquals(y, 1);
    }

    @Test
    public void division() throws Exception {
        BigNum x = new BigNum(300000);
        BigNum y = new BigNum(3000);
        x.division(y);
        assertEquals(x.toString(), "100");
        x = new BigNum(639);
        y = new BigNum(3);
        x.division(y);
        assertEquals(x.toString(), "213");
    }

    @Test()
    public void constr() throws Exception {
        try {
            BigNum x = new BigNum(-12);
        }
        catch (Exception e){
            assertThat(e.getMessage(), is("is Negative"));
        }
    }
}

