/**
 * Interface: Asuransi
 * Mendefinisikan kontrak perhitungan asuransi kendaraan.
 *
 * Konsep OOP yang diterapkan:
 * - Interface: mendefinisikan perilaku tanpa implementasi (default)
 */
public interface Asuransi {
    double RATE_ASURANSI_DASAR = 0.05; // 5% dari harga sewa per hari

    /**
     * Menghitung biaya asuransi berdasarkan lama penyewaan.
     * @param jumlahHari lama penyewaan
     * @param hargaSewaPerHari harga sewa per hari kendaraan
     * @return biaya asuransi total
     */
    double hitungAsuransi(int jumlahHari, double hargaSewaPerHari);

    /**
     * Mendapatkan jenis asuransi yang diterapkan.
     */
    String getJenisAsuransi();
}
