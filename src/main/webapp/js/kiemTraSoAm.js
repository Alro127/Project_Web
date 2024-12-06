document.querySelectorAll("#namKinhNghiem, #luong").forEach(function (element) {
  element.addEventListener("keydown", function (event) {
    if (event.key === '-' || event.key === 'e') {
      event.preventDefault(); // Ngừng hành động nhập liệu
      alert("Vui lòng nhập số không âm.");
    }
  });
});
