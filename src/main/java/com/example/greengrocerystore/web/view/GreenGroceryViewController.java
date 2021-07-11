package com.example.greengrocerystore.web.view;

import com.example.greengrocerystore.common.type.GreenGroceryType;
import com.example.greengrocerystore.external.dto.GetFruitDto;
import com.example.greengrocerystore.external.dto.GetVegetableDto;
import com.example.greengrocerystore.external.service.GetFruitNamesService;
import com.example.greengrocerystore.external.service.GetFruitService;
import com.example.greengrocerystore.external.service.GetVegetableNamesService;
import com.example.greengrocerystore.external.service.GetVegetableService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RequiredArgsConstructor
@Controller
public class GreenGroceryViewController {

    private final GetFruitNamesService getFruitNamesService;
    private final GetVegetableNamesService getVegetableNamesService;

    private final GetFruitService getFruitService;
    private final GetVegetableService getVegetableService;

    @GetMapping("/greenGrocery")
    private ModelAndView getFruit(
        @RequestParam GreenGroceryType type, @RequestParam String name,
        ModelAndView modelAndView) {
        modelAndView.addObject("types", GreenGroceryType.values());
        modelAndView.addObject("fruitNames", getFruitNamesService.get().block());
        modelAndView.addObject("vegetableNames", getVegetableNamesService.get().block());

        if (type.equals(GreenGroceryType.FRUIT)) {
            GetFruitDto getFruitDto = getFruitService.get(name).block();

            modelAndView.addObject("name", getFruitDto.getName());
            modelAndView.addObject("price", getFruitDto.getPrice());
        } else if (type.equals(GreenGroceryType.VEGETABLE)) {
            GetVegetableDto getVegetableDto = getVegetableService.get(name).block();

            modelAndView.addObject("name", getVegetableDto.getName());
            modelAndView.addObject("price", getVegetableDto.getPrice());
        }

        modelAndView.setViewName("index");

        return modelAndView;
    }
}
