package bus.uigen.reflect;

import java.lang.annotation.Annotation;

import javax.swing.JTable;
import javax.swing.JTree;


public interface ElementProxy {
	 Annotation[] getAnnotations();
	 //Annotation getAnnotation(ClassProxy annotationClass);
	 public <T extends Annotation> T getAnnotation(Class<T> annotationType); 
	
	 

}
