package com.example.greengrocerystore.web.view;

import com.example.greengrocerystore.common.type.GreenGroceryType;
import com.example.greengrocerystore.external.service.GetFruitNamesService;
import com.example.greengrocerystore.external.service.GetVegetableNamesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RequiredArgsConstructor
@Controller
public class CommonViewController {

    private final GetFruitNamesService getFruitNamesService;
    private final GetVegetableNamesService getVegetableNamesService;

    @GetMapping("/index")
    private ModelAndView index(ModelAndView modelAndView) {
        modelAndView.addObject("types", GreenGroceryType.values());
        modelAndView.addObject("fruitNames", getFruitNamesService.get().block());
        modelAndView.addObject("vegetableNames", getVegetableNamesService.get().block());

        modelAndView.setViewName("index");

        return modelAndView;
    }
}
