package bus.uigen.reflect.remote;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.Hashtable;

import bus.uigen.reflect.ClassProxy;
import bus.uigen.reflect.FieldProxy;
import bus.uigen.reflect.MethodProxy;

public class RemoteMethodProxy extends CachingRemoteMethodProxy implements MethodProxy {
	
	public RemoteMethodProxy(String name, ClassProxy[] parameters,
			ClassProxy returnType, ClassProxy theClass) {
		 super (name, parameters, returnType, theClass);
		// TODO Auto-generated constructor stub
	}
	/*
	public RemoteJavaMethodProxy(int theMethodOrConstructorID, boolean isMethod) {
		super(theMethodOrConstructorID, isMethod);
		
	}
	*/
	public RemoteMethodProxy(Object theFactory, int theMethodOrConstructorID, boolean isMethod) {
		super(theFactory, theMethodOrConstructorID, isMethod);
		/*
		super.setRealMethod(true);
		if (isMethod)
			methodID = theMethodOrConstructorID;
		else
			constructorID = theMethodOrConstructorID;
			*/
		// TODO Auto-generated constructor stub
	}
	/*
	public String getFactoryName() {
		return RemoteJavaObjectProxy.getStaticFactoryName();
	}
	*/
	static transient Hashtable<Integer, RemoteMethodProxy> idsToMethodProxies = new Hashtable();
	public static MethodProxy methodProxy (Object theFactory, int id, boolean isMethod) {
		
		RemoteMethodProxy retVal =idsToMethodProxies.get(id);
		if (retVal == null) {
			retVal = new RemoteMethodProxy(theFactory, id, isMethod);
			idsToMethodProxies.put(id, retVal);			
		}
		return retVal;			
	}
	public static MethodProxy[] toMethodProxies(Object theFactory, int[] ids) {
		MethodProxy[] retVal = new RemoteMethodProxy[ids.length];
		for (int i = 0; i < ids.length; i++)
			retVal[i] = new RemoteMethodProxy(theFactory, ids[i], true);
		return retVal;			
	}
	public static MethodProxy[] toConstructorProxies(Object theFactory, int[] ids) {
		MethodProxy[] retVal = new RemoteMethodProxy[ids.length];
		for (int i = 0; i < ids.length; i++)
			retVal[i] = new RemoteMethodProxy(theFactory, ids[i], false);
		return retVal;			
	}

	@Override
	public MethodProxy cloneMethod() {
		// TODO Auto-generated method stub
		return new RemoteMethodProxy(getFactoryName(), getMethodID(), true);
	}
	
	@Override
	public MethodProxy cloneMethod(String name, ClassProxy[] parameters,
			ClassProxy returnType, ClassProxy theClass) {
		// TODO Auto-generated method stub
		return new RemoteMethodProxy(name, parameters, returnType, theClass);
		//return null;
	}
	
	@Override
	public Object constructorNewInstance(Object... parameters)
			throws InvocationTargetException, IllegalAccessException,
			InstantiationException {
		//int returnTypeID = ((CachingRemoteClassProxy) getReturnType()).getClassID();
		return RemoteObjectProxy.objectProxy(getFactoryName(),ObjectManagerProxy.constructorInvoke(getFactoryName(), getConstructorID(), 
				
				CachingRemoteObjectProxy.toIDs(parameters, getParameterTypes())));
	}

	@Override
	public String constructorToString() {
		// TODO Auto-generated method stub
		return ObjectManagerProxy.constructorToString(getFactoryName(), getConstructorID());
	}

	@Override
	public Annotation getConstructorAnnotation(Class annotationType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Annotation[] getConstructorAnnotations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClassProxy getConstructorDeclaringClass() {
		// TODO Auto-generated method stub
		return RemoteClassProxy.classProxy(getFactoryName(),ObjectManagerProxy.getConstructorDeclaringClass(getFactoryName(), getConstructorID()));
	}

	@Override
	public String getConstructorName() {
		// TODO Auto-generated method stub
		return ObjectManagerProxy.getConstructorName(getFactoryName(), getConstructorID());
	}

	@Override
	public ClassProxy[] getConstructorParameterTypes() {
		// TODO Auto-generated method stub
		return RemoteClassProxy.toClassProxies(getFactoryName(), ObjectManagerProxy.getConstructorParameterTypes(getFactoryName(), getConstructorID()));
	}

	@Override
	public ClassProxy getConstructorReturnType() {
		// TODO Auto-generated method stub
		return RemoteClassProxy.classProxy(getFactoryName(),ObjectManagerProxy.getConstructorReturnType(getFactoryName(), getConstructorID()));
	}

	@Override
	public Annotation[] getDeclaredConstructorAnnotations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Annotation[] getDeclaredMethodAnnotations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Annotation getMethodAnnotation(Class annotationType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Annotation[] getMethodAnnotations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClassProxy getMethodDeclaringClass() {
		// TODO Auto-generated method stub
		return RemoteClassProxy.classProxy(getFactoryName(),ObjectManagerProxy.getMethodDeclaringClass(getFactoryName(), getMethodID()));
	}

	@Override
	public int getMethodModifiers() {
		// TODO Auto-generated method stub
		return ObjectManagerProxy.getMethodModifiers(getFactoryName(), getMethodID());
	}
	@Override
	public int getConstructorModifiers() {
		// TODO Auto-generated method stub
		return ObjectManagerProxy.getConstructorModifiers(getFactoryName(), getConstructorID());
	}

	@Override
	public String getMethodName() {
		// TODO Auto-generated method stub
		return ObjectManagerProxy.getMethodName(getFactoryName(), getMethodID());
	}

	@Override
	public ClassProxy[] getMethodParameterTypes() {
		// TODO Auto-generated method stub
		return RemoteClassProxy.toClassProxies(getFactoryName(), ObjectManagerProxy.getMethodParameterTypes(getFactoryName(), getMethodID()));
	}

	@Override
	public ClassProxy getMethodReturnType() {
		// TODO Auto-generated method stub
		return RemoteClassProxy.classProxy(getFactoryName(),ObjectManagerProxy.getMethodReturnType(getFactoryName(), getMethodID()));
	}

	@Override
	public boolean isConstructorAnnotationPresent(
			Class<? extends Annotation> annotationType) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isMethodAnnotationPresent(
			Class<? extends Annotation> annotationType) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object methodInvoke(Object targetObject, Object... parameters)
			throws InvocationTargetException, IllegalAccessException,
			InstantiationException {
		int returnTypeID = ((CachingRemoteClassProxy) getReturnType()).getClassID();
		return RemoteObjectProxy.objectProxy(getFactoryName(),ObjectManagerProxy.methodInvoke(getFactoryName(), getMethodID(), 
				CachingRemoteObjectProxy.toID(targetObject),
				CachingRemoteObjectProxy.toIDs(parameters, getParameterTypes())), 
				returnTypeID);
	}

	@Override
	public String methodToString() {
		// TODO Auto-generated method stub
		return ObjectManagerProxy.methodToString(getFactoryName(), getMethodID());
	}

	@Override
	public <T extends Annotation> T getAnnotation(Class<T> annotationType) {
		// TODO Auto-generated method stub
		return null;
	}
	/*
	@Override
	public ClassProxy[] getParameterTypes(Object methodOrVirtualMethod) {
		// TODO Auto-generated method stub
		return null;
	}
	*/
	public String remoteToString() {
		return ObjectManagerProxy.remoteMethodToString(getFactoryName(), getMethodID());
	}
	
}
