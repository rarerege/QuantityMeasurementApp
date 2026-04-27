public class QuantityMeasurementApp {

    public static void main(String[] args) {

        QuantityWeight kg = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight gram = new QuantityWeight(1000.0, WeightUnit.GRAM);
        QuantityWeight pound = new QuantityWeight(2.20462, WeightUnit.POUND);

        System.out.println(kg.equals(gram)); // true
        System.out.println(kg.equals(pound)); // true (approx)

        System.out.println(kg.convertTo(WeightUnit.GRAM));
        // Quantity(1000.0, GRAM)

        QuantityWeight sum = kg.add(gram);
        System.out.println(sum);
        // Quantity(2.0, KILOGRAM)

        QuantityWeight sum2 = kg.add(gram, WeightUnit.GRAM);
        System.out.println(sum2);
        // Quantity(2000.0, GRAM)
    }
}