<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Employees Management Application</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>

<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: tomato">
        <div>
            <a href="<%=request.getContextPath()%>/list" class="navbar-brand"> Employees Management App </a>
        </div>
    </nav>
</header>
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:if test="${employer != null}">
            <form action="edit" method="post">
                </c:if>
                <c:if test="${employer == null}">
                <form action="create" method="post">
                    </c:if>

                    <caption>
                        <h2>
                            <c:if test="${employer != null}">
                                Edit User
                            </c:if>
                            <c:if test="${employer == null}">
                                Add New User
                            </c:if>
                        </h2>
                    </caption>

                    <c:if test="${employer != null}">
                        <input type="hidden" name="id" value="<c:out value='${employer._id}' />" />
                    </c:if>

                    <fieldset class="form-group">
                        <label>Name</label> <input type="text"
                                                        value="<c:out value='${employer.name}' />" class="form-control"
                                                        name="name" required="required">
                    </fieldset>
                    <fieldset class="form-group">
                        <label>gender</label> <input type="text"
                                                   value="<c:out value='${employer.gender}' />" class="form-control"
                                                   name="gender" required="required">
                    </fieldset>
                    <fieldset class="form-group">
                        <label>address</label> <input type="text"
                                                   value="<c:out value='${employer.address}' />" class="form-control"
                                                   name="address" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Email</label> <input type="email"
                                                         value="<c:out value='${employer.email}' />" class="form-control"
                                                         name="email" required>
                    </fieldset>

                    <fieldset class="form-group">
                        <label>phone</label> <input type="text"
                                                           value="<c:out value='${employer.phone}' />" class="form-control"
                                                           name="phone" required>
                    </fieldset>

                    <button type="submit" class="btn btn-success">Save</button>
                </form>
        </div>
    </div>
</div>
</body>
</html>