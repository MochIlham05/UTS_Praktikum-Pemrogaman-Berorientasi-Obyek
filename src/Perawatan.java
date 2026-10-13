/**
 * Interface: Perawatan
 * Mendefinisikan kontrak layanan perawatan kendaraan.
 *
 * Konsep OOP yang diterapkan:
 * - Interface: multiple interface dapat diimplementasikan
 */
public interface Perawatan {
    /**
     * Melakukan pemeriksaan kendaraan sebelum disewa.
     * @return true jika kendaraan layak jalan
     */
    boolean periksaKondisi();

    /**
     * Mendapatkan catatan perawatan terakhir.
     */
    String getCatatanPerawatan();
}
