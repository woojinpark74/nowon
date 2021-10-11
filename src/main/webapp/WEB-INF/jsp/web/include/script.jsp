<%@page contentType="text/html; charset=UTF-8" %>

	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />    
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    
    <%-- <link href="/css/bootstrap.customize.css" rel="stylesheet">
	<link href="/css/bootstrap-datepicker.css" rel="stylesheet">
	<link href="/css/bootstrap-select.css" rel="stylesheet">
	<link href="/css/animate.min.css" rel="stylesheet">
	<link href="/css/slick.css" rel="stylesheet">
	<link href="/css/style.css?version=${version}" rel="stylesheet">
	
	<script src="/js/jquery.min.js"></script>
	<script src="/js/bootstrap.min.js"></script>
	<script src="/js/bootstrap-datepicker.min.js"></script>
	<script src="/js/bootstrap-select.min.js"></script>
	<script src="/js/feather.min.js"></script>
	<script src="/js/slick.min.js"></script>	
	<script src="/js/common.js?version=${version}"></script>
	<script src="/js/xutil.js?version=${version}"></script>
	<script src="/js/XE_validate.js"></script> --%>
	
	<link type="text/css" rel="stylesheet" href="<c:url value='/web/resources/css/egovframework/sample.css'/>"/>
	
	<script type="text/javascript">		
		$(document).ready(function() {
			$.ajaxSetup({
				beforeSend : function() {
					$(".loading").addClass("active").stop().delay(10000).queue(function(){
						$(".loading").removeClass("active")
					});
				},
				complete : function() {
					feather.replace({'width':24,'height':24});
					$(".loading").removeClass("active");
				}
			});
			$("input[type=number]").keyup(function() {
				maxLengthCheck(this);
			});
			$("input[type=number]").change(function() {
				var val = $(this).val();
				if (val < 0) $(this).val("");
				maxLengthCheck(this);
			});
		});
		
		function maxLengthCheck(obj){
			var val = $(obj).val();
			var maxlength = $(obj).attr("maxlength");
			if (val.length > maxlength){
		        $(obj).val(val.slice(0, maxlength));
		    }    
		}
	</script>