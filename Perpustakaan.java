// RELASI OOP: COMPOSITION / AGGREGATION 
// KONSEP: list & ArrayList)
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Perpustakaan {

    // RELASI OOP: AGGREGATION / COMPOSITION 
    private final List<Buku> koleksiBuku;
    private final List<Anggota> daftarAnggota; // Menampung objek Mahasiswa & Dosen ke satu list induk (Polimorfisme)[cite: 8]
    private final List<Transaksi> daftarTransaksi;

    private static final String FILE_BUKU = "data_buku.txt";
    private static final String FILE_ANGGOTA = "data_anggota.txt";

    public Perpustakaan() {
        this.koleksiBuku = new ArrayList<>();
        this.daftarAnggota = new ArrayList<>();
        this.daftarTransaksi = new ArrayList<>();
    }

    // ================= BUKU =================
    public void tambahBuku(Buku buku) {
        koleksiBuku.add(buku);
        System.out.println("Buku berhasil ditambahkan: " + buku);
    }

    public Buku cariBukuByKode(String kode) {
        for (Buku b : koleksiBuku) {
            if (b.getKodeBuku().equalsIgnoreCase(kode)) {
                return b;
            }
        }
        return null; 
    }

    public void tampilkanSemuaBuku() {
        if (koleksiBuku.isEmpty()) {
            System.out.println("Belum ada data buku.");
            return;
        }
        System.out.println("=== DAFTAR BUKU ===");
        for (Buku b : koleksiBuku) {
            System.out.println(b);
        }
    }

    // ================= ANGGOTA =================
    public void tambahAnggota(Anggota anggota) {
        daftarAnggota.add(anggota);
        System.out.println("Anggota berhasil ditambahkan.");
    }

    public Anggota cariAnggotaById(String id) {
        for (Anggota a : daftarAnggota) {
            if (a.getIdAnggota().equalsIgnoreCase(id)) {
                return a;
            }
        }
        return null;
    }

    public void tampilkanSemuaAnggota() {
        if (daftarAnggota.isEmpty()) {
            System.out.println("Belum ada data anggota.");
            return;
        }
        System.out.println("=== DAFTAR ANGGOTA ===");

        for (Anggota a : daftarAnggota) {
            // PILAR OOP: POLYMORPHISM (Meskipun tipenya list Anggota, Java otomatis memanggil info versi Mahasiswa/Dosen)[cite: 8]
            a.tampilkanInfo(); 
        }
    }

    // ================= TRANSAKSI PEMINJAMAN =================
    public boolean pinjamBuku(String kodeBuku, String idAnggota) {
        Buku buku = cariBukuByKode(kodeBuku);
        Anggota anggota = cariAnggotaById(idAnggota);

        if (buku == null) {
            System.out.println("Buku dengan kode " + kodeBuku + " tidak ditemukan.");
            return false;
        }
        if (anggota == null) {
            System.out.println("Anggota dengan id " + idAnggota + " tidak ditemukan.");
            return false;
        }
        if (!buku.kurangiStok()) {
            System.out.println("Stok buku \"" + buku.getJudul() + "\" habis, tidak bisa dipinjam.");
            return false;
        }

        anggota.pinjamBuku(buku);
        Transaksi transaksi = new Transaksi(buku, anggota);
        daftarTransaksi.add(transaksi);
        System.out.println("Peminjaman berhasil dicatat: " + transaksi.getIdTransaksi());
        return true;
    }

    public void kembalikanBuku(String idTransaksi) {
        for (Transaksi t : daftarTransaksi) {
            if (t.getIdTransaksi().equalsIgnoreCase(idTransaksi) && !t.isSudahKembali()) {
                double denda = t.kembalikanBuku();
                System.out.println("Buku \"" + t.getBuku().getJudul() + "\" berhasil dikembalikan.");
                if (denda > 0) {
                    System.out.println("Terlambat! Denda yang harus dibayar: Rp" + (int) denda);
                } else {
                    System.out.println("Tidak telat, denda: Rp0");
                }
                return;
            }
        }
        System.out.println("Transaksi dengan id " + idTransaksi + " tidak ditemukan / sudah dikembalikan.");
    }

    public void simpanDataKeFile() {
        // KONSEP: Try-With-Resources (Manajemen memori penutupan file otomatis)[cite: 8]
        try (BufferedWriter writerBuku = new BufferedWriter(new FileWriter(FILE_BUKU))) {
            for (Buku b : koleksiBuku) {
                writerBuku.write(b.toFileString());
                writerBuku.newLine();
            }
        } catch (IOException e) {
            System.out.println("Gagal menyimpan data buku: " + e.getMessage());
            return;
        }

        try (BufferedWriter writerAnggota = new BufferedWriter(new FileWriter(FILE_ANGGOTA))) {
            for (Anggota a : daftarAnggota) {
                // PILAR OOP: POLYMORPHISM (toFileString() memanggil versi kelas anak masing-masing)
                writerAnggota.write(a.toFileString());
                writerAnggota.newLine();
            }
        } catch (IOException e) {
            System.out.println("Gagal menyimpan data anggota: " + e.getMessage());
            return;
        }

        System.out.println("Semua data berhasil disimpan ke " + FILE_BUKU + " dan " + FILE_ANGGOTA);
    }

    public void muatDataDariFile() {
        try (BufferedReader readerBuku = new BufferedReader(new FileReader(FILE_BUKU))) {
            koleksiBuku.clear();
            String baris;
            while ((baris = readerBuku.readLine()) != null) {
                if (baris.trim().isEmpty()) continue;
                try {
                    koleksiBuku.add(Buku.fromFileString(baris));
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("Baris buku rusak/dilewati: " + baris);
                }
            }
        } catch (IOException e) {
            System.out.println("Belum ada file data buku tersimpan (" + FILE_BUKU + ").");
        }

        try (BufferedReader readerAnggota = new BufferedReader(new FileReader(FILE_ANGGOTA))) {
            daftarAnggota.clear();
            String baris;
            while ((baris = readerAnggota.readLine()) != null) {
                if (baris.trim().isEmpty()) continue;
                try {
                    String[] bagian = baris.split(";");
                    String tipe = bagian[0];
                    String id = bagian[1];
                    String nama = bagian[2];
                    String infoTambahan = bagian[3];

                    // KONSEP: Parsing Object (Mengubah data teks menjadi bentuk objek polimorfisme)
                    if (tipe.equalsIgnoreCase("MAHASISWA")) {
                        daftarAnggota.add(new Mahasiswa(id, nama, infoTambahan));
                    } else if (tipe.equalsIgnoreCase("DOSEN")) {
                        daftarAnggota.add(new Dosen(id, nama, infoTambahan));
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Baris anggota rusak/dilewati: " + baris);
                }
            }
        } catch (IOException e) {
            System.out.println("Belum ada file data anggota tersimpan (" + FILE_ANGGOTA + ").");
        }
        System.out.println("Proses memuat data selesai.");
    }
}
