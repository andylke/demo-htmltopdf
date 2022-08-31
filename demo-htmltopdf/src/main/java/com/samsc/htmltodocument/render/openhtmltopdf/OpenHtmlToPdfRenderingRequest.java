package com.samsc.htmltodocument.render.openhtmltopdf;

import com.samsc.htmltodocument.render.RenderingRequest;

/**
 * Represent a request that uses <a
 * href="https://github.com/danfickle/openhtmltopdf">openhtmltopdf</a> to render a given text
 * content.
 *
 * @author Andy Lian
 */
public class OpenHtmlToPdfRenderingRequest extends RenderingRequest {

  private RenderingOutputFormat outputFormat = RenderingOutputFormat.PDF;

  public RenderingOutputFormat getOutputFormat() {
    return outputFormat;
  }

  public void setOutputFormat(RenderingOutputFormat outputFormat) {
    this.outputFormat = outputFormat;
  }
}
