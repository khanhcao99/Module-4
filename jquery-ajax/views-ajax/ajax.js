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
        }
    })

}

function displayTable(data) {
    let result = ""
    result += "<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\">"
    result += "<tbody>"
    for (let i = 0; i < data.length; i++) {
        result += "<tr>"
        result += "<td>" + (i + 1) + "</td>"
        result += "<td>" + data[i].name + "</td>"
        result += "<td>" + data[i].price + "</td>"
        result += "<td>" + data[i].color + "</td>"
        result += "<td>" + data[i].description + "</td>"
        result += "<td>" + data[i].category.name + "</td>"
        result += "<td><button type=\"button\" class=\"btn btn-danger\" onclick='updateFrom(" + data[i].id + ")'>Sửa</button>"
        result += "<button type=\"button\" class=\"btn btn-danger\" onclick='deleteForm(" + data[i].id + ")'>Xóa</button></td>"
        result += "</tr>"
    }
    result += "</tbody>"
    result += "</table>"
    document.getElementById("list-phones").innerHTML = result;
}

function displayCreateForm() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/phones/categories",
        success: function (data) {
            let result = ""
            for (let i = 0; i < data.length; i++) {
                result += "<option value='" + data[i].id + "'>" + data[i].name
                result += "</option>"
            }
            document.getElementById("categories").innerHTML = result;
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
    document.getElementById("categories").value = ""
    document.getElementById("messageCreate").innerHTML = ""
}

function setFormUpdate(data){
    document.getElementById("name").value = data.name
    document.getElementById("price").value = data.price
    document.getElementById("color").value = data.color
    document.getElementById("description").value = data.description
    document.getElementById("categories").value = data.category.id
}

function createPhone() {
    let name = $('#name').val()
    let price = $('#price').val()
    let color = $('#color').val()
    let descriptionP = $('#description').val()
    let id_category = $('#categories').val()

    let phone = {
        name: name,
        price: price,
        color: color,
        description: descriptionP,
        category: {
            id: id_category,
        }
    }

    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "POST",
        url: "http://localhost:8080/phones",
        data: JSON.stringify(phone),
        success: function () {
            resetFormCreate()
            document.getElementById("messageCreate").innerHTML = "Tạo thành công!"
            getAllPhone()
        }
    })
    event.preventDefault()
}

function updateFrom(id){
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/phones/categories",
        success: function (data1) {
            let result = ""
            for (let i = 0; i < data1.length; i++) {
                result += "<option value='" + data1[i].id + "'>" + data1[i].name
                result += "</option>"
            }
            document.getElementById("categories").innerHTML = result;
        }
    })

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/phones/" + id,
        success: function (data){
            idPhone = data.id
           setFormUpdate(data)
            document.getElementById("messageCreate").innerHTML = ""
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
    let id_category = $('#categories').val()

    let phone = {
        id: idPhone,
        name: name,
        price: price,
        color: color,
        description: descriptionP,
        category: {
            id: id_category,
        }
    }

    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "PUT",
        url: "http://localhost:8080/phones",
        data: JSON.stringify(phone),
        success: function (data) {
            setFormUpdate(data)
            document.getElementById("messageCreate").innerHTML = "Chỉnh sửa thành công"
            getAllPhone()
        }
    })
    event.preventDefault()
}

function deleteForm(id){
    idPhone = id
    document.getElementById("messageDelete").innerHTML = ""
    $('#deleteModal').modal("show")
}
function deletePhone(){
    $.ajax({
        type: "DELETE",
        url: "http://localhost:8080/phones/" + idPhone,
        success: function() {
            document.getElementById("messageDelete").innerHTML = "Xóa thành công"
            getAllPhone()
        }
    })
    event.preventDefault()
}


