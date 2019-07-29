package com.ison.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ison.app.dao.DemoDAO;

@RestController
@RequestMapping("/api")
public class DemoRestController {

  @Autowired
  DemoDAO demoDAO;

  @RequestMapping(value = "/new", method = RequestMethod.GET, produces = {"application/json"})
  public List<?> allProducts(HttpServletRequest request) throws Exception {
    return demoDAO.getTemplate();
  }

}
