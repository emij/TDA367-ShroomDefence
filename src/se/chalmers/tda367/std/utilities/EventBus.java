package se.chalmers.tda367.std.utilities;

/**
 * A wrapper for the Google Guava EventBus.
 * @see com.google.common.eventbus.EventBus
 * @author Emil Edholm
 * @date   Apr 27, 2012
 */
public enum EventBus {
	INSTANCE;
	private com.google.common.eventbus.EventBus gEventBus; // g as in guava.
	private EventBus(){
		gEventBus = new com.google.common.eventbus.EventBus();
	}
	
	/**
	 * Posts an event to all registered handlers.
	 * @param event the event to post.
	 */
	public void post(Object event){
		gEventBus.post(event);
	}
	
	/**
	 * Registers all handler methods on {@code object} to receive events
	 * @param object object whose handler methods should be registered.
	 */
	public void register(Object object){
		gEventBus.register(object);
	}
	
	/**
	 * Unregisters all handler methods on a registered {@code object}.
	 * @param object object whose handler methods should be unregistered.
	 */
	public void unregister(Object object){
		gEventBus.unregister(object);
	}
}
