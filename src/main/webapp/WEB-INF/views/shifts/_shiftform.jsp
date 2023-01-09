<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="constants.AttributeConst" %>

<c:if test="${errors != null}">
    <div id="flush_error">
        正しく入力してください<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>

    </div>

</c:if>
<fmt:parseDate value="${shift.InputAt}" pattern="yyyy-MM-dd（E）-HH:mm" var="shifttDay" type="date" />
<label for="${AttributeConst.SHI_INPUT_AT.getValue()}">日時</label><br />
<input type="datetime-local" name="${AttributeConst.SHI_INPUT_AT.getValue()}" id="${AttributeConst.SHI_INPUT_AT.getValue()}" value="<fmt:formatDate value='${shiftDay}' pattern='yyyy-MM-dd（E）-HH:mm' />" />
<br /><br />


<label>氏名</label><br />
<c:out value="${sessionScope.login_employee.name}" />
<br /><br />

<label for="${AttributeConst.SHI_INOROUT.getValue()}">出退勤（選択してください）</label><br />
<select name="${AttributeConst.SHI_INOROUT.getValue()}" id="${AttributeConstSHI_INOROUT.getValue()}">
<option value="which">--- いずれかを選択してください ---</option>
        <option value="出勤">出勤</option>
        <option value="退勤">退勤</option>
</select>
<br /><br />

<input type="hidden" name="${AttributeConst.SHI_ID.getValue()}" value="${shift.id}" />
<input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
<button type="submit">登録</button>