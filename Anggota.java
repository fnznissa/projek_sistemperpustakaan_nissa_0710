public abstract class Anggota implements Peminjam {

    protected String idAnggota; //enkapsulasi
    protected String nama;

    private final KartuAnggota kartuAnggota;

    public Anggota(String idAnggota, String nama) {
        this.idAnggota = idAnggota;
        this.nama = nama;
        this.kartuAnggota = new KartuAnggota(idAnggota);
    }

    public String getIdAnggota() {
        return idAnggota;
    }

    public String getNama() {
        return nama;
    }

    public KartuAnggota getKartuAnggota() {
        return kartuAnggota;
    }

    public abstract void tampilkanInfo(); //abstraksi

    public abstract int getMaksHariPinjam();

    public abstract double getTarifDendaPerHari();

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

    @Override
    public String toString() {
        return "[" + idAnggota + "] " + nama;
    }
}
