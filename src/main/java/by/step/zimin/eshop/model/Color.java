package by.step.zimin.eshop.model;
public enum Color {
    WHITE("The color is  white."),
    BLACK("The color is black."),
    GRAY("The color is gray."),
    BLUE("The color is blue."),
    RED("The color is red."),
    GREEN("The color is GREEN."),
    GOLD("The color is GOLD."),
    YELLOW("The color is YELLOW."),
    OTHER("The color is OTHER.");

    private String color;

    private   Color (String color) {
        this.color=color;
    }

    public String getCurrency(){
        return color;
    }
}

