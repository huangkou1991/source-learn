package com.learn.dozerspringboot.controller;

import com.learn.dozerspringboot.domain.Person;
import com.learn.dozerspringboot.domain.Personne;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author :lwy
 * @date 2018/7/12 18:53
 */
@Controller
public class DozerController {


    @Autowired
    private DozerBeanMapper dozerBeanMapper;

    @GetMapping("/index")
    @ResponseBody
    public String execute(){
        Person person=new Person();
        person.setAge(2);
        person.setName("hello");
        person.setNickname("world");
        Personne personne=dozerBeanMapper.map(person,Personne.class);

        System.out.println(personne.getAge());
        System.out.println(personne.getNom());
        System.out.println(personne.getSurnom());
        return "s";
    }

    //http://dozer.sourceforge.net/ 官网
    //http://www.importnew.com/21947.html
}
