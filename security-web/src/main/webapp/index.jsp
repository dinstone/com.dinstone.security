<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<style type="text/css">
.text-field {
	position: absolute;
	left: 40%;
}

label {
	display: inline-table;
	width: 90px;
	margin: 0px 0px 10px 20px;
}

img {
	width: 150px;
	margin: 0px 20px 10px 110px;
}

h2 {
	margin: 20px 20px 20px 40px;
}

button {
	margin: 20px 20px 10px 110px
}
</style>
<script type="text/javascript" src="jquery-1.8.3.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#submit").click(function() {
			var req = {};
			req.username = $("#accessUser").val();
			req.password = $("#accessPassword").val();
			//alert(JSON.stringify(req));

			var urls = "account/login.do"
			$.ajax({
				type : "POST",
				url : urls,
				data : req,
				dataType : "json",
				success : function(msg) {
					alert("success");
					$("#data").val(JSON.stringify(msg));
				},
				error : function(xhrequest, textStatus, errorThrown) {
					//alert(xhrequest.status);
					//alert(xhrequest.readyState);
					//alert(textStatus);
					var msg = "服务未认证";
					var sessionstatus = xhrequest.getResponseHeader("AC-Status");
					if(sessionstatus=="UNAUTHORIZED"){
						msg = "会话失效，请重新登录";
					}else if(sessionstatus=="DENIED"){
						msg = "你没有权限访问，请重新登录";
					}
					$("#data").val(msg);
				}
			});
		});
	});
</script>
</head>
<body>

	<div class="text-field">
		<h2>获取认证信息</h2>
		<form id="authen" method="post" onsubmit="return false;">
			<table>
				<tr>
					<td><label>AccessUser</label></td>
					<td><input type="text" name="accessUser" id="accessUser" /></td>
				</tr>
				<tr>
					<td><label>AccessPassword</label></td>
					<td><input type="password" name="accessPassword" id="accessPassword" /></td>
				</tr>
				<tr>
					<td><button type="submit" id="submit">提交</button></td>
					<td><button type="reset">重置</button></td>
				</tr>
			</table>
		</form>
		<textarea rows="5" style="width: 100%" id="data"></textarea>
	</div>

</body>
</html>
