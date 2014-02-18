package bus.uigen.reflect.remote;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Hashtable;

//import util.Explanation;

import bus.uigen.reflect.ClassProxy;
import bus.uigen.reflect.RemoteSelector;
import bus.uigen.reflect.FieldProxy;
import bus.uigen.reflect.MethodProxy;
import bus.uigen.reflect.local.AClassProxy;

public abstract class CachingRemoteFieldProxy extends RemoteProxy implements FieldProxy, FactoryName {
	int field;
	/*
	public RemoteFieldProxy (int f) {
		field = f;
	}
	*/
	public CachingRemoteFieldProxy (Object theFactory, int f) {
		super (theFactory);
		field = f;
	}
	//static transient Hashtable<Field, FieldProxy> fieldToFieldProxy = new Hashtable();
	 
	public int getFieldID() {
		return field;
	}
	public static int[] toIDs(CachingRemoteFieldProxy[] fieldProxies) {
		int[] retVal = new int[fieldProxies.length];
		for (int i = 0; i < fieldProxies.length; i++)
			retVal[i] = fieldProxies[i].getFieldID();
		return retVal;
	}
	 String name;
	 public String getName() {
		 if (name == null)
			 name = remoteGetName();
		 return name;
	 }

	@Override
	public Object get(Object obj) throws IllegalArgumentException, IllegalAccessException {
		// TODO Auto-generated method stub
		return remoteGet(obj);
	}

	@Override
	public void set(Object obj, Object value) throws IllegalArgumentException, IllegalAccessException {
		
		remoteSet(obj, value);
	}
	Integer modifiers;
	@Override
	public int getModifiers() {
		if (modifiers == null)
			modifiers = remoteGetModifiers();
		return modifiers;
	}
	ClassProxy type;
	@Override
	public ClassProxy getType() {
		if (type == null)
			type = remoteGetType();
		return type;
	}
	ClassProxy declaringClass;
	@Override
	public ClassProxy getDeclaringClass() {
		if (declaringClass == null)
			declaringClass = remoteGetDeclaringClass();
		return remoteGetDeclaringClass();
	}

	@Override
	//public Annotation getAnnotation(ClassProxy classProxy) {
	public  Annotation getAnnotation(Class annotationType) { 
		
		// TODO Auto-generated method stub
		/*
		if (!(classProxy instanceof AClassProxy))
				return null;
		
		return field.getAnnotation(((AClassProxy) classProxy).getJavaClass());
		*/
		return null;
	}
	
	public abstract String remoteToString();
	String remoteToString;
	public String toString() {
		if (remoteToString == null)
			remoteToString = remoteToString();
		return remoteToString + "\n" + super.toString();
	}
	
	public abstract String remoteGetName();

	
	public abstract Object remoteGet(Object obj) throws IllegalArgumentException, IllegalAccessException;

	
	public abstract void remoteSet(Object obj, Object value) throws IllegalArgumentException, IllegalAccessException ;

	
	public abstract int remoteGetModifiers() ;

	
	public abstract ClassProxy remoteGetType() ;

	
	public abstract ClassProxy remoteGetDeclaringClass();

	
	
		
}
