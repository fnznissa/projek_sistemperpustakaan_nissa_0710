// PILAR OOP: INHERITANCE (Dosen mewarisi kelas Anggota)
// RELASI OOP: ASSOCIATION (interaksi bebas dengan kelas Mahasiswa)
public class Dosen extends Anggota {

    private String nip;

    public Dosen(String idAnggota, String nama, String nip) {
        super(idAnggota, nama); // Keyword super untuk memanggil konstruktor induk
        this.nip = nip;
    }

    public String getNip() { return nip; }

    // PILAR OOP: POLYMORPHISM (Method Overriding)
    @Override
    public int getMaksHariPinjam() {
        return 30; 
    }

    @Override
    public double getTarifDendaPerHari() {
        return 500.0;
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("Dosen     | " + nama + " | NIP: " + nip
                + " | Maks pinjam: " + getMaksHariPinjam() + " hari"
                + " | Denda: Rp" + (int) getTarifDendaPerHari() + "/hari");
    }

    // RELASI OOP: ASSOCIATION (Method ini berinteraksi langsung dengan objek Mahasiswa)
    public void mengajar(Mahasiswa m) {
        System.out.println(nama + " mengajar " + m.getNama());
    }

    @Override
    public String toFileString() {
        return "DOSEN;" + idAnggota + ";" + nama + ";" + nip;
    }
}
