package guru.springframework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.services.StressService;

@Controller
public class StressController {
    private StressService stressService;


    @Autowired
    public void setStressService(StressService stressService) {
        this.stressService = stressService;
    }

    @RequestMapping("/product/stress/insert/{total}")
    public String stressInsert(@PathVariable String total, Model model) {
        stressService.stressInsert(Integer.parseInt(total));
        return "redirect:/product/list";
    }

    @RequestMapping("/product/stress/randomget/{total}")
    public String stressRandomGet(@PathVariable String total) {
        stressService.stressRandomGet(Integer.parseInt(total));
        return "redirect:/product/list";
    }

    @RequestMapping("/product/stress/randomdelete/{total}")
    public String stressRandomDelete(@PathVariable String total) {
        stressService.stressRandomDelete(Integer.parseInt(total));
        return "redirect:/product/list";
    }

    @RequestMapping("/product/stress/delete/{total}")
    public String stressDelete(@PathVariable String total, Model model) {
        stressService.stressDelete(Integer.parseInt(total));
        return "redirect:/product/list";
    }

}
