package bus.uigen.reflect.remote;

import java.lang.reflect.InvocationTargetException;

import bus.uigen.reflect.ClassProxy;

public abstract class CachingRemoteObjectProxy extends RemoteProxy implements  ObjectProxy {
	String objectID;
	CachingRemoteClassProxy classProxy;
	/*
	ClassProxy classProxy;
	public RemoteObjectProxy(int theObjectID, ClassProxy theClass) {
		objectID = theObjectID;
		classProxy = theClass;
	}
	*/
	/*
	public RemoteObjectProxy(int theObjectID) {
		objectID = theObjectID;
		//classProxy = theClass;
	}
	*/
	public CachingRemoteObjectProxy( Object theFactory, String theObjectID) {
		super (theFactory);
		objectID = theObjectID;
		//classProxy = theClass;
	}
	
	public String getObjectID() {
		return objectID;
	}
	
	
	public static String toID(ObjectProxy objProxy) {
		//int classID objProxy = objProxy.get
		return objProxy.getObjectID();		
	}
	public static String toID(Object obj) {
		return toID((ObjectProxy) obj);
	}
	public static String[] toIDs(Object[] objects) {
		String[] retVal = new String[objects.length];
		for (int i = 0; i < objects.length; i++)
			retVal[i] = toID(objects[i]);
		return retVal;
	}
	public static String[] toIDs(Object[] objects, ClassProxy[] types) {
		if (objects.length != types.length) {
			System.out.println("Invalid number of parameters");
			return null;
		}
		String[] retVal = new String[objects.length];
		for (int i = 0; i < objects.length; i++) {
			CachingRemoteClassProxy classProxy = (CachingRemoteClassProxy) types[i];
			int classID = classProxy.getClassID();
			if (objects[i] == null)
				retVal[i] = null;
			else if (StandardTypeConverter.isImmutable(classID))
				retVal[i] = objects[i].toString();
				
			else
				retVal[i] = toID(objects[i]);
		}
		return retVal;
	}
	static String[] toIDs(ObjectProxy[] objectProxies) {
		String[] retVal = new String[objectProxies.length];
		for (int i = 0; i < objectProxies.length; i++)
			retVal[i] = toID(objectProxies[i]);
		return retVal;
	}
	
	public abstract String remoteToString();

	String remoteToString;
	public String toString() {
		if (remoteToString == null)
			remoteToString = remoteToString();
		//return remoteToString + "\n" + super.toString();
		return remoteToString ;
	}
	public CachingRemoteClassProxy getClassProxy() {
	
		if (classProxy == null) {
			classProxy = RemoteClassProxy.classProxy(getFactoryName(), ObjectManagerProxy.remoteGetClass(objectID));
			
		}
		return classProxy;
	}
	public void setClassProxy(CachingRemoteClassProxy retVal) {
		classProxy = retVal;
	}
	
	/*
	public ClassProxy getClassProxy() {
		return classProxy;
	}
	*/

}
