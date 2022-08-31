package com.samsc.htmltodocument.binder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Abstract {@link Binder} class providing common error handling methods.
 *
 * @author Andy Lian
 * @param <T>
 */
public abstract class AbstractBinder<T extends BindingRequest> implements Binder<T> {

  private static final Logger LOGGER = LoggerFactory.getLogger(AbstractBinder.class);

  @Override
  public BindingResult bind(T bindingRequest) {
    final BindingResult bindingResult = new BindingResult();
    try {
      process(bindingRequest, bindingResult);
    } catch (Exception e) {
      LOGGER.error(
          String.format("Failed to process the given binding request '{0}'", bindingRequest), e);
    }
    return bindingResult;
  }

  protected abstract void process(T bindingRequest, BindingResult bindingResult);
}
