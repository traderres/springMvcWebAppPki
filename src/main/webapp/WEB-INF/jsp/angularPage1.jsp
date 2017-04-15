<%@ include file="/WEB-INF/jsp/stdJspIncludes.jsp" %>

<!DOCTYPE HTML>

<html lang="en" data-ng-app="myApp">

<head>
    <title>Angular Page</title>

    <!-- Load Bootstrap CSS -->
    <link href="${contextPath}/resources/bootstrap-3.3.4/dist/css/bootstrap.min.css" rel="stylesheet" media="screen">

    <!-- Load Bootstrap CSS Themes -->
    <link href="${contextPath}/resources/bootstrap-3.3.4/dist/css/bootstrap-theme.min.css" rel="stylesheet" media="screen">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="${contextPath}/resources/bootstrap-3.3.4/dist/assets/html5shiv.js"></script>
    <script src="${contextPath}/resources/bootstrap-3.3.4/dist/assets/respond.min.js"></script>
    <![endif]-->
</head>



<body data-ng-controller="PhoneListCtrl as ctrl">

<%-- S T A N D A R D       H E A D E R  --%>
<%@ include file="/WEB-INF/jsp/stdHeader.jsp" %>

<h2>angularPage1.jsp</h2>

<br/>

Hello ${userInfo.username} <br/>


<br/>
<br/>
<button data-ng-click="ctrl.makeValidRestCall()">Make Valid Rest Call</button>&nbsp;&nbsp;
<button data-ng-click="ctrl.makeInvalidRestCall()">Make Invalid Rest Call</button>&nbsp;&nbsp;
<button data-ng-click="ctrl.clearResults()()">Clear Results</button>
<br/>
Status: {{ctrl.statusMessage}}<br/>
<textarea data-ng-model="ctrl.restCallResults" style="width: 400px; height: 100px"></textarea>
<br/>
<br/>

<%-- Loop through an array of phone dictionaries --%>
<table width="500" cellpadding="1" cellspacing="2" style="border: 1px solid black">
    <tr>
        <th>&nbsp;</th>
        <th>Phone Model</th>
        <th>Summary</th>
    </tr>

    <tr data-ng-repeat="phone in ctrl.phones">
        <td>{{$index + 1}}.</td>
        <td>{{phone.name}}</td>
        <td>{{phone.snippet}}</td>
    </tr>
</table>



<br/>
<br/>

<%-- S T A N D A R D       F O O T E R  --%>
<%@ include file="/WEB-INF/jsp/stdFooter.jsp" %>

<%-- Load jQuery --%>
<script src="${contextPath}/resources/jquery-1.12.4/jquery-1.12.4.min.js"></script>

<%-- Load Angular --%>
<script src="${contextPath}/resources/angular-1.5.11/angular.min.js" type="text/javascript"></script>

<%-- Include all compiled plugins (below), or include individual files as needed --%>
<script src="${contextPath}/resources/bootstrap-3.3.4/dist/js/bootstrap.min.js"></script>

<script type="text/javascript">
    var gsContextPath = '${contextPath}';

    var myApp = angular.module('myApp', []);

    myApp.controller('PhoneListCtrl', function ($scope, $http, $log)
    {
        var self = this;
        self.restCallResults = '';
        self.statusMessage = '';

        self.phones = [
            {
                'name': 'Nexus S',
                'snippet': 'Fast just got faster with Nexus S.'
            },
            {
                'name': 'Motorola XOOM with Wi-Fi',
                'snippet': 'The Next, Next Generation tablet.'
            },
            {
                'name':  'MOTOROLA XOOM',
                'snippet': 'The Next, Next Generation tablet.'
            }
        ];

        /*******************************************************************
         * makeValidRestCall()
         *
         * Call a REST end point that will succeed
         *******************************************************************/
        self.makeValidRestCall = function()
        {
            var sUrl = gsContextPath + '/rest/users';
            $log.debug("makeRestCall() started making a call to ", sUrl);

            $http.get(sUrl)
                    .success(function(data, status) {
                        // I got a response from the end point
                        self.statusMessage = 'I got a response with status=' + status;

                        // Convert the list of Java objects into a string
                        var sFormattedResults = self.getUserListAsString(data);

                        self.restCallResults = self.restCallResults + sFormattedResults + "\n";
                    })
                    .error(function(data, status) {
                        // I got an error response from the end point
                        self.statusMessage = 'I got a response with status=' + status;

                        self.restCallResults = self.restCallResults + data + "\n";
                    })
                    .then(function() {
                        self.statusMessage = 'Angular Call Finished';
                    });

            self.statusMessage = 'Made Rest Call....Waiting for results.';
        };


        /*******************************************************************
         * makeInvalidRestCall()
         *
         * Call a REST end point that will throw an error
         *******************************************************************/
        self.makeInvalidRestCall = function()
        {
            // This url will throw a RunTime Exception
            var sUrl = gsContextPath + '/rest/users/exception';
            $log.debug("makeRestCall() started making a call to ", sUrl);

            $http.get(sUrl)
                    .success(function(data, status) {
                        // I got a response from the end point
                        self.statusMessage = 'I got a response with status=' + status;

                        // Convert the list of Java objects into a string
                        var sFormattedResults = self.getUserListAsString(data);

                        self.restCallResults = self.restCallResults + sFormattedResults + "\n";
                    })
                    .error(function(data, status) {
                        // I got an error response from the end point
                        self.statusMessage = 'I got a response with status=' + status;

                        self.restCallResults = self.restCallResults + data + "\n";
                    })
                    .then(function() {
                        self.statusMessage = 'Angular Call Finished';
                    });

            self.statusMessage = 'Made Rest Call....Waiting for results.';

        };


        self.getUserListAsString = function(aUserList)
        {
            var sResults = '';

            for(var i=0; i<aUserList.length; i++)
            {
                var user = aUserList[i];
                sResults = sResults + 'username=' + user.username + '   IsAdministrator=' + user.isAdministrator + "\n";
            }

            return sResults;
        };


        self.clearResults = function()
        {
            $log.debug("clearResults() started");
            self.restCallResults = '';
        };
    });

</script>

</body>
</html>