package com.wade.springbookakka.controlle;

import com.wade.springbookakka.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author :lwy
 * @Date : 2018/8/24 15:35
 * @Description :
 */
@RestController
public class ActorController {

    @Autowired
    private ActorService service;

    @GetMapping("/actor")
    public String actor() {
        return service.userActor().toString();
    }
}
