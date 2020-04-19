// package com.security.demo1;
// /*
// import org.springframework.boot.SpringApplication;
// import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.web.bind.annotation.RequestMapping;
// //import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;

// @SpringBootApplication
// @RestController
// public class controller {

//   //  @RequestMapping(value="/welcome", method=RequestMethod.POST)
//     @RequestMapping(value="/welcome")
// 	public String getMethodName(@RequestParam(name = "name", defaultValue = "NA") String name)  {
// 		return "My Name is : " + name;
		
//     }
//     @RequestMapping("/getrequest")
// public request getRequest() {
//     return new request(1,"Sany");
// }
// 	public static void main(String[] args) {
// 		SpringApplication.run(DemoApplication.class, args);
// 	}

// }*/



// import org.springframework.boot.autoconfigure.security.servlet.WebSecurityEnablerConfiguration;
// import org.springframework.boot.autoconfigure.session.RedisSessionProperties.ConfigureAction;
// import org.springframework.context.annotation.Configuration;

// @Configuration
// public class controller extends WebSecurityEnablerConfiguration  {
    
//     @Override
//     public void ConfigureAction(HttpSecurity http) throws Exception {   

//     }
    
//     @Override
//     protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//         auth.inMemoryAuthentication()
//             .withUser("user")
//             .password("{noop}pass") // Spring Security 5 requires specifying the password storage format
//             .roles("USER");
//     }
    
// }



