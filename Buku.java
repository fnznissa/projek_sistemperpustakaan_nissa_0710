
public class Buku {

    private String kodeBuku; //enkapsulasi
    private String judul;
    private String penulis;
    private int stok;

    public Buku(String kodeBuku, String judul) {
        this(kodeBuku, judul, "Tidak diketahui", 0);
    }

    public Buku(String kodeBuku, String judul, int stok) {
        this(kodeBuku, judul, "Tidak diketahui", stok);
    }

    public Buku(String kodeBuku, String judul, String penulis, int stok) {
        this.kodeBuku = kodeBuku;
        this.judul = judul;
        this.penulis = penulis;
        this.stok = stok;
    }

    public String getKodeBuku() {
        return kodeBuku;
    }

    public String getJudul() {
        return judul;
    }

    public String getPenulis() {
        return penulis;
    }

    public int getStok() {
        return stok;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public void setStok(int stok) {
        // validasi sederhana supaya stok tidak pernah negatif
        if (stok < 0) {
            this.stok = 0;
        } else {
            this.stok = stok;
        }
    }

    // ===== METHOD tambahan untuk transaksi peminjaman/pengembalian =====
    public boolean kurangiStok() {
        if (stok > 0) {
            stok--;
            return true;
        }
        return false; // stok habis, tidak bisa dipinjam
    }

    public void tambahStok() {  //enkapsulasi
        stok++;
    }

    public String toFileString() {
        return kodeBuku + ";" + judul + ";" + penulis + ";" + stok;
    }

    // Parsing balik dari baris file menjadi object Buku
    public static Buku fromFileString(String baris) {
        String[] bagian = baris.split(";");
        String kode = bagian[0];
        String judul = bagian[1];
        String penulis = bagian[2];
        int stok = Integer.parseInt(bagian[3]);
        return new Buku(kode, judul, penulis, stok);
    }

    @Override
    public String toString() {
        return "[" + kodeBuku + "] " + judul + " - " + penulis + " (stok: " + stok + ")";
    }
}
