package bus.uigen.reflect.remote;

import java.util.Hashtable;

import bus.uigen.reflect.ClassProxy;
import bus.uigen.reflect.remote.CachingRemoteClassProxy;

public abstract class RemoteAbstractFactory extends RemoteProxy implements RemoteFactory {	
	//Hashtable<String, RemoteClassProxy> objectIDToClassProxy = new Hashtable();
	Hashtable<String, CachingRemoteClassProxy> nameToClassProxy = new Hashtable();
	public RemoteAbstractFactory(Object theFactory) {
		super (theFactory);
	}
	@Override
	public ClassProxy forName(String s) throws ClassNotFoundException {
		CachingRemoteClassProxy retVal = nameToClassProxy.get(s);
		if (retVal == null) {
			retVal = remoteForName(s);
			nameToClassProxy.put(s, retVal);
		}
		return retVal;
	}

	@Override
	public ClassProxy getClass(Object o) {
		ObjectProxy objectProxy = (ObjectProxy) o;
		//String objectID = ((RemoteObjectProxy) o).getObjectID();
		//String objectID = ((RemoteObjectProxy) o).getObjectID();
		//RemoteClassProxy retVal = objectIDToClassProxy.get(objectID);
		CachingRemoteClassProxy retVal = objectProxy.getClassProxy();
		if (retVal == null) {
			String objectID = objectProxy.getObjectID();
			retVal = remoteGetClass(objectID);
			objectProxy.setClassProxy(retVal);
			//objectIDToClassProxy.put(objectID, retVal);
		}
		return retVal;
	}
	
	
	public abstract CachingRemoteClassProxy remoteForName(String s) throws ClassNotFoundException;
	public abstract CachingRemoteClassProxy remoteGetClass(String objectID);

}
