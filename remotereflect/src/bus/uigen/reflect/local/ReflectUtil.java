package bus.uigen.reflect.local;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.rmi.Remote;
import java.util.ArrayList;
import java.util.List;

import util.misc.RemoteReflectionUtility;
import util.remote.InvocationHandlerWithProperties;
import util.trace.Tracer;
import bus.uigen.reflect.ClassProxy;
import bus.uigen.reflect.RemoteSelector;


public class ReflectUtil {

	public static ClassProxy toMaybeProxyTargetClass (Object anObject) {
			return toMaybeProxyTargetClass(RemoteSelector.getClass(anObject), anObject);
	//		return toMaybeProxyTargetClass(ACompositeLoggable.getTargetClass(anObject), anObject);
	
		}

	public static ClassProxy toMaybeProxyTargetClass (ClassProxy aClass, Object anObject) {
		ClassProxy retVal = aClass;
		if (isProxyClass(aClass)) {
			ClassProxy targetClass = getProxyTargetClass(anObject);
			if (targetClass != null)
				retVal = targetClass;
		}
		return retVal;
	}

	public static boolean isProxyClass(ClassProxy aClassProxy) {
		return proxyClass().isAssignableFrom(aClassProxy);
	}

	public static ClassProxy proxyClass() {
		return RemoteSelector.classProxy(Proxy.class);
	}

	public static ClassProxy getProxyTargetClass(Object object) {
		InvocationHandler invocationHandler = getInvocationHandler(object);
		if (invocationHandler == null)
			return null;
		if (invocationHandler instanceof InvocationHandlerWithProperties) {
			Class targetClass = ((InvocationHandlerWithProperties) invocationHandler)
					.getProxyTargetClass();
			if (!targetClass.isInterface()) {
//				Class[] interfaces = targetClass.getInterfaces();
				Class[] interfaces = RemoteReflectionUtility.getProxyInterfaces(targetClass);

				List<Class> interfacesSet = new ArrayList();
				for (Class aClass: interfaces) {
					if (aClass == Remote.class || aClass == Serializable.class || aClass == Cloneable.class)
						continue;
					interfacesSet.add(aClass);
				}
				
				if (interfacesSet.size() == 0) {
					Tracer.error("Proxy "
							+ targetClass
							+ " has no non empty interfaces. Cannot display a remote instarnce of it");
					return null;
				} else {
					if (interfacesSet.size() > 1) {
						Tracer.warning("Proxy "
								+ targetClass
								+ " has more than one interface. Using for remote UI generation the interface:"
								+ targetClass);
					}
					targetClass = interfacesSet.get(0);

				}
				

			}
			return RemoteSelector
					.classProxy(targetClass);
		}
		return null; // maybe should return Object.class
	}

	public static InvocationHandler getInvocationHandler(Object object) {
	
		if (!(object instanceof Proxy)) return null;
		return Proxy.getInvocationHandler(object);
	}

}
