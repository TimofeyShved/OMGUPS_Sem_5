import java.lang.*;

class BigNum implements Numb{
    static final int base = 100;    // кратность
    static final int len = 100;     // длинна массива
    protected byte [] digits;       //внутренний массив с которым ми работаем

    boolean sign = true;            //знак - или +

    BigNum(){
        digits = new byte[len];     // массив байтов(100)
    }

    BigNum(long x){     //если большое число
        this();
        if(x<0)
            throw new ArithmeticException("is Negative");
        fromLong(x);    //вызываем фнутреную функцию
    }

    BigNum(BigNum bigNum){                  // получем такой же класс?
        digits = bigNum.digits.clone();     // клонируем массив из него
    }

    protected void fromLong(long x){        // получаем большое число
        for(int i=0; x>0; i++){             //запускаем цикл, пока число больше 0
            digits[i] = (byte) (x % base); //остаток от деления на 100, переводим в байткод
            x = x / base;                   // делим на 100
            if(i==len){
                throw new ArithmeticException("is more");
            }
        }
    }

    @Override
    public void add(Numb x){                            // переопределённая функция +
        byte c=0;
        int sum;
        for(int i=0; i<len; i++){                       //запускаем цикл
            sum = digits[i]+((BigNum)x).digits[i]+c;
            digits[i] = (byte) (sum % base);
            c = (byte) (sum / base);
        }
    }

    @Override
    public void sub(Numb x){
        int new_sub_num;
        //переменные
        byte[]  my_one_Num = new byte[len],
                my_two_Num = new byte[len];
        if (isLarger(((BigNum)x))){
            //кто больше?
            my_one_Num = digits;
            // для правильного порядка отнимания
            my_two_Num = ((BigNum)x).digits;
        }
        else {
            sign=false;
            my_two_Num = digits;
            my_one_Num = ((BigNum)x).digits;
        }
        for(int i=0; i<len; i++){
            //цикл
            new_sub_num = my_one_Num[i] - my_two_Num[i];
            //вычитание
            if (new_sub_num <0){
                //больше 0?
                new_sub_num +=base;
                my_one_Num[i+1]-=1;
                //забираем еденицу у впереди стоящего
            }
            my_one_Num[i]=(byte) new_sub_num;
            //записываем в новое число
        }
        this.digits= my_one_Num;
        //записываем нужные числа в начальную переменную
    }

    @Override
    public void mul(Numb x) {
        int tmpNum;
        BigNum newerNum = new BigNum();        //переменные
        BigNum summNum = new BigNum();
        byte[] my_one_Num = new byte[len],
                my_two_Num = new byte[len];
        my_one_Num = digits;
        my_two_Num = ((BigNum)x).digits;

        for(int i=0; i<len; i++){
            if (my_one_Num[i]!=0)
            for(int j=0; j<len; j++){
                if(my_two_Num[j]!=0) {
                    //2 цикла
                    for (int k = 0; k < len; k++) {
                        //сдвиг в зависимости от положения
                        newerNum.digits[k] = 0;
                    }
                    newerNum = mulDigit(my_one_Num[i], my_two_Num[j], (i + j));
                    //в переменную записываем полученное число
                    summNum.add(newerNum);
                    //сумма
                }
            }
        }
        digits = summNum.digits;

        /*
        int tmpNum;
        BigNum newerNum = new BigNum();        //переменные
        BigNum summNum = new BigNum();
        BigNum shiftNum = new BigNum();
        byte[] my_one_Num = new byte[len],
                my_two_Num = new byte[len];
        my_one_Num = digits;
        my_two_Num = ((BigNum)x).digits;

        for(int i=0; i<len; i++){
            for(int j=0; j<len; j++){
                //2 цикла
                tmpNum = my_one_Num[i]* my_two_Num[j];
                //в переменную записываем полученное число
                for(int k=0; k<len; k++){
                    //сдвиг в зависимости от положения
                    shiftNum.digits[k]=0;
                    newerNum.digits[k]=0;
                }
                newerNum.fromLong(tmpNum);//перевод в другой формат
                for(int k=0; k<len; k++){
                    shiftNum = mulDigit(newerNum.digits[k],, (j+i+k));
                    summNum.add(shiftNum);
                    //сумма
                }
            }
        }
        digits = summNum.digits;

         */
    }

    @Override
    public void mul(long x) {
        this.mul(new BigNum(x));
    }

    @Override
    public Numb getNumb(int x) {
        BigNum getNum = new BigNum(x);
        return getNum;
    }


    void mul(BigNum x, int shift){
        this.mul(x);
        this.shift(shift);
    }

    public boolean isLarger(Numb x){
        return isLarger((BigNum) x);
    }

    public boolean isLarger(BigNum x){
        for(int i=len-1; i>=0; i--){
            //вычислениее большего
            if (digits[i]!=x.digits[i]) {
                if (digits[i]>x.digits[i]) {
                    return true;
                }
                else {
                    return false;
                }

            }
        }
        return false;
    }

    private BigNum mulDigit(byte digit, byte digit1, int shift) {
        BigNum mulDigitNum = new BigNum(digit*digit1);
        mulDigitNum.shift(shift);//сдвиг при умножении
        return mulDigitNum;
    }

    @Override
    public void division(Numb x) {
        int XNumb=((BigNum)x).digits[0];
        for(int i=1; i<len; i++){
            XNumb+=((BigNum)x).digits[i]*( Math.pow(base, i));
        }
        division(XNumb);
    }

    @Override
    public void division(int x) {
        BigNum newerNum = new BigNum(0);
        byte[] my_one_Num = digits;
        int Mynum=my_one_Num[len-1];
        for(int i=len-1; i>=0; i--){
            Mynum += (my_one_Num[i]);
            if (Mynum>=x){
                newerNum.digits[i]= (byte) ((Mynum)/x);
                if(i!=0){
                    Mynum+=(Mynum % x);
                }
                Mynum -= newerNum.digits[i]*x;
            }
            Mynum *= base;
        }
        digits = newerNum.digits;
    }

    protected void shift(int x){
        //сдвиг
        if(x<0){
            for (int j=0; j>x; j--) {
                for (int i = 0; i<len-1; i++) {
                    digits[i] = digits[i+1];
                }
                digits[len-1]=0;
            }
        }
        else {
            for (int j=0; j<x; j++) {
                for (int i = len-1; i>0; i--) {
                    digits[i] = digits[i-1];
                }
                digits[0]=0;
            }
        }
    }

    public String toString(){
        String result = "";
        int i = len - 1;
        while(i>0 & digits[i]==0)
            i--;
        while(i>=0) {
            String d = ((Byte) digits[i--]).toString();
            if(d.length()==1)
                d = "0" + d;
            result = result + d;
        }
        if(result.charAt(0)=='0')
            return result.substring(1);
        return result;
    }

}

