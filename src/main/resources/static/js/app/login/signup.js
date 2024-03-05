/**
 * <pre>
 * 회원가입 JS
 * </pre>
 * @author 김재성
 * @since 2024. 02. 26.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 *    수정일          수정자            수정내용
 * ----------     --------    ----------------------
 * 2024.02.26.     김재성       		최초작성
 * Copyright (c) 2024 by INNOVATION-T All right reserved
 * </pre>
 */

$(function(){
	
$("#signUpFormTag").on("submit",function(e){
		//alert("확인");
		e.preventDefault();
		
//		let formData = $(this).serialize();
		
		let formData = {
	        memLoginId: $("#memLoginId").val(),
	        memPw: $("#memPw").val(),
	        memNm: $("#memNm").val(),
	        memNick: $("#memNick").val(),
	        memTelno: $("#memTelno").val(),
	        memBirthDate: $("#memBirthDate").val(),
	        memEmail: $("#memEmail").val(),
	        memZip: $("#sample6_postcode").val(),
	        memAdres1: $("#sample6_address").val(),
	        memAdres2: $("#sample6_detailAddress").val()
    	};
		
		console.log("폼 입력값 : ",formData);
		
		$.ajax({
			url : "/login/signup",
			method : "POST",
			data : JSON.stringify(formData) ,
//			data : formData ,
			dataType : 'json',
			contentType: 'application/json',
			success : function(resp){
				if(resp.result =="OK"){
					alert(resp.message);
					location.href="/";
				}else if(resp.result =="DUPLICATED"){
					alert(resp.message);
				}else{
					alert(resp.message);
					location.reload();
				}
			},
			error : function(xhr, status,err){
				console.log("상태 : ", status);
				console.log("에러 : ", err);
				alert("잘못된 요청 발생 !");
			}
		});
	});
}) ;