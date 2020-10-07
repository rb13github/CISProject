package com.ibm.springcisdemo.view;


import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import com.ibm.springcisdemo.models.Customer;
import com.ibm.springcisdemo.service.CustomerService;



@Controller
@CrossOrigin(origins="*")
@RequestMapping(value="/cisview")
public class CustomerViewController {
	
	private static final Logger log = LoggerFactory.getLogger(CustomerViewController.class);
	
	 @Value("${greeter.message}")
	    private String greeterMessageProperty; 
	 @Autowired
	 CustomerService customerService;


	 @RequestMapping("/welcome")
	    public String viewCustomerwelcomePage(Model model) {
		 
		 String prefix = System.getenv().getOrDefault("GREETING_PREFIX", "Hi");
		 
		 System.out.println("greeterMessageProperty   :   "+greeterMessageProperty);
	        
		 if(null!=greeterMessageProperty)
	        {
			 System.out.println("greet prefix from greeterMessageProperty  :   "+greeterMessageProperty); 
			 prefix=greeterMessageProperty;
	        
	        }
	   
		 
	        System.out.println("logger classs   :   "+log.getName());
	        
	        if ( null == prefix) {
	            prefix = "Hello!";
	        }
	        
	        model.addAttribute("prefix", prefix);
	        
	        return "welcome_customer";
	        
		 
	 }
	
	 
	 

	    @RequestMapping(value="/login", method = RequestMethod.POST)
	    public String showWelcomePage(ModelMap model, @RequestParam String name, @RequestParam String password){

	       // boolean isValidUser = service.validateUser(name, password);

//	         Appuser user=service.checkUser(name);
//	        if (user!=null && password.equals(user.getPassword()))
//	        {
//	        	 System.out.println("in login valid user view Home page : "+name);
//	        	 System.out.println("in login valid user email : "+user.getEmail());
//	        }
//	        else
//	        {
//	        	System.out.println("in login invalid user view login page : "+name);
//	        	model.put("errorMessage", "Invalid Credentials");
//	           return "login";
//	        }
//	        
////	        if (!isValidUser) {
////	            model.put("errorMessage", "Invalid Credentials");
////	            return "login";
////	        }
//
//	        model.put("name", name);
//	        model.put("password", password);
//	        
//	        
//	        model.addAttribute("user", user);
//	        
//	       
//
//	        System.out.println("in login success for user : "+name);
//	        //return "redirect:/healthmgmt/Products/getProducts";
	    	
	    	List<Customer> customerList = customerService.listAll();;
	        model.addAttribute("customerList", customerList);
	      //  System.out.println("customerList    :   "+customerList);
	    	
	    	
	    	
	        return "view_customers";
	        
	    }

@RequestMapping("/createcustomerview")
public String showNewPatientPage(Model model) {
	 Customer customer = new Customer();
	 model.addAttribute("customer", customer);
	   
	        return "new_customer";
}
	    
@RequestMapping(value = "/create", method = RequestMethod.POST)
 public String savePatient(ModelMap model,@ModelAttribute("customer") Customer customer) {
	    	
   	 log.info("create customer   :   "+customer.getForm_id());
   	 customerService.save(customer,true);
     List<Customer> customerList = customerService.listAll();;
	  model.addAttribute("customerList", customerList);
	  // System.out.println("customerList    :   "+customerList);
	   return "view_customers";
    }
	    
//	    @RequestMapping("/new")
//	    public String showNewPatientPage(Model model) {
//	    	Patient patient = new Patient();
//	        model.addAttribute("patient", patient);
//	         
//	        return "new_patient";
//	    }
//	    
//	    @RequestMapping(value = "/save", method = RequestMethod.POST)
//	    public String savePatient(@ModelAttribute("patient") Patient patient) {
//	    	
//	    	 log.info("save patient   :   "+patient.getMobile());
//	    	patientService.save(patient);
//	         
//	        return "redirect:/healthmgmt/patient/getPatients";
//	    }
//	    
//	    @RequestMapping("/edit/{id}")
//	    public ModelAndView showEditPatientPage(@PathVariable(name = "id") String id) {
//	        ModelAndView mav = new ModelAndView("edit_patient");
//	        System.out.println("showEditPatientPage: editing the product with id : "+id);
//	       
//	        log.info("showEditPatientPage: editing the product with id : "+id);
//	        
//	        Patient patient = patientService.findById(Integer.parseInt(id));
//	        mav.addObject("patient", patient);
//	         
//	        System.out.println("showEditPatientPage: In  the patient with id : "+patient.getId());
//	        return mav;
//	    }
//	    
//	    @RequestMapping("/delete/{id}")
//	    public String deletePatient(@PathVariable(name = "id") int id) {
//	    	patientService.deletePatientById(id);
//	        return "redirect:/healthmgmt/patient/getPatients";       
//	    }
}
