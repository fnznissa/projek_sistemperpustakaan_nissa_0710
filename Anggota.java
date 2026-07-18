// PILAR OOP: ABSTRACTION (Abstract Class) & INHERITANCE (Implementasi Interface)
// RELASI OOP: ASSOCIATION (Asosiasi dengan kelas Buku pada method pinjamBuku)
//             AGGREGATION (Anggota "memiliki" KartuAnggota sebagai bagian dari dirinya)

public abstract class Anggota implements Peminjam {

    // PILAR OOP: ENCAPSULATION (Menggunakan protected agar anak bisa akses langsung)
    protected String idAnggota;
    protected String nama;

    // RELASI OOP: COMPOSITION / AGGREGATION (Anggota "memiliki" KartuAnggota)
    private final KartuAnggota kartuAnggota;

    public Anggota(String idAnggota, String nama) {
        this.idAnggota = idAnggota;
        this.nama = nama;
        this.kartuAnggota = new KartuAnggota(idAnggota);
    }

    // KONSEP: Encapsulation
    public String getIdAnggota() { return idAnggota; }
    public String getNama() { return nama; }
    public KartuAnggota getKartuAnggota() { return kartuAnggota; }

    // PILAR OOP: ABSTRACTION
    public abstract void tampilkanInfo();
    public abstract int getMaksHariPinjam();
    public abstract double getTarifDendaPerHari();

    // PILAR OOP: POLYMORPHISM
    @Override
    public void pinjamBuku(Buku buku) {
        System.out.println(nama + " meminjam buku: " + buku.getJudul());
    }

    @Override
    public double hitungDenda(long hariTerlambat) {
        if (hariTerlambat <= 0) {
            return 0; // tidak telat, denda = Rp0
        }
        return hariTerlambat * getTarifDendaPerHari();
    }

    public abstract String toFileString();

    // KONSEP: Polymorphism (Meng-override method toString bawaan Java Object
    @Override
    public String toString() {
        return "[" + idAnggota + "] " + nama;
    }
}
