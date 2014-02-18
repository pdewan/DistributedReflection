package bus.uigen.reflect.remote;

import bus.uigen.reflect.ClassProxy;

public interface RemoteFactory {	
	ClassProxy getClass(Object o);
	ClassProxy forName(String s) throws ClassNotFoundException;
	ObjectProxy objectProxy(Object theFactory, String theObjectID);
	//ClassProxy classProxy(Class c);

}
