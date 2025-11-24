# TokoSiDia

TokoSiDia adalah aplikasi e-commerce desktop Java untuk penjual dan pembeli, menyediakan manajemen produk, keranjang, checkout, histori transaksi, pengiriman, autentikasi, dan antarmuka grafis lengkap untuk toko daring lokal dengan dukungan gambar produk, laporan, dan notifikasi email otomatis.

**Fitur Utama:**
- **Manajemen Produk:** Tambah, edit, hapus, dan tampilkan produk dengan gambar.
- **Keranjang & Checkout:** Proses pembelian dan perhitungan total.
- **Transaksi & Histori:** Simpan dan lihat riwayat transaksi.
- **Pengiriman & Laporan:** Update pengiriman dan lihat laporan pemasukan.
- **Autentikasi & Profil:** Login, registrasi, dan pengelolaan profil pembeli/penjual.
- **Notifikasi Email:** Pengiriman email otomatis (pengingat atau konfirmasi).

**Struktur Singkat:**
- `Controller/` - Logika aplikasi dan pengontrol GUI.
- `View/` - Halaman antarmuka pengguna (Swing).
- `Modul/` - Model data dan utilitas (produk, user, transaksi).
- `Photos/` - Koleksi gambar produk dan seller.
- `lib/` - Dependensi eksternal (jika ada).

**Prasyarat:**
- Java JDK 8 atau lebih baru.
- IDE Java (IntelliJ IDEA, Eclipse, NetBeans) direkomendasikan.

**Menjalankan (disarankan menggunakan IDE):**
1. Buka folder proyek `TokoSiDia` di IDE pilihan Anda.
2. Atur classpath agar menyertakan folder `lib/` (jika ada library eksternal).
3. Jalankan kelas `Main.java` di root proyek atau `View/Main.java` sebagai aplikasi Java Swing.

Contoh singkat (PowerShell) — sesuaikan bila perlu:
```powershell
# Compile (opsional; IDE biasanya lebih mudah)
javac -cp "lib/*" -d out (Get-ChildItem -Recurse -Filter "*.java" | ForEach-Object -ExpandProperty FullName)

# Jalankan (ganti MainClass sesuai struktur)
java -cp "out;lib/*" Main
```
