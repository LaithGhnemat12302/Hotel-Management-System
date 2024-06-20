# Hotel Management System

This repository is for a Hotel Management System. It contains a Spring Boot applicaton that manages a hotel based on the provided data base schema. The project includes a docker configuration for easy setup and deployment. The API endpoints have been secured and swagger documentation is available.

<p dir="auto">These instructions will get you a copy of the project up and running on your local machine for development and testing purposes:</p>

<p dir="auto">Prerequests:</p>

<ul dir="auto">
    <li>
      <p dir="auto">Java 17</p>
    </li>
    <li>
      <p dir="auto">Maven</p>
    </li>
    <li>
      <p dir="auto">Docker</p>
    </li>
    <li>
      <p dir="auto">Postman</p>
    </li>
</ul>

<p dir="auto">Clone the Repository:</p>

<p dir="auto">To clone the repository and navigate into the directory, run the following commands:</p>

<ul>
    <li>
        git clone <a href="https://github.com/AlaYOD/Hotel-Management-System.git">https://github.com/AlaYOD/HotelManagement-System.git</a>
    </li>
    <li>
        <p>cd Hotel-Management-System</p>
    </li>
</ul>

<p dir="auto">Building the Application:</p>

<p dir="auto">To compile the source code, package the compiled code into a JAR file, and install the packaged code in your local repository:</p>

<p dir="auto">mvn clean install</p>

<p dir="auto">Creating and Running the Docker Image</p>
<p dir="auto">To create a Docker image for the application, ensure Docker is running and execute:</p>

<p dir="auto">docker build -t hotel-management-app .</p>
<p dir="auto">This will build a Docker image named hotel-management-app.</p>

<p dir="auto">To run the application in a Docker container:</p>
<p dir="auto">docker run -p 8080:8080 hotel-management-app</p>
<p dir="auto">The application will be accessible at <a href="http://localhost:8080" rel="nofollow">http://localhost:8080</a>.</p>

<p dir="auto">Postman Collection</p>
<p dir="auto">You can find the Postman collection for API testing in the repository. Download it and import into your Postman application for local testing.</p>

<p dir="auto">API Documentation</p>
<p dir="auto">You can find the API documentation at <a href="http://localhost:8080/swagger-ui.html" rel="nofollow">http://localhost:8080/swagger-ui.html</a> when the application is running. The documentation includes information about all endpoints, models, and authentication.</p>

<p dir="auto">Security</p>
<p dir="auto">This application uses JWT for securing the APIs. To get the token, use the /api/authenticate endpoint with the correct username and password.
Include the token in the Authorization header with the prefix "Bearer " in all requests.</p>


<p dir="auto">Code Documentation</p>
<p dir="auto">The source code is documented following Java best practices, which means each method, class, and module includes comments and descriptions of their functionality.</p>

<p dir="auto">Setup the Application:</p>
<ul dir="auto">
<li>
<p dir="auto">create a database named "hotel-management".</p>
</li>
<li>
<p dir="auto">deploy the application</p>
</li>
<li>
<p dir="auto">mvn clean</p>
</li>
<li>
<p dir="auto">mvn install -DskipTests</p>
</li>
<li>
<p dir="auto">Build the docker image</p>
</li>
<li>
<p dir="auto">docker build -t hotel_management .</p>
</li>
<li>
<p dir="auto">run the docker image:</p>
</li>
<li>
<p dir="auto">docker run -p 8080:8080 -e SPRING_DATASOURCE_URL="jdbc:mysql://host.docker.internal:3306/hotel-management?useSSL=false&amp;useUnicode=true&amp;useJDBCCompliantTimezoneShift=true&amp;useLegacyDatetimeCode=false&amp;serverTimezone=UTC&amp;zeroDateTimeBehavior=convertToNull" -e SPRING_DATASOURCE_USERNAME=&lt;your_username&gt; -e SPRING_DATASOURCE_PASSWORD=&lt;your_password&gt; my-spring-app</p>
</li>
</ul>

</article></div></div></div></div></div> <!-- --> <!-- --> <script type="application/json" id="__PRIMER_DATA_:R0:__">{"resolvedServerColorMode":"night"}</script></div>
</react-partial>

<input type="hidden" value="GFzJJQuLU1Z2K1zNvp41gOARARK-bejxPeGf0fe649AnNUqcWUpppASqIwIUIBZPtoVQNX4AjP-XhOfTrb-GIw" data-csrf="true" id="react-codespace-csrf" />
</div>

<div data-view-component="true" class="Layout-sidebar">      
      <div class="BorderGrid about-margin" data-pjax>
        <div class="BorderGrid-row">
          <div class="BorderGrid-cell">
            <div class="hide-sm hide-md">
  <h2 class="mb-3 h4">About</h2>
      <div class="f4 my-3 color-fg-muted text-italic">
        No description, website, or topics provided.
      </div>
    <h3 class="sr-only">Resources</h3>
    <div class="mt-2">
      <a class="Link--muted" data-analytics-event="{&quot;category&quot;:&quot;Repository Overview&quot;,&quot;action&quot;:&quot;click&quot;,&quot;label&quot;:&quot;location:sidebar;file:readme&quot;}" href="#readme-ov-file">
        <svg aria-hidden="true" height="16" viewBox="0 0 16 16" version="1.1" width="16" data-view-component="true" class="octicon octicon-book mr-2">
    <path d="M0 1.75A.75.75 0 0 1 .75 1h4.253c1.227 0 2.317.59 3 1.501A3.743 3.743 0 0 1 11.006 1h4.245a.75.75 0 0 1 .75.75v10.5a.75.75 0 0 1-.75.75h-4.507a2.25 2.25 0 0 0-1.591.659l-.622.621a.75.75 0 0 1-1.06 0l-.622-.621A2.25 2.25 0 0 0 5.258 13H.75a.75.75 0 0 1-.75-.75Zm7.251 10.324.004-5.073-.002-2.253A2.25 2.25 0 0 0 5.003 2.5H1.5v9h3.757a3.75 3.75 0 0 1 1.994.574ZM8.755 4.75l-.004 7.322a3.752 3.752 0 0 1 1.992-.572H14.5v-9h-3.495a2.25 2.25 0 0 0-2.25 2.25Z"></path>
</svg>
        Readme
</a>    </div>
