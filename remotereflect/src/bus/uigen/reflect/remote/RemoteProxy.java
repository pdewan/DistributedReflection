package bus.uigen.reflect.remote;

public class RemoteProxy implements FactoryName {
	Object factory;
	public RemoteProxy (Object theFactory) {
		factory = theFactory;
	}
	public RemoteProxy () {
	}
	@Override
	public Object getFactoryName() {
		return factory;
	}

	@Override
	public void setFactoryName(Object newVal) {
		factory = newVal;
		
	}

}
