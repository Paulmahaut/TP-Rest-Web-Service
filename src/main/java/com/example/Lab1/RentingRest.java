@RestController
public class HelloService {
@GetMapping("/")
public String hello() {
return "hello";
}
}