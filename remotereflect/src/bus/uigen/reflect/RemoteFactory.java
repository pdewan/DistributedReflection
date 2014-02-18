package bus.uigen.reflect;

import bus.uigen.reflect.remote.RemoteObjectProxy;

public interface RemoteFactory {	
	ClassProxy getClass(Object o);
	ClassProxy forName(String s) throws ClassNotFoundException;
	RemoteObjectProxy objectProxy(Object theFactory, int theObjectID);
	//ClassProxy classProxy(Class c);

}
