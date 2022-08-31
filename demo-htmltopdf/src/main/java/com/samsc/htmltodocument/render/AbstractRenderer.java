package com.samsc.htmltodocument.render;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Abstract Renderer class providing common error handling methods.
 *
 * @author Andy Lian
 * @param <T>
 */
public abstract class AbstractRenderer<T extends RenderingRequest> implements Renderer<T> {

  private static final Logger LOGGER = LoggerFactory.getLogger(AbstractRenderer.class);

  @Override
  public RenderingResult render(T renderingRequest) {
    final RenderingResult renderingResult = new RenderingResult();
    try {
      process(renderingRequest, renderingResult);
    } catch (Exception e) {
      LOGGER.error(
          String.format("Failed to process the given rendering request '{0}'", renderingRequest),
          e);
    }
    return renderingResult;
  }

  protected abstract void process(T renderingRequest, RenderingResult renderingResult);
}
