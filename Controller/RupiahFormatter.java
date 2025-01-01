package Controller;

public class RupiahFormatter {
    public static String formatRupiah(int amount) {
        return String.format("Rp%,d", amount).replace(',', '.');
    }
}
