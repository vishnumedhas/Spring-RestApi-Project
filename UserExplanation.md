1.Registering a user
So User Entity fields are:
userId,email,password,createdAt,updatedAt 

use @Entity at the top of class to recognize one class as entity class because entity is always mapped to the database
As well as use @getter,@setter,@AllArgsConstructor,@NoArgsConstructor we will not use @data because to avoid tostring annotation we used all these.

Next create user Repository,controller,servicelayer,serviceimplementation,AppException,Global Exception, Api Response ,Req dto,Response Dto

First Registering a user So

Create a RegisterRequest dto 
email, password are the fields  shown for client for registering for the first time. use @Data Annotation becaue 
@Data contains all @getter,@setter,@noArgsConstructor,@ToString so instead of all these we use single annotation @Data

Next Create User Controller
At the top Of the Class use @RestController it works as both @Controller-->To recognize one class as controller class
@ResponseBody -> It sends the method return value directly to the client

We used to perform methods like register,login,delete,getById,getAll .  
For this we use http methods which are of 4 types:
@PostMapping --> means client manually fills the form/details basically sends data to server.
@GetMApping --> In Spring Boot, it is used to handle HTTP GET requests. @GetMapping is generally used to retrieve/read data from the server.
@PutMapping --> Tells Spring Boot which method should execute when a client sends a PUT request, usually to update existing data.
@DeleteMapping --> Usually to delete existing data. @DeleteMapping is a Spring MVC annotation used to map an HTTP DELETE request to a specific controller method.

ResponseEntity --> ResponseEntity is a Spring class used to return the complete HTTP response from a controller.
Response Body (the data returned)
HTTP Status Code (200, 201, 404, 500, etc.)
HTTP Headers (optional)

@RequestBody --> @RequestBody tells Spring to read the data from the HTTP request body, convert it into a Java object, and pass that object to the controller method.

@RequestMapping --> @RequestMapping is used to map a URL (and optionally an HTTP method) to a controller class or controller method.

@PathVariable --> @PathVariable is a Spring MVC annotation used to extract a value from the URL path and pass it to a controller method as a method parameter.

Now Controller want to perform persistence logice so it communicate with service layer.As service layer is injected[dependency injection] 
to controller . Like field level injection [Autowired].

In Service Layer we used to declare the methods such as:
UserDto register(RegisterRequest request);
UserDto login(LoginRequest request);
void deleteUserById(Integer userId);
UserDto getUserById(Integer userId);
List<UserDto> getAllUser();

In service implementation we used to perform business logic and to perform crud operations it communicates with repository by repository is injected
to service impl [DI] and logic is done in service implementation. 
We used to declare @Service at top of class to recognize one class as service class

We use ModelMapper for transferring the data of one class obj to another class obj without using normal way such as get the value and set the value 
by getters and setters.

Repository it is extended to jpa repository to perform crud operations and jpa repository can also does sorting,pagenation,query by example
USe @Repository at top of the class to recognize one class as repository class

Api Response is the custom http response developer used to create his own response . Such as making custom and message,status code .
--------------------------------------------------------------------------------------------------------------------------------------------------
@Data
@AllArgsConstructor
public class ApiResponse <T>{

	private String message;
	
	private T t;
	
	private HttpStatus httpStatus;
}
--------------------------------------------------------------------------------------------------------------------------------------------------
This api response is used in controller class returning the response of a method [while returning the response to the client]

AppException --> It is custom exception class used to write own custom exception message instead of getting huge exception message default it gives
--------------------------------------------------------------------------------------------------------------------------------------------------
public class AppException extends RuntimeException{

	private HttpStatus httpStatus;
	
	public AppException(String message, HttpStatus httpStatus) {
		super(message);    //calling the constructor of the parent class (RuntimeException) and passing message to it.
		this.httpStatus=httpStatus;
	}
	
	public HttpStatus getHttpStatus() {  //getter is used to read/access the data of private HttpStaus httpstatus
		return httpStatus;
	}
}
--------------------------------------------------------------------------------------------------------------------------------------------------
It extends to RunTimeException it is a class which extends to exception class
Global Exception Handler : IT is used to handle exceptions thrown anywhere in your Spring Boot application in one central place.
To recognize one class as Global Exception Handler class we use @RestControllerAdvice
@ExceptionHandler --> Tells Spring which method should execute when a particular exception occurs.

------------------------------------------------------------------------------------------------------------------------------------------------
@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(exception=AppException.class)
public ResponseEntity<?> handleUserException(AppException exception){
	
	return new ResponseEntity<>(new ApiResponse<>(exception.getMessage(),null,exception.getHttpStatus()),exception.getHttpStatus());
}
	
}
--------------------------------------------------------------------------------------------------------------------------------------------------
                                                                   HandlerMapper
                                                                     / /
Req flow goeslike this Client-> [Ui]-->http request-->DispatcherServlet --> Controller -->Service -->dto mapped to Entity-> Repository -->DataBase
                                           |               |           |      /                                       
                                    jackson format     jackson Api     |     /
                         DeSerialization [converts json to java obj]   |    /   
                                                                Req dto
                                                             


resp flow Database->[Entity]--> Repository-->Service --> entity mapped to resp dto-> Controller -->DS -->UI-> Client
                                                                                                    |                                                                                   
                                                                        Serialization [converts java to json] jackson Api                                   
