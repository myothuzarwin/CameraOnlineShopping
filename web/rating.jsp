


<%@page import="EntityCollection.CommentEntity"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList"%>
<%@include file="header.jsp" %>  
<script src="//code.jquery.com/jquery-1.10.2.min.js"></script>

<link href="css/rating.css" rel="stylesheet">

<script src="js/review-rating.js" type="text/javascript"></script>

<div class="container">
    <div class="row" style="margin-top:40px;">
        <div class="col-md-6">
            <div class="well well-sm">
                <div class="text-right">
                    <a class="btn btn-success btn-green" href="#reviews-anchor" id="open-review-box">Leave a Review</a>
                </div>

                <div class="row" id="post-review-box" style="display:none;">
                    <div class="col-md-12">
                        <!--  <form action="reviewrating.action?productid=${param.pid}" method="post"> -->
                        <form accept-charset="UTF-8" action="reviewrating.action?productid=${param.pid}&type=2" method="post">
                            <input id="ratings-hidden" name="rating" type="hidden"> 
                            <textarea class="form-control animated" cols="50" id="new-review" name="comment" placeholder="Enter your review here..." rows="5"></textarea>

                            <div class="text-right">
                                <div class="stars starrr" data-rating="0"></div>
                                <a class="btn btn-danger btn-sm" href="#" id="close-review-box" style="display:none; margin-right: 10px;">
                                    <span class="glyphicon glyphicon-remove"></span>Cancel</a>
                                <button class="btn btn-success btn-lg" type="submit">Save</button>


                            </div>
                        </form>
                    </div>
                </div>
            </div> 

        </div>
    </div>
</div>

<div class="table-responsive"><table class="table">
        <thead>
            <tr>
                <th>Comment Date</th>
                <th>User Name</th>
                <th>Comment</th>
                <th>Rating</th>

            </tr>
        </thead>
        <tbody>

            <c:forEach var="comment" items="${requestScope.showcomment}">
                <tr>

                    <td>
                        ${comment.getCreatetime()}
                    </td>
                    <td>${comment.getCustomername()}</td>
                    <td>${comment.getComment()}</td>
                    <td>${comment.getRating()}</td>

                </tr>
            </c:forEach>
        </tbody>

    </table>

</div>



<script src="js/review-rating.js" type="text/javascript"></script>


</body>
</html>









