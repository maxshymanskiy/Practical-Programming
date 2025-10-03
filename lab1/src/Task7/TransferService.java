package Task7;

public class TransferService {
    private TransferService() { // Utility class
    }

    public static TransferResult transfer(BankAccount from, BankAccount to, double amount) {
        validateTransferParameters(from, to, amount);

        final boolean sameOwner = from.getOwner().equals(to.getOwner());
        final boolean sameBank = from.getBank().equals(to.getBank());
        final double feePercent = calculateFee(sameOwner, sameBank);
        final double fee = amount * feePercent / 100.0;
        final double amountAfterFee = amount - fee;

        final double convertedAmount = CurrencyConverter.convert(
                amountAfterFee, from.getCurrency(), to.getCurrency()
        );

        final double fromBalanceBefore = from.getBalance();
        final double toBalanceBefore = to.getBalance();

        from.withdraw(amount);
        to.deposit(convertedAmount);

        final TransferResult result = new TransferResult(
                amount, fee, feePercent, convertedAmount, true, "Successful",
                from.getCurrency(), to.getCurrency()
        );

        printTransferInfo(from, to, fromBalanceBefore, toBalanceBefore, result);

        return result;
    }

    private static void printTransferInfo(BankAccount from, BankAccount to,
                                          double fromBalanceBefore, double toBalanceBefore,
                                          TransferResult result) {
        System.out.printf("=== TRANSFER FROM %s TO %s ===%n", from.getOwner(), to.getOwner());
        System.out.printf("Sender's bank: %s%n", from.getBank().getName());
        System.out.printf("Receiver's bank: %s%n", to.getBank().getName());
        System.out.printf("Operation type: %s%n", getOperationType(from, to));

        result.printDetailedInfo();

        System.out.printf("=== OPERATION RESULT ===%n");
        System.out.printf("From account %s:%n", from.getOwner());
        System.out.printf("  Before: %.2f %s%n", fromBalanceBefore, from.getCurrency());
        System.out.printf("  Withdrawn: %.2f %s%n", result.originalAmount(), from.getCurrency());
        System.out.printf("  Remaining: %.2f %s%n", from.getBalance(), from.getCurrency());

        System.out.printf("To account %s:%n", to.getOwner());
        System.out.printf("  Before: %.2f %s%n", toBalanceBefore, to.getCurrency());
        System.out.printf("  Added: %.2f %s%n", result.finalAmount(), to.getCurrency());
        System.out.printf("  Current: %.2f %s%n", to.getBalance(), to.getCurrency());
        System.out.println();
    }

    private static String getOperationType(BankAccount from, BankAccount to) {
        final boolean sameOwner = from.getOwner().equals(to.getOwner());
        final boolean sameBank = from.getBank().equals(to.getBank());

        if (sameOwner && sameBank) {
            return "Own accounts in same bank (0% fee)";
        }

        if (sameOwner) {
            return "Own accounts in different banks (2% fee)";
        }

        if (sameBank) {
            return "Different users in same bank (3% fee)";
        }

        return "Different users in different banks (6% fee)";
    }

    private static void validateTransferParameters(BankAccount from, BankAccount to, double amount) {
        if (from == null || to == null) {
            throw new IllegalArgumentException("Accounts cannot be null");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Transfer amount must be positive");
        }
        if (from.getBalance() < amount) {
            throw new IllegalArgumentException(String.format(
                    "Insufficient funds in sender's account. Available: %.2f %s, attempted withdrawal: %.2f %s",
                    from.getBalance(), from.getCurrency(), amount, from.getCurrency()
            ));
        }
    }

    private static double calculateFee(boolean sameOwner, boolean sameBank) {
        if (sameOwner && sameBank) {
            return 0.0;
        }

        if (sameOwner) {
            return 2.0;
        }

        if (sameBank) {
            return 3.0;
        }

        return 6.0;
    }
}