<h1 style="text-align: center"> Tugas Kecil 3 Strategi Algoritma </h1>
<h2 style="text-align: center"> Word Ladder Solver </h2>

#### Nama : Bagas Sambega Rosyada
#### NIM : 13522071

Program ini adalah program yang dibuat untuk menyelesaikan permainan Word Ladder menggunakan algoritma UCS (_Uniform Cost Search_), 
Greedy Best First Search, dan A*. Program ini dibuat dalam rangka memenuhi Tugas Kecil 3 Strategi Algoritma menggunakan bahasa Java dan berbasis GUI dengan Java Swing.

## Daftar Isi
1. [Spesifikasi Program](#spesifikasi-program)
2. [Instalasi Program](#instalasi-program)
3. [Cara Penggunaan](#cara-penggunaan)

## Spesifikasi Program
Program ini dibuat menggunakan bahasa Java dan berbasis GUI dengan Java Swing. Program ini memiliki beberapa fitur, yaitu:
- Memilih algoritma yang ingin digunakan (UCS, Greedy Best First Search, A*)
- Memasukkan dan validasi kata awal dan kata akhir
- Menampilkan _output_ berupa _path_ dari kata awal ke kata akhir, banyak langkah yang diperlukan, waktu eksekusi, dan estimasi besar memori yang digunakan

Program Word Ladder _Solver_ ini menggunakan kamus bahasa Inggris yang berasal dari beberapa sumber yang sudah disediakan di folder `/data`. Kamus yang digunakan adalah:
1. github.com/dwyl (https://github.com/dwyl/english-words)
2. Merriam-Webster (https://github.com/matthewreagan/WebstersEnglishDictionary/)
3. Collins (https://boardgames.stackexchange.com/questions/38366/latest-collins-scrabble-words-list-in-text-file) (Search through https://www.collinsdictionary.com/dictionary/english)
4. Linux Mint list of words (libs/word.txt)
5. Free Dev API (https://github.com/meetDeveloper/freeDictionaryAPI/)

**_NOTE_:**
Ada kemungkinan besar beberapa kata tidak terdaftar pada kamus-kamus yang sudah ditambahkan di atas. Untuk menambahkan kamus Anda sendiri,
masukkan sebuah file berformat .txt ke folder `/data` yang berisi kumpulan kata dan dipisahkan oleh _newline_ untuk setiap katanya.

**_NOTE_:**
Semua kata yang dimasukkan dan juga diambil dari `/data` diubah  ke _lowercase_. Ada kemungkinan _path_ yang dihasilkan berbeda karena perbedaan kamus yang digunakan.

## Instalasi Program
Untuk menjalankan program ini, diperlukan Java Runtime Environment (JRE) terinstal yang dapat diunduh di https://www.java.com/en/download/. Setelah Java terpasang di perangkat, ikuti langkah-langkah berikut untuk menjalankan program ini:
1. Clone repository ini dengan menjalankan perintah berikut di terminal:
    ```
    git clone https://github.com/bagassambega/Tucil3_13522071.git
    ```
2. Buka folder tempat repository ini di-_clone_, dan jalankan program dengan membuka terminal dan menjalankan
    ```
   ./run.bat
   ```
   pada Windows, atau
    ```
   chmod +x run.sh
    ./run.sh
   ```
   pada Linux/MacOS. Atau _double click_ pada `run.sh` atau `run.bat` pada _file explorer_.
3. Jika program tidak dapat dijalankan dengan menggunakan _script_ di atas, maka jalankan program dengan menjalankan perintah berikut di terminal:
    ```
    cd src
    javac Main.java Dictionary/*.java UCS/*.java A_Star/*.java GUI/*.java GBFS/*.java -d ../bin
    cd ..
    cd bin
    java Main
    ```
## Cara Penggunaan
1. Jalankan program dengan cara pada bagian [Instalasi Program](#instalasi-program)
2. Masukkan kata awal dan kata akhir yang ingin dicari _path_-nya
3. Pilih algoritma yang ingin digunakan dengan menekan tombol di bawah _input_
4. Jika terdapat kesalahan pada _input_, akan muncul _error message_ di bawah _input_
5. Jika _input_ benar, maka akan muncul jendela baru yang menampilkan _output_ berupa _path_ dari kata awal ke kata akhir, banyak langkah yang diperlukan, waktu eksekusi, dan estimasi besar memori yang digunakan