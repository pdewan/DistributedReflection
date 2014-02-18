package bus.uigen.reflect.remote;

import java.util.Hashtable;

import bus.uigen.reflect.ClassProxy;
import bus.uigen.reflect.remote.StandardTypeConverter;
import bus.uigen.reflect.remote.StandardTypeIDs;

public class RemoteObjectProxy extends CachingRemoteObjectProxy  {
	/*
	public RemoteJavaObjectProxy(int theObjectID, ClassProxy theClass) {
		super(theObjectID, theClass);
		// TODO Auto-generated constructor stub
	}
	*/
	/*
	static String JAVA_NAME = "java";
	
	public String getFactoryName() {
		return getStaticFactoryName();
	}
	
	public static String getStaticFactoryName() {
		return JAVA_NAME;
	}
	*/
	public RemoteObjectProxy(Object theFactory, String theObjectID) {
		super(theFactory, theObjectID);
		// TODO Auto-generated constructor stub
	}
	public String remoteToString() {
		return ObjectManagerProxy.remoteObjectToString(getObjectID());
	}
	public static transient Hashtable<String, RemoteObjectProxy> idsToObjectProxies = new Hashtable();
	public static RemoteObjectProxy objectProxy (Object theFactory, String objectID) {
		if (objectID == null)
			return null;
		RemoteObjectProxy objectProxy =idsToObjectProxies.get(objectID);
		if (objectProxy == null) {
			objectProxy = new RemoteObjectProxy(theFactory, objectID);
			idsToObjectProxies.put(objectID, objectProxy);			
		}
		return objectProxy;
			
	}
	public static Object objectProxy (Object theFactory, String objectID, int classID) {
		if (StandardTypeConverter.isImmutable(classID))
			return StandardTypeConverter.toStandardObject(objectID, classID);
		else
			
			return objectProxy(theFactory, objectID);
		
			
	}
	public static ObjectProxy[] toObjectProxies(Object theFactory, String[] ids) {
		if (ids == null)
			return null;
		 ObjectProxy[] retVal = new RemoteObjectProxy[ids.length];
		for (int i = 0; i < ids.length; i++)
			retVal[i] = new RemoteObjectProxy(theFactory, ids[i]);
		return retVal;			
	}
	
	

}
