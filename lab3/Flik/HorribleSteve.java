public class HorribleSteve {
    public static void main(String [] args) {
        int i = 125;
        for (int j = 125; i < 500; ++i, ++j) {
            if (!Flik.isSameNumber(i, j)) {
                break; // break exits the for loop!
            }
        }
        System.out.println("i is " + i);
    }
}
