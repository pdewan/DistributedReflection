package bus.uigen.reflect.remote;

import java.lang.annotation.Annotation;
import java.util.Hashtable;
import java.util.Set;

//import bus.uigen.Message;
import bus.uigen.reflect.ClassProxy;
import bus.uigen.reflect.RemoteSelector;
import bus.uigen.reflect.FieldProxy;
import bus.uigen.reflect.MethodProxy;
import bus.uigen.reflect.local.AClassProxy;

public abstract class CachingRemoteClassProxy extends RemoteProxy implements ClassProxy, FactoryName {
	int classID;
	
	public CachingRemoteClassProxy(int theClassID) {
		classID = theClassID;
	}
	
	public CachingRemoteClassProxy(Object theFactory, int theClassID) {
		super (theFactory);
		classID = theClassID;
	}
	public int getClassID() {
		return classID;
	}
	public static int[] toIDs(CachingRemoteClassProxy[] classProxies) {
		int[] retVal = new int[classProxies.length];
		for (int i = 0; i < classProxies.length; i++)
			retVal[i] = classProxies[i].getClassID();
		return retVal;
	}
	Hashtable<Class, Annotation> classToAnnotation = new Hashtable();
	//public Annotation getAnnotation(Class annotationType) {
	public <T extends Annotation> T getAnnotation(Class<T> annotationType) {
		T retVal = (T) classToAnnotation.get(annotationType);
		if (retVal == null) {
			retVal = remoteGetAnnotation(annotationType);
			if (retVal != null)
			classToAnnotation.put(annotationType, retVal);
		}
		return retVal;
	}
	Annotation[] annotations;
	@Override
	public Annotation[] getAnnotations() {
		if (annotations == null)
			annotations = remoteGetAnnotations();
		return annotations;
	}
	String canonicalName;
	@Override
	public String getCanonicalName() {
		if (canonicalName == null)
			canonicalName = remoteGetCanonicalName();
		return canonicalName;
	}
	ClassProxy componentType;
	@Override
	public ClassProxy getComponentType() {
		if (componentType == null)
			componentType = remoteGetComponentType();
		return componentType;
	}
	
	MethodProxy[] constructors;
	@Override
	public MethodProxy[] getConstructors() {
		if (constructors == null)
			constructors = remoteGetConstructors();
		return constructors;
	}
	ClassProxy[] declaredClasses;
	@Override
	public ClassProxy[] getDeclaredClasses() {
		if (declaredClasses == null)
			declaredClasses = remoteGetDeclaredClasses();
		return declaredClasses;
	}
	FieldProxy[] declaredFields;
	@Override
	public FieldProxy[] getDeclaredFields() {
		if (declaredFields == null)
			declaredFields = remoteGetDeclaredFields();
		return declaredFields;
	}
	ClassProxy declaringClass;
	@Override
	public ClassProxy getDeclaringClass() {
		if (declaringClass == null)
			declaringClass = remoteGetDeclaringClass();
		return declaringClass;
	}
	Object[] enumConstants;
	@Override
	public Object[] getEnumConstants() {
		if (enumConstants == null)
			enumConstants = remoteGetEnumConstants();
		return enumConstants;
	}
	Hashtable<String, FieldProxy> nameToField = new Hashtable();
	@Override
	public FieldProxy getField(String name) throws SecurityException,
			NoSuchFieldException {
		FieldProxy retVal = nameToField.get(name);
		if (retVal == null) {
			retVal = remoteGetField(name);
			nameToField.put(name, retVal);
		}
		return retVal;
	}
	FieldProxy[] fields;
	@Override
	public FieldProxy[] getFields() {
		if (fields == null)
			fields = remoteGetFields();
		return fields;
	}
	ClassProxy[] interfaces;
	@Override
	public ClassProxy[] getInterfaces() {
		if (interfaces == null)
			interfaces = remoteGetInterfaces();
		return interfaces;
	}
	@Override
	public MethodProxy getMethod(String name, ClassProxy... parameterTypes)
			throws NoSuchMethodException, SecurityException {
		// TODO Auto-generated method stub
		return getMethod(this, name, parameterTypes);
	}
	MethodProxy[] methods;
	@Override
	public MethodProxy[] getMethods() {
		if (methods == null)
			methods = remoteGetMethods();
		return methods;
	}
	int modifiers = -1;
	@Override
	public int getModifiers() {
		if (modifiers == -1)
			modifiers = remoteGetModifiers();
		return modifiers;
	}
	String name;
	@Override
	public String getName() {
		if (name == null)
			name = remoteGetName();
		return name;
	}
	String simpleName;
	@Override
	public String getSimpleName() {
		if (simpleName == null)
			simpleName = remoteGetSimpleName();
		return simpleName;
	}
	ClassProxy superClass;
	@Override
	public ClassProxy getSuperclass() {
		if (superClass == null)
			superClass = remoteGetSuperclass();
		return superClass;
	}
	Boolean isArray;
	@Override
	public boolean isArray() {
		if (isArray == null)
			isArray = remoteIsArray();
		return isArray;
	}
	Hashtable<ClassProxy, Boolean> assignableFrom = new Hashtable();
	@Override
	public boolean isAssignableFrom(ClassProxy cls) {
		Boolean retVal = assignableFrom.get(cls);		
		if (retVal == null) {
			if (!(cls instanceof CachingRemoteClassProxy))
				retVal = false;
			else
				retVal = remoteIsAssignableFrom((CachingRemoteClassProxy) cls);
			assignableFrom.put(cls, retVal);
		}
		return retVal;
	}
	Boolean isEnum;
	@Override
	public boolean isEnum() {
		if (isEnum == null)
			isEnum = remoteIsEnum();
		return isEnum;
	}
	//Hashtable<Object, Boolean> isInstance = new Hashtable();
	@Override
	public boolean isInstance(Object obj) {
		return this.isAssignableFrom(RemoteSelector.getClass(obj));
	}
	Boolean isInterface;
	@Override
	public boolean isInterface() {
		if (isInterface == null)
			isInterface = remoteIsInterface();
		return isInterface;
	}
	Boolean isPrimitive;
	@Override
	public boolean isPrimitive() {
		if (isPrimitive == null)
			isPrimitive = remoteIsPrimitive();
		return isPrimitive;
	}
	@Override
	public Object newInstance() throws InstantiationException,
			IllegalAccessException {
		// TODO Auto-generated method stub
		return remoteNewInstance();
	}
	public ClassProxy forName(String className) throws ClassNotFoundException  {
		return RemoteSelector.remoteClassForName(getFactoryName(), className);
	}
	String remoteToString;
	public abstract String remoteToString();
	public String toString() {
		if (remoteToString == null)
			remoteToString = remoteToString();
		return remoteToString + "\n" + super.toString();
	}
	
	////
	//public abstract <T extends Annotation> remoteGetAnnotation(Class annotationType); 
	public abstract <T extends Annotation> T remoteGetAnnotation(Class<T> annotationType);
	
	public abstract Annotation[] remoteGetAnnotations();
	public abstract String remoteGetCanonicalName() ;
	public abstract ClassProxy remoteGetComponentType();
	public abstract MethodProxy[] remoteGetConstructors();
	public abstract ClassProxy[] remoteGetDeclaredClasses() ;
	public abstract FieldProxy[] remoteGetDeclaredFields();
	public abstract ClassProxy remoteGetDeclaringClass();
	public abstract Object[] remoteGetEnumConstants();
	public abstract FieldProxy remoteGetField(String string) throws SecurityException,
			NoSuchFieldException ;
	public abstract FieldProxy[] remoteGetFields();
	public abstract ClassProxy[] remoteGetInterfaces() ;
	public  abstract MethodProxy remoteGetMethod(String name, CachingRemoteClassProxy... parameterTypes)
			throws NoSuchMethodException, SecurityException ;
	public  abstract MethodProxy[] remoteGetMethods() ;
	public abstract int remoteGetModifiers();
	public abstract String remoteGetName() ;
	public abstract String remoteGetSimpleName() ;
	public abstract ClassProxy remoteGetSuperclass();
	public abstract boolean remoteIsArray();
	public abstract boolean remoteIsAssignableFrom(CachingRemoteClassProxy cls);
	public abstract boolean remoteIsEnum() ;
	public abstract boolean remoteIsInstance(ObjectProxy obj) ;
	public abstract boolean remoteIsInterface();
	public abstract boolean remoteIsPrimitive() ;
	public  abstract Object remoteNewInstance() throws InstantiationException,
	IllegalAccessException;
	
	public static MethodProxy getMethod(ClassProxy c, 
			String targetName,
			ClassProxy... targetParameterTypes) {
		//Method[] methods = c.getMethods();
		MethodProxy[] methods = c.getMethods();
		for (int i = 0; i < methods.length; i++)
			if (matchMethod(methods[i], targetName, targetParameterTypes)) return methods[i];
		return null;
		
	}
	
	public static boolean matchMethod(MethodProxy method, 
			   String targetName, 
			   ClassProxy... targetParameterTypes) {		

		return  method.getName().equals(targetName) &&
			matchParameters(method, targetParameterTypes);
}
	public static boolean matchParameters(MethodProxy method, ClassProxy... parameters) {
		ClassProxy[] parameters1 = method.getParameterTypes();
		if (parameters1.length != parameters.length)
			return false;
		for (int i = 0; i < parameters1.length; i++)
			if (parameters1[i] != parameters[i])
				return false;
		return true;
		
	}
	

}
