package bus.uigen.reflect.remote;

import java.lang.annotation.Annotation;
import java.util.Hashtable;

//import bus.uigen.Message;
import bus.uigen.reflect.ClassProxy;
import bus.uigen.reflect.FieldProxy;
import bus.uigen.reflect.MethodProxy;
import bus.uigen.reflect.local.AClassProxy;

public class RemoteFieldProxy extends CachingRemoteFieldProxy {
	/*
	public RemoteJavaFieldProxy(int field) {
		super (field);
	}
	*/
	public RemoteFieldProxy(Object theFactory, int field) {
		super (theFactory, field);
	}
	
	static transient Hashtable<Integer, RemoteFieldProxy> idsToFieldProxies = new Hashtable();
	public static FieldProxy fieldProxy (Object theFactory, int id) {
		
		RemoteFieldProxy retVal =idsToFieldProxies.get(id);
		if (retVal == null) {
			retVal = new RemoteFieldProxy(theFactory, id);
			idsToFieldProxies.put(id, retVal);			
		}
		return retVal;			
	}
	
	public static FieldProxy[] fieldProxies(Object theFactory, int[] ids) {
		FieldProxy[] retVal = new RemoteFieldProxy[ids.length];
		for (int i = 0; i < ids.length; i++)
			retVal[i] = new RemoteFieldProxy(theFactory, ids[i]);
		return retVal;			
	}
	/*
	public String getFactoryName() {
		return RemoteJavaObjectProxy.getStaticFactoryName();
	}
	*/

	@Override
	public Object remoteGet(Object obj) throws IllegalArgumentException,
			IllegalAccessException {
		// TODO Auto-generated method stub
		return ObjectManagerProxy.remoteGet(getFactoryName(), getFieldID(), RemoteObjectProxy.toID(obj));
	}

	@Override
	public ClassProxy remoteGetDeclaringClass() {
		// TODO Auto-generated method stub
		return RemoteClassProxy.classProxy(getFactoryName(), ObjectManagerProxy.remoteGetFieldDeclaringClass(getFactoryName(), getFieldID()));
	}

	@Override
	public int remoteGetModifiers() {
		// TODO Auto-generated method stub
		return ObjectManagerProxy.remoteGetFieldModifiers(getFactoryName(), getFieldID());
	}

	@Override
	public String remoteGetName() {
		// TODO Auto-generated method stub
		return ObjectManagerProxy.remoteGetFieldName(getFactoryName(), getFieldID());
	}

	@Override
	public ClassProxy remoteGetType() {
		// TODO Auto-generated method stub
		return RemoteClassProxy.classProxy(getFactoryName(), ObjectManagerProxy.remoteGetType(getFactoryName(), getFieldID()));
	}

	@Override
	public void remoteSet(Object obj, Object value)
			throws IllegalArgumentException, IllegalAccessException {
		// TODO Auto-generated method stub
		ObjectManagerProxy.remoteSet(getFactoryName(), getFieldID(), 
				RemoteObjectProxy.toID(obj),
				RemoteObjectProxy.toID(value));
	}
	public String remoteToString() {
		return ObjectManagerProxy.remoteFieldToString(getFactoryName(), getFieldID());
	}

}
