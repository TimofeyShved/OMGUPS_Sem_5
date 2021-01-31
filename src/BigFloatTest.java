import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

strictfp public class BigFloatTest{

    @Test
    public void normalize() throws Exception {
        BigFloat x = new BigFloat(100);
        x.sub(new BigFloat(99.99f));
        x.normalize();
        float y = Float.parseFloat(x.toString());
        assertTrue(Math.abs(y - 0.01) < 0.001);
    }


    @Test
    public void mul() throws Exception {
        BigFloat x = new BigFloat(5.3f);
        BigFloat y = new BigFloat(7.5f);
        x.mul(y);
        assertEquals(Float.parseFloat(x.toString())+"", "39.75");
        /*x.mul( new BigFloat(-2.7f));
        assertEquals(Float.parseFloat(x.toString())+"", "107.325");*/
    }

    @Test
    public void sub() throws Exception {
        BigFloat x = new BigFloat(7.5f);
        BigFloat y = new BigFloat(5.3f);
        x.sub(y);
        assertEquals(Float.parseFloat(x.toString())+"", "2.2");
        /*x.sub(new BigFloat(-1.2f));
        assertEquals(Float.parseFloat(x.toString())+"", "3.4");
        x.sub(y);
        assertEquals(Float.parseFloat(x.toString())+"", "-1.9");*/
    }


    @Test
    public void add() throws Exception {
        BigFloat x = new BigFloat(100f);
        x.add(new BigFloat((float) 0.01f));
        float y = Float.parseFloat(x.toString());
        assertTrue(Math.abs(y - 100.01) < 0.001);
        x = new BigFloat(1);
        /*x.add(new BigFloat((float) -10000));
        y = Float.parseFloat(x.toString());
        assertTrue(Math.abs(y + 9999f) < 0.001);*/
    }

    @Test
    public void toStringTest() throws Exception {
        BigFloat x = new BigFloat((float) 1);
        float y = Float.parseFloat(x.toString());
        assertTrue(Math.abs(y - 1) < 0.001);
        /*x = new BigFloat((float) -10.01);
        y = Float.parseFloat(x.toString());
        assertTrue(Math.abs(y + 10.01) < 0.001);*/
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
