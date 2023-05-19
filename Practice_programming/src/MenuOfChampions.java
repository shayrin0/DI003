public class MenuOfChampions {
    /*
        The menu should include at least three different foods, each with its own price.
        Each value (food name and price) should be stored in its own variable,
        using the appropriate type.
        Display the restaurant name and menu, followed by a list of dishes and their prices,
        similar to the output shown below.
        Feel free to use some ASCII art to decorate your menu.
    */
    public static void main (String[] args) {
        String foodName1 = "Pitza Delux", foodName2 = "Pesto Pasta", foodName3 = "Chicken Soup";
        double food1Price = 22.50, food2Price = 32.00, food3Price = 15.60;
        String restaurantName = "HAILAR RESTAURANT";

        System.out.println(".oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.");
        System.out.println("            WELCOME TO " + restaurantName + "!");
        System.out.println("            Today's Menu Is...");
        System.out.println(".oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.");
        System.out.println("            $ " + food1Price + "*** " + foodName1);
        System.out.println("            $ " + food2Price + "*** " + foodName2);
        System.out.println("            $ " + food3Price + "*** " + foodName3);
    }
}
