import java.*;
import java.util.ArrayList;

public class BigNum2 implements Numb{
    static final int base = 100;
    ArrayList<Byte> digits = new ArrayList<>();

    BigNum2(){}

    public BigNum2(int x) {
        if(x<0)
            throw new ArithmeticException("is Negative");
        while(x>0){
            digits.add((byte) (x % base));
            x = x / base;
        }
    }

    void add(BigNum2 x){
        byte c=0;
        int sum;
        if(digits.size()>x.digits.size()){
            int i=0;
            for(; i<x.digits.size(); i++){
                sum = digits.get(i)+x.digits.get(i)+c;
                digits.set(i, (byte) (sum % base));
                c = (byte) (sum / base);
            }
            for(; i<digits.size(); i++){
                sum = digits.get(i)+c;
                digits.set(i, (byte) (sum % base));
                c = (byte) (sum / base);
            }
            if(c==1) digits.add((byte) 1);
        } else {
            int i=0;
            for(; i<digits.size(); i++){
                sum = digits.get(i)+x.digits.get(i)+c;
                digits.set(i, (byte) (sum % base));
                c = (byte) (sum / base);
            }
            for(; i<x.digits.size(); i++){
                sum = x.digits.get(i)+c;
                digits.add((byte) (sum % base));
                c = (byte) (sum / base);
            }
            if(c==1) digits.add((byte) 1);
        }
    }


    void mul(BigNum2 x){
        int tmpNum;
        BigNum2 newerNum = new BigNum2(0);        //переменные
        BigNum2 summNum = new BigNum2(0);
        ArrayList<Byte> my_one_Num, my_two_Num;
        my_one_Num = digits;
        my_two_Num = ((BigNum2)x).digits;

        for(int i=0; i<digits.size(); i++){
            if (my_one_Num.get(i)!=0)
                for(int j=0; j<x.digits.size(); j++){
                    if(my_two_Num.get(j)!=0) {
                        //2 цикла
                            for (int k = 0; k < newerNum.digits.size(); k++) {
                                //сдвиг в зависимости от положения
                                newerNum.digits.set(k, (byte) (0));
                            }
                        newerNum = mulDigit(my_one_Num.get(i), my_two_Num.get(j), (i+j));
                        //в переменную записываем полученное число
                        summNum.add(newerNum);
                        //сумма
                    }
                }
        }
        digits = summNum.digits;
    }

    private BigNum2 mulDigit(byte digit, byte digit1, int shift) {
        BigNum2 mulDigitNum = new BigNum2(digit*digit1);
        mulDigitNum.shift(shift);//сдвиг при умножении
        return mulDigitNum;
    }

    protected void shift(int x){
        //сдвиг
        if (x!=0){
            if(x>0){
                for (int j=0; j<x; j++) {
                    digits.add(digits.get(digits.size()-1));
                    int z = digits.size()-1;
                    for (int i = z; i>0; i--) {
                        digits.set(i, digits.get(i-1));
                    }
                    digits.set(0, (byte) 0);
                }
            }
            else {
                for (int j=0; j>x; j--) {
                    int z = digits.size()-1;
                    for (int i = 0; i<z; i++) {
                        digits.set(i, digits.get(i+1));
                    }
                    digits.remove(z);
                }
            }
        }
    }

    @Override
    public void add(Numb x) {
        this.mul(((BigNum2) x));
    }

    @Override
    public void mul(Numb x) {
        this.mul(((BigNum2) x));
    }

    @Override
    public void mul(long x) {
        this.mul(new BigNum2((int) x));
    }

    @Override
    public void division(Numb x) {
        int XNumb=((BigNum2)x).digits.get(0);
        for(int i=1; i<((BigNum2)x).digits.size(); i++){
            XNumb+=((BigNum2)x).digits.get(i)*( Math.pow(base, i));
        }
        division(XNumb);
    }

    @Override
    public void division(int x) {
        BigNum2 newerNum = new BigNum2(0);
        ArrayList<Byte> my_one_Num = digits;
        int Mynum=0;
        for(int i=my_one_Num.size()-1; i>=0; i--){

            /*
            if(newerNum.digits.size()<my_one_Num.size()){
            newerNum.digits.add(0,(byte) 0);
            }
                        //System.out.println(newerNum.digits.get(i));
             */

            Mynum += (my_one_Num.get(i));
            if (Mynum>=x){
                newerNum.digits.add(0, (byte) ((Mynum)/x));
                if(i!=0){
                    Mynum+=(Mynum % x);
                }
                Mynum -= newerNum.digits.get(0)*x;
            }
            else {
                newerNum.digits.add(0, (byte) (0));
            }
            Mynum *= base;
        }
        digits = newerNum.digits;
    }



    @Override
    public void sub(Numb x){
        //переменные
        BigNum2 new_sub_num = new BigNum2(0);
        BigNum2 summNum = new BigNum2(0);
        ArrayList<Byte> my_one_Num, my_two_Num;
        if (isLarger(((BigNum2)x))){
            //кто больше?
            my_one_Num = digits;
            // для правильного порядка отнимания
            my_two_Num = ((BigNum2)x).digits;
        } else {
            //System.out.println("-");
            my_one_Num = ((BigNum2)x).digits;
            my_two_Num = digits;
        }
        int len = my_one_Num.size();
        for(int i=0; i<len; i++){//цикл
            if (my_two_Num.size()<len) {
                my_two_Num.add((byte) 0);
            }
            new_sub_num.digits.add(i, (byte)(my_one_Num.get(i) - my_two_Num.get(i)));
            //вычитание
            if (new_sub_num.digits.get(i) <0){
                //больше 0?
                new_sub_num.digits.set(i,(byte)(new_sub_num.digits.get(i)+base));
                my_one_Num.set(i+1, (byte)(my_one_Num.get(i+1)-1));
                //забираем еденицу у впереди стоящего
            }//записываем в новое число
            my_one_Num.set(i, (byte) new_sub_num.digits.get(i));
        }
        this.digits= my_one_Num;
        //записываем нужные числа в начальную переменную
    };

    @Override
    public Numb getNumb(int x) {
        return new BigNum(x);
    }

    @Override
    public boolean isLarger(Numb x) {
        return isLarger((BigNum2) x);
    }

    boolean isLarger(BigNum2 x){
        if (digits.size()>x.digits.size())
        {
            return true;
        }
        else if(digits.size()<x.digits.size())
        {
            return false;
        }
        else {
            for(int i=digits.size()-1; i>=0; i--){
                //вычислениее большего
                if (digits.get(i)!=x.digits.get(i)) {
                    if (digits.get(i)>x.digits.get(i)) {
                        return true;
                    }
                    else {
                        return false;
                    }
                }
            }
            return false;
        }
    }

    public String toString(){
        if(digits.size()==0) return "0";
        String result = "";
        int i = digits.size() - 1;
        while(i>0 & digits.get(i)==0)
            i--;
        while(i>=0) {
            String d = ((Byte) digits.get(i--)).toString();
            if(d.length()==1)
                d = "0" + d;
            result = result + d;
        }
        if(result.charAt(0)=='0')
            return result.substring(1);
        return result;
    }

}
