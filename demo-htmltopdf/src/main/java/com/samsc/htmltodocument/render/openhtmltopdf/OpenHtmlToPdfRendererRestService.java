package com.samsc.htmltodocument.render.openhtmltopdf;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samsc.htmltodocument.render.RenderingResult;

@RestController
@RequestMapping(path = "/renderer")
public class OpenHtmlToPdfRendererRestService {

  private final OpenHtmlToPdfRenderer renderer;

  @Autowired
  public OpenHtmlToPdfRendererRestService(final OpenHtmlToPdfRenderer renderer) {
    this.renderer = Objects.requireNonNull(renderer);
  }

  @PostMapping(path = "/render")
  public RenderingResult render(@RequestBody final OpenHtmlToPdfRenderingRequest renderingRequest) {
    return renderer.render(renderingRequest);
  }
}
