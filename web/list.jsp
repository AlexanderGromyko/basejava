<%@ page import="java.util.List" %>
<%@ page import="com.urise.webapp.Config" %>
<%@ page import="com.urise.webapp.storage.SqlStorage" %>
<%@ page import="com.urise.webapp.storage.Storage" %>
<%@ page import="com.urise.webapp.model.Resume" %><%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 23.01.2023
  Time: 11:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of resumes</title>
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }
    </style>
</head>
<body>
<div>
    <div>
        <div>
            <h2>Resume list</h2>
        </div>
        <%
            Storage storage = (Storage) request.getAttribute("storage");
            List<Resume> resumes = storage.getAllSorted();

            if (resumes.size() == 0) {
                out.println("<p>No resumes yet!</p>");
            } else {
                out.println("<table style=\"width:50%\">");
                out.println("<tr>");
                out.println("<th>Id</th>");
                out.println("<th>Full name</th>");
                out.println("</tr>");
                for (Resume r:resumes) {
                    out.println("<tr>");
                    out.println("<td>" + r.getUuid() +"</td>");
                    out.println("<td>" + (r.getFullName().equals("") ? "-" : r.getFullName()) +"</td>");
                    out.println("</tr>");
                }
                out.println("</table>");
            }
        %>
    </div>
</div>
<div>
    <button onclick="window.location.href='/resumes';">Back</button>
</div>
</body>
</html>