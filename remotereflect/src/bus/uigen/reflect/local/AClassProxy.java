package bus.uigen.reflect.local;

import java.awt.Color;
import java.awt.Font;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.JTree;

import bus.uigen.reflect.ClassProxy;
import bus.uigen.reflect.FieldProxy;
import bus.uigen.reflect.MethodProxy;
import bus.uigen.reflect.RemoteSelector;
import bus.uigen.reflect.remote.StandardTypeConverter;

public class AClassProxy implements ClassProxy {
	Class javaClass;
	public AClassProxy(Class theClass) {
		javaClass = theClass;
	}
	Annotation[] annotations;
	@Override
	public Annotation[] getAnnotations() {
		// TODO Auto-generated method stub
		if (annotations == null)
			annotations = javaClass.getAnnotations();
		return annotations;
	}
	
	@Override
	public String getCanonicalName() {
		// TODO Auto-generated method stub
		return javaClass.getCanonicalName();
	}
	MethodProxy[] constructors;
	@Override
	public MethodProxy[] getConstructors() {
		// TODO Auto-generated method stub
		if (constructors == null) {
			Constructor[] javaConstructors = javaClass.getConstructors();
			constructors = new MethodProxy[javaConstructors.length];
			for (int i = 0; i < javaConstructors.length; i++) {
				constructors[i] = AVirtualMethod.virtualMethod(javaConstructors[i]);
			}
		}
		return constructors;
	}
	ClassProxy[] declaredClasses;
	@Override
	public ClassProxy[] getDeclaredClasses() {
		// TODO Auto-generated method stub
		if (declaredClasses == null) {
			Class[] javaClasses = javaClass.getDeclaredClasses();
			declaredClasses = new ClassProxy[javaClasses.length];
			for (int i = 0; i < javaClasses.length; i++) {
				declaredClasses[i] = classProxy(javaClasses[i]);
			}
		}
		return declaredClasses;
		
	}

	
	ClassProxy declaringClass;
	
	public ClassProxy getDeclaringClass() {
		// TODO Auto-generated method stub
		if (declaringClass == null)
			declaringClass = classProxy(javaClass.getDeclaringClass());
		return declaringClass;
	}

	@Override
	public Object[] getEnumConstants() {
		// TODO Auto-generated method stub
		return javaClass.getEnumConstants();
	}
	
	FieldProxy[] fields;
	
	@Override	
	public FieldProxy[] getFields() {
		// TODO Auto-generated method stub
		if (fields == null) {
			fields = toFieldProxy(javaClass.getFields());
			/*
			Field[] javaFields = javaClass.getFields();			
			fields = new FieldProxy[javaFields.length];
			for (int i = 0; i < javaFields.length; i++) {
				fields[i] = AFieldProxy.fieldProxy(javaFields[i]);
			}
			*/
		}
		return fields;
		//return null;
	}
	ClassProxy[] interfaces;
	@Override
	public ClassProxy[] getInterfaces() {
		// TODO Auto-generated method stub
		if (interfaces == null) {
			Class[] javaClasses = javaClass.getInterfaces();
			interfaces = new ClassProxy[javaClasses.length];
			for (int i = 0; i < javaClasses.length; i++) {
				interfaces[i] = classProxy(javaClasses[i]);
			}
		}
		return interfaces;
		//return null;
	}
	Class[] toClass(ClassProxy[] list) {
		if (list ==  null) return null;
		Class[] retVal = new Class[list.length];
		for (int i = 0; i < retVal.length; i++)
			retVal[i] = ((AClassProxy) list[i]).getJavaClass();
		return retVal;
	}
	@Override
	public MethodProxy getMethod(String name, ClassProxy... parameterTypes)
			throws NoSuchMethodException, SecurityException {
		return AVirtualMethod.virtualMethod(javaClass.getMethod(name, toClass(parameterTypes)));
	}
	MethodProxy[] methods;
	@Override
	public MethodProxy[] getMethods() {
		// TODO Auto-generated method stub
		if (methods == null) {
			Method[] javaMethods = javaClass.getMethods();
			methods = new MethodProxy[javaMethods.length];
			for (int i = 0; i < javaMethods.length; i++) {
				methods[i] = AVirtualMethod.virtualMethod(javaMethods[i]);
			}
		}
		return methods;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return javaClass.getName();
	}

	@Override
	public String getSimpleName() {
		// TODO Auto-generated method stub
		return javaClass.getSimpleName();
	}

	@Override
	public ClassProxy getSuperclass() {
		// TODO Auto-generated method stub
		return classProxy(javaClass.getSuperclass());
	}

	@Override
	public boolean isArray() {
		// TODO Auto-generated method stub
		return javaClass.isArray();
	}

	@Override
	public boolean isAssignableFrom(ClassProxy cls) {
		// TODO Auto-generated method stub
		if (!(cls instanceof AClassProxy))
			return false;
		AClassProxy otherClass = (AClassProxy) cls;
		return javaClass.isAssignableFrom(otherClass.javaClass);
	}

	@Override
	public boolean isEnum() {
		// TODO Auto-generated method stub
		return javaClass.isEnum();
	}

	@Override
	public boolean isInterface() {
		// TODO Auto-generated method stub
		return javaClass.isInterface();
	}

	@Override
	public Object newInstance() throws InstantiationException, IllegalAccessException {
		// TODO Auto-generated method stub
		return javaClass.newInstance();
	}
	static transient Hashtable<Class, ClassProxy> classesToClassProxies = new Hashtable();
	public static ClassProxy classProxy (Class c) {
		if (c == AClassProxy.class)
			System.out.println("ClassProxy from ClassProxy");
		/*
		else if (c == Class.class)
			Message.warning("Class to Class Proxy");
			*/
		if (c == null) return null;
		ClassProxy cp =classesToClassProxies.get(c);
		if (cp == null) {
			cp = new AClassProxy(c);
			classesToClassProxies.put(c, cp);			
		}
		return cp;
			
	}
	public static ClassProxy[] classProxy(Class[] clses) {
		ClassProxy[] retVal = new ClassProxy[clses.length];
		for (int i = 0; i < clses.length; i++)
			retVal[i] = classProxy(clses[i]);
		return retVal;
		
	}
	public static ClassProxy staticForName(String name) throws ClassNotFoundException {
		Class c = Class.forName(name);
		//if (c == null) return null;
		return classProxy(c);
	}
	public  ClassProxy forName(String name) throws ClassNotFoundException {
		return staticForName(name);
	}
	public boolean equals (Object otherClass) {
		if (this == otherClass) return true;
		if (!(otherClass instanceof AClassProxy))
			return false;
		AClassProxy otherClassProxy = (AClassProxy) otherClass;
		return this.javaClass == otherClassProxy.javaClass;
	}
	public Class getJavaClass() {
		return javaClass;
	}

	//public Annotation getAnnotation(ClassProxy classProxy) {
	@Override
	//public <T extends Annotation> T getAnnotation(Class<T> annotationType) { 
	public  Annotation  getAnnotation(Class annotationType) { 
		// TODO Auto-generated method stub
		//if (!(classProxy instanceof AClassProxy))
				//return null;
		//return javaClass.getAnnotation(((AClassProxy) classProxy).getJavaClass());
		return javaClass.getAnnotation(annotationType);
	}

	@Override
	public boolean isPrimitive() {
		// TODO Auto-generated method stub
		return javaClass.isPrimitive();
	}

	@Override
	public ClassProxy getComponentType() {
		// TODO Auto-generated method stub
		return classProxy(javaClass.getComponentType());
	}
	FieldProxy[] declaredFields;
	@Override
	public FieldProxy[] getDeclaredFields() {
		if (declaredFields == null)
			declaredFields = toFieldProxy(javaClass.getDeclaredFields());
		return declaredFields;
	}
	public FieldProxy[] toFieldProxy(Field[] javaFields) {		
		FieldProxy[] theFields = new FieldProxy[javaFields.length];
		for (int i = 0; i < javaFields.length; i++) {
			theFields[i] = AFieldProxy.fieldProxy(javaFields[i]);
		}
		return theFields;		
	}

	@Override
	public int getModifiers() {
		// TODO Auto-generated method stub
		return javaClass.getModifiers();
	}

	@Override
	public FieldProxy getField(String string) throws SecurityException, NoSuchFieldException {
		// TODO Auto-generated method stub
		return AFieldProxy.fieldProxy(javaClass.getField(string));
	}
	public boolean isInstance(Object obj) {
		return javaClass.isInstance(obj);
	}
	
	
	
	public String toString() {
		return javaClass.toString();
	}
	//adding types
	@Override
	public  ClassProxy objectClass () {
		return RemoteSelector.classProxy(Object.class);
	}
	@Override
	public  ClassProxy integerType() {
		return RemoteSelector.classProxy(Integer.TYPE);
	}

	@Override
	public  ClassProxy shortType() {
		return RemoteSelector.classProxy(Short.TYPE);
	}
	@Override
	public  ClassProxy longType() {
		return RemoteSelector.classProxy(Long.TYPE);
	}
	@Override
	public  ClassProxy characterType() {
		return RemoteSelector.classProxy(Character.TYPE);
	}
	@Override
	public  ClassProxy booleanType() {
		return RemoteSelector.classProxy(Boolean.TYPE);
	}
	@Override
	public  ClassProxy doubleType() {
		return RemoteSelector.classProxy(Double.TYPE);
	}
	@Override
	public  ClassProxy floatType() {
		return RemoteSelector.classProxy(Float.TYPE);
	}
	@Override
	public  ClassProxy byteType() {
		return RemoteSelector.classProxy(Byte.TYPE);
	}
	@Override
	public  ClassProxy integerClass() {
		return RemoteSelector.classProxy(Integer.class);
	}
	@Override
	public  ClassProxy shortClass() {
		return RemoteSelector.classProxy(Short.class);
	}
	@Override
	public  ClassProxy longClass() {
		return RemoteSelector.classProxy(Long.class);
	}
	@Override
	public  ClassProxy characterClass() {
		return RemoteSelector.classProxy(Character.class);
	}
	@Override
	public  ClassProxy booleanClass() {
		return RemoteSelector.classProxy(Boolean.class);
	}
	@Override
	public  ClassProxy doubleClass() {
		return RemoteSelector.classProxy(Double.class);
	}
	@Override
	public  ClassProxy floatClass() {
		return RemoteSelector.classProxy(Float.class);
	}
	@Override
	public  ClassProxy byteClass() {
		return RemoteSelector.classProxy(Byte.class);
	}
	@Override
	public  ClassProxy stringClass() {
		return RemoteSelector.classProxy(String.class);
	}
	@Override
	public  ClassProxy enumerationClass() {
		return RemoteSelector.classProxy(Enumeration.class);
	}
	@Override
	public  ClassProxy voidType() {
		return RemoteSelector.classProxy(Void.TYPE);
	}
	public  ClassProxy mapClass() {
		return RemoteSelector.classProxy(java.util.Map.class);
	}
	public  ClassProxy collectionClass() {
		return RemoteSelector.classProxy(java.util.Collection.class);
	}
	public  ClassProxy listClass() {
		return RemoteSelector.classProxy(java.util.List.class);
	}
	public  ClassProxy setClass() {
		return RemoteSelector.classProxy(java.util.Set.class);
	}
	
	public  ClassProxy tableClass() {
		return RemoteSelector.classProxy(JTable.class);
	}
	public  ClassProxy treeClass() {
		return RemoteSelector.classProxy(JTree.class);
	}
	public  ClassProxy colorClass() {
		return RemoteSelector.classProxy(Color.class);
	}
	@Override
	public  ClassProxy strokeClass() {
		return RemoteSelector.classProxy(Stroke.class);
	}
	@Override
	public  ClassProxy paintClass() {
		return RemoteSelector.classProxy(Paint.class);
	}
	@Override
	public  ClassProxy fontClass() {
		return RemoteSelector.classProxy(Font.class);
	}
	@Override
	public  ClassProxy vectorClass() {
		return RemoteSelector.classProxy(Vector.class);
	}
	@Override
	public  ClassProxy awtShapeClass() {
		return RemoteSelector.classProxy(Shape.class);
	}
	@Override
	public  ClassProxy proxyClass() {
		return RemoteSelector.classProxy(Proxy.class);
	}
	public boolean isImmutable() {
		return StandardTypeConverter.isImmutable(javaClass);
	}

//	@Override
//	public <T extends Annotation> T getAnnotation(Class<T> annotationType) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public String getPackageName() {
		return javaClass.getPackage().getName();
	}

}
