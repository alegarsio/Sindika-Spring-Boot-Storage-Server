document.addEventListener('DOMContentLoaded', () => {
    const API_URL = 'http://localhost:8080/api/monitoring/usage-by-user';
    const tableBody = document.getElementById('user-usage-body');

    // Fungsi bantuan untuk format ukuran file
    function formatBytes(bytes, decimals = 2) {
        if (bytes === 0) return '0 Bytes';
        const k = 1024;
        const dm = decimals < 0 ? 0 : decimals;
        const sizes = ['Bytes', 'KB', 'MB', 'GB', 'TB'];
        const i = Math.floor(Math.log(bytes) / Math.log(k));
        return parseFloat((bytes / Math.pow(k, i)).toFixed(dm)) + ' ' + sizes[i];
    }

    async function fetchUserUsageData() {
        try {
            const response = await fetch(API_URL);
            if (!response.ok) {
                throw new Error(`Gagal mengambil data: ${response.statusText}`);
            }
            const data = await response.json();

            // Kosongkan isi tabel sebelum diisi data baru
            tableBody.innerHTML = '';

            if (data.length === 0) {
                tableBody.innerHTML = `<tr><td colspan="4" class="empty-text">Belum ada data penggunaan.</td></tr>`;
                return;
            }

            // Isi tabel dengan data dari API
            data.forEach(user => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${user.userId}</td>
                    <td>${user.userName}</td>
                    <td>${user.fileCount}</td>
                    <td>${formatBytes(user.totalSize)}</td>
                `;
                tableBody.appendChild(row);
            });

        } catch (error) {
            console.error('Error:', error);
            tableBody.innerHTML = `<tr><td colspan="4" class="error-text">Gagal memuat data. Periksa koneksi ke server.</td></tr>`;
        }
    }

    // Panggil fungsi untuk memuat data saat halaman dibuka
    fetchUserUsageData();
});