function loadCVContent(idCV) {
    $.ajax({
      url: 'LoadCVServlet?id=' + idCV +"&mode=view", // Đường dẫn đến file JSP của bạn
      type: 'GET',
      success: function(response) {
        // Chèn nội dung của file JSP vào modal
        $('#modalContent').html(response);
      },
      error: function() {
        $('#modalContent').html('<p>Error loading content.</p>');
      }
    });
  }