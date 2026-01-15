package lu.cnfpcfullstackdev.tfl_api.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@Tag(name = "Hello", description = "Simple greeting endpoints")
public class HelloController {
  @GetMapping("/hello")
  @Operation(summary = "Say hello", description = "Returns a greeting message. Optional `name` query param.")
  public String sayHello(@RequestParam(required = false, defaultValue = "Too Food To Leave") String name) {
      return "Hello, " + name + "!";
  }

}
