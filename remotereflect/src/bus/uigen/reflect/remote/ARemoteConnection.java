package bus.uigen.reflect.remote;

import bus.uigen.reflect.AbstractRemoteConnection;
import bus.uigen.reflect.RemoteSelector;



public class ARemoteConnection extends AbstractRemoteConnection {

	@Override
	public void connect(Object theFactoryName) {
		RemoteSelector.register(theFactoryName, new ARemoteFactory(theFactoryName));
		
	}

}
