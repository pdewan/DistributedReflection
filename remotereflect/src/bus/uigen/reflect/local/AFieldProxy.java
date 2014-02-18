package bus.uigen.reflect.local;

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

public class AFieldProxy implements FieldProxy {
	Field field;
	public AFieldProxy (Field f) {
		field = f;
	}
	static transient Hashtable<Field, FieldProxy> fieldToFieldProxy = new Hashtable();
	 
	 public static FieldProxy fieldProxy (Field f) {
			if (f == null) return null;
			FieldProxy fp = fieldToFieldProxy.get(f);
			if (fp == null) {
				fp = new AFieldProxy(f);
				fieldToFieldProxy.put(f, fp);			
			}
			return fp;
				
		}
	 
	 public String getName() {
		 return field.getName();
	 }

	@Override
	public Object get(Object obj) throws IllegalArgumentException, IllegalAccessException {
		// TODO Auto-generated method stub
		return field.get(obj);
	}

	@Override
	public void set(Object obj, Object value) throws IllegalArgumentException, IllegalAccessException {
		// TODO Auto-generated method stub
		field.set(obj, value);
	}

	@Override
	public int getModifiers() {
		// TODO Auto-generated method stub
		return field.getModifiers();
	}

	@Override
	public ClassProxy getType() {
		// TODO Auto-generated method stub
		return AClassProxy.classProxy(field.getType());
	}

	@Override
	public ClassProxy getDeclaringClass() {
		// TODO Auto-generated method stub
		return RemoteSelector.classProxy(field.getDeclaringClass());
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
		return field.getAnnotation(annotationType);
	}
	
		
}
