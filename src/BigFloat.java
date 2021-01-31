import java.lang.*;

strictfp class BigFloat extends BigNum implements Numb{
    private boolean isPositive=true;
    private long shift;

    BigFloat(float x){
        super();
        if(x<0)
            throw new ArithmeticException("is Negative");
        shift = -(long) (Math.log10((1000000)/Math.abs(x))/2);
        long xl = (long) (x*Math.pow(100, -shift));
        if(x>0)
            isPositive = true;
        else {
            xl = -xl;
            isPositive = false;
        }
        fromLong(xl);
        normalize();
    }

    BigFloat(BigFloat x){
        super(x);
        isPositive = x.isPositive;
        shift = x.shift;
    }

    private void calcShift(BigFloat x){
        if(shift < x.shift)
            shift((int) (shift- x.shift));
        else
            x.shift((int) (x.shift - shift));
        shift = Math.max(shift, x.shift);
    }

    void normalize(){
        int i;
        for(i=len-1; i>=0 && digits[i]==0; i--)
            ;
        shift(len-i-1);
        shift = shift - len + i + 1;
    }

    @Override
    public void add(Numb x){
        BigFloat floatNum = new BigFloat((BigFloat) x);
        if (floatNum.isPositive) {
            calcShift(floatNum);
            if (isPositive){
                super.add(floatNum);
            }
            else if (super.isLarger(floatNum)){
                super.sub(floatNum);
            }
            else {
                ((BigNum) floatNum).sub(this);
                digits = floatNum.digits.clone();
                shift = floatNum.shift;
                isPositive = false;
            }
            normalize();
        } else {
            floatNum.isPositive = true;
            sub(floatNum);
        }
    };
    @Override
    public void sub(Numb x) {
        BigFloat floatNum = new BigFloat((BigFloat) x);
        // новое значение мы приводим к BigFloat
        calcShift(floatNum);
        if(new BigFloat((BigFloat)x).isPositive)
        {   //+?
            if(isPositive) // в зависимости от знака делаем действие
            {   //+?
                if(super.isLarger(floatNum))
                {
                    super.sub(floatNum);
                }
                else {
                    ((BigNum) floatNum).sub(this);
                    digits = floatNum.digits.clone();
                    shift = floatNum.shift;
                    isPositive = false;
                }
            }
            else {
                super.add(floatNum);
            }
        }
        else {
            if(isPositive)
            {   //+?
                super.add(floatNum);
            }
            else {
                if(super.isLarger(floatNum))
                {   //больше?
                    super.sub(floatNum);
                }
                else {
                    ((BigNum) floatNum).sub(this);
                    digits = floatNum.digits.clone();
                    shift = floatNum.shift;
                    isPositive = false;
                }
            }
        }
        // данный момент отпадает,
        // так как у нас есть проверка,
        // на то что бы он не было наше число<0
        normalize();
    };
    @Override
    public void mul(Numb x){
        //умножение
        BigFloat floatNum = new BigFloat((BigFloat) x);
        calcShift(floatNum);
        //переменные
        floatNum.shift(-(len/2));
        super.shift(-(len/2));
        super.mul((BigNum) floatNum);
        shift=shift+ floatNum.shift+len;
        if(((new BigFloat((BigFloat) x).isPositive)&&(isPositive))||((!new BigFloat((BigFloat) x).isPositive)&&(!isPositive)))
        {   //++ или --?
            isPositive=true;
        }
        else {
            isPositive=false;
        }
        normalize();
    };
    @Override
    public void mul(long x){
        mul(new BigNum(x));
    };

    @Override
    public Numb getNumb(int x){
        BigFloat getNum = new BigFloat(x);
        return getNum;
    };
    @Override
    public String toString(){
        String sign = isPositive?"":"-";
        return sign + super.toString() + "e" + ((Long)(shift*2)).toString();
    };

    /*
    @Override
    public void division(Numb x) {

        //shift-=((BigFloat)x).shift;
        //for ()
        ((BigFloat)x).shift();
        System.out.println(x);
        super.division(x);
        normalize();

    };

    public void division(int x) {
        super.division(x);
        normalize();
    };

     */

    /*
    private boolean isPositive;
    private long shift;

    BigFloat(float x){
        super();
        shift = -(long) (Math.log10(1000000/Math.abs(x))/2);
        long xl = (long) (x*Math.pow(100, -shift));
        if(x>0)
            isPositive = true;
        else {
            xl = -xl;
            isPositive = false;
        }
        fromLong(xl);
        normalize();
    }

    BigFloat(BigFloat x){
        super(x);
        isPositive = x.isPositive;
        shift = x.shift;
    }

    private void calcShift(BigFloat x){
        if(shift < x.shift)
            shift((int) (shift- x.shift));
        else
            x.shift((int) (x.shift - shift));
        shift = Math.max(shift, x.shift);
    }

    void normalize(){
        int i;
        for(i=len-1; i>=0 && digits[i]==0; i--)
            ;
        shift(len-i-1);
        shift = shift - len + i + 1;
    }

    void add(BigFloat x) {
        BigFloat floatNum = new BigFloat(x);
        if (floatNum.isPositive) {
            calcShift(floatNum);
            if (isPositive)
                super.add(floatNum);
            else if (super.isLarger(floatNum))
                super.sub(floatNum);
            else {
                ((BigNum) floatNum).sub(this);
                digits = floatNum.digits.clone();
                shift = floatNum.shift;
                isPositive = false;
            }
            normalize();
        } else {
            floatNum.isPositive = true;
            sub(floatNum);
        }
    }

    void sub(BigFloat x) {
        BigFloat floatNum = new BigFloat(x);
        calcShift(floatNum);
        if(x.isPositive)
        {   //+?
            if(isPositive)
            {   //+?
                if(super.isLarger(floatNum))
                {
                    super.sub(floatNum);
                }
                else {
                    ((BigNum) floatNum).sub(this);
                    digits = floatNum.digits.clone();
                    shift = floatNum.shift;
                    isPositive = false;
                }
            }
            else {
                super.add(floatNum);
            }
        }
        else {
            if(isPositive)
            {   //+?
                super.add(floatNum);
            }
            else {
                if(super.isLarger(floatNum))
                {   //больше?
                    super.sub(floatNum);
                }
                else {
                    ((BigNum) floatNum).sub(this);
                    digits = floatNum.digits.clone();
                    shift = floatNum.shift;
                    isPositive = false;
                }
            }
        }
        normalize();
    }

    void mul(BigFloat x){
        //умножение
        BigFloat floatNum = new BigFloat(x);
        calcShift(floatNum);
        //переменные
        floatNum.shift(-2);
        super.shift(-2);
        super.mul((BigNum) floatNum);
        shift=shift+ floatNum.shift+4;
        if(((x.isPositive)&&(isPositive))||((!x.isPositive)&&(!isPositive)))
        {   //++ или --?
            isPositive=true;
        }
        else {
            isPositive=false;
        }
        normalize();
    }

    public String toString(){
        String sign = isPositive?"":"-";
        return sign + super.toString() + "e" + ((Long)(shift*2)).toString();
    }

     */
}

