function deleteCate(id) {
    Swal.fire({
        title: "Are you sure?",
        text: "You won't be able to revert this!",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Yes, delete it!"
    }).then((result) => {
        if (result.isConfirmed) {
            Swal.fire({
                title: "Deleted!",
                text: "Your file has been deleted.",
                icon: "success"
            });
        }
    });
}


// $('#exampleModal').on('show.bs.modal', function (event) {
//     alert("abc");
//     var button = $(event.relatedTarget);
//     var id = button.data('id');
//     var name = button.data('name');
//     console.log(id)
//     console.log(name)
//
//     // Điền dữ liệu vào modal
//     var modal = $(this);
//     modal.find('#adminId').val(id);
//     modal.find('#newName').val(name);
// });