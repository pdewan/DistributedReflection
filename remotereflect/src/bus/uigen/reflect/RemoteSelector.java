package bus.uigen.reflect;

import java.util.Enumeration;
import java.util.Hashtable;

import bus.uigen.reflect.local.AClassProxy;
import bus.uigen.reflect.remote.CachingRemoteObjectProxy;
import bus.uigen.reflect.remote.ObjectProxy;
import bus.uigen.reflect.remote.RemoteClassProxy;
import bus.uigen.reflect.remote.RemoteFactory;

public class RemoteSelector {
	static Hashtable<Object, RemoteFactory> registry = new Hashtable();
	public static ClassProxy getClass(Object o) {
		if (o == null)
			return AClassProxy.classProxy(Object.class);
		if (o instanceof CachingRemoteObjectProxy) {
			return ((ObjectProxy) o).getClassProxy();
			/*
			CachingRemoteObjectProxy proxy = (CachingRemoteObjectProxy) o;
			Object factoryName = proxy.getFactoryName();
			RemoteFactory factory = registry.get(factoryName);
			return factory.getClass(o);
			*/
		}		
		return AClassProxy.classProxy(o.getClass());
		
	}
	public static ClassProxy classProxy(Object theClassOrClassProxy) {
		if (theClassOrClassProxy == null)
			return null;
		else if (theClassOrClassProxy instanceof ClassProxy)
			return (ClassProxy) theClassOrClassProxy;
		else if (theClassOrClassProxy instanceof Class)
			return AClassProxy.classProxy((Class)theClassOrClassProxy);
		else return null;
	}
	public static ObjectProxy objectProxy(Object theFactoryName, String theObjectID) {
		
			RemoteFactory factory = registry.get(theFactoryName);
			return factory.objectProxy(theFactoryName, theObjectID);
		
		
	}
	public static void register (Object name, RemoteFactory factory) {
		registry.put(name, factory);
	}
	public static RemoteFactory unRegister (Object name) {
		return registry.remove(name);
	}
	public static RemoteFactory getFactoryName(Object factory) {
		return registry.get(factory);
	}
	public static ClassProxy remoteClassForName (Object factory,String className) throws ClassNotFoundException {
		return getFactoryName(factory).forName(className);
	}
	public static ClassProxy javaClassForName (String s) throws ClassNotFoundException {
		//try {
			Class cls = Class.forName(s);
			return AClassProxy.classProxy(cls);
		/*	
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		*/
	}
	public static ClassProxy forName (String s) throws ClassNotFoundException {
		
		Enumeration<RemoteFactory> factories = registry.elements();
		while (factories.hasMoreElements()) {
			ClassProxy classProxy = factories.nextElement().forName(s);
			if (classProxy != null)
				return classProxy;
		}
		return javaClassForName(s);		
	}
	public static ClassProxy classProxy (Class c) {
		/*
		Enumeration<ClassFactory> factories = registry.elements();
		while (factories.hasMoreElements()) {
			ClassProxy classProxy = factories.nextElement().classProxy(c);
			if (classProxy != null)
				return classProxy;
		}
		*/
		return AClassProxy.classProxy(c);		
	}
	public static ClassProxy remoteClassProxy (Object factory, int cls) {
		/*
		Enumeration<ClassFactory> factories = registry.elements();
		while (factories.hasMoreElements()) {
			ClassProxy classProxy = factories.nextElement().classProxy(c);
			if (classProxy != null)
				return classProxy;
		}
		*/
		return RemoteClassProxy.classProxy(factory, cls);		
	}
	/*
	public static ClassProxy[] classProxy(Class[] clses) {
		ClassProxy[] retVal = new ClassProxy[clses.length];
		for (int i = 0; i < clses.length; i++)
			retVal[i] = classProxy(clses[i]);
		return retVal;
		
	}
	*/

}
