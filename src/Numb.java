public interface Numb {
    void add(Numb x);
    void sub(Numb x);
    void mul(Numb x);
    void mul(long x);
    boolean isLarger(Numb x);
    Numb getNumb(int x);
    String toString();
    void division(Numb x);
    void division(int x);
}
