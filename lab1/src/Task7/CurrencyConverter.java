package Task7;

public class CurrencyConverter {
    private CurrencyConverter() {} // Utility class

    public static double convert(double amount, String fromCurrency, String toCurrency) {
        if (fromCurrency.equals(toCurrency)) {
            return amount;
        }

        final double amountInUAH = amount * getRateToUAH(fromCurrency);
        return amountInUAH / getRateToUAH(toCurrency);
    }

    private static double getRateToUAH(String currency) {
        return switch (currency) {
            case "UAH" -> 1.0;
            case "USD" -> 41.2;
            case "EUR" -> 48.5;
            default -> throw new IllegalArgumentException("Unknown currency: " + currency);
        };
    }
}