package com.test.example1.controller;

import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private ResourceBundleMessageSource messageSource;

    @GetMapping
    public String helloWold(){
        return "Hello World";
    }

    @GetMapping("/hello-int")
    public String getMessagesInI18NFormat(@RequestHeader(name="Accept-Language", required = false) String locale){
        return messageSource.getMessage("label.hello", null, new Locale(locale));
    }

    @GetMapping("/hello-int2")
    public String getMessagesInI18NFormat2(){
        return messageSource.getMessage("label.hello", null, LocaleContextHolder.getLocale());
    }
}
