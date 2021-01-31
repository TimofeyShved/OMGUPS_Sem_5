import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BigNumTest2{
    @Test
    public void shift() throws Exception {
        BigNum2 x = new BigNum2(7);
        x.shift(1);
        assertEquals(x.toString(), "700");
        x.shift(2);
        assertEquals(x.toString(), "7000000");
        x = new BigNum2(8000000);
        x.shift(-2);
        assertEquals(x.toString(), "800");
        x.shift(-1);
        assertEquals(x.toString(), "8");
    }

    @Test
    public void isLarger() throws Exception {
        BigNum2 x = new BigNum2(7);
        BigNum2 y = new BigNum2(4);
        assertEquals(x.isLarger(y), true);
        assertEquals(y.isLarger(x), false);
        BigNum2 z = new BigNum2(120);
        assertEquals(x.isLarger(z), false);
        assertEquals(z.isLarger(y), true);
    }

    @Test
    public void mul() throws Exception {
        BigNum2 x = new BigNum2(3);
        BigNum2 y = new BigNum2(230);
        x.mul(y);
        assertEquals(x.toString(), "690");
        x.mul(y);
        assertEquals(x.toString(), "158700");
    }

    @Test
    public void sub() throws Exception {
        BigNum2 x = new BigNum2(6000);
        BigNum2 y = new BigNum2(4);
        x.sub(y);
        assertEquals(x.toString(), "5996");
        y.sub(new BigNum2(1));
        assertEquals(y.toString(), "3");
        y.sub(new BigNum2(400));
        assertEquals(y.toString(), "397");
        y.sub(new BigNum2(400));
        assertEquals(y.toString(), "3");
    }

    @Test
    public void add() throws Exception {
        BigNum2 x = new BigNum2(44);
        x.add(x);
        assertEquals(x.toString(), "88");
        x.add(new BigNum2(9999));
        assertEquals(x.toString(), "10087");
    }

    @Test
    public void toStringTest() throws Exception {
        assertEquals(new BigNum2(13).toString(), "13");
        assertEquals(new BigNum2(9999).toString(), "9999");
        assertEquals(new BigNum2(78569).toString(), "78569");
        BigNum2 x = new BigNum2(1);
        long y = Integer.parseInt(x.toString());
        assertEquals(y, 1);
    }

    @Test
    public void division() throws Exception {
        BigNum2 x = new BigNum2(300000);
        BigNum2 y = new BigNum2(3000);
        x.division(y);
        assertEquals(x.toString(), "100");
        x = new BigNum2(639);
        y = new BigNum2(3);
        x.division(y);
        assertEquals(x.toString(), "213");
    }

    @Test()
    public void constr() throws Exception {
        try {
            BigNum2 x = new BigNum2(-12);
        }
        catch (Exception e){
            assertThat(e.getMessage(), is("is Negative"));
        }
    }
}

