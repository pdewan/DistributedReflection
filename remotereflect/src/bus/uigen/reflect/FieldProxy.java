package bus.uigen.reflect;

import java.lang.annotation.Annotation;

//import util.Explanation;

public interface FieldProxy  {
	int getModifiers();
	public String getName();
	Object get(Object obj) throws IllegalArgumentException, IllegalAccessException;
	void set(Object obj, Object value) throws IllegalArgumentException, IllegalAccessException ;
	ClassProxy getType();
	ClassProxy getDeclaringClass();
	//Annotation getAnnotation(ClassProxy classProxy);
    public <T extends Annotation> T getAnnotation(Class<T> annotationType);

}
