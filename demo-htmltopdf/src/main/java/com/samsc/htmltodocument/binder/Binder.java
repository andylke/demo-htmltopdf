package com.samsc.htmltodocument.binder;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Strategy interface to generate a text output from a given template content and {@link JsonNode
 * JSON data}.
 *
 * @author Andy Lian
 * @param <T>
 */
public interface Binder<T extends BindingRequest> {

  BindingResult bind(T bindingRequest);
}
