package bus.uigen.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.annotation.*;

public interface MethodProxy extends ElementProxy {

	public boolean isMethod();
	public boolean isConstructor();
	public Object newInstance(Object[] parameterValues) throws IllegalAccessException, InstantiationException, InvocationTargetException;
	
	public boolean isDynamicCommand();

	//public Method getMethod();
	//public Constructor getConstructor();

	public int getModifiers();

	public String getName();

	public ClassProxy[] getParameterTypes();

	public ClassProxy getReturnType();

	public ClassProxy getDeclaringClass();

	public Object invoke(Object targetObject, Object... parameterTypes)
			throws InvocationTargetException, IllegalAccessException, InstantiationException;

	//public ClassProxy[] getParameterTypes(Object methodOrVirtualMethod);
	//public ClassProxy[] getParameterTypes();
	public MethodProxy moveFromObject(Object o);
	public void setSourceObject (Object o);
	public Object getSourceObject ();
	public void setDynamiclClass (ClassProxy newVal);
    
    public ClassProxy getDynamicClass() ;
    /*
    public <T extends Annotation> T getAnnotation(Class<T> annotationType); 
    //public Annotation getAnnotation(ClassProxy annotationType); 
    //public Annotation getAnnotation(ClassProxy annotationType);   
    public Annotation[] getAnnotations();   
    */  
    public Annotation[] getDeclaredAnnotations() ;
    public boolean isAnnotationPresent(Class<? extends Annotation> annotationType); 
    //public boolean isAnnotationPresent(Class<? extends Annotation> annotationType);
	//public void invoke(Object targetObject, Object newVal);
   

	

}