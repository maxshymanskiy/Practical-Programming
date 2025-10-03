package Task6;

public class ConvertCurrency {

    private static final double USD_RATE = 41.5;
    private static final double EUR_RATE = 49.0;
    private static final double CAD_RATE = 29.7;

    public double convert(final String input) {
        final String[] parts = input.split("\\s+");

        String into = "into";
        if (parts.length != 4 || !parts[2].equalsIgnoreCase(into)) {
            throw new IllegalArgumentException("Invalid format. Use: number VAL into VAL");
        }

        final double amount = Double.parseDouble(parts[0]);
        final String from = parts[1].toUpperCase();
        final String to = parts[3].toUpperCase();

        final double amountInUAH = convertToUAH(amount, from);
        return convertFromUAH(amountInUAH, to);
    }

    private double convertToUAH(double amount, String currency) {
        return switch (currency) {
            case "UAH" -> amount;
            case "USD" -> amount * USD_RATE;
            case "EUR" -> amount * EUR_RATE;
            case "CAD" -> amount * CAD_RATE;
            default -> throw new IllegalArgumentException("Unknow value: " + currency);
        };
    }

    private double convertFromUAH(double amount, String currency) {
        return switch (currency) {
            case "UAH" -> amount;
            case "USD" -> amount / USD_RATE;
            case "EUR" -> amount / EUR_RATE;
            case "CAD" -> amount / CAD_RATE;
            default -> throw new IllegalArgumentException("Unknow value: " + currency);
        };
    }
}
