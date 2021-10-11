<%@ include file="/WEB-INF/jsp/include/taglibs.jsp"%>
<%@ attribute name="pagedListHolder" required="true" type="kr.nwlle.cmmn.vo.Pagination" %>

<c:if test="${pagedList.pageCount > 0}">
<nav aria-label="Page navigation example">
	<ul class="pagination justify-content-center">		
		<li class="page-item">
		<c:choose>
	    	<c:when test="${pagedList.firstLinkedPage-1 > 0}">
				<a class="page-link" href="javascript:jumpPage(${pagedList.firstLinkedPage});" aria-label="Previous">
					<i data-feather="chevron-left" width="16" height="18"></i>
				</a>
			</c:when>
			<c:when test="${!pagedList.firstPage}">
				<a class="page-link" href="javascript:jumpPage(${pagedList.page-1});" aria-label="Previous">
					<i data-feather="chevron-left" width="16" height="18"></i>
				</a>
	    	</c:when>
	    	<c:otherwise>
				<a class="page-link" href="javascript:;" aria-label="Previous">
					<i data-feather="chevron-left" width="16" height="18"></i>
				</a>
	    	</c:otherwise>
	    </c:choose>
		</li>
	
		<c:forEach begin="${pagedList.firstLinkedPage}" end="${pagedList.lastLinkedPage}" var="i">
	        <c:choose>
	            <c:when test="${(pagedList.page-1) == i}">
	                <li class="page-item active"><a class="page-link" href="javascript:;">${i+1}</a></li>
	            </c:when>
	            <c:otherwise>
	                <li class="page-item"><a class="page-link" href="javascript:jumpPage(${i+1});">${i+1}</a></li>
	            </c:otherwise>
	        </c:choose>
	    </c:forEach>

		<li class="page-item">
			<c:choose>
		    	<c:when test="${pagedList.lastLinkedPage+1 >= pagedList.pageCount-1 && !pagedList.lastPage}">
		    		<a class="page-link" href="javascript:jumpPage(${pagedList.page+1});" aria-label="Next">
						<i data-feather="chevron-right" width="16" height="18"></i>
					</a>
		    	</c:when>
		    	<c:when test="${!pagedList.lastPage}">
		    		<a class="page-link" href="javascript:jumpPage(${pagedList.lastLinkedPage+2});" aria-label="Next">
						<i data-feather="chevron-right" width="16" height="18"></i>
					</a>
		    	</c:when>
		    	<c:otherwise>
		    		<a role="button" class="page-link" href="javascript:;" aria-label="Next">
						<i data-feather="chevron-right" width="16" height="18"></i>
					</a>
		    	</c:otherwise>
		    </c:choose>			
		</li>
	</ul>
</nav>
</c:if>
