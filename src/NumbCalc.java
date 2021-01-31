import java.lang.*;
import java.util.ArrayList;
import java.util.Collections;

class NumbCalc{
    private Numb numb;

    NumbCalc(Numb numb) {
        this.numb = numb;
    }

    Numb fact(int x){
        numb = numb.getNumb(1);
        for(; x>0; x--){
            numb.mul(x);
        }
        return numb;
    }

    Numb MyNumb(int x){
        numb = numb.getNumb(1);
        numb.mul(x);
        return numb;
    }

    Numb binom(int n, int k){
        numb = numb.getNumb(1);
        /*
        ArrayList<Integer> myN = new ArrayList(); // N!
        ArrayList<Integer> myKNK = new ArrayList(); // K! * (N-K)!

        for (int i=1; i<=n; i++){
            myN.add(i);
        }
        for (int i=1; i<=(n-k); i++){
            myKNK.add(i);
        }
        for (int i=1; i<=k; i++){
            myKNK.add(i);
        }

        //Сортировка О.о
        myN=Sotred(myN);
        myKNK=Sotred(myKNK);

        for (int i=0; i<myKNK.size(); i++){
            for (int j=0; j<myN.size(); j++){
                if(myN.get(j)==(myKNK.get(i))){
                    myN.set(j, 1);
                    myKNK.set(i, 1);
                }
            }
        }
*/
        ArrayList<Numb> myN = new ArrayList<>(); // N!
        ArrayList<Numb> myKNK = new ArrayList<>(); // K! * (N-K)!
        Numb N = fact(1);
        Numb K = fact(1);
        Numb TmpNK = fact(1);
        //Создание массивов целых чисел
        for (int i=1; i<=n; i++){
            myN.add(MyNumb(i));
        }
        for (int i=1; i<=(n-k); i++){
            myKNK.add(MyNumb(i));
        }
        for (int i=1; i<=k; i++){
            myKNK.add(MyNumb(i));
        }
        //Сортировка О.о
        myN=Sotred(myN);
        myKNK=Sotred(myKNK);

        for (int i=0; i<myKNK.size(); i++){
            for (int j=0; j<myN.size(); j++){
                if(myN.get(j).toString().equals(myKNK.get(i).toString())){
                    myN.set(j, fact(1));
                    myKNK.set(i, fact(1));
                }
            }
        }
        for (int i=0; i<myKNK.size(); i++){
            K.mul(myKNK.get(i));
        }
        for (int j=0; j<myN.size(); j++){
            N.mul(myN.get(j));
        }
        ((Numb)N).division(K);
        numb.mul(N);
        return numb;
        /*
        int K=1,N=1;
        for (int i=0; i<myKNK.size(); i++){
            K*=(myKNK.get(i));
        }
        for (int j=0; j<myN.size(); j++){
            N*=(myN.get(j));
        }
        numb.mul(N/K);
        return numb;

         */
    }
    /*
    ArrayList<Integer> Sotred(ArrayList<Integer> Ar){
        for (int i=0; i<Ar.size()-1; i++){
            if((Ar.get(i))>(Ar.get(i+1))){
                int x = Ar.get(i+1);
                Ar.set(i+1, Ar.get(i));
                Ar.set(i, x);
                i=0;
            }
            //System.out.println(0);
        }
        return Ar;
    }

     */


    ArrayList<Numb> Sotred(ArrayList<Numb> Ar){
        for (int i=0; i<Ar.size()-1; i++){
            if((Ar.get(i)).isLarger(Ar.get(i+1))){
                Numb x = Ar.get(i+1);
                Ar.set(i+1, Ar.get(i));
                Ar.set(i, x);
                i=0;
            }
            //System.out.println(0);
        }
        return Ar;
    }



    /*
    Numb binom(int n, int k){   // (a+b)^2
        numb = numb.getNumb(1);
        int summ = (int) Math.round(Math.pow((n+k),2)); // = n^2 + 2nk + k^2 = (a+b)^2
        numb.mul(summ);
        return numb;
    }
     */
}
