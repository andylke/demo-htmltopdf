package com.samsc.htmltodocument.binder.freemarker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samsc.htmltodocument.binder.BindingResult;

@RestController
@RequestMapping(path = "/binder")
public class FreeMarkerBinderRestService {

  @Autowired private FreeMarkerBinder binder;

  @PostMapping(path = "/bind")
  public BindingResult bind(@RequestBody final FreeMarkerBindingRequest bindingRequest) {
    return binder.bind(bindingRequest);
  }
}
