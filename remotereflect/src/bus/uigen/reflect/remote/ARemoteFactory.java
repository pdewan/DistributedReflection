package bus.uigen.reflect.remote;



import bus.uigen.reflect.ClassProxy;

public class ARemoteFactory extends RemoteAbstractFactory  {
	
	public ARemoteFactory (Object theFactory) {
		super (theFactory);
	}
	@Override
	public CachingRemoteClassProxy remoteForName(String s)
			throws ClassNotFoundException {
		return RemoteClassProxy.classProxy(getFactoryName(), ObjectManagerProxy.remoteForName(s));
	}

	@Override
	public CachingRemoteClassProxy remoteGetClass(String objectID) {
		return RemoteClassProxy.classProxy(getFactoryName(), ObjectManagerProxy.remoteGetClass(objectID));
		
	}
	@Override
	public ObjectProxy objectProxy(Object theFactory, String theObjectID) {
		return RemoteObjectProxy.objectProxy(theFactory, theObjectID);
	}
	
	
	

}
