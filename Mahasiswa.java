// PILAR OOP: INHERITANCE (Mahasiswa mewarisi semua sifat & kelakuan Anggota)
public class Mahasiswa extends Anggota {

    private String npm; // Enkapsulasi atribut spesifik Mahasiswa

    public Mahasiswa(String idAnggota, String nama, String npm) {
        // KONSEP: Keyword 'super' (Memanggil constructor milik induk kelas Anggota)
        super(idAnggota, nama); 
        this.npm = npm;
    }

    public String getNpm() { return npm; }

    // PILAR OOP: POLYMORPHISM (Method Overriding)
    @Override
    public int getMaksHariPinjam() {
        return 7;
    }

    @Override
    public double getTarifDendaPerHari() {
        return 1000.0;
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("Mahasiswa | " + nama + " | NPM: " + npm
                + " | Maks pinjam: " + getMaksHariPinjam() + " hari"
                + " | Denda: Rp" + (int) getTarifDendaPerHari() + "/hari");
    }

    @Override
    public String toFileString() {
        return "MAHASISWA;" + idAnggota + ";" + nama + ";" + npm;
    }
}
