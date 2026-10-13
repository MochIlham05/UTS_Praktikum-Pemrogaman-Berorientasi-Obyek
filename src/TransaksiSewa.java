import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Class: TransaksiSewa
 * Merepresentasikan satu transaksi penyewaan kendaraan.
 *
 * Konsep OOP yang diterapkan:
 * - Relationship: TransaksiSewa memiliki Kendaraan (Aggregation) dan Pelanggan (Association)
 * - Encapsulation: semua atribut private
 */
public class TransaksiSewa {

    // === ENUM STATUS TRANSAKSI ===
    public enum StatusTransaksi {
        AKTIF, SELESAI, DIBATALKAN
    }

    // === ATRIBUT ===
    private String          idTransaksi;
    private Pelanggan       pelanggan;        // Association dengan Pelanggan
    private Kendaraan       kendaraan;        // Aggregation dengan Kendaraan
    private LocalDate       tanggalMulai;
    private LocalDate       tanggalSelesai;
    private int             jumlahHari;
    private double          totalBiaya;
    private StatusTransaksi status;
    private String          catatanTransaksi;

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd MMMM yyyy",
            java.util.Locale.forLanguageTag("id-ID"));

    // === CONSTRUCTOR ===
    public TransaksiSewa(String idTransaksi, Pelanggan pelanggan, Kendaraan kendaraan,
                         LocalDate tanggalMulai, int jumlahHari) {
        this.idTransaksi     = idTransaksi;
        this.pelanggan       = pelanggan;
        this.kendaraan       = kendaraan;
        this.tanggalMulai    = tanggalMulai;
        this.jumlahHari      = jumlahHari;
        this.tanggalSelesai  = tanggalMulai.plusDays(jumlahHari);
        this.totalBiaya      = kendaraan.hitungTotalBiaya(jumlahHari);
        this.status          = StatusTransaksi.AKTIF;
        this.catatanTransaksi = "-";
    }

    // === METHOD ===
    /**
     * Memproses penyewaan kendaraan.
     */
    public boolean prosesSewa() {
        if (!kendaraan.isTersedia()) {
            System.out.println("[ERROR] Kendaraan " + kendaraan.getModel() + " sedang tidak tersedia!");
            return false;
        }
        kendaraan.setTersedia(false);
        pelanggan.tambahTransaksi();
        System.out.println("[INFO] Sewa berhasil diproses!");
        return true;
    }

    /**
     * Memproses pengembalian kendaraan.
     * @param tanggalKembali tanggal aktual pengembalian
     */
    public void prosesKembali(LocalDate tanggalKembali) {
        kendaraan.setTersedia(true);
        this.status = StatusTransaksi.SELESAI;

        long hariKembali = ChronoUnit.DAYS.between(tanggalMulai, tanggalKembali);
        if (tanggalKembali.isAfter(tanggalSelesai)) {
            long hariTelat = ChronoUnit.DAYS.between(tanggalSelesai, tanggalKembali);
            double denda   = hariTelat * kendaraan.getHargaSewaPerHari() * 1.5; // denda 150%
            totalBiaya    += denda;
            this.catatanTransaksi = String.format("Terlambat %d hari. Denda: Rp %.2f", hariTelat, denda);
            System.out.printf("[INFO] Kendaraan dikembalikan terlambat %d hari. Denda: Rp %.2f%n",
                hariTelat, denda);
        } else {
            this.catatanTransaksi = "Kendaraan dikembalikan tepat waktu";
            System.out.println("[INFO] Kendaraan dikembalikan tepat waktu.");
        }
    }

    /**
     * Membatalkan transaksi sewa.
     */
    public void batalkanSewa(String alasan) {
        if (this.status == StatusTransaksi.AKTIF) {
            kendaraan.setTersedia(true);
            this.status = StatusTransaksi.DIBATALKAN;
            this.catatanTransaksi = "Dibatalkan: " + alasan;
            System.out.println("[INFO] Transaksi " + idTransaksi + " berhasil dibatalkan.");
        } else {
            System.out.println("[ERROR] Transaksi tidak dapat dibatalkan. Status: " + status);
        }
    }

    /**
     * Mencetak struk transaksi.
     */
    public void cetakStruk() {
        System.out.println();
        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("           STRUK TRANSAKSI SEWA KENDARAAN           ");
        System.out.println("              RentaCar - PENS SUMENEP               ");
        System.out.println("╠══════════════════════════════════════════════════╣");
        System.out.println("  ID Transaksi   : " + idTransaksi);
        System.out.println("  Status         : " + status);
        System.out.println("──────────────────────────────────────────────────");
        System.out.println("  PELANGGAN");
        System.out.println("  Nama           : " + pelanggan.getNama());
        System.out.println("  No. KTP        : " + pelanggan.getNoKTP());
        System.out.println("──────────────────────────────────────────────────");
        System.out.println("  KENDARAAN");
        System.out.println("  ID Kendaraan   : " + kendaraan.getIdKendaraan());
        System.out.println("  Kendaraan      : " + kendaraan.getMerek() + " " + kendaraan.getModel());
        System.out.println("  Deskripsi      : " + kendaraan.getDeskripsi());
        System.out.println("──────────────────────────────────────────────────");
        System.out.println("  DETAIL SEWA");
        System.out.println("  Mulai          : " + tanggalMulai.format(FMT));
        System.out.println("  Selesai        : " + tanggalSelesai.format(FMT));
        System.out.println("  Jumlah Hari    : " + jumlahHari + " hari");
        System.out.printf ("  Harga/Hari     : Rp %.2f%n", kendaraan.getHargaSewaPerHari());
        System.out.printf ("  TOTAL BIAYA    : Rp %.2f%n", totalBiaya);
        System.out.println("  Catatan        : " + catatanTransaksi);
        System.out.println("╚══════════════════════════════════════════════════╝");
        System.out.println();
    }

    // === GETTER ===
    public String          getIdTransaksi()    { return idTransaksi; }
    public Pelanggan       getPelanggan()       { return pelanggan; }
    public Kendaraan       getKendaraan()       { return kendaraan; }
    public LocalDate       getTanggalMulai()    { return tanggalMulai; }
    public LocalDate       getTanggalSelesai()  { return tanggalSelesai; }
    public int             getJumlahHari()      { return jumlahHari; }
    public double          getTotalBiaya()      { return totalBiaya; }
    public StatusTransaksi getStatus()          { return status; }
    public String          getCatatanTransaksi(){ return catatanTransaksi; }
}
