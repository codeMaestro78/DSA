package StriverDsaSheet.Bitmanupulation;

public class setRightmostUnsetBit {
    public static void main(String[] args) {
    int n=10;
        System.out.println(setRightmostUnsetBit1(n));
    }
//    OR with n+1 sets the rightmost 0 to 1
    public static int setRightmostUnsetBit1(int n) {
        return n | n+1;
    }
}
