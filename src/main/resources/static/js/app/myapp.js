
function dynamicModal(){
    $("#dynamic-modal").modal('show');
    $("#myForm")[0].reset();
}

function modaltransaksi(){
    $("#modal-transaksi").modal('show');
    $("#add_transaksi")[0].reset();
    
}

function modalproduct(){
    $("#modal-product").modal('show');
    $("#formProduct")[0].reset();
}

//menampilkan modal list kategori
function listmodal(){
    $("#listmodalkategori").modal('show');
}

function reloadTable(){
    $("#tabel-kategori").DataTable().ajax.reload();
}

function reloadTableProduct(){
    $("#tabel-product").DataTable().ajax.reload();
}

//dynamic field
$(document).ready(function (){
    
   var i=1;
   
   $("#add-field").click(function (){
       
      i++; 
       
      $('#dynamic-field').append('<tr id="row'+i+'">\n\
            <td><select class="form-control" name="idproduct[]" id="idproduct">\n\
            <option value="">Pilih Product</option>\n\
            <option value="547de2c1-c6f8-42cf-b432-f657c701e45c">GIV</option>\n\
            <option value="ff7dba25-657c-42c2-8bb2-750724f0c309">Lifebuoy</option>\n\
            </select>\n\
            </td>\n\
            <td>\n\
            <input type="number" id="jumlah" class="form-control" name="jumlah[]"/>\n\
            </td>\n\
            <td>\n\
            <button type="button" id="'+i+'" class="btn btn-warning button-remove">\n\
            <span class="glyphicon glyphicon-trash"></span>Tidak Jadi</button>\n\
            </td>\n\
            </tr>'); 
   });
   
    $(document).on('click', '.button-remove', function(){
       
        var buttonRemove = $(this).attr('id');
        $('#row'+buttonRemove+'').remove();
    });
    
    
});

function insertkategori(){
    
    var txtNamaKategori = $("#nama").val();
    var txtActived = $("#active").val();
    var txtIdkategori = $("#idkategori").val();
    
    $.ajax({
       
        url: '/api/insertkategori',
        type: 'POST',
        data : JSON.stringify({nama : txtNamaKategori, active : txtActived, idkategori : txtIdkategori}),
        contentType : 'application/json;charset=UTF-8',
        dataType : 'JSON',
        
        complete: function () {
            reloadTable();
            $("#dynamic-modal").modal('hide');
            bootbox.alert('<center><h3>OK !</h3></center>');
        }
    });
}

function insertTransaksi(){
    
    var idproduct = $("#idproduct").val();
    
    $.ajax({
        url: '/api/insertTransaksi/',
        type: 'POST',
        data : $('#add_transaksi').serialize(),
        contentType : 'application/json;charset=UTF-8',
        dataType : 'JSON',
        
        complete: function (){
            $("#modal-transaksi").modal('hide');
            $("#add_transaksi")[0].reset();
        }
    });
}

//insert transaksi
$(document).ready(function (){
    
   $("#insertAllTransaksi").click(function (){
      
       $.ajax({
          
            url: '/api/insertTransaksi',
            method : 'POST',
            data : $("#serialize_transaksi").serialize(),
            success : function (response){
                console.log(response);
                alert('Data berhasil disimpan !');
                window.location.href="http://localhost:8080/data/yourcart";
            }
       });
   }); 
    
});

function insertproduct(){
    
    var txtIdproduct = $("#idproduct").val();
    var txtNamaProduct = $("#nama").val();
    var txtActiveStatus = $("#active").val();
    var txtHargaProduct = $("#harga").val();
    var txtJumlahProduct = $("#jumlah").val();
    var txtKeterangan = $("#keterangan").val();
    var txtIdkategori = $("#idkategori").val();
    
    $.ajax({
       
        url : '/api/insertproduct/'+txtIdkategori,
        type: 'POST',
        data : JSON.stringify({idproduct : txtIdproduct ,
            nama : txtNamaProduct, 
            active : txtActiveStatus, 
            harga : txtHargaProduct, 
            jumlah : txtJumlahProduct, 
            keterangan : txtKeterangan, 
            idkategori : txtIdkategori}),
        contentType : 'application/json;charset=UTF-8',
        dataType : 'JSON',
        
        complete : function(){
            reloadTableProduct();
            $("#modal-product").modal('hide');
            bootbox.alert('<center><h3>OK !</h3></center>');
        }
    });
}

$(document).on('click', '.editKategori', function(){
    
   var idkategori = $(this).attr('data-id'); 
    
   $.ajax({
        url: '/api/getkategori/'+idkategori,
        method : 'GET'
   }).success(function(response){
       
       $('[name="idkategori"]').val(response.idkategori);
       $('[name="kode"]').val(response.kode);
       $('[name="nama"]').val(response.nama);
       $('#active option[value="'+response.active+'"]').prop('selected', true);
       $('#dynamic-modal').modal('show');
   });
});

$(document).on('click', '.editProduct', function(){
    
    
   var idproduct = $(this).attr('data-id');
   
   $.ajax({
       
      url : '/api/products/'+idproduct ,
      method : 'GET' 
   }).success(function(response){
       
       $('[name="idproduct"]').val(response.idproduct);
       $('[name="kode"]').val(response.kode);
       $('[name="nama"]').val(response.nama);
       $('#active option[value="'+response.active+'"]').prop('selected', true);
       $('[name="harga"]').val(response.harga);
       $('[name="jumlah"]').val(response.jumlah);
       $('[name="keterangan"]').val(response.keterangan);
       $('[name="idkategori"]').val(response.kategori.idkategori);
       $('[name="namakategori"]').val(response.kategori.nama);
       $('#modal-product').modal('show');
   });
    
});

//list product
$(document).ready(function(){
    
   var table = $("#tabel-product");
   
   if(table.length){
       
       table.DataTable({
           
           lengthMenu : [[3, 5, -1], ['Tampilkan 3', 'Tampilkan 5', 'Tampilkan All']],
           pageLength : 3,
           ajax : {
               
               url : '/api/products',
               dataSrc : ''
           },
           
           columns : [
               {
                   data : 'idproduct'
               },
               {
                   data : 'kode'
               },
               {
                   data : 'nama'
               },
               {
                   data : 'active'
               },
               {
                   data : 'harga'
               },
               {
                   data : 'jumlah'
               },
               {
                   data : 'keterangan'
               },
               {
                   data : 'kategori.nama'
               },
               {
                   data : 'idproduct',
                   bSortable : false,
                   mRender : function(data, type, row){
                       
                       var str = '';
                       
                       str += '<button class="btn btn-primary editProduct" data-id="'+data+'" >\n\
                       <span class="glyphicon glyphicon-pencil"></span>Update</button>';
                       str += '<button class="btn btn-danger deleteProduct" data-id="'+data+'">\n\
                       <span class="glyphicon glyphicon-trash"></span>Hapus</button>';
                       return str;
                   }
               }
           ]
       });
   }
    
});

//menampilkan transaksi list product
$(document).ready(function (){
   
    var tableshow = $("#tabels");
    
    if(tableshow.length){
        
        tableshow.DataTable({
           
            lengthMenu : [[3, 5, -1], ['Tampilkan 3', 'Tampilkan 5', 'Tampilkan Semua']],
            pageLength : 3,
            
            ajax : {
                
                url : '/api/products',
                dataSrc : ''
            },
            
                columns : [
                    {
                        data : 'nama'
                    },
                    {
                        data : 'harga'
                    },
                    {
                        data : 'jumlah'
                    },
                    {
                        data : 'kategori.nama'
                    },
                    {
                        data : 'idproduct',
                        bSortable : false,
                        mRender:function(data, type, row){
                            
                            var str = "";
                            
                            str += '<a href="/data/insertProductToCart/'+data+'" class="btn btn-primary">\n\
                            <span class="glyphicon glyphicon-shopping-cart"></span></a>';
                            
                            return str;
                        }
                    }
                ]
        });
    }
    
});



//list modal kategori
$(document).ready(function (){
    
   var tablelist = $("#tabel-list");
   
   if(tablelist.length){
       
       tablelist.DataTable({
           
           lengthMenu : [[3, 5], ['3', '5']],
           pageLength : 3,
           
           ajax : {
               
               url : '/api/kategoris',
               dataSrc : ''
           },
           
           columns : [
               {
                   data : 'nama'
               },
               {
                   data : 'active'
               },
               {
                   data : 'idkategori',
                   bSortable : false,
                   mRender:function(data, type, row){
                       
                       var str = '';
                       
                       str += '<button class="btn btn-primary pilih" data-id="'+data+'">\n\
                       <span class="glyphicon glyphicon-ok"></span>Pilih</button>';
                       
                       return str;
                   }
               }
           ]
       });
   }
    
});

//click pilih kategori
$(document).on('click', '.pilih', function(e){
    
    var idkategori = $(this).attr("data-id");
    $.ajax({
       url : '/api/getkategori/'+idkategori, 
       method : 'GET'
    }).success(function(response){
        
        document.getElementById("idkategori").value = response.idkategori;
        document.getElementById("namakategori").value = response.nama;
        
        $("#listmodalkategori").modal('hide');
    });
});

//click hapus product
$(document).on('click', '.deleteProduct', function (e){
    
   var idproduct = $(this).attr("data-id"); 
   if(confirm('yakin ingin hapus data ini '+idproduct+' ?')){
       
       $.ajax({
            url: '/api/hapusproduct/'+idproduct, 
            method : 'DELETE'
       }).success(function(){
          reloadTableProduct();
          bootbox.alert('<center><h3>Data berhasil dihapus !</h3></center>');
       });
   }
    
});

//list kategori
$(document).ready(function(){
    
    
   var table = $('#tabel-kategori');
   
   
   if(table.length){
       
       table.DataTable({
           
          lengthMenu : [[3, 5, -1], ['Tampilkan 3', 'Tampilkan 5', 'All']],
          pageLength : 3,
          ajax : {
              url : '/api/kategoris',
              dataSrc : ''
          },
          
            columns : [
                {
                    data : 'idkategori'
                },
                {
                    data : 'kode'
                },
                {
                    data : 'nama'
                },
                {
                    data : 'active'
                },
                {
                    data : 'idkategori',
                    bSortable : false,
                    mRender : function(data, type, row){
                        
                        var str = '';
                        
                       str += '<button class="btn btn-primary editKategori" data-id="'+ data +'">\n\
                       <span class="glyphicon glyphicon-pencil"></span>Update</button> ';
                       
                       str += '<button class="btn btn-danger deleteKategori" data-id="'+ data +'">\n\
                       <span class="glyphicon glyphicon-trash"></span>Delete</button>';
                       
                               
                        return str;
                    }
                }
            ]
       });
   }
    
    
});
