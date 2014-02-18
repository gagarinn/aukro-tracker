package xren;

/**
 * User: Viktor Burmaka
 * Date: 07.02.14
 * Time: 20:36
 */
public class Xren {

    private static int a = 0;
    public static void main(String[] args) {
        System.out.println("a = " + a);
        a = tupilovo(1,2);
        System.out.println("a = " + a);

    }

    private static int tupilovo(int x, int y){
        int result = -1;

        result = x +y;
           while (result < 10){
               result++;
           }


        return result;
    }
}
