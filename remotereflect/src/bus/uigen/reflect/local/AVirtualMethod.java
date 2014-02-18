package bus.uigen.reflect.local;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Hashtable;

//import bus.uigen.uiBean;
import bus.uigen.reflect.ClassProxy;
import bus.uigen.reflect.UnifiedMethod;
import bus.uigen.reflect.MethodProxy;

public class AVirtualMethod extends UnifiedMethod implements MethodProxy {
	/*
	String name;
	ClassProxy[] parameterTypes; 
	ClassProxy returnType;
	ClassProxy declaringClass;
	Method method;
	Constructor constructor;
	MethodProxy invokeMethodWithParameters;
	MethodProxy invokeParameterLessMethod;
	boolean isRealMethod = false;
	Object originalObject;
	
	boolean isRealMethod = false;
	
	Object originalObject;
	*/
	Method method;
	Constructor constructor;
	
	
	static final Class[] emptyArgs = {};
	/*
	static  Method dummy = null;
	static MethodProxy virtualDummy = null;
	
	public static Method dummy() {
		if (dummy == null)			
			try {
				dummy = String.class.getMethod("toString", emptyArgs);
				virtualDummy = AVirtualMethod.virtualMethod(dummy);
				
			} catch (Exception e) {
				
			}
		return dummy;
	}
	
	public static MethodProxy virtualDummy() {
		dummy();
		return virtualDummy;
	}
	*/
	
	
	public AVirtualMethod (String theName, ClassProxy[] theParameterTypes, 
			ClassProxy theReturnType, ClassProxy theDeclaringClass) {
		super (theName, theParameterTypes, theReturnType, theDeclaringClass);
		/*
		name = theName;
		parameterTypes = theParameterTypes;
		returnType = theReturnType;
		declaringClass = theDeclaringClass;
		invokeMethodWithParameters = uiBean.getInvokeDynamicCommand(declaringClass);		 
		invokeParameterLessMethod = uiBean.getParameterlessInvokeDynamicCommand(declaringClass);
		try {
			dummy = String.class.getMethod("toString", emptyArgs);
			virtualDummy = AVirtualMethod.virtualMethod(dummy);
			
		} catch (Exception e) {
			
		}
		*/
		
		
	}
	public  MethodProxy  cloneMethod() {
		return new AVirtualMethod(method);
	}
	
	public  MethodProxy  cloneMethod(String name, 
			ClassProxy[] parameters, 
			ClassProxy returnType,
			ClassProxy theClass) {
		ClassProxy cl = theClass;
		if (getSourceObject() != null)
			cl = AClassProxy.classProxy(getSourceObject().getClass());
		//return  new AVirtualMethod(getName(), getParameterTypes(), getReturnType(), AClassProxy.classProxy(getSourceObject().getClass()));
		AVirtualMethod retVal = new AVirtualMethod(getName(), getParameterTypes(), getReturnType(), cl);
		retVal.setSourceObject(getSourceObject());
		retVal.setCloned(true);
		//return  new AVirtualMethod(getName(), getParameterTypes(), getReturnType(), cl);
		return retVal;
	}
	/*
	public MethodProxy moveFromObject(Object sourceObject) {	
		MethodProxy newVM;
		if (isMethod())
			newVM = new AVirtualMethod(method);
		else
			newVM =  new AVirtualMethod(getName(), getParameterTypes(), getReturnType(), AClassProxy.classProxy(sourceObject.getClass()));
		newVM.setSourceObject(sourceObject);
		return newVM;
	}
	public void setSourceObject (Object o) {
		originalObject = o;
	}
	public Object getSourceObject () {
		return originalObject;
	}
	*/
	public AVirtualMethod (Method theMethod) {
		super();
		setRealMethod(true);		
		//isRealMethod = true;
		method = theMethod;
		
		/*
		try {
			dummy = String.class.getMethod("toString", emptyArgs);
			
		} catch (Exception e) {
			
		}
		*/
		
	}
	/*
	public boolean isDynamicCommand() {
		  return !isMethod() && !isConstructor();
	  }
	  */
	public AVirtualMethod (Constructor theConstructor) {
		super();
		setRealMethod(true);
		//isRealMethod = true;
		constructor = theConstructor;
		/*
		try {
			dummy = String.class.getMethod("toString", emptyArgs);
			
		} catch (Exception e) {
			
		}
		*/
	}
	
	
	/* (non-Javadoc)
	 * @see bus.uigen.controller.VirtualMethod#isMethod()
	 */
	public boolean isMethod() {
		return method != null;
	}
	public boolean isConstructor() {
		return constructor != null;
	}
	
	/* (non-Javadoc)
	 * @see bus.uigen.controller.VirtualMethod#getMethod()
	 */
	public Method getMethod() {
		return method;
	}
	public Constructor getConstructor() {
		return constructor;
	}
	
	/* (non-Javadoc)
	 * @see bus.uigen.controller.VirtualMethod#getModifiers()
	 */
	public int getMethodModifiers() {
		//if (method != null)
			return method.getModifiers();
		//return 0;
	}
	public int getConstructorModifiers() {
		//if (method != null)
			return constructor.getModifiers();
		//return 0;
	}
	
	/* (non-Javadoc)
	 * @see bus.uigen.controller.VirtualMethod#getName()
	 */
	public  String  getMethodName() {
		return method.getName();
	}
	public  String getConstructorName() {
		return constructor.getName();
	}
	/*
	public String getName() {
		if (method == null && constructor == null)
			return name;
		else if (method != null)
			return method.getName();
		else 
			return constructor.getName();
	}
	*/
	public ClassProxy[] getMethodParameterTypes() {
		return AClassProxy.classProxy(method.getParameterTypes());
	}
	public ClassProxy[] getConstructorParameterTypes() {
		return AClassProxy.classProxy(constructor.getParameterTypes());
	}
	
	/* (non-Javadoc)
	 * @see bus.uigen.controller.VirtualMethod#getParameterTypes()
	 */
	/*
	public ClassProxy[] getParameterTypes() {
		if (method != null)
			return AClassProxy.classProxy(method.getParameterTypes());
		else if (constructor != null)
			return AClassProxy.classProxy(constructor.getParameterTypes());
		else
			return parameterTypes;
		
	}
	*/
	/* (non-Javadoc)
	 * @see bus.uigen.controller.VirtualMethod#getReturnType()
	 */
	public ClassProxy getMethodReturnType() {
		return  AClassProxy.classProxy(method.getReturnType());
	}
	public ClassProxy getConstructorReturnType() {
		return AClassProxy.classProxy(constructor.getDeclaringClass());
	}
	/*
	public ClassProxy getReturnType() {
		if (method != null)
			return  AClassProxy.classProxy(method.getReturnType());
		else if (constructor != null)
			return AClassProxy.classProxy(constructor.getDeclaringClass());
		else
			return returnType;
		
	}
	*/
	/* (non-Javadoc)
	 * @see bus.uigen.controller.VirtualMethod#getDeclaringClass()
	 */
	public ClassProxy getMethodDeclaringClass() {
		return AClassProxy.classProxy(method.getDeclaringClass());
	}
	public ClassProxy getConstructorDeclaringClass() {
		return AClassProxy.classProxy(constructor.getDeclaringClass());
	}
	/*
	public ClassProxy getDeclaringClass() {
		if (method != null)
			return AClassProxy.classProxy(method.getDeclaringClass());
		else if (constructor != null)
			return AClassProxy.classProxy(constructor.getDeclaringClass());
		else
			return declaringClass;
		
	}
	*/
	/* (non-Javadoc)
	 * @see bus.uigen.controller.VirtualMethod#invoke(java.lang.Object, java.lang.Object[])
	 */
	public Object methodInvoke (Object targetObject, Object... parameterTypes) throws InvocationTargetException, IllegalAccessException, InstantiationException  {
		return method.invoke(targetObject, parameterTypes);
	}
	public Object constructorNewInstance (Object... parameterTypes) throws InvocationTargetException, IllegalAccessException, InstantiationException  {
		return constructor.newInstance(parameterTypes);
	}
	/*
	public Object invoke (Object targetObject, Object[] parameterTypes) throws InvocationTargetException, IllegalAccessException, InstantiationException  {
		///try {
			if (originalObject != null)
				targetObject = originalObject;
			if (method != null)
				return method.invoke(targetObject, parameterTypes);
			if (constructor != null)
				return constructor.newInstance(parameterTypes);
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
			
		
	}
	
	@Override
	public void invoke(Object targetObject, Object newVal) {
		Object[] params = {newVal};
		invoke(targetObject, newVal);
		// TODO Auto-generated method stub
		
	}
	*/
	
	// code to unify Method and VirtualMethod  - if only Method implemented an interface!
	// not needed if method is wrappped in a virtual method
	public static ClassProxy getDeclaringClass(Object methodOrVirtualMethod) {
		if (methodOrVirtualMethod instanceof Method)
			return AClassProxy.classProxy(((Method)	methodOrVirtualMethod).getDeclaringClass());
		else if (methodOrVirtualMethod instanceof Constructor)
			return AClassProxy.classProxy(((Constructor)	methodOrVirtualMethod).getDeclaringClass());
		else if (methodOrVirtualMethod instanceof AVirtualMethod)
			return ((MethodProxy)	methodOrVirtualMethod).getDeclaringClass();
		else return null;
	}
	public static String getName(Object methodOrVirtualMethod) {
		if (methodOrVirtualMethod instanceof Method)
			return ((Method)	methodOrVirtualMethod).getName();
		else if (methodOrVirtualMethod instanceof Constructor)
			return ((Constructor)	methodOrVirtualMethod).getName();
		else if (methodOrVirtualMethod instanceof AVirtualMethod)
			return ((MethodProxy)	methodOrVirtualMethod).getName();
		else return null;
	}
	/* (non-Javadoc)
	 * @see bus.uigen.controller.VirtualMethod#getParameterTypes(java.lang.Object)
	 */
	public ClassProxy[] getParameterTypes(Object methodOrVirtualMethod) {
		if (methodOrVirtualMethod instanceof Method)
			return AClassProxy.classProxy(((Method)	methodOrVirtualMethod).getParameterTypes());
		else if (methodOrVirtualMethod instanceof Constructor)
			return AClassProxy.classProxy(((Constructor)	methodOrVirtualMethod).getParameterTypes());
		else if (methodOrVirtualMethod instanceof AVirtualMethod)
			return ((MethodProxy)	methodOrVirtualMethod).getParameterTypes();
		else return null;
	}
	public static ClassProxy getReturnType(Object methodOrVirtualMethod) {
		if (methodOrVirtualMethod instanceof Method)
			return AClassProxy.classProxy(((Method)	methodOrVirtualMethod).getReturnType());
		else  if (methodOrVirtualMethod instanceof Constructor)
			return AClassProxy.classProxy(((Constructor)	methodOrVirtualMethod).getDeclaringClass());
		else if (methodOrVirtualMethod instanceof AVirtualMethod)
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
		else if (methodOrVirtualMethod instanceof AVirtualMethod)
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
	
	/*
	public String toString() {
		return super.toString() + " " + method;
	}
	*/
	/*
	public String toString() {
		if (method != null)
			return method.toString();
		else if (constructor != null)
			return constructor.toString();
		else return super.toString();
	}
	*/
	//ClassProxy virtualClass;
	static transient Hashtable<Constructor, MethodProxy> constructorsToVirtualMethods = new Hashtable();
	static transient Hashtable<Method, MethodProxy> methodsToVirtualMethods = new Hashtable();
	/*
	public void setDynamiclClass (ClassProxy newVal) {
   	 virtualClass = newVal;
    }
    
    public ClassProxy getDynamicClass() {
   	 return virtualClass;
    }
    */
    //public Annotation getAnnotation(ClassProxy theAnnotationType) {
    //public <T extends Annotation> T getAnnotation(Class<T> annotationType) {
	public  Annotation getMethodAnnotation(Class annotationType) {
		return method.getAnnotation(annotationType);
	}
	public  Annotation getConstructorAnnotation(Class annotationType) {
		return constructor.getAnnotation(annotationType);
	}
	/*
    public Annotation getAnnotation(Class annotationType) {
    //if (!(theAnnotationType instanceof AClassProxy))
    		//return null;
    //Class annotationType = ((AClassProxy)theAnnotationType).getJavaClass();
    	//public Annotation getAnnotation(Class annotationType) {
    	if (method != null)
			return method.getAnnotation(annotationType);
		else if (constructor != null)
			return constructor.getAnnotation(annotationType);
		else
			return null;
    }
    */
	public Annotation[] getMethodAnnotations() {
		return method.getAnnotations();
	}
	public Annotation[] getConstructorAnnotations() {
		return constructor.getAnnotations();
	}
	/*
    public Annotation[] getAnnotations() {
    	if (method != null)
		return method.getAnnotations();
	else if (constructor != null)
		return constructor.getAnnotations();
	else
		return null;
    }
    */
	public Annotation[] getDeclaredMethodAnnotations()  {
		return method.getDeclaredAnnotations();
	}
	public Annotation[] getDeclaredConstructorAnnotations()  {
		return constructor.getDeclaredAnnotations();
	}
	/*
    public Annotation[] getDeclaredAnnotations()  {
    	if (method != null)
    		return method.getDeclaredAnnotations();
    	else if (constructor != null)
    		return constructor.getDeclaredAnnotations();
    	else
    		return null;
        
    }
    */
	public boolean isMethodAnnotationPresent(Class<? extends Annotation> annotationType) {
		return method.isAnnotationPresent(annotationType);
	}
	public boolean isConstructorAnnotationPresent(Class<? extends Annotation> annotationType) {
		return constructor.isAnnotationPresent(annotationType);
	}
	/*
    public boolean isAnnotationPresent(Class<? extends Annotation> annotationType) {
    	if (method != null)
    		return method.isAnnotationPresent(annotationType);
    	else if (constructor != null)
    		return constructor.isAnnotationPresent(annotationType);
    	else
    		return false;
    }
    */
    /*
    public boolean equals(Object otherMethod) {
    	if (this == otherMethod)
    		return true;
    	if (!(otherMethod instanceof AVirtualMethod))
    		return false;
    	AVirtualMethod otherInstance = (AVirtualMethod) otherMethod;
    		
    	if (isMethod() && otherInstance.isMethod())
    		return method == otherInstance.method;
    	if (isConstructor() && otherInstance.isConstructor())
    		return constructor == otherInstance.constructor;
    	return false;
    }
    public Object newInstance(Object[] parameterValues) throws IllegalAccessException, InstantiationException, InvocationTargetException {
    	if (!isConstructor()) return null;    	
    	return constructor.newInstance(parameterValues);
    
    	
    }
    */
	public  String methodToString() {
		return method.toString();
	}
	public String constructorToString() {
		return constructor.toString();
	}
	/*
    public String toString() {
    	if (isMethod())
    		return method.toString();
    	else if (isConstructor())
    		return constructor.toString();
    	else
    		return super.toString();
    }
    */
	public static MethodProxy virtualMethod (Constructor m) {
		if (m == null) return null;
		MethodProxy vm = AVirtualMethod.constructorsToVirtualMethods.get(m);
		if (vm == null) {
			vm = new AVirtualMethod(m);
			AVirtualMethod.constructorsToVirtualMethods.put(m, vm);			
		}
		return vm;
			
	}
	public static MethodProxy virtualMethod (MethodProxy m) {
		return m;
	}
	public static MethodProxy virtualMethod (Method m) {
		if (m == null) return null;
		
		MethodProxy vm = AVirtualMethod.methodsToVirtualMethods.get(m);
		if (vm == null) {
			vm = new AVirtualMethod(m);
			AVirtualMethod.methodsToVirtualMethods.put(m, vm);			
		}
		return vm;
			
	}
	 public  boolean equalsMethod(UnifiedMethod theMethod) {
		 
	    	return method.equals(((AVirtualMethod)theMethod).method);
	    }
	    public  boolean equalsConstructor(UnifiedMethod theConstructor) {
	    	return constructor.equals(((AVirtualMethod)theConstructor).constructor);
	    	
	    }

	
	

}
