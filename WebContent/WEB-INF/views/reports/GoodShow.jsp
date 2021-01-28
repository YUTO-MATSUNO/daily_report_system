<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${employeeList != null}">
                <h2>いいねした人</h2>

                <table>
                    <tbody>
                        <tr>
                            <th>いいねした人</th>
                            <td>
                            <c:forEach var="employee" items="${employeeList}">
                            <c:out value="${employee.name}" />
                            </c:forEach>
                            </td>
                        </tr>
                    </tbody>
                </table>


            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <p><a href="<c:url value="/reports/index" />">日報一覧に戻る</a></p>
    </c:param>
</c:import>