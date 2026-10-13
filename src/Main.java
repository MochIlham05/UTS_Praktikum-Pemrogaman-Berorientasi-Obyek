import java.time.LocalDate;

/**
 * Class: Main (Entry Point)
 * Program demonstrasi Sistem Rental Kendaraan
 * UTS Praktikum Pemrograman Berorientasi Obyek
 *
 * Mahasiswa: Moch. Ilham Abdul Aziz
 * NIM      : 3125522014
 * Kelas    : 3D3PSDKU-SM-IT A
 * Tipe     : A (Sistem Rental Kendaraan)
 *
 * Konsep OOP yang didemonstrasikan:
 * 1. Class & Object
 * 2. Encapsulation
 * 3. Relationship (Association, Aggregation, Composition)
 * 4. Inheritance
 * 5. Polymorphism
 * 6. Abstract Class
 * 7. Interface
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("         SISTEM RENTAL KENDARAAN - RENTACAR PENS          ");
        System.out.println("         UTS Praktikum PBO | Moch. Ilham Abdul Aziz       ");
        System.out.println("╚════════════════════════════════════════════════════════╝");
        System.out.println();

        // ====================================================
        // 1. MEMBUAT OBJEK SISTEM RENTAL (Composition)
        // ====================================================
        SistemRental sistem = new SistemRental("RentaCar PENS Sumenep");

        // ====================================================
        // 2. MENDAFTARKAN KENDARAAN (Inheritance & Polymorphism)
        //    - Mobil, Motor, Truk semuanya adalah Kendaraan
        //    - Setiap objek memiliki getDeskripsi() berbeda (Polymorphism)
        // ====================================================
        System.out.println("\n>>> Mendaftarkan Kendaraan...");

        Mobil mobil1 = new Mobil("M-001", "Toyota", "Avanza", 2022, "Putih",
                                  350000.0, 7, "Manual", true);
        Mobil mobil2 = new Mobil("M-002", "Honda",  "Brio",   2023, "Merah",
                                  300000.0, 5, "Otomatis", true);
        Motor motor1 = new Motor("MT-001", "Honda", "Vario 160", 2023, "Hitam",
                                  100000.0, 160, "Matic", true);
        Motor motor2 = new Motor("MT-002", "Yamaha","NMAX",      2022, "Biru",
                                  125000.0, 155, "Matic", true);
        Truk  truk1  = new Truk ("TK-001", "Mitsubishi", "Colt Diesel", 2021, "Kuning",
                                  750000.0, 5.0, "Box", true);

        sistem.tambahKendaraan(mobil1);
        sistem.tambahKendaraan(mobil2);
        sistem.tambahKendaraan(motor1);
        sistem.tambahKendaraan(motor2);
        sistem.tambahKendaraan(truk1);

        // ====================================================
        // 3. MENDAFTARKAN PELANGGAN (Association)
        // ====================================================
        System.out.println("\n>>> Mendaftarkan Pelanggan...");

        Pelanggan pelanggan1 = new Pelanggan("P-001", "Ahmad Fauzi",
                "3517211234560001", "08123456789", "Jl. Merdeka No.10 Sumenep");
        Pelanggan pelanggan2 = new Pelanggan("P-002", "Siti Aisyah",
                "3517219876540002", "08987654321", "Jl. Pemuda No.5 Sumenep");

        sistem.tambahPelanggan(pelanggan1);
        sistem.tambahPelanggan(pelanggan2);

        // ====================================================
        // 4. MENAMPILKAN INFO KENDARAAN (Polymorphism via tampilkanInfo())
        // ====================================================
        System.out.println("\n>>> Info Detail Kendaraan (Polymorphism):");
        mobil1.tampilkanInfo();
        motor1.tampilkanInfo();
        truk1.tampilkanInfo();

        // ====================================================
        // 5. DEMONSTRASI INTERFACE PERAWATAN
        // ====================================================
        System.out.println("\n>>> Pemeriksaan Kondisi Kendaraan (Interface Perawatan):");
        // Mobil dan Truk mengimplementasikan interface Perawatan
        Perawatan[] kendaraanBerperawatan = {mobil1, truk1};
        for (Perawatan p : kendaraanBerperawatan) {
            boolean layak = p.periksaKondisi();
            System.out.println("    Status: " + (layak ? "LAYAK JALAN" : "TIDAK LAYAK"));
        }

        // ====================================================
        // 6. DEMONSTRASI INTERFACE ASURANSI
        // ====================================================
        System.out.println("\n>>> Perhitungan Asuransi (Interface Asuransi):");
        Asuransi[] kendaraanBerasuransi = {mobil1, motor1, truk1};
        for (Asuransi a : kendaraanBerasuransi) {
            System.out.printf("    %s: Rp %.2f (3 hari)%n",
                a.getJenisAsuransi(), a.hitungAsuransi(3, 300000));
        }

        // ====================================================
        // 7. PROSES TRANSAKSI SEWA (Relationship & Polymorphism)
        // ====================================================
        System.out.println("\n>>> Proses Transaksi Sewa...");

        // Transaksi 1: Ahmad menyewa Avanza 3 hari
        TransaksiSewa t1 = sistem.buatTransaksi(pelanggan1, mobil1,
                            LocalDate.of(2026, 10, 13), 3);
        if (t1 != null) {
            t1.cetakStruk();
        }

        // Transaksi 2: Siti menyewa NMAX 5 hari
        TransaksiSewa t2 = sistem.buatTransaksi(pelanggan2, motor2,
                            LocalDate.of(2026, 10, 13), 5);
        if (t2 != null) {
            t2.cetakStruk();
        }

        // Transaksi 3: Ahmad menyewa Truk 2 hari
        TransaksiSewa t3 = sistem.buatTransaksi(pelanggan1, truk1,
                            LocalDate.of(2026, 10, 14), 2);
        if (t3 != null) {
            t3.cetakStruk();
        }

        // ====================================================
        // 8. PROSES PENGEMBALIAN - Mobil dikembalikan tepat waktu
        // ====================================================
        System.out.println("\n>>> Proses Pengembalian Kendaraan...");
        if (t1 != null) {
            t1.prosesKembali(LocalDate.of(2026, 10, 16)); // tepat waktu
            System.out.println("Struk setelah pengembalian:");
            t1.cetakStruk();
        }

        // ====================================================
        // 9. PROSES PENGEMBALIAN - Motor dikembalikan terlambat
        // ====================================================
        if (t2 != null) {
            t2.prosesKembali(LocalDate.of(2026, 10, 20)); // terlambat 2 hari
            System.out.println("Struk dengan denda:");
            t2.cetakStruk();
        }

        // ====================================================
        // 10. MENAMPILKAN SEMUA DATA (Encapsulation via getter)
        // ====================================================
        sistem.tampilkanSemuaKendaraan();
        sistem.tampilkanSemuaPelanggan();
        sistem.tampilkanSemuaTransaksi();

        // ====================================================
        // 11. LAPORAN PENDAPATAN
        // ====================================================
        sistem.cetakLaporanPendapatan();

        // ====================================================
        // 12. DEMONSTRASI POLYMORPHISM - array tipe Kendaraan
        // ====================================================
        System.out.println("\n>>> Demonstrasi Polymorphism (Array Kendaraan):");
        Kendaraan[] semuaKendaraan = {mobil1, mobil2, motor1, motor2, truk1};
        System.out.println("Deskripsi semua kendaraan melalui reference Kendaraan:");
        for (Kendaraan k : semuaKendaraan) {
            System.out.println("  - [" + k.getIdKendaraan() + "] " + k.getDeskripsi());
            System.out.printf("    Estimasi biaya 3 hari: Rp %.2f%n",
                k.hitungTotalBiaya(3));
        }

        System.out.println("\n╔════════════════════════════════════════════════════════╗");
        System.out.println("           Demo Selesai - RentaCar PENS Sumenep           ");
        System.out.println("╚════════════════════════════════════════════════════════╝");
    }
}
