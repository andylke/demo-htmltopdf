package com.samsc.htmltodocument.render;

/**
 * Strategy interface to render a text content into a specific output format.
 *
 * @author Andy Lian
 * @param <T>
 */
public interface Renderer<T extends RenderingRequest> {

  RenderingResult render(T renderingRequest);
}
