package fr.univ_amu.iut.exercice3_1;


import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.beans.binding.StringExpression;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import static javafx.beans.binding.Bindings.*;
import static javafx.beans.binding.Bindings.divide;

public class TriangleArea {

    private IntegerProperty x1 = new SimpleIntegerProperty(0);
    private IntegerProperty y1 = new SimpleIntegerProperty(0);

    private IntegerProperty x2 = new SimpleIntegerProperty(0);
    private IntegerProperty y2 = new SimpleIntegerProperty(0);

    private IntegerProperty x3 = new SimpleIntegerProperty(0);
    private IntegerProperty y3 = new SimpleIntegerProperty(0);

    private NumberBinding area;

    private StringExpression output;

    public TriangleArea() {
        createBinding();
    }

    public static void main(String[] args) {
        TriangleArea triangleArea = new TriangleArea();

        triangleArea.printResult();

        triangleArea.setP1(0, 1);
        triangleArea.setP2(5, 0);
        triangleArea.setP3(4, 3);

        triangleArea.printResult();

        triangleArea.setP1(1, 0);
        triangleArea.setP2(2, 2);
        triangleArea.setP3(0, 1);

        triangleArea.printResult();
    }

    public void setP1(int x1, int y1) {
        this.x1.set(x1);
        this.y1.set(y1);
    }

    public void setP2(int x2, int y2) {
        this.x2.set(x2);
        this.y2.set(y2);
    }

    public void setP3(int x3, int y3) {
        this.x3.set(x3);
        this.y3.set(y3);
    }

    public double getArea() {
        return area.getValue().doubleValue();
    }

    void printResult() {
        System.out.println(output.getValue());
    }

    private void createBinding() {

        NumberBinding x1y2 = x1.multiply(y2);
        NumberBinding x1y3 = x1.multiply(y3);
        NumberBinding x2y3 = x2.multiply(y3);
        NumberBinding x2y1 = x2.multiply(y1);
        NumberBinding x3y1 = x3.multiply(y1);
        NumberBinding x3y2 = x3.multiply(y2);
        NumberBinding diff = x1y2.subtract(x1y3);
        NumberBinding diff2 = x2y3.subtract(x2y1);
        NumberBinding diff3 = x3y1.subtract(x3y2);
        NumberBinding somme = diff.add(diff2).add(diff3);

        NumberBinding res = when(lessThanOrEqual(0, somme)).then(somme).otherwise(negate(somme));// Valeur Absolue
        area = res.divide(2.0);

        output = format("For P1(%d,%d), P2(%d,%d), P3(%d,%d), the area of triangle ABC is %.1f", x1, y1, x2, y2, x3, y3, area);

    }
}
