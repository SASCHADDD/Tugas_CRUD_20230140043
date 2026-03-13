$(document).ready(function () {
    const API_URL = '/ktp';

    // Initial load
    loadKtpData();

    // Form submission (Create/Update)
    $('#ktp-form').on('submit', function (e) {
        e.preventDefault();

        const id = $('#ktp-id').val();
        const data = {
            nomorKtp: $('#nomorKtp').val(),
            namaLengkap: $('#namaLengkap').val(),
            alamat: $('#alamat').val(),
            tanggalLahir: $('#tanggalLahir').val(),
            jenisKelamin: $('#jenisKelamin').val()
        };

        if (id) {
            updateKtp(id, data);
        } else {
            createKtp(data);
        }
    });

    // Cancel edit
    $('#cancel-btn').on('click', function () {
        resetForm();
    });

    // Search functionality
    $('#table-search').on('keyup', function() {
        var value = $(this).val().toLowerCase();
        $("#ktp-list tr").filter(function() {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });

    function loadKtpData() {
        $.ajax({
            url: API_URL,
            type: 'GET',
            success: function (response) {
                renderTable(response);
            },
            error: function (xhr) {
                showNotification('Gagal memuat data: ' + getErrorMessage(xhr), 'error');
            }
        });
    }

    function createKtp(data) {
        $.ajax({
            url: API_URL,
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function () {
                showNotification('Data berhasil ditambahkan!', 'success');
                resetForm();
                loadKtpData();
            },
            error: function (xhr) {
                showNotification('Gagal menambahkan data: ' + getErrorMessage(xhr), 'error');
            }
        });
    }

    function updateKtp(id, data) {
        $.ajax({
            url: `${API_URL}/${id}`,
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function () {
                showNotification('Data berhasil diperbarui!', 'success');
                resetForm();
                loadKtpData();
            },
            error: function (xhr) {
                showNotification('Gagal memperbarui data: ' + getErrorMessage(xhr), 'error');
            }
        });
    }

    window.deleteKtp = function (id) {
        if (confirm('Apakah Anda yakin ingin menghapus data ini?')) {
            $.ajax({
                url: `${API_URL}/${id}`,
                type: 'DELETE',
                success: function () {
                    showNotification('Data berhasil dihapus!', 'success');
                    loadKtpData();
                },
                error: function (xhr) {
                    showNotification('Gagal menghapus data: ' + getErrorMessage(xhr), 'error');
                }
            });
        }
    };

    window.editKtp = function (id) {
        $.ajax({
            url: `${API_URL}/${id}`,
            type: 'GET',
            success: function (data) {
                $('#ktp-id').val(data.id);
                $('#nomorKtp').val(data.nomorKtp);
                $('#namaLengkap').val(data.namaLengkap);
                $('#alamat').val(data.alamat);
                $('#tanggalLahir').val(data.tanggalLahir);
                $('#jenisKelamin').val(data.jenisKelamin);

                $('#form-title').text('Update Data KTP');
                $('#submit-btn').html('<i class="fas fa-save"></i> Perbarui Data');
                $('#cancel-btn').show();
                
                // Scroll to form
                window.scrollTo({ top: 0, behavior: 'smooth' });
            },
            error: function (xhr) {
                showNotification('Gagal mengambil data: ' + getErrorMessage(xhr), 'error');
            }
        });
    };

    function renderTable(data) {
        const $list = $('#ktp-list');
        $list.empty();

        if (data.length === 0) {
            $list.append('<tr><td colspan="6" style="text-align: center; color: #94a3b8;">Tidak ada data yang ditemukan.</td></tr>');
            return;
        }

        data.forEach(item => {
            $list.append(`
                <tr>
                    <td>${item.nomorKtp}</td>
                    <td>${item.namaLengkap}</td>
                    <td>${item.alamat}</td>
                    <td>${item.tanggalLahir}</td>
                    <td>${item.jenisKelamin}</td>
                    <td>
                        <div class="action-btns">
                            <button class="btn-icon btn-edit" onclick="editKtp(${item.id})">
                                <i class="fas fa-edit"></i>
                            </button>
                            <button class="btn-icon btn-delete" onclick="deleteKtp(${item.id})">
                                <i class="fas fa-trash"></i>
                            </button>
                        </div>
                    </td>
                </tr>
            `);
        });
    }

    function resetForm() {
        $('#ktp-form')[0].reset();
        $('#ktp-id').val('');
        $('#form-title').text('Tambah KTP Baru');
        $('#submit-btn').html('<i class="fas fa-plus"></i> Simpan Data');
        $('#cancel-btn').hide();
    }

    function showNotification(message, type) {
        const $notif = $('#notification');
        $notif.text(message).removeClass('success error').addClass(`show ${type}`);
        
        setTimeout(() => {
            $notif.removeClass('show');
        }, 3000);
    }

    function getErrorMessage(xhr) {
        try {
            const response = JSON.parse(xhr.responseText);
            return response.message || response.error || 'Terjadi kesalahan server';
        } catch (e) {
            return xhr.statusText || 'Kesalahan tidak diketahui';
        }
    }
});
