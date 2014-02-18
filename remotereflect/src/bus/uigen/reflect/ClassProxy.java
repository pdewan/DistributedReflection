package bus.uigen.reflect;

import java.lang.annotation.Annotation;

import javax.swing.JTable;
import javax.swing.JTree;


public interface ClassProxy extends ElementProxy {
	/*
	 Annotation[] getAnnotations();
	 //Annotation getAnnotation(ClassProxy annotationClass);
	 public <T extends Annotation> T getAnnotation(Class<T> annotationType); 
	 */
	 boolean isPrimitive();
	 String getCanonicalName();	
	 MethodProxy[] getConstructors();
	 ClassProxy[] getDeclaredClasses();
	 ClassProxy getDeclaringClass();
	 Object[] getEnumConstants() ;
	 FieldProxy[] getFields() ;
	 FieldProxy[] getDeclaredFields() ;
	 ClassProxy[] getInterfaces();
	 
	 public MethodProxy getMethod(String name, ClassProxy... parameterTypes) throws NoSuchMethodException,
             SecurityException;
             
	 MethodProxy[] getMethods();
	 String getName();
	 String getSimpleName();
	 ClassProxy getSuperclass();
	 boolean isArray();
	 boolean isAssignableFrom(ClassProxy cls);
	 boolean isEnum();
	 boolean isInterface();
	 Object newInstance() throws InstantiationException, IllegalAccessException;
	 ClassProxy getComponentType();
	int getModifiers();	
	FieldProxy getField(String string) throws SecurityException, NoSuchFieldException;
	boolean isInstance(Object obj);
	ClassProxy forName(String s) throws ClassNotFoundException;
	ClassProxy voidType();
	ClassProxy enumerationClass();
	ClassProxy stringClass();
	ClassProxy byteClass();
	ClassProxy floatClass();
	ClassProxy doubleClass();
	ClassProxy booleanClass();
	public  ClassProxy objectClass () ;
	public  ClassProxy integerType() ;
	public  ClassProxy shortType() ;
	public  ClassProxy longType() ;
	public  ClassProxy characterType();
	public  ClassProxy booleanType() ;
	public  ClassProxy doubleType() ;
	public  ClassProxy floatType() ;
	public  ClassProxy byteType() ;
	public  ClassProxy integerClass();
	public  ClassProxy shortClass() ;
	public  ClassProxy longClass();
	public  ClassProxy characterClass() ;
	public  ClassProxy mapClass() ;
	public  ClassProxy collectionClass() ;
	public  ClassProxy listClass();
	public  ClassProxy setClass() ;
	
	public  ClassProxy tableClass();
	public  ClassProxy treeClass() ;
	public 	ClassProxy colorClass();
	public 	ClassProxy proxyClass();
	ClassProxy strokeClass();
	ClassProxy paintClass();
	ClassProxy fontClass();
	ClassProxy vectorClass();
	ClassProxy awtShapeClass();
	public boolean isImmutable();
	String getPackageName();
	 

}
