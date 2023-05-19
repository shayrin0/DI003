import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class NextPresenter {

    private final String[] names = {"Shirin", "Nathalie", "Michaela",
                                           "Maryann", "Yuliet", "Gabby"};
    private final int SHIRIN = 0, NATHALIE = 1, MICHAELA = 2, MARYANN = 3, YULIET = 4, GABBY = 5;
    private Random myRand = new Random();
    private Integer[] count = {0,0,0,0,0,0};

    public NextPresenter() {}

    public void updateList () {
        count[MICHAELA] = 1;
        count[YULIET] = 1;
    }

    public void presenterSelection () {

        updateList();

        int min = Collections.min(Arrays.asList(count));
        int num = choosePresenter();

        while (count[num] > min) {
            System.out.println(names[num] + " has already presented for " +
                               count[num] + " times!");
            num = choosePresenter();
        }

        System.out.println("The next lucky presenter is " + names[num] + ". Congrats!!! :D");
    }

    public int choosePresenter() {
        return myRand.nextInt(6);
    }
}
