<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css"
	rel="stylesheet" type="text/css">
	<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script
	src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js"></script>
	<title>DataTables examples - Examples index</title>
<script >
$(document).ready(function() {
	$('#contactTbl').DataTable({
		"pagingType" : "full_numbers"
	});
});
   function deleteConfirm() {
	   return confirm("Are you sure,You want to delete?");
	
}

</script>
</head>
<body>
<a href="addContact">+Add New Contact</a>
<table border="1">
	<thead>
		<tr>
			<th>S.No</th>
			<th>Name</th>
			<th>Email</th>
			<th>Phone Number</th>
			<th>Action</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${contacts}" var="c" varStatus="index">
			<tr>
				<td>${index.count}</td>
				<td>${c.contactName}</td>
				<td>${c.contactEmail}</td>
				<td>${c.contactNumber}</td>
				<td>
					<a href="editContact?cid=${c.contactId}">Edit</a>
					<a href="deleteContact?cid=${c.contactId}" onclick="deleteConfirm()">Delete</a>
				</td>
			</tr>
		</c:forEach>
	
	</tbody>
</table>
  <c:if test="${cpn > 1}">
  	<a href="viewContacts?pno=${cpn-1}">Previous</a>
  </c:if>
<c:forEach begin="1" end="${tp}" var="pno">
  <c:if test="${cpn==pno}">
     ${pno}
  </c:if>
  <c:if test="${cpn!=pno}">
      <a href="viewContacts?pno=${pno}">${pno}</a>
  </c:if>

</c:forEach>
<c:if test="${cpn < tp}">
  	<a href="viewContacts?pno=${cpn+1}">Next</a>
  </c:if>
</body>
</html>