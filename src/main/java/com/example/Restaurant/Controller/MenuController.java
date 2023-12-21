package com.example.Restaurant.Controller;


import com.example.Restaurant.Service.MenuService;
import com.example.Restaurant.Service.MenuServiceImpl;
import com.example.Restaurant.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @PostMapping(path = "/add")
    public String add(@RequestBody Menu menu){
        menuService.saveMenu(menu);

        return "New Menu Item Added!";
    }

    @GetMapping(path = "/getMenu")
    public List<Menu> getAllMenu(){
        return menuService.getAllMenu();
    }
}
