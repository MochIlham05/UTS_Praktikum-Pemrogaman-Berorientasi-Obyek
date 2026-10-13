import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class: SistemRental
 * Mengelola keseluruhan operasi rental kendaraan.
 *
 * Konsep OOP yang diterapkan:
 * - Composition: SistemRental memiliki (owns) kumpulan Kendaraan, Pelanggan, Transaksi
 * - Encapsulation: semua list dikelola secara private
 */
public class SistemRental {

    // === ATRIBUT ===
    private String              namaPerusahaan;
    private List<Kendaraan>     daftarKendaraan;
    private List<Pelanggan>     daftarPelanggan;
    private List<TransaksiSewa> daftarTransaksi;
    private int                 counterTransaksi;

    // === CONSTRUCTOR ===
    public SistemRental(String namaPerusahaan) {
        this.namaPerusahaan   = namaPerusahaan;
        this.daftarKendaraan  = new ArrayList<>();
        this.daftarPelanggan  = new ArrayList<>();
        this.daftarTransaksi  = new ArrayList<>();
        this.counterTransaksi = 1;
    }

    // === MANAJEMEN KENDARAAN ===
    public void tambahKendaraan(Kendaraan k) {
        daftarKendaraan.add(k);
        System.out.println("[INFO] Kendaraan '" + k.getMerek() + " " + k.getModel() + "' berhasil ditambahkan.");
    }

    public Kendaraan cariKendaraan(String idKendaraan) {
        return daftarKendaraan.stream()
            .filter(k -> k.getIdKendaraan().equalsIgnoreCase(idKendaraan))
            .findFirst()
            .orElse(null);
    }

    public List<Kendaraan> getKendaraanTersedia() {
        return daftarKendaraan.stream()
            .filter(Kendaraan::isTersedia)
            .collect(Collectors.toList());
    }

    public void tampilkanSemuaKendaraan() {
        System.out.println("\n========== DAFTAR KENDARAAN ==========");
        System.out.printf("%-10s %-15s %-15s %-12s %-10s%n",
            "ID", "Merek", "Model", "Harga/Hari", "Status");
        System.out.println("─".repeat(65));
        for (Kendaraan k : daftarKendaraan) {
            System.out.printf("%-10s %-15s %-15s Rp%-10.0f %-10s%n",
                k.getIdKendaraan(), k.getMerek(), k.getModel(),
                k.getHargaSewaPerHari(),
                k.isTersedia() ? "TERSEDIA" : "DISEWA");
        }
        System.out.println("─".repeat(65));
    }

    // === MANAJEMEN PELANGGAN ===
    public void tambahPelanggan(Pelanggan p) {
        daftarPelanggan.add(p);
        System.out.println("[INFO] Pelanggan '" + p.getNama() + "' berhasil terdaftar.");
    }

    public Pelanggan cariPelanggan(String idPelanggan) {
        return daftarPelanggan.stream()
            .filter(p -> p.getIdPelanggan().equalsIgnoreCase(idPelanggan))
            .findFirst()
            .orElse(null);
    }

    public void tampilkanSemuaPelanggan() {
        System.out.println("\n========== DAFTAR PELANGGAN ==========");
        System.out.printf("%-10s %-20s %-20s %-15s%n",
            "ID", "Nama", "No. Telpon", "Total Transaksi");
        System.out.println("─".repeat(70));
        for (Pelanggan p : daftarPelanggan) {
            System.out.printf("%-10s %-20s %-20s %-15d%n",
                p.getIdPelanggan(), p.getNama(), p.getNoTelpon(), p.getTotalTransaksi());
        }
        System.out.println("─".repeat(70));
    }

    // === MANAJEMEN TRANSAKSI ===
    public TransaksiSewa buatTransaksi(Pelanggan pelanggan, Kendaraan kendaraan,
                                        java.time.LocalDate tanggalMulai, int jumlahHari) {
        if (!kendaraan.isTersedia()) {
            System.out.println("[ERROR] Kendaraan tidak tersedia untuk disewa!");
            return null;
        }
        String idTransaksi = String.format("TRX-%04d", counterTransaksi++);
        TransaksiSewa transaksi = new TransaksiSewa(idTransaksi, pelanggan, kendaraan,
                                                     tanggalMulai, jumlahHari);
        if (transaksi.prosesSewa()) {
            daftarTransaksi.add(transaksi);
            return transaksi;
        }
        return null;
    }

    public void tampilkanSemuaTransaksi() {
        System.out.println("\n========== RIWAYAT TRANSAKSI ==========");
        System.out.printf("%-12s %-20s %-20s %-10s %-15s%n",
            "ID Transaksi", "Pelanggan", "Kendaraan", "Hari", "Total Biaya");
        System.out.println("─".repeat(80));
        for (TransaksiSewa t : daftarTransaksi) {
            System.out.printf("%-12s %-20s %-20s %-10d Rp%.2f%n",
                t.getIdTransaksi(),
                t.getPelanggan().getNama(),
                t.getKendaraan().getMerek() + " " + t.getKendaraan().getModel(),
                t.getJumlahHari(),
                t.getTotalBiaya());
        }
        System.out.println("─".repeat(80));
    }

    // === LAPORAN ===
    public void cetakLaporanPendapatan() {
        double totalPendapatan = daftarTransaksi.stream()
            .filter(t -> t.getStatus() == TransaksiSewa.StatusTransaksi.SELESAI
                      || t.getStatus() == TransaksiSewa.StatusTransaksi.AKTIF)
            .mapToDouble(TransaksiSewa::getTotalBiaya)
            .sum();

        long totalAktif   = daftarTransaksi.stream()
            .filter(t -> t.getStatus() == TransaksiSewa.StatusTransaksi.AKTIF).count();
        long totalSelesai = daftarTransaksi.stream()
            .filter(t -> t.getStatus() == TransaksiSewa.StatusTransaksi.SELESAI).count();

        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println("           LAPORAN PENDAPATAN RENTAL                ");
        System.out.println("         " + namaPerusahaan);
        System.out.println("╠══════════════════════════════════════════════════╣");
        System.out.println("  Total Kendaraan   : " + daftarKendaraan.size());
        System.out.println("  Total Pelanggan   : " + daftarPelanggan.size());
        System.out.println("  Transaksi Aktif   : " + totalAktif);
        System.out.println("  Transaksi Selesai : " + totalSelesai);
        System.out.printf ("  TOTAL PENDAPATAN  : Rp %.2f%n", totalPendapatan);
        System.out.println("╚══════════════════════════════════════════════════╝");
    }

    // === GETTER ===
    public String              getNamaPerusahaan() { return namaPerusahaan; }
    public List<Kendaraan>     getDaftarKendaraan(){ return daftarKendaraan; }
    public List<Pelanggan>     getDaftarPelanggan(){ return daftarPelanggan; }
    public List<TransaksiSewa> getDaftarTransaksi(){ return daftarTransaksi; }
}
