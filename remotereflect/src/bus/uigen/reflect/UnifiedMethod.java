package bus.uigen.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Hashtable;

import bus.uigen.reflect.ClassProxy;
import bus.uigen.reflect.MethodProxy;
import bus.uigen.reflect.local.AClassProxy;
import bus.uigen.reflect.local.AVirtualMethod;

public abstract class UnifiedMethod implements MethodProxy  {
	
	String name;
	ClassProxy[] parameterTypes; 
	ClassProxy returnType;
	ClassProxy declaringClass;
	MethodProxy invokeMethodWithParameters;
	MethodProxy invokeParameterLessMethod;
	MethodProxy getDynamicCommandAnnotation;
	//Annotation annotations;
	
	boolean isRealMethod = false;
	//Method method;
	//Constructor constructor;
	Object originalObject;
	
	public void setRealMethod(boolean theNewValue) {
		isRealMethod = theNewValue;
	}
	public boolean isRealMethod() {
		return isRealMethod;
	}
	public UnifiedMethod() {
		
	}
	/*
	public MethodEmbellishment (Method theMethod) {
		isRealMethod = true;
		//method = theMethod;
		
		
	}
	*/
	public UnifiedMethod (String theName, ClassProxy[] theParameterTypes, 
			ClassProxy theReturnType, ClassProxy theDeclaringClass) {
		name = theName;
		parameterTypes = theParameterTypes;
		returnType = theReturnType;
		declaringClass = theDeclaringClass;
		invokeMethodWithParameters = DynamicMethods.getInvokeDynamicCommand(declaringClass);		 
		invokeParameterLessMethod = DynamicMethods.getParameterlessInvokeDynamicCommand(declaringClass);
		getDynamicCommandAnnotation =  DynamicMethods.getGetDynamicCommandAnnotation(declaringClass);
		/*
		try {
			dummy = String.class.getMethod("toString", emptyArgs);
			virtualDummy = AVirtualMethod.virtualMethod(dummy);
			
		} catch (Exception e) {
			
		}
		*/
		
	}
	public abstract MethodProxy  cloneMethod();
	public abstract MethodProxy  cloneMethod(String name, 
			ClassProxy[] parameters, 
			ClassProxy returnType,
			ClassProxy theClass);
	public MethodProxy moveFromObject(Object sourceObject) {	
		MethodProxy newVM;
		
		if (isMethod())
			//newVM = new MethodEmbellishment(method);
			newVM = cloneMethod();
		else
			newVM =  cloneMethod(getName(), getParameterTypes(), getReturnType(), AClassProxy.classProxy(sourceObject.getClass()));
			//newVM =  new MethodEmbellishment(getName(), getParameterTypes(), getReturnType(), AClassProxy.classProxy(sourceObject.getClass()));
		newVM.setSourceObject(sourceObject);
		return newVM;
	}
	public void setSourceObject (Object o) {
		originalObject = o;
	}
	public Object getSourceObject () {
		return originalObject;
	}
	
	public boolean isDynamicCommand() {
		  return !isMethod() && !isConstructor();
	  }
	
	
	/* (non-Javadoc)
	 * @see bus.uigen.controller.VirtualMethod#getMethod()
	 */
	/*
	public Method getMethod() {
		return method;
	}
	public Constructor getConstructor() {
		return constructor;
	}
	*/
	public abstract String  getMethodName();
	public abstract String getConstructorName();
	public String getName() {
		//if (method == null && constructor == null)
		if (!isMethod() && !isConstructor())
			return name;
		else if (isMethod())
		//else if (method != null)
			//return method.getName();
			return getMethodName();
		else 
			return getConstructorName();
			//return constructor.getName();
	}
	
	public abstract ClassProxy[] getMethodParameterTypes();
	public abstract ClassProxy[] getConstructorParameterTypes();
	
	public ClassProxy[] getParameterTypes() {
		//if (method != null)
		if (isMethod())
			//return AClassProxy.classProxy(method.getParameterTypes());
			return getMethodParameterTypes();
		//else if (constructor != null)
		else if (isConstructor())
			//return AClassProxy.classProxy(constructor.getParameterTypes());
			//return AClassProxy.classProxy(constructor.getParameterTypes());
			return getConstructorParameterTypes();
		else
			return parameterTypes;
		/*
		if (method == null)
			return parameterTypes;
		else
			return method.getParameterTypes();
			*/
	}
	/* (non-Javadoc)
	 * @see bus.uigen.controller.VirtualMethod#getReturnType()
	 */
	public abstract ClassProxy  getMethodReturnType();
	public abstract ClassProxy getConstructorReturnType();
	public ClassProxy getReturnType() {
		//if (method != null)
		if (isMethod())
			//return  AClassProxy.classProxy(method.getReturnType());
			return getMethodReturnType();
		//else if (constructor != null)
		else if (isConstructor())
			return getConstructorDeclaringClass();
			//return AClassProxy.classProxy(constructor.getDeclaringClass());
			//return getConstructorReturnType();
		else
			return returnType;
		/*
		if (method == null)
			return returnType;
		else
			return method.getReturnType();
			*/
	}
	/* (non-Javadoc)
	 * @see bus.uigen.controller.VirtualMethod#getDeclaringClass()
	 */
	public abstract ClassProxy getMethodDeclaringClass();
	public abstract ClassProxy getConstructorDeclaringClass();
	public ClassProxy getDeclaringClass() {
		if (isMethod())
		//if (method != null)
			return getMethodDeclaringClass();
			//return AClassProxy.classProxy(method.getDeclaringClass());
		else if (isConstructor())
		//else if (constructor != null)
			return getConstructorDeclaringClass();
			//return AClassProxy.classProxy(constructor.getDeclaringClass());
		else
			return declaringClass;
		/*
		if (method == null)
			return declaringClass;
		else
			return method.getDeclaringClass();
			*/
	}
	public abstract Object methodInvoke (Object targetObject, Object... parameterTypes) throws InvocationTargetException, IllegalAccessException, InstantiationException;
	public abstract Object constructorNewInstance (Object... parameterTypes) throws InvocationTargetException, IllegalAccessException, InstantiationException;
	/* (non-Javadoc)
	 * @see bus.uigen.controller.VirtualMethod#invoke(java.lang.Object, java.lang.Object[])
	 */
	boolean cloned = false;
	public void setCloned(boolean newVal) {
		cloned = newVal;
	}
	public boolean isCloned() {
		return cloned;
	}
	// targetObject not the class of originalObject if it is filter class
	boolean targetAndOriginalOfSameClass(Object targetObject, Object originalObject) {
		if (targetObject == null || originalObject == null)
			return true;
		return RemoteSelector.getClass(targetObject) == RemoteSelector.getClass(originalObject);
		
	}
	public Object invoke (Object targetObject, Object... parameterTypes) throws InvocationTargetException, IllegalAccessException, InstantiationException  {
		///try {
			//if (originalObject != null)
		
			if ( (originalObject != null && targetObject == null)) 
					//|| !targetAndOriginalOfSameClass(targetObject, originalObject))
				targetObject = originalObject;
					
			if (isMethod())
				return methodInvoke(targetObject, parameterTypes);
				//return method.invoke(targetObject, parameterTypes);
			if (isConstructor())
				return constructorNewInstance(parameterTypes);
				//return constructor.newInstance(parameterTypes);
			//else {
			MethodProxy invokeMethod = invokeMethodWithParameters;
			Object[] parameters = {name, parameterTypes};
			if (invokeMethod == null) {
				invokeMethod = invokeParameterLessMethod;
				Object[] temp = {name};
				parameters = temp;
			}
			if (invokeMethod != null)	
				return invokeMethod.invoke(targetObject, parameters);
			else {
				System.out.println("Did not find an InvokeDynamicCommand method");
				return null;
			}
			//}
			
		/*	
		} catch (Exception e) {
			System.out.println("Did not find an InvokeDynamicCommand method");
			return null;
		}
		*/
	}
	/*
	@Override
	public void invoke(Object targetObject, Object newVal) {
		Object[] params = {newVal};
		invoke(targetObject, params);
		// TODO Auto-generated method stub
		
	}
	*/
	/*
	public  static ClassProxy getMethodDeclaringClass(Object methodOrVirtualMethod) {
		return AClassProxy.classProxy(((Method)	methodOrVirtualMethod).getDeclaringClass());
	}
	public  static ClassProxy getConstructorDeclaringClass(Object methodOrVirtualMethod) {
		return AClassProxy.classProxy(((Constructor)	methodOrVirtualMethod).getDeclaringClass());
	}
		
	
	// code to unify Method and VirtualMethod  - if only Method implemented an interface!
	// not needed if method is wrappped in a virtual method
	public static ClassProxy getDeclaringClass(Object methodOrVirtualMethod) {
		if (methodOrVirtualMethod instanceof Method)
			return AClassProxy.classProxy(((Method)	methodOrVirtualMethod).getDeclaringClass());
		else if (methodOrVirtualMethod instanceof Constructor)
			return AClassProxy.classProxy(((Constructor)	methodOrVirtualMethod).getDeclaringClass());
		else if (methodOrVirtualMethod instanceof MethodEmbellishment)
			return ((MethodProxy)	methodOrVirtualMethod).getDeclaringClass();
		else return null;
	}
	
	public static String getName(Object methodOrVirtualMethod) {
		if (methodOrVirtualMethod instanceof Method)
			return ((Method)	methodOrVirtualMethod).getName();
		else if (methodOrVirtualMethod instanceof Constructor)
			return ((Constructor)	methodOrVirtualMethod).getName();
		else if (methodOrVirtualMethod instanceof MethodEmbellishment)
			return ((MethodProxy)	methodOrVirtualMethod).getName();
		else return null;
	}
	*/

	/* (non-Javadoc)
	 * @see bus.uigen.controller.VirtualMethod#getParameterTypes(java.lang.Object)
	 */
	/*
	public ClassProxy[] getParameterTypes(Object methodOrVirtualMethod) {
		if (methodOrVirtualMethod instanceof Method)
			return AClassProxy.classProxy(((Method)	methodOrVirtualMethod).getParameterTypes());
		else if (methodOrVirtualMethod instanceof Constructor)
			return AClassProxy.classProxy(((Constructor)	methodOrVirtualMethod).getParameterTypes());
		else if (methodOrVirtualMethod instanceof MethodEmbellishment)
			return ((MethodProxy)	methodOrVirtualMethod).getParameterTypes();
		else return null;
	}
	public static ClassProxy getReturnType(Object methodOrVirtualMethod) {
		if (methodOrVirtualMethod instanceof Method)
			return AClassProxy.classProxy(((Method)	methodOrVirtualMethod).getReturnType());
		else  if (methodOrVirtualMethod instanceof Constructor)
			return AClassProxy.classProxy(((Constructor)	methodOrVirtualMethod).getDeclaringClass());
		else if (methodOrVirtualMethod instanceof MethodEmbellishment)
			return ((MethodProxy)	methodOrVirtualMethod).getReturnType();
		else return null;
	}
	public static Object invoke (Object methodOrVirtualMethod, Object targetObject,
			Object[] parameterTypes ) {
		try {
		if (methodOrVirtualMethod instanceof Method)
			return ((Method)	methodOrVirtualMethod).invoke(targetObject, parameterTypes);
		else if (methodOrVirtualMethod instanceof Constructor)
			return ((Constructor)	methodOrVirtualMethod).newInstance(parameterTypes);
		else if (methodOrVirtualMethod instanceof MethodEmbellishment)
			return ((MethodProxy)	methodOrVirtualMethod).invoke(targetObject, parameterTypes); 
		else 
		{			
			System.out.println("invoke:" + "did not find virtual method to invoke");
			return null;
		}
		} catch (Exception e) {
			return null;
		}
	}
	*/
	
	ClassProxy virtualClass;
	//static transient Hashtable<Constructor, MethodProxy> constructorsToVirtualMethods = new Hashtable();
	//static transient Hashtable<Method, MethodProxy> methodsToVirtualMethods = new Hashtable();
	public void setDynamiclClass (ClassProxy newVal) {
   	 virtualClass = newVal;
    }
    
    public ClassProxy getDynamicClass() {
   	 return virtualClass;
    }
    //public Annotation getAnnotation(ClassProxy theAnnotationType) {
    //public <T extends Annotation> T getAnnotation(Class<T> annotationType) {
    public abstract <T extends Annotation> T getMethodAnnotation(Class<T> annotationType);
    public abstract <T extends Annotation> T  getConstructorAnnotation(Class<T> annotationType);
    //public abstract <T extends Annotation> T getDynamicCommandAnnotation(Class<T> annotationType);
    public <T extends Annotation> T getAnnotation(Class<T> annotationType) {
   //public Annotation getAnnotation(Class annotationType) {
    //if (!(theAnnotationType instanceof AClassProxy))
    		//return null;
    //Class annotationType = ((AClassProxy)theAnnotationType).getJavaClass();
    	//public Annotation getAnnotation(Class annotationType) {
    	if (isMethod())
    		return getMethodAnnotation(annotationType);
			//return method.getAnnotation(annotationType);
		else if (isConstructor())
			return getConstructorAnnotation(annotationType);
			//return constructor.getAnnotation(annotationType);
		else {
			if (getDynamicCommandAnnotation != null) {
				try {
					Object params[] = {name, annotationType};
					return (T) getDynamicCommandAnnotation.invoke(originalObject, params);
				}catch (Exception e) {
					return null;
				}
			}
			return null;
		}
    }
    public abstract  Annotation[] getMethodAnnotations();
    public abstract  Annotation[] getConstructorAnnotations();
    public Annotation[] getAnnotations() {
    	if (isMethod())
    		return getMethodAnnotations();
		//return method.getAnnotations();
	else if (isConstructor())
		return getConstructorAnnotations();
		//return constructor.getAnnotations();
	else
		return null;
    }
    public abstract  Annotation[] getDeclaredMethodAnnotations();
    public abstract  Annotation[] getDeclaredConstructorAnnotations();
    public Annotation[] getDeclaredAnnotations()  {
    	if (isMethod())
    		return getDeclaredMethodAnnotations();
    	else if (isConstructor())
    		return getDeclaredConstructorAnnotations();
    	else
    		return null;
        
    }
    public abstract boolean isMethodAnnotationPresent(Class<? extends Annotation> annotationType);
    public abstract boolean isConstructorAnnotationPresent(Class<? extends Annotation> annotationType);
    
    public boolean isAnnotationPresent(Class<? extends Annotation> annotationType) {
    	if (isMethod())
    		return isMethodAnnotationPresent(annotationType);
    	else if (isConstructor())
    		return isConstructorAnnotationPresent(annotationType);
    	else
    		return false;
    }
    public abstract int getMethodModifiers();
    public abstract int getConstructorModifiers();
    public int getModifiers() {
		if (isMethod())
			return getMethodModifiers();
		else if (isConstructor())
			return getConstructorModifiers();
		return 0;
	}
    // these two methods can probably be folded into one
    public  boolean equalsMethod(UnifiedMethod theMethod) {
    	return this == theMethod;
    }
    public  boolean equalsConstructor(UnifiedMethod theConstructor) {
    	return this == theConstructor;
    	
    }
    //public boolean equals(VirtualMethod otherMethod) {
    public boolean equals(Object otherMethod) {
    	try {
    	if (this == otherMethod)
    		return true;
    	if (!(otherMethod instanceof AVirtualMethod))
    		return false;
    	UnifiedMethod otherInstance = (UnifiedMethod) otherMethod;
    	
    	if (isMethod() && otherInstance.isMethod())
    		return equalsMethod(otherInstance);
    	if (isConstructor() && otherInstance.isConstructor())
    		return equalsConstructor(otherInstance);
    	return false;
    	} catch (Exception e) {
    		e.printStackTrace();
    		return false;
    	}
    }
    //public abstract Object constructorNewInstance(Object[] parameterValues);
    public Object newInstance(Object[] parameterValues) throws IllegalAccessException, InstantiationException, InvocationTargetException {
    	if (!isConstructor()) return null;    	
    	return constructorNewInstance(parameterValues);
    
    	
    }
    public abstract String methodToString();
    public abstract String constructorToString();
    public String toString() {
    	if (isMethod())
    		return methodToString();
    	else if (isConstructor())
    		return constructorToString();
    	else
    		return super.toString();
    }
    /*
	public static MethodProxy virtualMethod (Constructor m) {
		if (m == null) return null;
		MethodProxy vm = MethodEmbellishment.constructorsToVirtualMethods.get(m);
		if (vm == null) {
			vm = new MethodEmbellishment(m);
			MethodEmbellishment.constructorsToVirtualMethods.put(m, vm);			
		}
		return vm;
			
	}
	*/
    /*
	public static MethodProxy virtualMethod (MethodProxy m) {
		return m;
	}
	*/
	/*
	public static MethodProxy virtualMethod (Method m) {
		if (m == null) return null;
		
		MethodProxy vm = MethodEmbellishment.methodsToVirtualMethods.get(m);
		if (vm == null) {
			vm = new MethodEmbellishment(m);
			MethodEmbellishment.methodsToVirtualMethods.put(m, vm);			
		}
		return vm;
			
	}
	*/

	
	

}
