import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class: Pelanggan
 * Merepresentasikan pelanggan yang menyewa kendaraan.
 *
 * Konsep OOP yang diterapkan:
 * - Encapsulation: atribut private dengan getter/setter
 * - Relationship: Pelanggan berasosiasi dengan TransaksiSewa
 */
public class Pelanggan {

    // === ATRIBUT ===
    private String idPelanggan;
    private String nama;
    private String noKTP;
    private String noTelpon;
    private String alamat;
    private int    totalTransaksi;

    // === CONSTRUCTOR ===
    public Pelanggan(String idPelanggan, String nama, String noKTP,
                     String noTelpon, String alamat) {
        this.idPelanggan   = idPelanggan;
        this.nama          = nama;
        this.noKTP         = noKTP;
        this.noTelpon      = noTelpon;
        this.alamat        = alamat;
        this.totalTransaksi = 0;
    }

    // === METHOD ===
    public void tampilkanInfo() {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("       DATA PELANGGAN");
        System.out.println("╠══════════════════════════════════════════╣");
        System.out.println("  ID Pelanggan   : " + idPelanggan);
        System.out.println("  Nama           : " + nama);
        System.out.println("  No. KTP        : " + noKTP);
        System.out.println("  No. Telpon     : " + noTelpon);
        System.out.println("  Alamat         : " + alamat);
        System.out.println("  Total Transaksi: " + totalTransaksi + " kali");
        System.out.println("╚══════════════════════════════════════════╝");
    }

    public void tambahTransaksi() {
        this.totalTransaksi++;
    }

    // === GETTER & SETTER ===
    public String getIdPelanggan()  { return idPelanggan; }
    public String getNama()         { return nama; }
    public String getNoKTP()        { return noKTP; }
    public String getNoTelpon()     { return noTelpon; }
    public String getAlamat()       { return alamat; }
    public int    getTotalTransaksi() { return totalTransaksi; }

    public void setNoTelpon(String noTelpon) { this.noTelpon = noTelpon; }
    public void setAlamat(String alamat)     { this.alamat = alamat; }
}
