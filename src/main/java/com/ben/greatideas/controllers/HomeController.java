package com.ben.greatideas.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ben.greatideas.models.Idea;
import com.ben.greatideas.models.User;
import com.ben.greatideas.services.IdeaService;
import com.ben.greatideas.services.UserService;
import com.ben.greatideas.validators.UserValidator;

@Controller
public class HomeController {

	@Autowired
	private  UserService uService;
	
	@Autowired
	private IdeaService iService;
	
	@Autowired
	private  UserValidator validators;
	
	//registration and login page
	@GetMapping("/")
	public String index(@ModelAttribute("user") User user) {
		return "index.jsp";
	}
	
	//registration
	@PostMapping("/registration")
	public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
		//validate info
		validators.validate(user, result);
		
		if(result.hasErrors()) {
			return "index.jsp";
		} 
		//no error, save new user in database and session
		else {
			User newUser = uService.registerUser(user);
			session.setAttribute("userId", newUser.getId());
			return "redirect:/dashboard";
		}
    }
	
	//login
	@PostMapping("/login") 
	public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
		//authenticate succeed 
		if(uService.authenticateUser(email, password)) {
			User user=uService.findByEmail(email);
			session.setAttribute("userId", user.getId());
			return "redirect:/dashboard";
		}
		//authentication fail
		else {
			redirectAttributes.addFlashAttribute("error", "Invaild Email or Password");
			return "redirect:/";	
		}
	}
	
	//logout
	@GetMapping("/logout")
    public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
    }
	
	//dashboard page
	@GetMapping("/dashboard")
	public String dashboard(Model model, HttpSession session) {
		//if logged in, display all ideas
		if(session.getAttribute("userId") != null) {
			List<Idea> ideas = this.iService.getAllIdeas();
			User user = uService.findById((Long)session.getAttribute("userId"));
			model.addAttribute("ideas", ideas);
			model.addAttribute("user", user);
			return "dashboard.jsp";
		}
		//if not logged in, go back to login page
		return "redirect:/";
	}
	
	//dashboard, but sort ideas by likes in ascending order
	@GetMapping("/dashboard/sortbylikesasc")
	public String dashboardSortByLikesAsc(Model model, HttpSession session) {
		//if logged in, display all ideas
		if(session.getAttribute("userId") != null) {
			List<Idea> ideas = this.iService.getAllIdeasSortByLikesAsc();
			User user = uService.findById((Long)session.getAttribute("userId"));
			model.addAttribute("ideas", ideas);
			model.addAttribute("user", user);
			return "dashboard.jsp";
		}
		//if not logged in, go back to login page
		return "redirect:/";
	}
	
	//dashboard, but sort ideas by likes in descending order
	@GetMapping("/dashboard/sortbylikesdesc")
	public String dashboardSortByLikesDesc(Model model, HttpSession session) {
		//if logged in, display all ideas
		if(session.getAttribute("userId") != null) {
			List<Idea> ideas = this.iService.getAllIdeasSortByLikesDesc();
			User user = uService.findById((Long)session.getAttribute("userId"));
			model.addAttribute("ideas", ideas);
			model.addAttribute("user", user);
			return "dashboard.jsp";
		}
		//if not logged in, go back to login page
		return "redirect:/";
	}
	
	//new idea page
	@GetMapping("/ideas/new")
	public String index(@ModelAttribute("newIdea") Idea idea) {
		return "newidea.jsp";
	}
	
	//create new idea
	@PostMapping("/ideas/create")
	public String createIdea(@Valid @ModelAttribute("newIdea") Idea idea, BindingResult result) {
		if (result.hasErrors()) {
			return "newidea.jsp";
		} else {
			Idea newIdea = iService.createIdea(idea);
			return "redirect:/dashboard";
		}
	}
	
	//idea detail page
	@GetMapping("/ideas/idea/{id}")
	public String ideaDeatil(@PathVariable("id") Long id, Model model, HttpSession session) {
		Idea idea = iService.getOneIdeaById(id);
		model.addAttribute("idea", idea);
		model.addAttribute("userLoggedIn",(Long)session.getAttribute("userId"));
		return "detailidea.jsp";
	}
	
	//delete idea
	@GetMapping("/ideas/delete/{id}")
	private String deleteIdea(@PathVariable("id") Long id) {
		iService.deleteIdea(id);
		return "redirect:/dashboard";
	}
	
	//idea update page
	@GetMapping("/ideas/edit/{id}")
	public String updateIdea(@PathVariable("id") Long id, @ModelAttribute("editedIdea") Idea idea, Model model, HttpSession session) {
		Idea ideaToEdit = iService.getOneIdeaById(id);
		model.addAttribute("ideaToEdit", ideaToEdit);
		return "editidea.jsp";
	}
	
	//update idea
	@PostMapping("/ideas/update/{id}")
	public String updateIdea(@PathVariable("id") Long id, @Valid @ModelAttribute("editedIdea") Idea idea, BindingResult result, Model model) {
		if (result.hasErrors()) {
			Idea ideaToEdit = iService.getOneIdeaById(id);
			model.addAttribute("ideaToEdit", ideaToEdit);
			return "edit.jsp";
		} else {
			Idea newIdea = iService.updateIdea(id, idea.getIdeaName(), idea.getIdeaContent());
			return "redirect:/dashboard";
		}		
	}
	
	//like idea
	@GetMapping("ideas/like/{ideaId}")
	public String like(@PathVariable("ideaId") Long ideaId, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		User user = uService.findById(userId);
		Idea idea = iService.getOneIdeaById(ideaId);
		iService.likeIdea(idea, user);
		return "redirect:/dashboard";
	}
	
	//unlike idea
	@GetMapping("ideas/unlike/{ideaId}")
	public String unlike(@PathVariable("ideaId") Long ideaId, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		User user = uService.findById(userId);
		Idea idea = iService.getOneIdeaById(ideaId);
		iService.unlikeIdea(idea, user);
		return "redirect:/dashboard";
	}
	
}
