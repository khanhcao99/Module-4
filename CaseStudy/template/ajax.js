// $(window).on("load resize ", function() {
//     var scrollWidth = $('.tbl-content').width() - $('.tbl-content table').width();
//     $('.tbl-header').css({'padding-right':scrollWidth});
// }).resize();

getAllPhone();
let idPhone;
function getAllPhone() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/phones",
        success: function (data) {
            console.log(data)
            displayTable(data)
            displayBrands()
        }
    })

}

function changePrice(n){
const formatter = new Intl.NumberFormat('it-IT', {
    style: 'currency',
    currency: 'VND',
    // minimumFractionDigits: 2
})
return  formatter.format(n);
}

function displayTable(data) {
    let result = ""
    result += "<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\">"
    result += "<tbody>"
    for (let i = 0; i < data.length; i++) {
        result += "<tr>"
        result += "<td>" + (i + 1) + "</td>"
        result += "<td>" + data[i].name + "</td>"
        result += "<td>" + changePrice(data[i].price) + "</td>"
        result += "<td>" + data[i].color + "</td>"
        result += "<td>" + data[i].description + "</td>"
        result += "<td><img width='100px' height='100px' src='../../../static/image/" + data[i].imageUrl + "' ></td>"
        result += "<td>" + data[i].brand.name + "</td>"
        result += "<td><button type=\"button\" class=\"btn btn-danger\" onclick='updateFrom(" + data[i].id + ")'>Sửa</button>"
        result += "<button style=\"margin-left: 20px\" type=\"button\" class=\"btn btn-danger\" onclick='deleteForm(" + data[i].id + ")'>Xóa</button></td>"
        result += "</tr>"
    }
    result += "</tbody>"
    result += "</table>"
    document.getElementById("list-phones").innerHTML = result;
}

function displayCreateForm() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/phones/brands",
        success: function (data) {
            let result = ""
            for (let i = 0; i < data.length; i++) {
                result += "<option value='" + data[i].id + "'>" + data[i].name
                result += "</option>"
            }
            document.getElementById("brands").innerHTML = result;
        }
    })
    resetFormCreate()
    document.getElementById("titleFrom").innerHTML = "Tạo mới sản phẩm"
    document.getElementById("button").innerHTML = "Tạo mới"
    document.getElementById("button").setAttribute("onclick", "createPhone()")
    $('#myModal').modal('show');
}

function resetFormCreate(){
    document.getElementById("name").value = ""
    document.getElementById("price").value = ""
    document.getElementById("color").value = ""
    document.getElementById("description").value = ""
    document.getElementById("imagePhone").innerHTML = ""
    document.getElementById("imageUrl").value = ""
    document.getElementById("brands").value = ""
    document.getElementById("messageCreate").innerHTML = ""
}

function setFormUpdate(data){
    document.getElementById("name").value = data.name
    document.getElementById("price").value = data.price
    document.getElementById("color").value = data.color
    document.getElementById("description").value = data.description
    document.getElementById("brands").value = data.brand.id
}

function createPhone() {
    let name = $('#name').val()
    let price = $('#price').val()
    let color = $('#color').val()
    let descriptionP = $('#description').val()
    let imageUrl =  $('#imageUrl')[0].files[0];
    let id_brand = $('#brands').val()
    let phone = {
        name: name,
        price: price,
        color: color,
        description: descriptionP,
        brand: {
            id: id_brand,
        }
    }
    let formData = new FormData();
    formData.append("fileImage", imageUrl);
    formData.append("phone",new Blob([JSON.stringify(phone)],{type : "application/json"}))
    $.ajax({
        contentType: false,
        processData: false,
        type: "POST",
        data: formData,
        url: "http://localhost:8080/phones",
        success: function () {
            $('#myModal').modal('hide');
            resetFormCreate()
            // document.getElementById("messageCreate").innerHTML = "Tạo thành công!"
            Swal.fire({
                position: 'center',
                icon: 'success',
                title: 'Tạo mới thành công',
                showConfirmButton: false,
                timer: 1000

            })
            setTimeout(getAllPhone, 100)
        }
    })
    event.preventDefault()
}

function updateFrom(id){
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/phones/brands",
        success: function (data1) {
            let result = ""
            for (let i = 0; i < data1.length; i++) {
                result += "<option value='" + data1[i].id + "'>" + data1[i].name
                result += "</option>"
            }
            document.getElementById("brands").innerHTML = result;
        }
    })

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/phones/" + id,
        success: function (data){
            let resultImage = ""
                resultImage += "<img width='100px' height='100px' src='../../../static/image/" + data.imageUrl + "' >"
            idPhone = data.id
           setFormUpdate(data)
            document.getElementById("imagePhone").innerHTML = resultImage;
            // document.getElementById("messageCreate").innerHTML = "";
        }
    })

    document.getElementById("titleFrom").innerHTML = "Chỉnh sửa sản phẩm"
    document.getElementById("button").innerHTML = "Chỉnh sửa"
    document.getElementById("button").setAttribute("onclick", "updatePhone()")
    $('#myModal').modal("show")

}

function updatePhone(){
    let name = $('#name').val()
    let price = $('#price').val()
    let color = $('#color').val()
    let descriptionP = $('#description').val()
    let imageUrl =  $('#imageUrl')[0].files[0];
    let id_brand = $('#brands').val()
    let phone = {
        id: idPhone,
        name: name,
        price: price,
        color: color,
        description: descriptionP,
        brand: {
            id: id_brand,
        }
    }
    let formData = new FormData();
    formData.append("fileImage", imageUrl);
    formData.append("phone",new Blob([JSON.stringify(phone)],{type : "application/json"}))
    Swal.fire({
        title: 'Bạn có chắc chắn muốn chỉnh sửa',
        showDenyButton: true,
        // showCancelButton: true,
        confirmButtonText: 'Đồng ý',
        denyButtonText: `Hủy bỏ`,
    }).then((result) => {
        /* Read more about isConfirmed, isDenied below */
        if (result.isConfirmed) {
            $.ajax({
                contentType: false,
                processData: false,
                type: "PUT",
                data: formData,
                url: "http://localhost:8080/phones",
                success: function (data) {
                    setFormUpdate(data)
                    let resultImage = ""
                    resultImage += "<img width='100px' height='100px' src='../../../static/image/" + data.imageUrl + "' >"
                    document.getElementById("imagePhone").innerHTML = resultImage;
                    // document.getElementById("messageCreate").innerHTML = "Chỉnh sửa thành công"
                }
            })

            Swal.fire('Chỉnh sửa thành công!', '', 'success')
            setTimeout(getAllPhone, 100)
        } else if (result.isDenied) {
            Swal.fire('Hủy bỏ thành công', '', 'info')
        }
    })
    event.preventDefault()
}

function deleteForm(id){
    idPhone = id
    const swalWithBootstrapButtons = Swal.mixin({
        customClass: {
            confirmButton: 'btn btn-success',
            cancelButton: 'btn btn-danger'
        },
        buttonsStyling: false
    })

    swalWithBootstrapButtons.fire({
        title: 'Bạn có chắc muốn xóa sản phẩm?',
        text: "Dữ liệu sẽ mất khi đồng ý",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Đồng ý!',
        cancelButtonText: 'Hủy bỏ!',
        reverseButtons: true
    }).then((result) => {
        if (result.isConfirmed) {
            swalWithBootstrapButtons.fire(
                'Xóa thành công!',
                'Sản phẩm đã bị xóa bỏ',
                'success'
            )
            deletePhone(idPhone);
           setTimeout(getAllPhone,100)
        } else if (
            /* Read more about handling dismissals below */
            result.dismiss === Swal.DismissReason.cancel
        ) {
            swalWithBootstrapButtons.fire(
                'Hủy bỏ',
                'Sản phẩm hoàn tác',
                'error'
            )
        }
    })
}
function deletePhone(){
    $.ajax({
        type: "DELETE",
        url: "http://localhost:8080/phones/" + idPhone,
        success: function() {
            // document.getElementById("messageDelete").innerHTML = "Xóa thành công"
            // $('#deleteModal').modal("hide")
            // getAllPhone()
        }
    })
    event.preventDefault()
}
function searchListPhone(){
    let search = $('#search').val()
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/phones/search/" + search,
        success: function (data) {
            console.log(data)
            displayTable(data)

        }
    })
    event.preventDefault()
}

$('#search').keypress(function(event) {
    if (event.keyCode == 13 || event.which == 13) {
        searchListPhone()
    }
});

function displayBrands(){
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/phones/brands",
        success: function (data) {
            let result = ""
            for (let i = 0; i < data.length; i++) {
                result += "<button style='margin: 10px 10px 0 0' class='btn btn-primary' onClick='findAllByBrands(" + data[i].id + ")'>" + data[i].name + "</button>"
            }
            document.getElementById("get_by_brands").innerHTML = result;
        }
    })

}

function findAllByBrands(id){
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/phones/id_brands/" + id,
        success: function (data) {
            console.log(data)
            displayTable(data)

        }
    })
    event.preventDefault()
}


