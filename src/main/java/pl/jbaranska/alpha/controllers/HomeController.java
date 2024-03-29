package pl.jbaranska.alpha.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.jbaranska.alpha.services.CategoryServices;

@RequestMapping("/")
@Controller
public class HomeController {
    private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    CategoryServices categoryServices;

    public HomeController(CategoryServices categoryServices) {
        this.categoryServices = categoryServices;
    }

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("cetegories", categoryServices.showCategoryList());
        return "homePage";
    }
}
