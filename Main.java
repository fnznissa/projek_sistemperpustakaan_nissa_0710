import java.util.InputMismatchException; //Import di bawah ini "never used" (bisa dihapus) karena pencegahan error 
// pada menu input sudah ditangani oleh NumberFormatException, bukan InputMismatchException
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Perpustakaan perpustakaan = new Perpustakaan();

        boolean berjalan = true;

        while (berjalan) {
            tampilkanMenu();
            int pilihan;

            // KONSEP: Exception Handling (Mengamankan aplikasi agar tidak crash saat salah input tipe data)
            try {
                pilihan = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid, masukkan angka menu yang benar.\n");
                continue; 
            }

            switch (pilihan) {
                case 1: tambahBuku(scanner, perpustakaan); break;
                case 2: tambahAnggota(scanner, perpustakaan); break;
                case 3: pinjamBuku(scanner, perpustakaan); break;
                case 4: kembalikanBuku(scanner, perpustakaan); break;
                case 5: perpustakaan.tampilkanSemuaBuku(); break;
                case 6: perpustakaan.tampilkanSemuaAnggota(); break;
                case 7: perpustakaan.simpanDataKeFile(); break;
                case 8: perpustakaan.muatDataDariFile(); break;
                case 0:
                    berjalan = false;
                    System.out.println("Terima kasih, sampai jumpa!");
                    break;
                default:
                    System.out.println("Menu tidak dikenali, coba lagi.");
            }
            System.out.println();
        }
        scanner.close();
    }

    private static void tampilkanMenu() {
        System.out.println("=== SISTEM PERPUSTAKAAN STEIN ===");
        System.out.println("1. Tambah Buku\n2. Tambah Anggota\n3. Pinjam Buku\n4. Kembalikan Buku (hitung denda)\n5. Tampilkan Semua Buku\n6. Tampilkan Semua Anggota\n7. Simpan Data ke File\n8. Muat Data dari File\n0. Keluar");
        System.out.print("Pilih menu: ");
    }

    private static void tambahBuku(Scanner scanner, Perpustakaan perpustakaan) {
        System.out.print("Kode buku: "); String kode = scanner.nextLine().trim();
        System.out.print("Judul buku: "); String judul = scanner.nextLine().trim();
        System.out.print("Penulis: "); String penulis = scanner.nextLine().trim();
        System.out.print("Stok: ");

        int stok;
        try {
            stok = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Stok harus angka, buku disimpan dengan stok 0.");
            stok = 0;
        }

        // PILAR OOP: POLYMORPHISM (Implementasi Overloading)
        perpustakaan.tambahBuku(new Buku(kode, judul, penulis, stok));
    }

    private static void tambahAnggota(Scanner scanner, Perpustakaan perpustakaan) {
        System.out.print("Tipe anggota (1=Mahasiswa, 2=Dosen): ");
        String tipe = scanner.nextLine().trim();
        System.out.print("ID anggota: "); String id = scanner.nextLine().trim();
        System.out.print("Nama: "); String nama = scanner.nextLine().trim();

        // PILAR OOP: POLYMORPHISM (Upcasting objek anak Mahasiswa/Dosen menjadi tipe dasar Anggota)
        if (tipe.equals("1")) {
            System.out.print("NPM: "); String npm = scanner.nextLine().trim();
            perpustakaan.tambahAnggota(new Mahasiswa(id, nama, npm));
        } else if (tipe.equals("2")) {
            System.out.print("NIP: "); String nip = scanner.nextLine().trim();
            perpustakaan.tambahAnggota(new Dosen(id, nama, nip));
        } else {
            System.out.println("Tipe tidak dikenali, anggota tidak ditambahkan.");
        }
    }

    private static void pinjamBuku(Scanner scanner, Perpustakaan perpustakaan) {
        System.out.print("Kode buku yang dipinjam: "); String kodeBuku = scanner.nextLine().trim();
        System.out.print("ID anggota peminjam: "); String idAnggota = scanner.nextLine().trim();
        perpustakaan.pinjamBuku(kodeBuku, idAnggota);
    }

    private static void kembalikanBuku(Scanner scanner, Perpustakaan perpustakaan) {
        System.out.print("ID transaksi yang dikembalikan (contoh: TRX1): ");
        String idTransaksi = scanner.nextLine().trim();
        perpustakaan.kembalikanBuku(idTransaksi);
    }
}
