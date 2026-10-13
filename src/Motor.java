/**
 * Subclass: Motor
 * Turunan dari Kendaraan yang merepresentasikan kendaraan roda 2.
 * Mengimplementasikan interface Asuransi.
 *
 * Konsep OOP yang diterapkan:
 * - Inheritance: extends Kendaraan
 * - Interface Implementation: implements Asuransi
 * - Polymorphism: override getDeskripsi() dan hitungBiayaTambahan()
 * - Encapsulation: atribut private khusus Motor
 */
public class Motor extends Kendaraan implements Asuransi {

    // === ATRIBUT KHUSUS MOTOR ===
    private int    kapasitasMesin; // dalam CC
    private String jenisMotor;     // Sport / Matic / Trail / Bebek
    private boolean helmTersedia;

    // === CONSTRUCTOR ===
    public Motor(String idKendaraan, String merek, String model,
                 int tahunProduksi, String warna, double hargaSewaPerHari,
                 int kapasitasMesin, String jenisMotor, boolean helmTersedia) {
        super(idKendaraan, merek, model, tahunProduksi, warna, hargaSewaPerHari);
        this.kapasitasMesin = kapasitasMesin;
        this.jenisMotor     = jenisMotor;
        this.helmTersedia   = helmTersedia;
    }

    // === OVERRIDE ABSTRACT METHOD (Polymorphism) ===
    @Override
    public String getDeskripsi() {
        return String.format("Motor %s %dcc jenis %s%s",
            getMerek(), kapasitasMesin, jenisMotor,
            helmTersedia ? " (Helm disediakan)" : "");
    }

    @Override
    public double hitungBiayaTambahan(int jumlahHari) {
        // Biaya tambahan: hanya asuransi (lebih ringan dibanding mobil)
        return hitungAsuransi(jumlahHari, getHargaSewaPerHari());
    }

    // === IMPLEMENTASI INTERFACE ASURANSI ===
    @Override
    public double hitungAsuransi(int jumlahHari, double hargaSewaPerHari) {
        // Motor punya rate asuransi lebih rendah (3%)
        return 0.03 * hargaSewaPerHari * jumlahHari;
    }

    @Override
    public String getJenisAsuransi() {
        return "Asuransi Kendaraan Bermotor Roda 2";
    }

    // === OVERRIDE TAMPILKAN INFO ===
    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("  Mesin          : " + kapasitasMesin + " CC");
        System.out.println("  Jenis Motor    : " + jenisMotor);
        System.out.println("  Helm           : " + (helmTersedia ? "Tersedia" : "Tidak Tersedia"));
        System.out.println("  Jenis Asuransi : " + getJenisAsuransi());
    }

    // === GETTER & SETTER ===
    public int    getKapasitasMesin() { return kapasitasMesin; }
    public String getJenisMotor()     { return jenisMotor; }
    public boolean isHelmTersedia()   { return helmTersedia; }
}
