package bus.uigen.reflect.remote;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.Hashtable;

import bus.uigen.reflect.ClassProxy;
import bus.uigen.reflect.MethodProxy;
import bus.uigen.reflect.UnifiedMethod;

public abstract class CachingRemoteMethodProxy extends UnifiedMethod implements MethodProxy, FactoryName {
	int methodID;
	int constructorID;
	boolean isMethod;
	Hashtable<Class, Annotation> classToAnnotation = new Hashtable();
	public CachingRemoteMethodProxy(String name, ClassProxy[] parameters,
			ClassProxy returnType, ClassProxy theClass) {
		 super (name, parameters, returnType, theClass);
		// TODO Auto-generated constructor stub
	}
	public CachingRemoteMethodProxy() {
		
		
	}
	public CachingRemoteMethodProxy(Object theFactory) {
		factory = theFactory;		
	}
	Object factory;
	@Override
	public Object getFactoryName() {
		return factory;
	}

	@Override
	public void setFactoryName(Object newVal) {
		factory = newVal;
		
	}
	public static int[] toIDs(CachingRemoteMethodProxy[] methodProxies) {
		int[] retVal = new int[methodProxies.length];
		for (int i = 0; i < methodProxies.length; i++) {
			if (methodProxies[i].isMethod())
				retVal[i] = methodProxies[i].getMethodID();
			else // can this be a dynamic method
				retVal[i] = methodProxies[i].getConstructorID();
		}
		return retVal;
	}
	/*
	public RemoteMethodProxy(int theMethodOrConstructorID, boolean theIsMethod) {
		
		super.setRealMethod(true);
		isMethod = theIsMethod;
		if (isMethod)
			methodID = theMethodOrConstructorID;
		else
			constructorID = theMethodOrConstructorID;
	}
	*/
	public CachingRemoteMethodProxy(Object theFactory, int theMethodOrConstructorID, boolean theIsMethod) {
		factory = theFactory;
		super.setRealMethod(true);
		isMethod = theIsMethod;
		if (isMethod)
			methodID = theMethodOrConstructorID;
		else
			constructorID = theMethodOrConstructorID;
	}
	@Override
	public boolean isConstructor() {
		// TODO Auto-generated method stub
		return !isMethod && isRealMethod();
	}

	@Override
	public boolean isMethod() {
		// TODO Auto-generated method stub
		return isMethod && isRealMethod();
	}
	
	public int getMethodID() {
		return methodID;
	}
	
	public int getConstructorID() {
		return constructorID;
	}

	
	//public Annotation getAnnotation(Class annotationType) {
	public <T extends Annotation> T getAnnotation(Class<T> annotationType) {
		T retVal = (T) classToAnnotation.get(annotationType);
		if (retVal == null) {
			retVal = super.getAnnotation(annotationType);
			classToAnnotation.put(annotationType, retVal);
		}
		return retVal;
	}
	Annotation[] annotations;
	@Override
	public Annotation[] getAnnotations() {
		if (annotations == null)
			annotations = super.getAnnotations();
		return annotations;
	}
	
	Annotation[] declaredAnnotations;
	@Override
	public Annotation[] getDeclaredAnnotations() {
		if (declaredAnnotations == null)
			declaredAnnotations = super.getDeclaredAnnotations();
		return declaredAnnotations;
	}
	ClassProxy declaringClass;
	@Override
	public ClassProxy getDeclaringClass() {
		if (declaringClass == null)
			declaringClass = super.getDeclaringClass();
		return declaringClass;
	}
	/*
	@Override
	public ClassProxy getDynamicClass() {
		// TODO Auto-generated method stub
		return null;
	}
	*/
	Integer modifiers;
	@Override
	public int getModifiers() {
		if (modifiers == null)
			modifiers = super.getModifiers();
		return modifiers;
	}
	String name;
	@Override
	public String getName() {
		if (name == null)
			name = super.getName();
		return name;
	}
	ClassProxy[] parameterTypes;
	@Override
	public ClassProxy[] getParameterTypes() {
		if (parameterTypes == null)
			parameterTypes = super.getParameterTypes();
		return parameterTypes;
	}
	ClassProxy returnType;
	
	@Override
	public ClassProxy getReturnType() {
		if (returnType == null)
			returnType = super.getReturnType();
		return returnType;
	}
	
	Boolean annotationPresent;
	@Override
	public boolean isAnnotationPresent(
			Class<? extends Annotation> annotationType) {
		if (annotationPresent == null)
			annotationPresent = super.isAnnotationPresent(annotationType);
		return annotationPresent;
	}
	public abstract String remoteToString();
	String remoteToString;
	public String toString() {
		if (remoteToString == null)
			remoteToString = remoteToString();
		return remoteToString + "\n" + super.toString();
	}

	
	
}
